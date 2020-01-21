package br.com.uol.cotacoes.webrest.mappers;

import java.util.HashMap;
import java.util.Map;

import br.com.uol.cotacoes.core.model.entity.CurrencyRateHistory;

/**
 * Classe abstrata do mapeamento de moedas
 * Campos:
 * id,name,bidvalue,askvalue,maxbid,minbid,variationbid,variationpercentbid,openbidvalue,date,relativepercent
 * 
 * @author mzp_dferraz
 *
 * @param <E>
 */
public abstract class CurrencyHistoryMapper<E extends CurrencyRateHistory> implements ClassMapper<E> {
	
	/* (non-Javadoc)
	 * @see br.com.uol.cotacoes.webrest.mappers.ClassMapper#generateMapFromEntity(java.lang.Object)
	 */
	@Override
	public Map<String, Object> generateMapFromEntity(E currency) {
		
		HashMap<String, Object> hashMap = new HashMap<>();
		
		hashMap.put("id", currency.getId());
		hashMap.put("name", currency.getCurrency().getName());
		hashMap.put("codinvestor", currency.getCurrency().getCodInvestor());
		hashMap.put("bidvalue", currency.getBidValue());
		hashMap.put("askvalue", currency.getAskValue());
		hashMap.put("maxbid", currency.getMaxBid());
		hashMap.put("minbid", currency.getMinBid());
		hashMap.put("variationbid", currency.getVariationBid());
		hashMap.put("variationpercentbid", currency.getVariationPercentBid());
		hashMap.put("openbidvalue", currency.getOpenBidValue());
		hashMap.put("relativepercent", currency.getRelativePercentual());
		
		return hashMap;
	}
	
}
