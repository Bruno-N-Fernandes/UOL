package br.com.uol.cotacoes.core.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.uol.cotacoes.core.model.entity.CurrencyRateIntraday;
import br.com.uol.cotacoes.core.model.entity.view.ViewCurrencyIntraday;

/**
 * Created by vrx_mtoledo on 26/04/17.
 */
public interface CurrencyRateIntradayRepository extends CrudRepository<CurrencyRateIntraday, Long> {
	
	/**
	 * Retorna a lista de registros do dia mais atual da moeda ordenado decrescente pela data
	 * 
	 * @param currencyId ID da moeda para ser caregada
	 * @return retorna a lista de registros do dia mais atual da moeda ordenado decrescente pela data
	 */
	@Query("select new br.com.uol.cotacoes.core.model.entity.view.ViewCurrencyIntraday( "
			+ "intraday.id, intraday.currency.name, intraday.currency.codInvestor, intraday.bidValue, intraday.askValue, intraday.maxBid, intraday.minBid, "
			+ "intraday.variationBid,intraday.variationPercentBid, intraday.openBidValue, intraday.date) "
			+ "from br.com.uol.cotacoes.core.model.entity.CurrencyRateIntraday intraday "
			+ "where intraday.currency.id = ?1 and "
			+ "intraday.date > ( "
			+ "select date_format ( max(intraday.date),'%Y-%m-%d') "
			+ "from br.com.uol.cotacoes.core.model.entity.CurrencyRateIntraday intraday "
			+ "where intraday.currency.id = ?1) "
			+ "order by intraday.date desc")
	List<ViewCurrencyIntraday> findByCurrencyIdMostRecentOrderByDateDesc(Integer currencyId);

	/**
	 * Retorna a lista de registros do dia mais atual da moeda ordenando decrescente pela data com o limite de registro definido no parametro
	 * 
	 * @param currencyId
	 * @param pageRequest
	 * @return
	 */	
    Page<CurrencyRateIntraday> findByCurrencyIdOrderByDateDesc(Integer currencyId, Pageable pageRequest);
	
	/**
	 * Carrega a lista dos primeiros itens conforme definido no parametro com id menor que o passado.
	 * Retorna os itens em ordem decrescente de data.
	 * Agrupa os itens por data para que não ocorram registros repetidos.
	 * 
	 * @param currencyId
	 * @param id
	 * @param pageRequest
	 * @return
	 */
	Page<CurrencyRateIntraday> findByCurrencyIdAndIdLessThanOrderByDateDesc(Integer currencyId, Integer id, Pageable pageRequest);
	
	/**
	 * Carrega a lista dos ultimos itens conforme definido no parametro com id maior que o passado.
	 * Retorna os itens em ordem crescente de data.
	 * Agrupa os itens por data para que não ocorram registros repetidos.
	 * 
	 * @param currencyId
	 * @param id
	 * @param pageRequest
	 * @return
	 */
	Page<CurrencyRateIntraday> findByCurrencyIdAndIdGreaterThanOrderByDateAsc(Integer currencyId, Integer id, Pageable pageRequest);

	/**
	 * Carrega o registro mais atual da moeda passada.
	 * 
	 * @param currencyId
	 * @return
	 */	
	List<CurrencyRateIntraday> findFirstByCurrencyIdOrderByDateDesc(Integer currencyId);
		
}
