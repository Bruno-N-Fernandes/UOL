package br.com.uol.cotacoes.core.model.entity.view;

import br.com.uol.cotacoes.core.model.entity.Country;
import br.com.uol.cotacoes.core.model.entity.Currency;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Classe criada para otimização no carregamento de um {@link Currency}.
 * Ela é a representação dos dados de uma consulta
 * 
 * @author mzp_dferraz
 *
 */
public class ViewCurrency {
    
    @Getter
    private Currency currency;
    
    @Getter
    private Country country;
    
    public Integer getId(){
    	return currency.getId();
    }
    
    public Boolean hasCountry(){
    	return country.getId() != null;
    }


	public ViewCurrency(Integer id, String name, String codInvestor, String codUnit, String codConversion,
						Boolean converted, Integer countryId, String countryName) {
		super();

		currency = new Currency();
		currency.setId(id);
		currency.setName(name);
		currency.setCodInvestor(codInvestor);
		currency.setCodConversion(codConversion);
		currency.setCodUnit(codUnit);
		currency.setConverted(converted);
		currency.setCountries(new ArrayList<>());

		country = new Country();
		country.setId(countryId);
		country.setName(countryName);
	}

}
