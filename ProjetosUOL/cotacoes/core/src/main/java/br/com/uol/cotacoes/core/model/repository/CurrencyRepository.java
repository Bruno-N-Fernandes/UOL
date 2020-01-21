package br.com.uol.cotacoes.core.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.uol.cotacoes.core.model.entity.Currency;
import br.com.uol.cotacoes.core.model.entity.view.ViewCurrency;

public interface CurrencyRepository extends CrudRepository<Currency, Integer>{

	/**
	 * Lista todas as moedas. 
	 * Este método foi criado para melhoria de performance do carregamento de moedas.
	 * Ele carrega os dados da moeda em um ViewObject com uma query mais otimizada para a necessidade.
	 * 
	 * @return
	 */
	@Query("select new br.com.uol.cotacoes.core.model.entity.view.ViewCurrency(currency.id, currency.name,currency.codInvestor, "
			+ " currency.codUnit,currency.codConversion,currency.converted,country.id, country.name ) "
			+ " from br.com.uol.cotacoes.core.model.entity.Currency currency "
			+ " left join currency.countries country " 			
			+ " order by currency.id, country.id")
	List<ViewCurrency> findViewCurrency();
	
		
}
