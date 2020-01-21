package br.com.uol.cotacoes.hibernate;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.stereotype.Component;

/**
 * Cria a funções no banco de dados no escopo de teste
 * 
 * @author mzp_dferraz
 *
 */
@Component
@Profile("test")
public class DatabaseFunctionCreator {	
	
	/**
	 * Foi utilizado o método set ou inves da interface {@link ApplicationListener}, por que a interface é chamda várias vezes e isso gera
	 * problemas nas chamadas do banco de dados, alem de na primeira vez em que é chamada ela ainda não tem todos os beans injetados.
	 * para evitar estes problemas foi utilizado o set que é chamado apenas uma vez.
	 * 
	 * @param sessionFactory
	 * @throws SQLException 
	 * @throws Exception
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) throws SQLException{		
		try(Connection connection = SessionFactoryUtils.getDataSource( sessionFactory).getConnection()){			
			createFunction(connection);			
			connection.commit();
		}
	}

	/**
	 * Cria as funções no banco de dados
	 * @param connection
	 * @throws SQLException
	 */
	private void createFunction(Connection connection) throws SQLException {
		connection.createStatement().execute("CREATE FUNCTION date_format (t TIMESTAMP, teste varchar(10) )   RETURNS TIMESTAMP   RETURN TIMESTAMP(TO_CHAR(t, 'YYYY-MM-DD'))");
	}

}