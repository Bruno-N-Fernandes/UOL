package br.com.uol.cotacoes.webrest.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.uol.cotacoes.core.business.service.AssetInterdayService;
import br.com.uol.cotacoes.core.business.service.AssetIntradayService;
import br.com.uol.cotacoes.core.business.service.ExchangeAssetService;
import br.com.uol.cotacoes.core.model.entity.AssetHistory;
import br.com.uol.cotacoes.core.model.entity.AssetInterday;
import br.com.uol.cotacoes.core.model.entity.AssetIntraday;
import br.com.uol.cotacoes.core.model.entity.ExchangeAsset;
import br.com.uol.cotacoes.core.model.repository.ExchangeAssetRepository;
import br.com.uol.cotacoes.webrest.helper.ControllerHelper;
import br.com.uol.cotacoes.webrest.helper.ResponseHelper;

/**
 * Created by vrx_mtoledo on 07/06/17.
 */
@RestController
public class AssetController {

    @Autowired
    private Logger logger;

    @Autowired
	private ResponseHelper responseHelper;
    
    @Autowired
	private ControllerHelper controllerHelper;
    
    @Autowired
    private ExchangeAssetService service;

    @Autowired
    private AssetIntradayService intradayService;

    @Autowired
    private AssetInterdayService interdayService;
    
    private HashMap<String, String> paths;

    private static final String APPLICATION_JSON = "application/json";

    private static final String TEXT_CSV = "text/csv";
    
    @PostConstruct
    public void init(){
    	paths = new HashMap<>();
    	
    	paths.put("index", ExchangeAssetRepository.ASSET_TYPE_INDEX);
    	paths.put("commodities", ExchangeAssetRepository.ASSET_TYPE_COMMODITIES);
    	paths.put("asset", ExchangeAssetRepository.ASSET_TYPE_ASSET);
    	paths.put("inflation", ExchangeAssetRepository.ASSET_TYPE_INFLATION);
    	paths.put("indicators", ExchangeAssetRepository.ASSET_TYPE_INDICATORS);
    }
    
    /**
     * Realiza a listagem de todos os indices
     *
     * @param fields
     * @return
     */
    @GetMapping(path= "/{exchangeAsset}/list", produces={APPLICATION_JSON})
    public @ResponseBody Object listAllIndexes( @RequestParam(value = "fields") final String fields,
    		@PathVariable("exchangeAsset") final String exchangeAsset){
    	
        logger.info("Request index list fields {} and path {}", fields, exchangeAsset);
        List<? extends ExchangeAsset> indexList = service.listAssetsBy(paths.get(exchangeAsset));

        return responseHelper.generateReturn(indexList, fields);
    }


    /**
     * Retorna a lista de ações baseadas nas letras que foram passadas
     * 
     * @param letters Letras que serão consultadas
     * @param fields Campos que serão retornados
     * @return Itens localizados com os parametros das letras
     */
    @GetMapping(path = "/asset/list", params={"letters","fields"}, produces={APPLICATION_JSON})
    public @ResponseBody Object getAsset(@RequestParam(value = "letters") final String letters,
    		@RequestParam(value = "fields") final String fields,
            @RequestParam(value = "size") final Integer size ){
    	logger.info("Request asset list fields {} letters {}", fields, letters);
        
		return  responseHelper.generateReturn(service.findAssetsByFilter(letters, size),fields);
    }


    /**
     * Retorna as informações resumidas e atualizadas do asset consultados
     * 
     * @param item Id do asset
     * @param fields Campos que serão retornados
     * @param exchangeAsset Path do asset 
     * @return Informações do asset consultado
     */
    @GetMapping(path= "/{exchangeAsset}/summary", produces={APPLICATION_JSON})
    public @ResponseBody Object getAssetSummary( @RequestParam(value = "item") final Integer item,
            @RequestParam(value = "fields") final String fields,
            @PathVariable("exchangeAsset") final String exchangeAsset){

        logger.info("Request asset summary path {}, assetId {} and fields {}", item, exchangeAsset, fields);
        List<? extends AssetHistory> assetSummary = intradayService.findMostRecentAssetBy(item);
        
        if(assetSummary.isEmpty()){
        	assetSummary = interdayService.findMostRecentAssetBy(item);
        }       

        return responseHelper.generateReturn(assetSummary, fields);
    }


