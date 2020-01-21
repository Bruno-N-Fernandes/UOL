package br.com.uol.cotacoes.core.model.repository;

import br.com.uol.cotacoes.core.model.entity.AssetIntraday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repositório de acesso aos dados de informações diárias.
 * 
 * Created by vrx_mtoledo on 07/06/17.
 */
public interface AssetIntradayRepository extends CrudRepository<AssetIntraday, Long> {

	/**
	 * Carrega os assets que mais variaram negativamente no dia
	 *
	 * @param type
	 * @param size
	 * @return Lista de Bottom assets de maior variação negativa do dia
	 */
	@Query(value = "select a.idt_asset_intraday, a.num_price, a.idt_exchange_asset, a.num_high, a.num_low, a.num_open, " +
			"a.num_volume, a.num_close, a.num_bid, a.num_ask, a.num_change, a.num_pct_change, a.dat_last_update from asset_intraday a " +
			"inner join exchange_asset e on e.idt_exchange_asset = a.idt_exchange_asset and e.ind_asset_type=?1 " +
			"where " +
			"a.dat_last_update> (select date_format ( max(dat_last_update),'%Y-%m-%d') from asset_intraday) and " +
			"a.num_pct_change < 0.0 and " +
			"exists ( select i.tes from " +
			"(select max(idt_asset_intraday) tes from asset_intraday " +
			"group by idt_exchange_asset) i " +
			"where i.tes = a.idt_asset_intraday " +
			") order by a.num_pct_change asc limit ?2", nativeQuery = true)
	List<AssetIntraday> findBottomVariationAssets(String type, Integer size);

	/**
	 * Carrega os assets que mais variaram no dia
	 *
	 * @param type
	 * @param size
	 * @return Lista de Top assets de maior variação do dia
	 */
	@Query(value = "select a.idt_asset_intraday, a.num_price, a.idt_exchange_asset, a.num_high, a.num_low, a.num_open, " +
			"a.num_volume, a.num_close, a.num_bid, a.num_ask, a.num_change, a.num_pct_change, a.dat_last_update from asset_intraday a " +
			"inner join exchange_asset e on e.idt_exchange_asset = a.idt_exchange_asset and e.ind_asset_type=?1 " +
			"where " +
			"a.dat_last_update> (select date_format ( max(dat_last_update),'%Y-%m-%d') from asset_intraday) and " +
			"a.num_pct_change > 0.0 and " +
			"exists ( select i.tes from " +
			"(select max(idt_asset_intraday) tes from asset_intraday " +
			"group by idt_exchange_asset) i " +
			"where i.tes = a.idt_asset_intraday " +
			") order by a.num_pct_change desc limit ?2", nativeQuery = true)
	List<AssetIntraday> findTopVariationAssets(String type, Integer size);

	/**
	 * Carrega os assets que foram mais negociadas no dia
	 *
	 * @param type
	 * @param size
	 * @return Lista de Top assets-mais negociadas do dia
	 */
	@Query(value = "select a.idt_asset_intraday, a.num_price, a.idt_exchange_asset, a.num_high, a.num_low, a.num_open, " +
			"a.num_volume, a.num_close, a.num_bid, a.num_ask, a.num_change, a.num_pct_change, a.dat_last_update from asset_intraday a " +
			"inner join exchange_asset e on e.idt_exchange_asset = a.idt_exchange_asset and e.ind_asset_type=?1 " +
			"where " +
			"a.dat_last_update> (select date_format ( max(dat_last_update),'%Y-%m-%d') from asset_intraday) and " +
			"exists ( select i.tes from " +
			"(select max(idt_asset_intraday) tes from asset_intraday " +
			"group by idt_exchange_asset) i " +
			"where i.tes = a.idt_asset_intraday " +
			") order by a.num_volume desc limit ?2", nativeQuery = true)
	List<AssetIntraday> findTopTradedAssets(String type, Integer size);

	/**
	 * Retorna o registro de informações mais atual do ativo escolhido.
	 * 
	 * @param exchangeAssetId ID do ativo
	 * @return Retorna o registro mais atual do ativo escolhido, ou vazio caso o ativo não exista ou não exista registros.
	 */		
    List<AssetIntraday> findFirstByExchangeAssetIdOrderByDateDesc(Integer exchangeAssetId);
    
    /**
     * Carrega todos os registros da data mais atual com informações do ativo escolhido.
     * 
     * @param exchangeAssetId ID do ativo
     * @return Retorna os registros mais atuais do ativo escolhido, ou vazio caso o ativo não exista ou não exista registros.
     */
	@Query("select intraday from br.com.uol.cotacoes.core.model.entity.AssetIntraday intraday where intraday.exchangeAsset.id = ?1 and "
			+ "intraday.date > ( "
			+ "select date_format ( max(intraday.date),'%Y-%m-%d') "
			+ "from br.com.uol.cotacoes.core.model.entity.AssetIntraday intraday "
			+ "where intraday.exchangeAsset.id = ?1) "
			+ "order by intraday.date desc")
    List<AssetIntraday> findByExchangeAssetIdMostRecentOrderByDateDesc(Integer exchangeAssetId);
	
    /**
     * Carrega os itens mais recentes do ativo escolhido, até a quantidade solicitada 
     * 
     * @param exchangeAssetId ID do ativo
     * @return Retorna os registros mais atuais do ativo escolhido, ou vazio caso o ativo não exista ou não exista registros.
     */	
    List<AssetIntraday> findByExchangeAssetIdOrderByDateDesc(Integer exchangeAssetId, Pageable pageable);
    
	/**
	 * Carrega a lista dos primeiros itens conforme definido no parametro com id menor que o passado.
	 * Retorna os itens em ordem decrescente de data.
	 * Agrupa os itens por data para que não ocorram registros repetidos.
	 * 
	 * @param exchangeAssetId ID do ativo
	 * @param id ID que será usado para filtrar o limite dos registros
	 * @param pageRequest Quantidade de registros a serem carregados
	 * @return Retorna os registros conforme a quantidade definida com o corte do filtro por ID
	 */
	Page<AssetIntraday> findByExchangeAssetIdAndIdLessThanOrderByDateDesc(Integer exchangeAssetId, Integer id, Pageable pageRequest);
	
	/**
	 * Carrega a lista dos ultimos itens conforme definido no parametro com id maior que o passado.
	 * Retorna os itens em ordem crescente de data.
	 * Agrupa os itens por data para que não ocorram registros repetidos.
	 * 
	 * @param exchangeAssetId ID do ativo
	 * @param id ID que será usado para filtrar o limite dos registros
	 * @param pageRequest Quantidade de registros a serem carregados
	 * @return Retorna os registros conforme a quantidade definida com o corte do filtro por ID
	 */
	Page<AssetIntraday> findByExchangeAssetIdAndIdGreaterThanOrderByDateAsc(Integer exchangeAssetId, Integer id, Pageable pageRequest);

}
