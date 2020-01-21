package br.com.uol.cotacoes.core.business.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.uol.cotacoes.core.model.entity.AssetInterday;
import br.com.uol.cotacoes.core.model.entity.AssetIntraday;
import br.com.uol.cotacoes.core.model.repository.AssetIntradayRepository;

/**
 * Serviços utilizados para os ativos com as informações diárias
 * 
 * Created by vrx_mtoledo on 09/06/17.
 */
@Service
public class AssetIntradayService {

	@Autowired
    private AssetIntradayRepository assetIntradayRepository;

	/**
	 * Carrega a informação mais recente do ativo procurado.
	 * 
	 * @param exchangeAssetId ID do ativo  a ser procurado
	 * @return Informações do ativo quando encotnrado. Ou uma lista vazia quando não encontra o ativo
	 */
	@Cacheable("AssetIntradayService-findMostRecentAssetBy")
    public List<AssetIntraday> findMostRecentAssetBy(Integer exchangeAssetId){    	
		return assetIntradayRepository.findFirstByExchangeAssetIdOrderByDateDesc(exchangeAssetId);
    }

	/**
	 * Carrega a lista de assets que foram mais negocidas no dia.
	 * A quantidade de registros retornados é definida nos parametros.
	 *
	 * @param type
	 * @param size
	 * @return Informações dos ativos.
	 */
	@Cacheable("AssetIntradayService-findMostTradedAssetsBy")
	public List<AssetIntraday> findMostTradedAssetsBy(String type, Integer size) {
		return assetIntradayRepository.findTopTradedAssets(type, size);
	}

	/**
	 * Carrega a lista de assets que mais variaram no dia.
	 * A quantidade de registros retornados é definida nos parametros.
	 *
	 * @param type
	 * @param size
	 * @return Informações dos ativos.
	 */
	@Cacheable("AssetIntradayService-findMostVariedAssetsBy")
	public List<AssetIntraday> findMostVariedAssetsBy(String type, Integer size){
		return assetIntradayRepository.findTopVariationAssets(type, size);
	}

	/**
	 * Carrega a lista de assets que mais variaram negativamente no dia.
	 * A quantidade de registros retornados é definida nos parametros.
	 *
	 * @param type
	 * @param size
	 * @return Informações dos ativos.
	 */
	@Cacheable("AssetIntradayService-findLowerVariedAssetsBy")
	public List<AssetIntraday> findLowerVariedAssetsBy(String type, Integer size) {
		return assetIntradayRepository.findBottomVariationAssets(type, size);
	}
    
    /**
     * Carrega as informações do ativo do dia mais recente que possui dados.
     * Os registros estão ordenados do mais novo para o mais antigo.
     * 
     * @param exchangeAssetId ID do ativo a ser procurado
     * @return Informações do ativo quando encotnrado. Ou uma lista vazia quando não encontra o ativo
     */
    @Cacheable("AssetIntradayService-listMostRecentDayAssetsBy")
    public List<AssetIntraday> listMostRecentDayAssetsBy(Integer exchangeAssetId){
        List<AssetIntraday> assets = assetIntradayRepository.findByExchangeAssetIdMostRecentOrderByDateDesc(exchangeAssetId);
        
        if(!assets.isEmpty()){        
	        BigDecimal referencePrice = assets.get(assets.size() - 1).getPrice();
	        for (AssetIntraday assetIntraday : assets) {
	        	assetIntraday.updateRelativePecentual(referencePrice);
			}
        }
        
		return assets;
    }
        
    /**
     * Carrega as informações mais recentes do ativo.
     * A quantidade de registros retornados é definida nos parametros.
     * Os registros estão ordenados do mais novo para o mais antigo. 
     * 
     * @param exchangeAssetId ID do ativo a ser procurado;
     * @param size Quantidade de registros para serem carregados.
     * @return
     */
    @Cacheable("AssetIntradayService-listMostRecentDayAssetsBy")
    public List<AssetIntraday> listMostRecentDayAssetsBy(Integer exchangeAssetId, int size){
        return assetIntradayRepository.findByExchangeAssetIdOrderByDateDesc(exchangeAssetId, new PageRequest(0, size));
    }
    

	
	/**
	 * Carrega as informações do ativo a partir a chave da paginação.
	 * A quantidade de registros retornados é definida nos parametros.
	 * Os registros estão ordenados do mais novo para o mais antigo.
	 * 
	 * @param exchangeAssetId ID do ativo a ser procurado;
	 * @param pointer Chave do ponteiro que será usado na consulta;
	 * @param size Quantidade de registros para serem carregados.
	 * @return
	 */
	@Cacheable("AssetIntradayService-listMostRecentAssetsFromPointer")
	public List<AssetIntraday> listMostRecentAssetsFromPointer(int exchangeAssetId, int pointer, int size) {
		List<AssetIntraday> assets = assetIntradayRepository.findByExchangeAssetIdAndIdLessThanOrderByDateDesc(
				exchangeAssetId, pointer, new PageRequest(0, size)).getContent();
		return assets;
	}

	
	/** 
	 * Carrega as informações do ativo até a chave da paginação.
	 * A quantidade de registros retornados é definida nos parametros.
	 * Os registros estão ordenados do mais novo para o mais antigo.
	 * 
	 * @param exchangeAssetId ID do ativo a ser procurado;
	 * @param pointer Chave do ponteiro que será usado na consulta;
	 * @param pageSize Quantidade de registros para serem carregados.
	 * @return
	 */
	@Cacheable("AssetIntradayService-listMostOlderAssetsFromPointer")
    public List<AssetIntraday> listMostOlderAssetsFromPointer(int exchangeAssetId, int pointer, int pageSize) {
        List<AssetIntraday> assets = assetIntradayRepository.findByExchangeAssetIdAndIdGreaterThanOrderByDateAsc(
        		exchangeAssetId, pointer, new PageRequest(0, pageSize)).getContent();
        assets = new ArrayList<>(assets);
        Collections.reverse(assets);
        
        return assets;
    }


}