    /**
     * Retorna os Top assets das mais negociadas do dia
     *
     * @param fields
     * @param size
     * @param exchangeAsset
     * @return Informações dos assets consultados
     */
    @GetMapping(path= "/{exchangeAsset}/traded/top", produces={APPLICATION_JSON})
    public @ResponseBody Object getAssetTopTraded( @RequestParam(value = "fields") final String fields,
              @RequestParam(value = "size") final Integer size,
              @PathVariable("exchangeAsset") final String exchangeAsset){

        logger.info("Request Top asset variation path {}, size {} and fields {}", exchangeAsset, size, fields);
        List<AssetIntraday> assetSummary = intradayService.findMostTradedAssetsBy(paths.get(exchangeAsset), size);

        return responseHelper.generateReturn(assetSummary, fields);
    }


    /**
     * Retorna os Top assets com as maiores variações do dia
     *
     * @param fields
     * @param size
     * @param exchangeAsset
     * @return Informações dos assets consultados
     */
    @GetMapping(path= "/{exchangeAsset}/variation/top", produces={APPLICATION_JSON})
    public @ResponseBody Object getAssetTopVariation( @RequestParam(value = "fields") final String fields,
             @RequestParam(value = "size") final Integer size,
             @PathVariable("exchangeAsset") final String exchangeAsset){

        logger.info("Request Top asset variation path {}, size {} and fields {}", exchangeAsset, size, fields);
        List<AssetIntraday> assetSummary = intradayService.findMostVariedAssetsBy(paths.get(exchangeAsset), size);

        return responseHelper.generateReturn(assetSummary, fields);
    }


    /**
     * Retorna os Bottom assets com as maiores variações negativas do dia
     *
     * @param fields
     * @param size
     * @param exchangeAsset
     * @return Informações dos assets consultados
     */
    @GetMapping(path= "/{exchangeAsset}/variation/bottom", produces={APPLICATION_JSON})
    public @ResponseBody Object getAssetBottomVariation( @RequestParam(value = "fields") final String fields,
              @RequestParam(value = "size") final Integer size,
              @PathVariable("exchangeAsset") final String exchangeAsset){

        logger.info("Request Bottom asset variation path {}, size {} and fields {}", exchangeAsset, size, fields);
        List<AssetIntraday> assetSummary = intradayService.findLowerVariedAssetsBy(paths.get(exchangeAsset), size);

        return responseHelper.generateReturn(assetSummary, fields);
    }
    

