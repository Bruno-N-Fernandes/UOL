package br.com.uol.cotacoes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import br.com.uol.cotacoes.hibernate.HSQLDBDataTypeFactory;

/**
 * Created by vrx_mtoledo on 24/04/17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {Application.class})
@ContextConfiguration(loader = SpringBootContextLoader.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApplicationTest {

    @Autowired
    SessionFactory sessionFactory;


    /**
     * Inicializa Banco de dados do DbUnit
     * Carrega xml e substitue valores necessarios
     * @throws DatabaseUnitException
     * @throws SQLException
     */
    @PostConstruct
    public void init() throws DatabaseUnitException, SQLException {
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/databaseTest.xml");

        ReplacementDataSet replaceDataSet = new ReplacementDataSet(dataSet);

        replaceDataSet.addReplacementObject("yes", "true");
        replaceDataSet.addReplacementObject("no", "false");

        // substitue as data para o testes de Interday
        LocalDateTime latest = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDate latestDate = LocalDate.now();
        //today
        replaceDataSet.addReplacementObject("today", Timestamp.valueOf( latest) );
        // last week
        replaceDataSet.addReplacementObject("dateReplace0", Date.valueOf( latestDate.minusDays(2)) );
        replaceDataSet.addReplacementObject("dateReplace1", Date.valueOf( latestDate.minusDays(7)) );
        // last month
        replaceDataSet.addReplacementObject("dateReplace2", Date.valueOf( latestDate.minusDays(8)) );
        replaceDataSet.addReplacementObject("dateReplace3", Date.valueOf( latestDate.minusMonths(1)) );
        // last 3 months
        replaceDataSet.addReplacementObject("dateReplace4", Date.valueOf( latestDate.minusMonths(1).minusDays(1)) );
        replaceDataSet.addReplacementObject("dateReplace5", Date.valueOf( latestDate.minusMonths(3)) );
        // last year
        replaceDataSet.addReplacementObject("dateReplace6", Date.valueOf( latestDate.minusMonths(3).minusDays(1)) );
        replaceDataSet.addReplacementObject("dateReplace7", Date.valueOf( latestDate.minusYears(1)) );
        // more than a year
        replaceDataSet.addReplacementObject("dateReplace8", Date.valueOf( latestDate.minusYears(1).minusDays(1)) );
        replaceDataSet.addReplacementObject("dateReplace9", Date.valueOf( latestDate.minusYears(1).minusDays(1)) );

        // substitue as data para o testes de Intraday
        LocalDateTime todayHour10 = LocalDateTime.now().withHour(10).withMinute(0).withSecond(0);
        LocalDateTime todayHour11 = LocalDateTime.now().withHour(11).withMinute(0).withSecond(0);
        replaceDataSet.addReplacementObject("dateTimeReplace0", Timestamp.valueOf( todayHour11) );
        replaceDataSet.addReplacementObject("dateTimeReplace1", Timestamp.valueOf( todayHour10) );

        try(Connection jdbcConnection = SessionFactoryUtils.getDataSource( sessionFactory).getConnection()){
	        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
	        connection.getConfig().setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, Boolean.TRUE);// config para permitir entidade com colunas vazias
	        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HSQLDBDataTypeFactory());
	
	        DatabaseOperation.CLEAN_INSERT.execute(connection, replaceDataSet);
        }
    }

}