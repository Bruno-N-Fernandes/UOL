package br.com.uol.cotacoes.webrest.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.uol.cotacoes.core.business.service.CurrencyInterdayService;
import br.com.uol.cotacoes.core.business.service.CurrencyIntradayService;
import br.com.uol.cotacoes.core.business.service.CurrencyService;
import br.com.uol.cotacoes.core.model.entity.Currency;
import br.com.uol.cotacoes.core.model.entity.CurrencyRateHistory;
import br.com.uol.cotacoes.core.model.entity.CurrencyRateInterday;
import br.com.uol.cotacoes.core.model.entity.CurrencyRateIntraday;
import br.com.uol.cotacoes.webrest.helper.ControllerHelper;
import br.com.uol.cotacoes.webrest.helper.ResponseHelper;

/**
 * Controller para as requisições dos serviços de moedas que a aplicação irá servir
 * 
 * @author mzp_dferraz
 *
 */
@RestController
public class CurrencyController {
	
	@Autowired
    private Logger logger;
	
	@Autowired
	private ResponseHelper responseHelper;
	
	@Autowired
	private ControllerHelper controllerHelper;
    
	@Autowired
	private CurrencyIntradayService intradayService;

    @Autowired
    private CurrencyInterdayService interdayService;
	
	@Autowired
	private CurrencyService currencyService;
	
	/**
     * 
     * Serviço que envia uma lista das informações diarias da moeda selecionada.
     * 
     * @param idCurrency Id da moeda que foi requisitada
     * @param fields Campos que devem ser respondidos na requisição.
     * @return JSON com a lista de moedas
     * 
     */
    @GetMapping(path = "/currency/intraday/list", produces={"application/json","text/csv"})
    public @ResponseBody Object getCurrencyIntradayList( @RequestParam(value = "currency") final Integer idCurrency,
    		@RequestParam(value = "fields") final String fields, @RequestParam(value = "format", required=false) final String format){
        logger.info("Request currency intraday list with id {}, fields {} and format {}", idCurrency, fields, format);

        List<CurrencyRateIntraday> currenciesOfToday = intradayService.listMostRecentDayCurrenciesBy(idCurrency);
        
        return responseHelper.generateReturnByFormat(currenciesOfToday, fields, format);
    }

    /**
     *
     * Serviço que cria a PRIMEIRA página da lista das informações diarias da moeda selecionada.
     *
     * @param currencyId Id da moeda que foi requisitada
     * @param fields Campos que devem ser respondidos na requisição.
     * @param size Tamanho da paginação
     * @return JSON com a lista de moedas
     *
     */
    @GetMapping(path = "/currency/intraday/list/paged", params={"currency","fields","size"})
    public @ResponseBody Object getCurrencyIntradayListFirstPage(@RequestParam(value = "currency") final Integer currencyId,
             @RequestParam(value = "fields") final String fields, @RequestParam(value = "size") final int size ){
        logger.info("Request first page of currency intraday list with id {}, fields {} and size {}", currencyId, fields, size);

        List<CurrencyRateIntraday> currenciesOfToday = intradayService.listMostRecentDayCurrenciesBy(currencyId, (size+1));

        return responseHelper.generateReturnFirstPage(currenciesOfToday, fields, size);
    }

