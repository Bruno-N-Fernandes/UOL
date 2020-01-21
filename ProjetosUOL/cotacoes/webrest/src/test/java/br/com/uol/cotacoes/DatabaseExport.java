package br.com.uol.cotacoes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.context.annotation.Profile;

import com.mysql.jdbc.Connection;

@Profile("test")
public class DatabaseExport {
	
		String assetIds = "10,11,12,13,18,232,233,645,699,1163,1164,1165,1166,1167,1168,1169,1170,1171,1172,1173,1174,1175,1176";
		
		public void init() throws ClassNotFoundException, SQLException, DatabaseUnitException, FileNotFoundException, IOException{
			
			Class driverClass = Class.forName("com.mysql.jdbc.Driver");
	        Connection jdbcConnection = (Connection) DriverManager.getConnection("jdbc:mysql://a1-oderzo-q-prt1.host.intranet:3306/finance_adm", "financeubr", "def39d01c723f454c065");
	        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

	        QueryDataSet partialDataSet = new QueryDataSet(connection);
	        partialDataSet.addTable("currency", "select idt_currency, nam_currency, cod_investor, cod_unit, cod_conversion, ind_currency_converted  from currency where idt_currency in (4,5)");
	        partialDataSet.addTable("country", "select cod_iso_country, nam_Country , nam_country_english, abv2_iso_country, abv3_iso_country , flg_postal_base, num_ddI_country from country where cod_iso_country in (196,246,300,372,442,470,492,528,620,703,705,724,895)");
	        partialDataSet.addTable("currency_country", "select idt_currency, Cod_Iso_Country from currency_country where idt_currency in (4,5)");
	        partialDataSet.addTable("exchange", "select idt_exchange, nam_exchange, abv_exchange from exchange where idt_exchange in (select idt_exchange from exchange_asset where idt_exchange_asset in (".concat(assetIds).concat("))"));
			partialDataSet.addTable("exchange_asset", "select idt_exchange_asset,idt_exchange,nam_exchange_asset,abv_exchange_asset,ind_asset_type from exchange_asset where idt_exchange_asset in (".concat(assetIds).concat(")") );
			partialDataSet.addTable("company", "select * from company where idt_company in (select idt_company from stock_asset where idt_exchange_asset in (".concat(assetIds).concat("))") );
			partialDataSet.addTable("stock_asset", "select * from stock_asset where idt_exchange_asset in (".concat(assetIds).concat(")") );
			partialDataSet.addTable("asset_intraday", "select idt_asset_intraday, num_price, idt_exchange_asset, num_high, num_low, num_open, num_volume, num_close, num_bid, num_ask, num_change, num_pct_change, dat_last_update from asset_intraday where idt_exchange_asset in (".concat(assetIds).concat(")") );
			partialDataSet.addTable("asset_interday", "select idt_asset_interday, num_price, idt_exchange_asset, num_high, num_low, num_open, num_volume, num_close, num_bid, num_ask, num_change, num_pct_change, dat_interday from asset_interday where idt_exchange_asset in (".concat(assetIds).concat(")") );

			FlatXmlDataSet.write(partialDataSet, new FileOutputStream(new StringBuilder(System.getProperty("user.dir")).append("/webrest/src/test/resources/partial.xml").toString()));
			connection.close();
			jdbcConnection.close();
		}
		
		public static void main(String[] args) {
			try {
				new DatabaseExport().init();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	
}
