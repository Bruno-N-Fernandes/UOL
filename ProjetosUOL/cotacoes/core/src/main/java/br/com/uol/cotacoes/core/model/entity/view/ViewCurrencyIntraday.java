package br.com.uol.cotacoes.core.model.entity.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.uol.cotacoes.core.model.entity.Currency;
import br.com.uol.cotacoes.core.model.entity.CurrencyRateIntraday;
import lombok.Getter;

public class ViewCurrencyIntraday {
	
	@Getter
	private CurrencyRateIntraday intraday;


	public ViewCurrencyIntraday(Integer id, String currencyName, String codInvestor, BigDecimal bidValue, BigDecimal askValue, BigDecimal maxBid, BigDecimal minBid, BigDecimal variationBid,
								BigDecimal variationPercentBid, BigDecimal openBidValue, LocalDateTime date){
		intraday = new CurrencyRateIntraday();

		intraday.setId(id);
		intraday.setBidValue(bidValue);
		intraday.setAskValue(askValue);
		intraday.setMaxBid(maxBid);
		intraday.setMinBid(minBid);
		intraday.setVariationBid(variationBid);
		intraday.setVariationPercentBid(variationPercentBid);
		intraday.setOpenBidValue(openBidValue);
		intraday.setDate(date);

		Currency currency = new Currency();
		currency.setName(currencyName);
		currency.setCodInvestor(codInvestor);
		intraday.setCurrency(currency);
	}
}