    /**
     *
     * Serviço que envia a PRÓXIMA página da lista de informações diarias da moeda selecionada.
     *
     * @param currencyId Id da moeda que foi requisitada
     * @param fields Campos que devem ser respondidos na requisição.
     * @param size Tamanho da paginação
     * @param next ponteiro da próxima página
     * @return JSON com a lista de moedas
     *
     */
    @GetMapping(path = "/currency/intraday/list/paged", params={"currency","fields","size","next"})
    public @ResponseBody Object getCurrencyIntradayListNextPage(@RequestParam(value = "currency") final Integer currencyId,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "size") final int size,
            @RequestParam(value = "next") String next ){
        logger.info("Request next page of currency intraday list with id {}, fields {}, size {}, next {}", currencyId, fields, size, next);

        int nextPointer = controllerHelper.createPointer(next);
        List<CurrencyRateIntraday> currenciesOfToday = intradayService.listMostRecentCurrenciesFromPointer(currencyId, nextPointer, (size+1));

        return  responseHelper.generateReturnNextPage(currenciesOfToday, fields, size);
    }

    /**
     *
     * Serviço que envia a página ANTERIOR da lista de informações diarias da moeda selecionada.
     *
     * @param currencyId Id da moeda que foi requisitada
     * @param fields Campos que devem ser respondidos na requisição.
     * @param size Tamanho da paginação
     * @param prev ponteiro da página anterior
     * @return JSON com a lista de moedas
     *
     */
    @GetMapping(path = "/currency/intraday/list/paged", params={"currency","fields","size","prev"})
    public @ResponseBody Object getCurrencyIntradayListPreviousPage(@RequestParam(value = "currency") final Integer currencyId,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "size") final int size,
            @RequestParam(value = "prev") final String prev ){
        logger.info("Request previous page of currency intraday list with id {}, fields {}, size {}, previous {}", currencyId, fields, size, prev);

        int prevPointer = controllerHelper.createPointer(prev);
        List<CurrencyRateIntraday> currenciesOfToday = intradayService.listMostOlderCurrenciesFromPointer(currencyId, prevPointer, (size+1));
        
        if(controllerHelper.hasToReturnoFirstPage(size, currenciesOfToday)){
        	return getCurrencyIntradayListFirstPage(currencyId, fields, size);
        }

        return  responseHelper.generateReturnPreviousPage(currenciesOfToday, fields, size);
    }

    /**
     * Serviço que retorna as informações do resumo da situação atual de uma moeda
     * 
     * @param currencyId Id da moeda que foi requisitada
     * @param fields Campos que devem ser respondidos na requisição.
     * @return JSON com o sumario das informações da moeda
     */
    @GetMapping(path = "/currency/summary")
    public @ResponseBody Object getCurrencySummary( @RequestParam(value = "currency") final Integer currencyId,
    		@RequestParam(value = "fields") final String fields
    		) {
    	logger.info("Request currency summary with id {} and fields {}", currencyId, fields);

        List<? extends CurrencyRateHistory> summaryCurrency = intradayService.findMostRecentCurrencyBy(currencyId);        
        if(summaryCurrency.isEmpty()){
        	summaryCurrency = interdayService.findMostRecentCurrencyBy(currencyId);
        }

        return  responseHelper.generateReturn(summaryCurrency, fields);
    }

    /**
     *
     * Serviço que envia uma lista das informações HISTÓRICAS da última semana da moeda selecionada.
     *
     * @param idCurrency Id da moeda que foi requisitada
     * @param fields Campos que devem ser respondidos na requisição.
     * @return JSON com a lista de moedas
     *
     */
    @GetMapping(path = {"/currency/interday/list/week"}, produces={"application/json","text/csv"})
    public @ResponseBody Object getCurrencyInterdayListForOneWeek( @RequestParam(value = "currency") final Integer idCurrency,
             @RequestParam(value = "fields") final String fields, @RequestParam(value = "format", required=false) final String format ){
        logger.info("Request currency interday list week with id {}, fields {} and format {}", idCurrency, fields, format);

        LocalDate today = LocalDate.now();
        LocalDate oneWeekBefore = today.minusDays(7);

        List<CurrencyRateInterday> currenciesOfToday = interdayService.listCurrenciesByPeriod(idCurrency, oneWeekBefore, today);

        return responseHelper.generateReturnByFormat(currenciesOfToday, fields, format);
    }

    /**
     *
     * Serviço que envia uma lista das informações HISTÓRICAS do último mês da moeda selecionada.
     *
     * @param idCurrency Id da moeda que foi requisitada
     * @param fields Campos que devem ser respondidos na requisição.
     * @return JSON com a lista de moedas
     *
     */
    @GetMapping(path = {"/currency/interday/list/month"}, produces={"application/json","text/csv"})
    public @ResponseBody Object getCurrencyInterdayListForOneMonth( @RequestParam(value = "currency") final Integer idCurrency,
             @RequestParam(value = "fields") final String fields, @RequestParam(value = "format", required=false) final String format ){
        logger.info("Request currency interday list month with id {}, fields {} and format {}", idCurrency, fields, format);

        LocalDate today = LocalDate.now();
        LocalDate oneMonthBefore = today.minusMonths(1);

        List<CurrencyRateInterday> currenciesOfToday = interdayService.listCurrenciesByPeriod(idCurrency, oneMonthBefore, today);
        
        return responseHelper.generateReturnByFormat(currenciesOfToday, fields, format);
    }

    /**
     *
     * Serviço que envia uma lista das informações HISTÓRICAS dos 3 últimos meses da moeda selecionada.
     *
     * @param idCurrency Id da moeda que foi requisitada
     * @param fields Campos que devem ser respondidos na requisição.
     * @return JSON com a lista de moedas
     *
     */
    @GetMapping(path = {"/currency/interday/list/months"}, produces={"application/json","text/csv"})
    public @ResponseBody Object getCurrencyInterdayListForThreeMonths( @RequestParam(value = "currency") final Integer idCurrency,
             @RequestParam(value = "fields") final String fields, @RequestParam(value = "format", required=false) final String format ){
        logger.info("Request currency interday list months with id {}, fields {} and format {}", idCurrency, fields, format);

        LocalDate today = LocalDate.now();
        LocalDate threeMonthsBefore = today.minusMonths(3);

        List<CurrencyRateInterday> currenciesOfToday = interdayService.listCurrenciesByPeriod(idCurrency, threeMonthsBefore, today);
        
        return responseHelper.generateReturnByFormat(currenciesOfToday, fields, format);
    }

    /**
     *
     * Serviço que envia uma lista das informações HISTÓRICAS do último ano da moeda selecionada.
     *
     * @param idCurrency Id da moeda que foi requisitada
     * @param fields Campos que devem ser respondidos na requisição.
     * @return JSON com a lista de moedas
     *
     */
    @GetMapping(path = {"/currency/interday/list/year"}, produces={"application/json","text/csv"})
    public @ResponseBody Object getCurrencyInterdayListForOneYear( @RequestParam(value = "currency") final Integer idCurrency,
             @RequestParam(value = "fields") final String fields, @RequestParam(value = "format", required=false) final String format ){
        logger.info("Request currency interday list year with id {}, fields {} and format {}", idCurrency, fields, format);

        LocalDate today = LocalDate.now();
        LocalDate oneYearBefore = today.minusYears(1);

        List<CurrencyRateInterday> currenciesOfToday = interdayService.listCurrenciesByPeriod(idCurrency, oneYearBefore, today);
        
        return responseHelper.generateReturnByFormat(currenciesOfToday, fields, format);
    }

    /**
     *
     * Serviço que cria a PRIMEIRA página da lista das informações históricas da moeda selecionada.
     *
     * @param currencyId Id da moeda que foi requisitada
     * @param fields Campos que devem ser respondidos na requisição.
     * @param size Tamanho da paginação
     * @return JSON com a lista de moedas
     *
     */
    @GetMapping(path = "/currency/interday/list/paged", params={"currency","fields","size"})
    public @ResponseBody Object getCurrencyInterdayListFirstPage(@RequestParam(value = "currency") final Integer currencyId,
                                                                 @RequestParam(value = "fields") final String fields, @RequestParam(value = "size") final int size ){
        logger.info("Request first page of currency interday list with id {}, fields {} and size {}", currencyId, fields, size);

        List<CurrencyRateInterday> currenciesOfToday = interdayService.listMostRecentDayCurrenciesBy(currencyId, (size+1));

        return  responseHelper.generateReturnFirstPage(currenciesOfToday, fields, size);
    }

    /**
     *
     * Serviço que envia a PRÓXIMA página da lista de informações históricas da moeda selecionada.
     *
     * @param currencyId Id da moeda que foi requisitada
     * @param fields Campos que devem ser respondidos na requisição.
     * @param size Tamanho da paginação
     * @param next ponteiro da próxima página
     * @return JSON com a lista de moedas
     *
     */
    @GetMapping(path = "/currency/interday/list/paged", params={"currency","fields","size","next"})
    public @ResponseBody Object getCurrencyInterdayListNextPage(@RequestParam(value = "currency") final Integer currencyId,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "size") final int size,
            @RequestParam(value = "next") String next ){
        logger.info("Request next page of currency interday list with id {}, fields {}, size {}, next {}", currencyId, fields, size, next);

        int nextPointer = controllerHelper.createPointer(next);
        List<CurrencyRateInterday> currenciesOfToday = interdayService.listMostRecentCurrenciesFromPointer(currencyId, nextPointer, (size+1));

        return  responseHelper.generateReturnNextPage(currenciesOfToday, fields, size);
    }

    /**
     *
     * Serviço que envia a página ANTERIOR da lista de informações históricas da moeda selecionada.
     *
     * @param currencyId Id da moeda que foi requisitada
     * @param fields Campos que devem ser respondidos na requisição.
     * @param size Tamanho da paginação
     * @param prev ponteiro da página anterior
     * @return JSON com a lista de moedas
     *
     */
    @GetMapping(path = "/currency/interday/list/paged", params={"currency","fields","size","prev"})
    public @ResponseBody Object getCurrencyInterdayListPreviousPage(@RequestParam(value = "currency") final Integer currencyId,
            @RequestParam(value = "fields") final String fields, @RequestParam(value = "size") final int size,
            @RequestParam(value = "prev") final String prev ){
        logger.info("Request previous page of currency interday list with id {}, fields {}, size {}, previous {}", currencyId, fields, size, prev);

        int prevPointer = controllerHelper.createPointer(prev);
        List<CurrencyRateInterday> currenciesOfToday = interdayService.listMostOlderCurrenciesFromPointer(currencyId, prevPointer, (size+1));

        if(controllerHelper.hasToReturnoFirstPage(size, currenciesOfToday)){
            return getCurrencyInterdayListFirstPage(currencyId, fields, size);
        }

        return  responseHelper.generateReturnPreviousPage(currenciesOfToday, fields, size);
    }
	
	/**
	 * Realiza a listagem de todas as moedas cadastradas
	 * 
	 * @param fields
	 * @return
	 */
	@GetMapping(path= "/currency/list")
    public @ResponseBody Object getCurrencyList(@RequestParam(value = "fields") final String fields){
    	
    	logger.info("Request currencies list fields {}", fields);
    	List<Currency> currencyList = (ArrayList<Currency>)currencyService.listCurrencies();
    	
    	return responseHelper.generateReturn(currencyList, fields);
    }
	

}
