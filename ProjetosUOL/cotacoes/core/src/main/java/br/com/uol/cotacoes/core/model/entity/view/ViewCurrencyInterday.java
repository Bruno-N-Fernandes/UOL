package br.com.uol.cotacoes.core.model.entity.view;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.uol.cotacoes.core.model.entity.Currency;
import br.com.uol.cotacoes.core.model.entity.CurrencyRateInterday;
import lombok.Getter;

public class ViewCurrencyInterday {
	
	@Getter
	private CurrencyRateInterday interday;

	public ViewCurrencyInterday(Integer id, String currencyName, String codInvestor, BigDecimal bidValue, BigDecimal askValue, BigDecimal maxBid, BigDecimal minBid, BigDecimal variationBid,
								BigDecimal variationPercentBid, BigDecimal openBidValue, LocalDate date){
		interday = new CurrencyRateInterday();

		interday.setId(id);
		interday.setBidValue(bidValue);
		interday.setAskValue(askValue);
		interday.setMaxBid(maxBid);
		interday.setMinBid(minBid);
		interday.setVariationBid(variationBid);
		interday.setVariationPercentBid(variationPercentBid);
		interday.setOpenBidValue(openBidValue);
		interday.setDate(date);

		Currency currency = new Currency();
		currency.setName(currencyName);
		currency.setCodInvestor(codInvestor);
		interday.setCurrency(currency);
	}
}
