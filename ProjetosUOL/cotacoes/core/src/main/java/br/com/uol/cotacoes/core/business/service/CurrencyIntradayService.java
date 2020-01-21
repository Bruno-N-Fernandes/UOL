package br.com.uol.cotacoes.core.business.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.uol.cotacoes.core.model.entity.CurrencyRateInterday;
import br.com.uol.cotacoes.core.model.entity.CurrencyRateIntraday;
import br.com.uol.cotacoes.core.model.entity.view.ViewCurrencyIntraday;
import br.com.uol.cotacoes.core.model.repository.CurrencyRateIntradayRepository;

/**
 * Serviços disponibilizados para o intraday, permite que sejam acessadas informações das transações diarias de moedas.
 * 
 * @author mzp_dferraz
 *
 */
@Service
public class CurrencyIntradayService {
	
	@Autowired
	private CurrencyRateIntradayRepository currencyRateIntradayRepository;

	/** 
	 * Retorna o registro mais atual da moeda requisitada
	 * 
	 * @param currencyId
	 * @return a última moeda registrada hoje
	 */
	@Cacheable("CurrencyIntradayService-findMostRecentCurrencyBy")
	public List<CurrencyRateIntraday> findMostRecentCurrencyBy(int currencyId){
		
		return currencyRateIntradayRepository.findFirstByCurrencyIdOrderByDateDesc(currencyId);
	}

	/**
	 * Retorna a listagem de todos os registros do ultimo dia com registro da moeda passada como parametro
	 * 
	 * @param currencyId Id da moeda para ser carregada
	 * @return todas as moedas registradas hoje e ordenadas da moeda mais recente para a mais antiga
	 */
	@Cacheable("CurrencyIntradayService-listMostRecentDayCurrenciesBy")
	public List<CurrencyRateIntraday> listMostRecentDayCurrenciesBy(int currencyId){
		
		List<CurrencyRateIntraday> intradays = convertViewListToObjectList(currencyRateIntradayRepository.findByCurrencyIdMostRecentOrderByDateDesc(currencyId));
			
		if(!intradays.isEmpty()){
			BigDecimal referenceValue = intradays.get(intradays.size() - 1).getAskValue();
	
			for (CurrencyRateIntraday currencyRateIntraday : intradays) {
				currencyRateIntraday.updateRelativePecentual(referenceValue);
			}
		}
		
		return intradays;
	}

	/**
	 * Carrega os registro mais atuais da moeda passada carregando a quantidade de registros definida.
	 * 
	 * @param currencyId
	 * @param size da página
	 * @return a primeira página de das moedas registradas hoje, ordenadas da moeda mais recente para a mais antiga
	 * 
	 */
	@Cacheable("CurrencyIntradayService-listMostRecentDayCurrenciesBy")
	public List<CurrencyRateIntraday> listMostRecentDayCurrenciesBy(int currencyId, int size) {

		return currencyRateIntradayRepository.findByCurrencyIdOrderByDateDesc(currencyId, new PageRequest(0, size)).getContent();
	}

	/**
	 * A próxima página das moedas registradas hoje, ordenadas da moeda mais recente para a mais antiga
	 * @param currencyId
	 * @param pointer
	 * @param size da página
	 * @return a próxima página das moedas registradas hoje, ordenadas da moeda mais recente para a mais antiga
	 * 
	 */
	@Cacheable("CurrencyIntradayService-listMostRecentCurrenciesFromPointer")
	public List<CurrencyRateIntraday> listMostRecentCurrenciesFromPointer(int currencyId, int pointer, int size) {

		List<CurrencyRateIntraday> nextCurrencies = currencyRateIntradayRepository.findByCurrencyIdAndIdLessThanOrderByDateDesc(
				currencyId, pointer, new PageRequest(0, size)).getContent();
		return nextCurrencies;
	}

	/**
	 * A página anterior das moedas registradas hoje, ordenadas da moeda mais recente para a mais antiga	 *
	 * 
	 * @param currencyId
	 * @param pointer
	 * @param pageSize da página
	 * @return a página anterior das moedas registradas hoje, ordenadas da moeda mais recente para a mais antiga	 
	 */
	@Cacheable("CurrencyIntradayService-listMostOlderCurrenciesFromPointer")
    public List<CurrencyRateIntraday> listMostOlderCurrenciesFromPointer(int currencyId, int pointer, int pageSize) {

        List<CurrencyRateIntraday> previousCurrencies = currencyRateIntradayRepository.findByCurrencyIdAndIdGreaterThanOrderByDateAsc(
        		currencyId, pointer, new PageRequest(0, pageSize)).getContent();
        previousCurrencies = new ArrayList<>(previousCurrencies);
        Collections.reverse(previousCurrencies);
        
        return previousCurrencies;
    }
    
	private List<CurrencyRateIntraday> convertViewListToObjectList(List<ViewCurrencyIntraday> list){
		return list.stream().map((ViewCurrencyIntraday intraday) -> intraday.getIntraday()).collect(Collectors.toList());
	}

}
