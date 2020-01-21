package br.com.uol.cotacoes.core.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.uol.cotacoes.core.model.entity.ExchangeAsset;
import br.com.uol.cotacoes.core.model.entity.view.ViewExchangeAsset;

/**
 * Repositório de acesso a dados dos ativos:
 * Códigos dos tipos de ativos
 * 
 * Ação = S
 * Indice = I
 * Inflação = N
 * Indicadores = N
 * Commodities = C
 * 
 * Created by vrx_mtoledo on 07/06/17.
 */
public interface ExchangeAssetRepository extends CrudRepository<ExchangeAsset, Long> {
	
	String ASSET_TYPE_ASSET = "S";
	String ASSET_TYPE_INDEX = "I";
	String ASSET_TYPE_INFLATION = "F";
	String ASSET_TYPE_INDICATORS = "N";
	String ASSET_TYPE_COMMODITIES = "C";

    /**
     * Carrega todos os ativos pelo código do tipo de ativo
     * 
     * @param type Código do tipo de ativo
     * @return Lista dos ativos daquele tipo.
     */
    @Query("from br.com.uol.cotacoes.core.model.entity.ExchangeAsset exchangeAsset "
    		+ "left join fetch exchangeAsset.exchange "
    		+ "left join fetch exchangeAsset.companies "
    		+ "where exchangeAsset.type = ?1")
    List<ExchangeAsset> findBy(String type);
    
    /**
     * Carrega todos assets de um determinado tipo que possuem registros de intraday
     * 
     * @param type
     * @return
     */
    @Query("select new br.com.uol.cotacoes.core.model.entity.view.ViewExchangeAsset(intraday.exchangeAsset.id, max(intraday.date)) "
    		+ "from br.com.uol.cotacoes.core.model.entity.AssetIntraday intraday "
			+ "where intraday.exchangeAsset.type = ?1 and "
			+ "intraday.date > ( "
			+ "select date_format ( max(intraday.date),'%Y-%m-%d') "
			+ "from br.com.uol.cotacoes.core.model.entity.AssetIntraday intraday) "			
			+ "group by intraday.exchangeAsset.id")
    List<ViewExchangeAsset> findByTypeInIntraday(String type);
    
    /**
     * Carrega todos assets de um determinado tipo que possuem registros de interday
     * 
     * @param type
     * @return
     */
    @Query("select new br.com.uol.cotacoes.core.model.entity.view.ViewExchangeAsset(interday.exchangeAsset.id, max(interday.date)) "
    		+ "from br.com.uol.cotacoes.core.model.entity.AssetInterday interday "
			+ "where interday.exchangeAsset.type = ?1 "
			+ "group by interday.exchangeAsset.id")
    List<ViewExchangeAsset> findByTypeInInterday(String type);

}
