package br.com.uol.cotacoes.core.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.uol.cotacoes.core.model.entity.Currency;
import br.com.uol.cotacoes.core.model.entity.view.ViewCurrency;
import br.com.uol.cotacoes.core.model.repository.CurrencyRepository;

@Service
public class CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;
	
	/**
	 * @return lista todas as moedas
	 */
	@Cacheable("CurrencyService-listCurrencies")
	public List<Currency> listCurrencies(){
		
		List<ViewCurrency> viewCurrencies = currencyRepository.findViewCurrency();
		
		return convertViewCurrency(viewCurrencies);
	}

	/**
	 * Converte uma lista de {@link ViewCurrency} para uma lista de {@link Currency}
	 * 
	 * @param viewCurrencies
	 * @return
	 */
	private List<Currency> convertViewCurrency(List<ViewCurrency> viewCurrencies) {
		HashMap<Integer, Currency> curenciesMap = new HashMap<>();
		
		for (ViewCurrency viewCurrency : viewCurrencies) {
			Currency currency = curenciesMap.get(viewCurrency.getId());
			if(currency == null){
				currency = viewCurrency.getCurrency();
				curenciesMap.put(viewCurrency.getId(), currency);
			}
			
			if(viewCurrency.hasCountry()){
				currency.addCountry(viewCurrency.getCountry());
			}
		}
		ArrayList<Currency> list = new ArrayList<Currency>(curenciesMap.values());
		list.sort((Currency c1, Currency c2) -> c1.getId().compareTo(c2.getId()));
		return list;
	}

	/**
	 * Verfica se o sistema está online e consultando o banco
	 */
	public boolean healthCheckWithDolar(){
		Currency findOne = currencyRepository.findOne(1);
		
		return findOne != null;
	}
	
	
}
