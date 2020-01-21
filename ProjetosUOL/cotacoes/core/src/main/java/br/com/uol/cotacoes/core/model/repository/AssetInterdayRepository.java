package br.com.uol.cotacoes.core.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.uol.cotacoes.core.model.entity.AssetInterday;

/**
 * Created by vrx_mtoledo on 07/06/17.
 */
public interface AssetInterdayRepository extends CrudRepository<AssetInterday, Long> {

    List<AssetInterday> findByExchangeAssetIdAndDateLessThanOrderByDateDesc(Integer exchangeAssetId, LocalDate end, Pageable pageRequest);

    List<AssetInterday> findByExchangeAssetIdAndDateLessThanAndIdLessThanOrderByDateDesc(Integer exchangeAssetId, LocalDate end, Integer id, Pageable pageRequest);

    List<AssetInterday> findByExchangeAssetIdAndDateLessThanAndIdGreaterThanOrderByDate(Integer exchangeAssetId, LocalDate end, Integer id, Pageable pageRequest);

	@Query("select interday "
			+ "from br.com.uol.cotacoes.core.model.entity.AssetInterday interday "
			+ "left join interday.exchangeAsset exchange "
			+ "where interday.exchangeAsset.id = ?1 and "
			+ "interday.date between ?2 and ?3 "
			+ "order by interday.date desc")
    List<AssetInterday> findByExchangeAssetIdAndDateBetweenOrderByDateDesc(Integer id, LocalDate start, LocalDate end);
	
	/**
	 * Retorna o registro de informações mais atual do ativo escolhido.
	 * 
	 * @param exchangeAssetId ID do ativo
	 * @return Retorna o registro mais atual do ativo escolhido, ou vazio caso o ativo não exista ou não exista registros.
	 */		
    List<AssetInterday> findFirstByExchangeAssetIdOrderByDateDesc(Integer exchangeAssetId);
	
}
