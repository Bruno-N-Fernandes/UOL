package br.com.uol.cotacoes.core.business.service;

import br.com.uol.cotacoes.core.model.entity.CurrencyRateInterday;
import br.com.uol.cotacoes.core.model.entity.view.ViewCurrencyInterday;
import br.com.uol.cotacoes.core.model.repository.CurrencyRateInterdayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviços disponibilizados para o interday, permite que sejam acessadas
 * informações das transações históricas de moedas.
 * 
 * @author vrx_mtoledo
 *
 */
@Service
public class CurrencyInterdayService {

	@Autowired
	private CurrencyRateInterdayRepository currencyRateInterdayRepository;

	/**
	 * Retorna a listagem de todos os registros dado um período de datas e o ID
	 * da moeda como parametro
	 *
	 * @param currencyId
	 *            Id da moeda para ser carregada
	 * @return todas as moedas registradas no período e ordenadas da moeda mais
	 *         recente para a mais antiga
	 */
	@Cacheable("CurrencyInterdayService-listCurrenciesByPeriod")
	public List<CurrencyRateInterday> listCurrenciesByPeriod(Integer currencyId, LocalDate start, LocalDate end) {

		List<CurrencyRateInterday> interdays = convertViewListToObjectList(
				currencyRateInterdayRepository.findByCurrencyIdAndDateBetweenOrderByDateDesc(currencyId, start, end));

		if(!interdays.isEmpty()){
			BigDecimal referenceValue = interdays.get(interdays.size() - 1).getAskValue();
	
			for (CurrencyRateInterday currencyRateInterday : interdays) {
				currencyRateInterday.updateRelativePecentual(referenceValue);
			}
		}

		return interdays;
	}

	/**
	 * Carrega os registros históricos mais atuais da moeda passada carregando a
	 * quantidade de registros definida.
	 *
	 * @param currencyId
	 * @param size
	 *            da página
	 * @return a primeira página das moedas registradas, ordenadas da moeda mais
	 *         recente para a mais antiga
	 *
	 */
	@Cacheable("CurrencyInterdayService-listMostRecentDayCurrenciesBy")
	public List<CurrencyRateInterday> listMostRecentDayCurrenciesBy(Integer currencyId, int size) {
		LocalDate today = LocalDate.now();

		return currencyRateInterdayRepository
				.findByCurrencyIdAndDateLessThanOrderByDateDesc(currencyId, today, new PageRequest(0, size))
				.getContent();
	}

	/**
	 * A próxima página das moedas registradas, ordenadas da moeda mais recente
	 * para a mais antiga
	 * 
	 * @param currencyId
	 * @param pointer
	 * @param size
	 *            da página
	 * @return a próxima página das moedas registradas, ordenadas da moeda mais
	 *         recente para a mais antiga
	 *
	 */
	@Cacheable("CurrencyInterdayService-listMostRecentCurrenciesFromPointer")
	public List<CurrencyRateInterday> listMostRecentCurrenciesFromPointer(Integer currencyId, int pointer, int size) {
		LocalDate today = LocalDate.now();

		return currencyRateInterdayRepository.findByCurrencyIdAndDateLessThanAndIdLessThanOrderByDateDesc(currencyId,
				today, pointer, new PageRequest(0, size)).getContent();
	}

	/**
	 * A página anterior das moedas registradas, ordenadas da moeda mais recente
	 * para a mais antiga *
	 *
	 * @param currencyId
	 * @param pointer
	 * @param pageSize
	 *            da página
	 * @return a página anterior das moedas registradas, ordenadas da moeda mais
	 *         recente para a mais antiga
	 */
	@Cacheable("CurrencyInterdayService-listMostOlderCurrenciesFromPointer")
	public List<CurrencyRateInterday> listMostOlderCurrenciesFromPointer(Integer currencyId, int pointer,
			int pageSize) {
		LocalDate today = LocalDate.now();

		List<CurrencyRateInterday> previousCurrencies = currencyRateInterdayRepository
				.findByCurrencyIdAndDateLessThanAndIdGreaterThanOrderByDate(currencyId, today, pointer,
						new PageRequest(0, pageSize))
				.getContent();
		previousCurrencies = new ArrayList<>(previousCurrencies);
		Collections.reverse(previousCurrencies);

		return previousCurrencies;
	}

	private List<CurrencyRateInterday> convertViewListToObjectList(List<ViewCurrencyInterday> list) {
		return list.stream().map((ViewCurrencyInterday interday) -> interday.getInterday())
				.collect(Collectors.toList());
	}

	/**
	 * Retorna o registro mais atual da moeda requisitada
	 * 
	 * @param currencyId
	 * @return a última moeda registrada no histórico
	 */
	@Cacheable("CurrencyInterdayService-findMostRecentCurrencyBy")
	public List<CurrencyRateInterday> findMostRecentCurrencyBy(int currencyId) {
		return currencyRateInterdayRepository.findFirstByCurrencyIdOrderByDateDesc(currencyId);
	}
}