    /**
     * Lista todas as informações recentes(por dia) de um asset
     * 
     * @param item Id do asset
     * @param fields Campos que serão retornados
     * @param exchangeAsset Path do asset 
     * @param format Fomato que será respondido
     * @return Informações do asset consultado    
     */
    @GetMapping(path= "/{exchangeAsset}/intraday/list", produces={APPLICATION_JSON, TEXT_CSV}, params={"item","fields"})
    public @ResponseBody Object getAssetIntradayList( @RequestParam(value = "item") final Integer item,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "format", required=false) final String format,
            @PathVariable("exchangeAsset") final String exchangeAsset){

    	logger.info("Request asset intraday list path {}, assetId {}, format {} and fields {}", item, exchangeAsset, format, fields);
        List<AssetIntraday> assetSummary = intradayService.listMostRecentDayAssetsBy(item);

        return responseHelper.generateReturnByFormat(assetSummary, fields, format);
    }
    

    /**
     * Retorna os registros paginados, sendo este serviço sempre correspondente a primeira pagina(Mais atual)
     * 
     * @param item Id do asset
     * @param fields Campos que serão retornados
     * @param exchangeAsset Path do asset 
     * @param size Quantidade de itens que serão retornado
     * @return Informações do asset consultado    
     */
    @GetMapping(path= "/{exchangeAsset}/intraday/list/paged", produces={APPLICATION_JSON}, params={"item","fields","size"})
    public @ResponseBody Object getAssetIntradayListFirstPage( @RequestParam(value = "item") final Integer item,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "size") final Integer size,
            @PathVariable("exchangeAsset") final String exchangeAsset){

    	logger.info("Request asset intraday paged list path {}, assetId {}, size {} and fields {}", exchangeAsset, item, size, fields);
        List<AssetIntraday> assets = intradayService.listMostRecentDayAssetsBy(item, (size +1));

        return responseHelper.generateReturnFirstPage(assets, fields,size);
    }
        

    /**
     * Carrega os registros da próxima página
     * 
     * @param item Id do asset
     * @param fields Campos que serão retornados
     * @param exchangeAsset Path do asset 
     * @param size Quantidade de itens que serão retornado
     * @param next Chave da próxima página
     * @return Informações do asset consultado   
     */
    @GetMapping(path = "/{exchangeAsset}/intraday/list/paged", params={"item","fields","size","next"})
    public @ResponseBody Object getAssetIntradayListNextPage(@RequestParam(value = "item") final Integer item,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "size") final int size,
            @RequestParam(value = "next") String next, @PathVariable("exchangeAsset") final String exchangeAsset ){
    	
    	logger.info("Request next page of asset intraday paged list path {}, assetId {}, next {}, size {} and fields {}", exchangeAsset, item, next, size, fields);
    	
    	int nextPointer = controllerHelper.createPointer(next);
        List<AssetIntraday> assets = intradayService.listMostRecentAssetsFromPointer(item, nextPointer, (size +1));

        return  responseHelper.generateReturnNextPage(assets, fields, size);
    }
    

    /**
     * Carrega os registros da página anterior. 
     * Caso os itens carregados da página anterior, sejam inferiores ao numero de registros 
     * solicitados o sistema entende que deve carregar a página mais atualizada
     * 
     * @param item Id do asset
     * @param fields Campos que serão retornados
     * @param exchangeAsset Path do asset 
     * @param size Quantidade de itens que serão retornado
     * @param prev Chave da página anterior
     * @return Informações do asset consultado  
     * @return
     */
    @GetMapping(path = "/{exchangeAsset}/intraday/list/paged", params={"item","fields","size","prev"})
    public @ResponseBody Object getAssetIntradayListPreviousPage(@RequestParam(value = "item") final Integer item,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "size") final int size,
            @RequestParam(value = "prev") final String prev, @PathVariable("exchangeAsset") final String exchangeAsset ){
    	
    	logger.info("Request previous page of asset intraday paged list path {}, assetId {}, previous {}, size {} and fields {}", exchangeAsset, item, prev, size, fields);
    	
    	int prevPointer = controllerHelper.createPointer(prev);
        List<AssetIntraday> assets = intradayService.listMostOlderAssetsFromPointer(item, prevPointer, (size +1));

        if(controllerHelper.hasToReturnoFirstPage(size, assets)){
        	return getAssetIntradayListFirstPage(item, fields, size, exchangeAsset);
        }

        return  responseHelper.generateReturnPreviousPage(assets, fields, size);
    }

    /**
     * Retorna a primeira página do Asset Interday escolhido ordenados do mais recente para o mais antigo a partir de ontem
     *
     * @param item Id do item
     * @param fields Campos que devem ser respondidos na requisição
     * @param exchangeAsset Path do asset
     * @param size Quantidade de itens que serão retornado
     * @return Informações do asset consultado
     */
    @GetMapping(path= "/{exchangeAsset}/interday/list/paged", produces={APPLICATION_JSON}, params={"item","fields","size"})
    public @ResponseBody Object getAssetInterdayListFirstPage( @RequestParam(value = "item") final Integer item,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "size") final Integer size,
            @PathVariable("exchangeAsset") final String exchangeAsset){

        logger.info("Request asset interday paged list path {}, assetId {}, fields {} and size {}", exchangeAsset, item, fields, size);
        List<AssetInterday> assets = interdayService.listMostRecentDayAssetsBy(item, (size +1));

        return responseHelper.generateReturnFirstPage(assets, fields,size);
    }

    /**
     * Retorna a próxima página do Asset Interday escolhido ordenados do mais recente para o mais antigo a partir de ontem
     *
     * @param item Id do item
     * @param fields Campos que devem ser respondidos na requisição
     * @param exchangeAsset Path do asset
     * @param size Quantidade de itens que serão retornado
     * @param next Ponteiro da próxima página
     * @return Informações do asset consultado
     */
    @GetMapping(path= "/{exchangeAsset}/interday/list/paged", produces={APPLICATION_JSON}, params={"item","fields","size","next"})
    public @ResponseBody Object getAssetInterdayListNextPage( @RequestParam(value = "item") final int item,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "size") final int size,
            @RequestParam(value = "next") final String next, @PathVariable("exchangeAsset") final String exchangeAsset){

        logger.info("Request asset interday paged list path {}, assetId {}, fields {}, size {}, next {}", exchangeAsset, item, fields, size, next);

        int nextPointer = controllerHelper.createPointer(next);
        List<AssetInterday> assets = interdayService.listMostRecentAssetsFromPointer(item, nextPointer, (size +1));

        return responseHelper.generateReturnNextPage(assets, fields,size);
    }

    /**
     * Retorna a página anterior do Asset Interday escolhido ordenados do mais recente para o mais antigo a partir de ontem
     *
     * @param item Id do item
     * @param fields Campos que devem ser respondidos na requisição
     * @param exchangeAsset Path do asset
     * @param size Quantidade de itens que serão retornado
     * @param prev Ponteiro da página anterior
     * @return Informações do asset consultado
     *
     */
    @GetMapping(path= "/{exchangeAsset}/interday/list/paged", produces={APPLICATION_JSON}, params={"item","fields","size","prev"})
    public @ResponseBody Object getAssetInterdayListPreviousPage(@RequestParam(value = "item") final int item,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "size") final int size,
            @RequestParam(value = "prev") final String prev, @PathVariable("exchangeAsset") final String exchangeAsset){
        logger.info("Request previous page of asset interday list with path {}, assetId {}, fields {}, size {}, previous {}", exchangeAsset, item, fields, size, prev);

        int prevPointer = controllerHelper.createPointer(prev);
        List<AssetInterday> assets = interdayService.listMostOlderAssetsFromPointer(item, prevPointer, (size+1));

        if(controllerHelper.hasToReturnoFirstPage(size, assets)){
            return getAssetInterdayListFirstPage(item, fields, size, exchangeAsset);
        }

        return  responseHelper.generateReturnPreviousPage(assets, fields, size);
    }
    
  
   @GetMapping(path = {"/{exchangeAsset}/interday/list/week"}, produces={APPLICATION_JSON,TEXT_CSV})
   public @ResponseBody Object getAssetInterdayListForOneWeek( @RequestParam(value = "item") final Integer item,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "format", required=false) final String format, 
            @PathVariable("exchangeAsset") final String exchangeAsset ){	   
	   logger.info("Request asset interday list week with path {}, assetId {}, fields {} and format {}", exchangeAsset, item, fields, format);       

       LocalDate today = LocalDate.now();
       LocalDate oneWeekBefore = today.minusDays(7);

       List<AssetInterday> assets = interdayService.listAssetsByPeriod(item, oneWeekBefore, today);

       return responseHelper.generateReturnByFormat(assets, fields, format);
   }


   @GetMapping(path = {"/{exchangeAsset}/interday/list/month"}, produces={APPLICATION_JSON,TEXT_CSV})
   public @ResponseBody Object getAssetInterdayListForOneMonth( @RequestParam(value = "item") final Integer item,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "format", required=false) final String format, 
            @PathVariable("exchangeAsset") final String exchangeAsset ){
	   logger.info("Request asset interday list month with path {}, assetId {}, fields {} and format {}", exchangeAsset, item, fields, format);

       LocalDate today = LocalDate.now();
       LocalDate oneMonthBefore = today.minusMonths(1);

       List<AssetInterday> assets = interdayService.listAssetsByPeriod(item, oneMonthBefore, today);
       
       return responseHelper.generateReturnByFormat(assets, fields, format);
   }

   @GetMapping(path = {"/{exchangeAsset}/interday/list/months"}, produces={APPLICATION_JSON,TEXT_CSV})
   public @ResponseBody Object getAssetInterdayListForThreeMonths( @RequestParam(value = "item") final Integer item,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "format", required=false) final String format, 
            @PathVariable("exchangeAsset") final String exchangeAsset ){
	   logger.info("Request asset interday list months with path {}, assetId {}, fields {} and format {}", exchangeAsset, item, fields, format);

       LocalDate today = LocalDate.now();
       LocalDate threeMonthsBefore = today.minusMonths(3);

       List<AssetInterday> assets = interdayService.listAssetsByPeriod(item, threeMonthsBefore, today);
       
       return responseHelper.generateReturnByFormat(assets, fields, format);
   }

   @GetMapping(path = {"/{exchangeAsset}/interday/list/year"}, produces={APPLICATION_JSON,TEXT_CSV})
   public @ResponseBody Object getAssetInterdayListForOneYear( @RequestParam(value = "item") final Integer item,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "format", required=false) final String format, 
            @PathVariable("exchangeAsset") final String exchangeAsset ){
	   logger.info("Request asset interday list year with path {}, assetId {}, fields {} and format {}", exchangeAsset, item, fields, format);

       LocalDate today = LocalDate.now();
       LocalDate oneYearBefore = today.minusYears(1);

       List<AssetInterday> assets = interdayService.listAssetsByPeriod(item, oneYearBefore, today);
       
       return responseHelper.generateReturnByFormat(assets, fields, format);
   }

}
