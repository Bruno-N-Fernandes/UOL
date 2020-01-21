package br.com.uol.cotacoes.webrest.mappers;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.uol.cotacoes.core.model.entity.CurrencyRateIntraday;

/**
 * Realiza o mapeamento de um objeto do tipo {@link CurrencyRateIntraday}
 * 
 * Campos: id,name,bidvalue,askvalue,maxbid,minbid,variationbid,variationpercentbid,openbidvalue,date
 * 
 * @author mzp_dferraz
 *
 */
@Component
public class CurrencyIntradayMapper extends CurrencyHistoryMapper<CurrencyRateIntraday> {
	
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	/* (non-Javadoc)
	 * @see br.com.uol.cotacoes.webrest.mappers.ClassMapper#getMappedClass()
	 */
	@Override
	public Class<CurrencyRateIntraday> getMappedClass() {
		return CurrencyRateIntraday.class;
	}

	/* (non-Javadoc)
	 * @see br.com.uol.cotacoes.webrest.mappers.ClassMapper#generateMapFromEntity(java.lang.Object)
	 */
	@Override
	public Map<String, Object> generateMapFromEntity(CurrencyRateIntraday currency) {
		
		Map<String, Object> hashMap = super.generateMapFromEntity(currency);

		String date = currency.getDate().format(dateFormat);
		hashMap.put("date", date);
		
		return hashMap;
	}
	
}
