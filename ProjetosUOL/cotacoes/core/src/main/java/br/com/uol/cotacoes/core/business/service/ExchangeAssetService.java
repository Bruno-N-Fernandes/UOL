package br.com.uol.cotacoes.core.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.uol.cotacoes.core.business.service.filter.ExchangeAssetFilter;
import br.com.uol.cotacoes.core.model.entity.Asset;
import br.com.uol.cotacoes.core.model.entity.ExchangeAsset;
import br.com.uol.cotacoes.core.model.entity.view.ViewExchangeAsset;
import br.com.uol.cotacoes.core.model.repository.ExchangeAssetRepository;

/**
 * Serviços referentes ao carregamento de ativos<br>
 * Os códigos dos ativos estão listados em {@link ExchangeAssetRepository}<br>  
 * 
 * Created by vrx_mtoledo on 08/06/17.
 */
@Service
public class ExchangeAssetService {

    @Autowired
    private ExchangeAssetRepository exchangeAssetRepository;

    @Autowired
    private ExchangeAssetFilter filter;
    
    /**
     * Filtra os ativos do tipo {@link Asset} pelas letras que ele contenha em se código e nome.
     * 
     * @param letters Letras que serão utilizadas no filtro
     * @return Retorna os itens que contenham as letras
     */
    public List<ExchangeAsset> findAssetsByFilter(String letters, Integer size){
    	return filter.findBy(letters, size);
    }
    
    /**
     * Carrega todos os itens do tipo de {@link ExchangeAsset} escolhido.
     * 
     * @param type Tipo do {@link ExchangeAsset} escolhido. Lista de tipos em {@link ExchangeAssetRepository}.
     * @return Retorna todos os itens do tipo escolhido.
     */
    @Cacheable("ExchangeAssetService-listAssetsBy")
    public List<? extends ExchangeAsset> listAssetsBy(String type){
        return exchangeAssetRepository.findBy(type);
    }
    
    /**
     * Carrega todos os itens do tipo de {@link ExchangeAsset} escolhido. Que possuam registro nas tabelas de intraday.
     * 
     * @param type Tipo do {@link ExchangeAsset} escolhido. Lista de tipos em {@link ExchangeAssetRepository}.
     * @return Retorna todos os itens do tipo escolhido.
     */
    public List<ViewExchangeAsset> listAssetsByIntraday(String type){
        return exchangeAssetRepository.findByTypeInIntraday(type);
    }
    
    /**
     * Carrega todos os itens do tipo de {@link ExchangeAsset} escolhido. Que possuam registro nas tabelas de intraday.
     * 
     * @param type Tipo do {@link ExchangeAsset} escolhido. Lista de tipos em {@link ExchangeAssetRepository}.
     * @return Retorna todos os itens do tipo escolhido.
     */
    public List<ViewExchangeAsset> listAssetsByInterday(String type){
        return exchangeAssetRepository.findByTypeInInterday(type);
    }
    
    /**
     * Todo dia no período da manhã atualiza a lista de ações em memória
     */
    @Scheduled(cron="0 0/5 * 1/1 * ?")
    public void updateAssetsIndex(){
    	filter.loadAssetIndex();
    }

}
