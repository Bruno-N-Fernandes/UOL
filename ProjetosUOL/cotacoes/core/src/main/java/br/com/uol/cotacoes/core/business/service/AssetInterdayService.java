package br.com.uol.cotacoes.core.business.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.uol.cotacoes.core.model.entity.AssetInterday;
import br.com.uol.cotacoes.core.model.repository.AssetInterdayRepository;

/**
 * Created by vrx_mtoledo on 09/06/17.
 */
@Service
public class AssetInterdayService {

	@Autowired
    private AssetInterdayRepository assetInterdayRepository;


    /**
     * Lista os itens históricos mais atuais a partir de ontem
     *
     * @param exchangeAssetId id do item a ser listado
     * @param size da página
     * @return a primeira página dos itens históricos
     *
     */
	@Cacheable("AssetInterdayService-listMostRecentDayAssetsBy")
    public List<AssetInterday> listMostRecentDayAssetsBy(final int exchangeAssetId,final  int size){
        LocalDate today = LocalDate.now();

        return assetInterdayRepository.findByExchangeAssetIdAndDateLessThanOrderByDateDesc(exchangeAssetId, today, new PageRequest(0, size));
    }

    /**
     * A próxima página dos itens históricos, ordenadas do mais recente para o mais antigo e a partir de ontem
     *
     * @param exchangeAssetId id do item a ser listado
     * @param nextPointer ponteiro da pagina que será gerada
     * @param size da página
     * @return a próxima página dos itens
     *
     */
	@Cacheable("AssetInterdayService-listMostRecentAssetsFromPointer")
    public List<AssetInterday> listMostRecentAssetsFromPointer(final int exchangeAssetId, final int nextPointer, final int size) {
        LocalDate today = LocalDate.now();

        return assetInterdayRepository.findByExchangeAssetIdAndDateLessThanAndIdLessThanOrderByDateDesc(exchangeAssetId, today, nextPointer, new PageRequest(0, size));
    }

    /**
     * A página anterior dos itens históricos, ordenados do mais recente para o mais antigo e a partir de ontem
     *
     * @param exchangeAssetId id do item a ser listado
     * @param prevPointer ponteiro da pagina que será gerada
     * @param pageSize da página
     * @return a página anterior das moedas registradas, ordenadas da moeda mais recente para a mais antiga
     */
	@Cacheable("AssetInterdayService-listMostOlderAssetsFromPointer")
    public List<AssetInterday> listMostOlderAssetsFromPointer(final Integer exchangeAssetId,final int prevPointer,final int pageSize) {
        LocalDate today = LocalDate.now();

        List<AssetInterday> previousAssets = assetInterdayRepository.findByExchangeAssetIdAndDateLessThanAndIdGreaterThanOrderByDate(exchangeAssetId, today, prevPointer, new PageRequest(0, pageSize));
        previousAssets = new ArrayList<>(previousAssets);
        Collections.reverse(previousAssets);

        return previousAssets;
    }
    
    /**
     * Lista de itens dentro de um período ordenados do mais recente para o mais antigo
     *
     * @param exchangeAssetId id do item a ser listado
     * @param start data de início
     * @param end data fim
     * @return a lista de itens
     *
     */
	@Cacheable("AssetInterdayService-listAssetsByPeriod")
    public List<AssetInterday> listAssetsByPeriod(final int exchangeAssetId,final LocalDate start, final LocalDate end) {
        List<AssetInterday> assets = assetInterdayRepository.findByExchangeAssetIdAndDateBetweenOrderByDateDesc(exchangeAssetId, start, end);
        
        if(!assets.isEmpty()){ 
	        BigDecimal referencePrice = assets.get(assets.size() - 1).getPrice();
	        for (AssetInterday assetInterday : assets) {
	        	assetInterday.updateRelativePecentual(referencePrice);
			}
        }
		return assets;
    }
	
	/**
	 * Carrega a informação mais recente do ativo procurado.
	 * 
	 * @param exchangeAssetId ID do ativo  a ser procurado
	 * @return Informações do ativo quando encotnrado. Ou uma lista vazia quando não encontra o ativo
	 */
	@Cacheable("AssetInterdayService-findMostRecentAssetBy")
    public List<AssetInterday> findMostRecentAssetBy(final Integer exchangeAssetId){
		return assetInterdayRepository.findFirstByExchangeAssetIdOrderByDateDesc(exchangeAssetId);
    }

}
