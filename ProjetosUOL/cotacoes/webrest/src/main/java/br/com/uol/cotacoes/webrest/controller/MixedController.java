package br.com.uol.cotacoes.webrest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.uol.cotacoes.core.business.service.AssetInterdayService;
import br.com.uol.cotacoes.core.business.service.AssetIntradayService;
import br.com.uol.cotacoes.core.business.service.CurrencyInterdayService;
import br.com.uol.cotacoes.core.business.service.CurrencyIntradayService;
import br.com.uol.cotacoes.core.model.entity.DomainEntity;
import br.com.uol.cotacoes.webrest.helper.ResponseHelper;

/**
 * Controller para as requisições que utilizam serviços de mais de um controller em uma unica requisição.
 * Em geral este Controller é utilizado para melhoria de performance e consumo de recursos.
 * 
 * @author mzp_dferraz
 *
 */
@RestController
public class MixedController {
	
	@Autowired
    private Logger logger;
	
	@Autowired
	private ResponseHelper responseHelper;
	    
	@Autowired
	private CurrencyIntradayService curencyIntradayService;
		
	@Autowired
	private AssetIntradayService assetIntradayService;	
	
	@Autowired
	private CurrencyInterdayService curencyInterdayService;
		
	@Autowired
	private AssetInterdayService assetInterdayService;	
    
    @GetMapping(path = "/mixed/summary")
    public @ResponseBody Object getMixedSummary( @RequestParam(value = "currencies", required=false) final String currencies,
    		@RequestParam(value = "itens", required=false) final String itens, @RequestParam(value = "fields") final String fields) {
    	
    	logger.info("Request mixed summary with curenciesid {}, itensid {} and fields {}", currencies, itens, fields);
    	
    	List<DomainEntity> mixedSummary = new ArrayList<>();
    	
    	loadValue(currencies, mixedSummary, curencyIntradayService::findMostRecentCurrencyBy, curencyInterdayService::findMostRecentCurrencyBy);
    	
    	loadValue(itens, mixedSummary, assetIntradayService::findMostRecentAssetBy, assetInterdayService::findMostRecentAssetBy);
    	
        return  responseHelper.generateReturn(mixedSummary, fields);
    }

	/**
	 * Carrega os itens que devem ser consultados
	 * 
	 * @param parameters Itens que devem ser consultados
	 * @param mixedSummary Lista que será preenchida
	 * @param function Função que será utilizada para consultar os itens
	 */
	private void loadValue(final String parameters, List<DomainEntity> mixedSummary, Function<Integer, List<? extends DomainEntity>> function, Function<Integer, List<? extends DomainEntity>> fallBackFunction) {
		if(hasValues(parameters)){
	    	iterate(parameters, mixedSummary, function, fallBackFunction);
    	}
	}

	/**
	 * Verifica se o parametro tems valores
	 * 
	 * @param parameters Itens que serão verificados
	 * @return
	 */
	private boolean hasValues(final String parameters) {
		return parameters != null;
	}

	/**
	 * Itera sobre o item passado carregando o item conforme a função que foi definida
	 * 
	 * @param parameters Itens que devem ser consultados
	 * @param mixedSummary Lista que será preenchida
	 * @param function Função que será utilizada para consultar os itens
	 */
	private void iterate(final String parameters, List<DomainEntity> mixedSummary, Function<Integer, List<? extends DomainEntity>> function, Function<Integer, List<? extends DomainEntity>> fallBackFunction) {
		for (String parameter : parameters.split(",")) {
			List<? extends DomainEntity> mostRecent = function.apply(Integer.valueOf(parameter));
			if(mostRecent.isEmpty()){
				 mostRecent = fallBackFunction.apply(Integer.valueOf(parameter));
			}
			if(!mostRecent.isEmpty()){
				mixedSummary.add(mostRecent.get(0));	
			}
		}
	}

}
