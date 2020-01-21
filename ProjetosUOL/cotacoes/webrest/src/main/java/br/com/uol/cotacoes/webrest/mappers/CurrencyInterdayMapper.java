package br.com.uol.cotacoes.webrest.mappers;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.uol.cotacoes.core.model.entity.CurrencyRateInterday;

/**
 * Realiza o mapeamento de um objeto do tipo {@link CurrencyRateInterday}
 * 
 * Campos: id,name,bidvalue,askvalue,maxbid,minbid,variationbid,variationpercentbid,openbidvalue,date
 * 
 * @author mzp_dferraz
 *
 */
@Component
public class CurrencyInterdayMapper extends CurrencyHistoryMapper<CurrencyRateInterday> {
	
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd000000");

	/* (non-Javadoc)
	 * @see br.com.uol.cotacoes.webrest.mappers.ClassMapper#getMappedClass()
	 */
	@Override
	public Class<CurrencyRateInterday> getMappedClass() {
		return CurrencyRateInterday.class;
	}

	/* (non-Javadoc)
	 * @see br.com.uol.cotacoes.webrest.mappers.ClassMapper#generateMapFromEntity(java.lang.Object)
	 */
	@Override
	public Map<String, Object> generateMapFromEntity(CurrencyRateInterday currency) {
		
		Map<String, Object> hashMap = super.generateMapFromEntity(currency);
		
		String date = currency.getDate().format(dateFormat);
		hashMap.put("date", date);
		
		return hashMap;
	}
	
}
