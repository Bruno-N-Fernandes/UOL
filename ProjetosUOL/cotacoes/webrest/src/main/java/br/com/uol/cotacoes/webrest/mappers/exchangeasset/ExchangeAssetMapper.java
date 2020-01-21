package br.com.uol.cotacoes.webrest.mappers.exchangeasset;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import br.com.uol.cotacoes.core.model.entity.Company;
import br.com.uol.cotacoes.core.model.entity.ExchangeAsset;
import br.com.uol.cotacoes.webrest.mappers.ClassMapper;

/**
 * Campos id,name,abbreviation,type,companies,exchange,services,active
 * @author mzp_dferraz
 *
 * @param <E>
 */
public abstract class ExchangeAssetMapper<E extends ExchangeAsset> implements ClassMapper<E>  {
	
	public Map<String, Object> generateMapFromEntity(final E exchangeAsset) {
		
		Map<String,Object> map = new HashMap<>();
		
		map.put("id", exchangeAsset.getId());
		map.put("name", exchangeAsset.getName());
		map.put("abbreviation", exchangeAsset.getAbbreviation());
		map.put("type", exchangeAsset.getType());
		map.put("companies", toCompanyArray(exchangeAsset));
		map.put("exchange", exchangeAsset.getExchange().getName());
		if(exchangeAsset.getServicesList() != null){
			map.put("services", toServicesArray(exchangeAsset));
		}

		return map;
	}

	private ArrayNode toCompanyArray(final E exchangeAsset) {

		final ArrayNode companies = JsonNodeFactory.instance.arrayNode();
		final Iterator<Company> iterator = exchangeAsset.getCompanies().iterator();
		while(iterator.hasNext())
		{
			final String name = iterator.next().getName();
			companies.add(name);
		}

		return companies;
	}
	
	private ArrayNode toServicesArray(final E exchangeAsset) {

		final ArrayNode companies = JsonNodeFactory.instance.arrayNode();		
		final Iterator<String> iterator = exchangeAsset.getServicesList().iterator();
		while(iterator.hasNext())
		{
			final String name = iterator.next();
			companies.add(name);
		}
		return companies;
	}
	
}



