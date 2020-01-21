package br.com.uol.cotacoes.core.model.entity.view.chain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author mzp_dferraz
 *
 */
public interface ChainListService {
	

	final String INTRADAY = "/asset/intraday/list";
	final String INTERDAY_WEEK = "/asset/interday/list/week";
	final String INTERDAY_MONTH = "/asset/interday/list/month";
	final String INTERDAY_MONTHS = "/asset/interday/list/months";
	final String INTERDAY_YEAR = "/asset/interday/list/year";
	final String CONTINUE_CHAIN_WITHOUT_ADD = "";
	
	default List<String> start(LocalDate dateToVerify){
		
		List<String> services = new ArrayList<>();		
		
		return this.chain(dateToVerify, services);
	}
	
	default List<String> chain(LocalDate dateToVerify, List<String> services){
		
		String service = getService(dateToVerify);
		
		if(!service.equals(CONTINUE_CHAIN_WITHOUT_ADD))
			services.add(service);
		
		if(next() != null)
			next().chain(dateToVerify, services);
		
		return services;
	}
	
	String getService(LocalDate dateToVerify);
		
	ChainListService next();

}
