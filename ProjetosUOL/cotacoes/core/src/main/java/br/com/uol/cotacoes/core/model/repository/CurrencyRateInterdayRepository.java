package br.com.uol.cotacoes.core.model.repository;

import br.com.uol.cotacoes.core.model.entity.CurrencyRateInterday;
import br.com.uol.cotacoes.core.model.entity.view.ViewCurrencyInterday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by vrx_mtoledo on 26/04/17.
 */
public interface CurrencyRateInterdayRepository extends CrudRepository<CurrencyRateInterday, Long> {

	@Query("select new br.com.uol.cotacoes.core.model.entity.view.ViewCurrencyInterday("
			+ "interday.id, interday.currency.name, interday.currency.codInvestor, interday.bidValue, interday.askValue, interday.maxBid, interday.minBid, "
			+ "interday.variationBid,interday.variationPercentBid, interday.openBidValue, interday.date) "
			+ "from br.com.uol.cotacoes.core.model.entity.CurrencyRateInterday interday "
			+ "left join interday.currency currency "
			+ "where interday.currency.id = ?1 and "
			+ "interday.date between ?2 and ?3 "
			+ "order by interday.date desc")
    List<ViewCurrencyInterday> findByCurrencyIdAndDateBetweenOrderByDateDesc(Integer id, LocalDate start, LocalDate end);

    Page<CurrencyRateInterday> findByCurrencyIdAndDateLessThanOrderByDateDesc(Integer id, LocalDate end, Pageable pageRequest);

    Page<CurrencyRateInterday> findByCurrencyIdAndDateLessThanAndIdLessThanOrderByDateDesc(Integer currencyId, LocalDate end, Integer id, Pageable pageRequest);

    Page<CurrencyRateInterday> findByCurrencyIdAndDateLessThanAndIdGreaterThanOrderByDate(Integer currencyId, LocalDate end, Integer id, Pageable pageRequest);
    
	/**
	 * Carrega o registro mais atual da moeda selecionada.
	 * 
	 * @param currencyId
	 * @return
	 */	
	List<CurrencyRateInterday> findFirstByCurrencyIdOrderByDateDesc(Integer currencyId);

}
