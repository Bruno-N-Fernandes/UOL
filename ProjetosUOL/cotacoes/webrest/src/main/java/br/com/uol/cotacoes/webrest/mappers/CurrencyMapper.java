package br.com.uol.cotacoes.webrest.mappers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import br.com.uol.cotacoes.core.model.entity.Country;
import br.com.uol.cotacoes.core.model.entity.Currency;

/**
 * 
 * Lista com todso sos campos
 * id,name,codinvestor,codunit,codconversion,converted,symbol,countries
 * @author mzp_dferraz
 *
 */
@Component
public class CurrencyMapper implements ClassMapper<Currency> { 
	
	@Override
	public Class<Currency> getMappedClass() {
		
		return Currency.class;
	}
	
	@Override
	public Map<String, Object> generateMapFromEntity(final Currency currency) {
		
		Map<String,Object> map = new HashMap<String,Object>(); 
		
		map.put("id", currency.getId());
		map.put("name", currency.getName());
		map.put("codinvestor", currency.getCodInvestor());
		map.put("codunit", currency.getCodUnit());
		map.put("codconversion", currency.getCodConversion());
		map.put("converted", currency.getConverted());
		map.put("symbol", "$");
		map.put("countries", toCountryArray(currency));
		
		return map;

	}

	private ArrayNode toCountryArray(final Currency currency) {
		
		final ArrayNode countries = JsonNodeFactory.instance.arrayNode();
		final Iterator<Country> iterator = currency.getCountries().iterator();
		while(iterator.hasNext())
		{
			final String name = iterator.next().getName();
			countries.add(name);
		}
		
		return countries;
	}
	
}



