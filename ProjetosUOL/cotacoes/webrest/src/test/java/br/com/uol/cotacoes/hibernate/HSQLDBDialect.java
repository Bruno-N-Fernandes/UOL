package br.com.uol.cotacoes.hibernate;

import org.hibernate.dialect.HSQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.context.annotation.Profile;

/**
 * Extende o dialeto do HSQL para que possamos introduzir novas funções conforme a necessidade.
 * Principal motivo ocorre por que algumas funções utilizadas nas queries em MYSQL existem em HSQL com outro nome ou de forma um pocuo diferente
 * 
 * @author mzp_dferraz
 *
 */
@Profile("test")
public class HSQLDBDialect extends HSQLDialect {
	
	public HSQLDBDialect() {
		super();		
		
		registerFunction( "date_format", new StandardSQLFunction( "date_format", StandardBasicTypes.STRING ) );
		
	}

}
