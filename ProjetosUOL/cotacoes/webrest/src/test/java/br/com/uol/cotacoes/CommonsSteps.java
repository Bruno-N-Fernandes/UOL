package br.com.uol.cotacoes;

import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import org.dbunit.DatabaseUnitException;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
public class CommonsSteps extends  ApplicationTest{

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private ContextTest contextTest;
	
    @Before
    public void background(){ // Limpar todos os parâmetros do contexto
        contextTest.clear();
    }
    
    @Dado("^que enviamos o formato JSONP \"([^\"]*)\"$")
    public void que_enviamos_o_formato_JSONP(String arg1) throws Throwable {
    	contextTest.setParameterJsonp("jsonp="+arg1);
    }

    @Dado("^que enviamos o formato \"([^\"]*)\"$")
    public void que_enviamos_o_formato(String arg1) throws Throwable {
        contextTest.setParameterFormat("format="+arg1);
    }

    @Dado("^que enviamos a moeda de ID (\\d+)$")
    public void que_enviamos_a_moeda_de_ID(int arg1) {
        contextTest.setParemeterCurrency("currency=" + arg1);
    }

    @Dado("^que enviamos os campos: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\" e \"([^\"]*)\"$")
    public void que_enviamos_os_campos_e(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10) {
        StringBuilder fields = new StringBuilder("fields=")
                .append(arg1).append(",")
                .append(arg2).append(",")
                .append(arg3).append(",")
                .append(arg4).append(",")
                .append(arg5).append(",")
                .append(arg6).append(",")
                .append(arg7).append(",")
                .append(arg8).append(",")
                .append(arg9).append(",")
                .append(arg10);
        contextTest.setParemeterFields(fields.toString());
    }

    @Dado("^que enviamos o tamanho (\\d+)$")
    public void que_enviamos_o_tamanho(int arg1) {
        contextTest.setParemeterSize("size="+arg1);
    }

    @Dado("^que incluímos novos valores para essa moeda no mesmo dia$")
    public void que_incluímos_novos_valores_para_essa_moeda_no_mesmo_dia() throws SQLException, DatabaseUnitException {

        String insertTableSQL = "INSERT INTO currency_rate_intraday"
                + "(idt_currency_rate, idt_currency, num_value_bid, num_value_ask, num_max_bid, num_min_bid, num_var_bid, num_varpct_bid, num_open_bid, dat_currency_rate) VALUES"
                + "(?,?,?,?,?,?,?,?,?,?)";

        Connection jdbcConnection = SessionFactoryUtils.getDataSource( sessionFactory).getConnection();

        PreparedStatement preparedStatement = jdbcConnection.prepareStatement(insertTableSQL);
        preparedStatement.setInt(1, 20641153);
        preparedStatement.setInt(2, 18);
        preparedStatement.setDouble(3, 3.2333);
        preparedStatement.setDouble(4, 3.3222);
        preparedStatement.setDouble(5, 3.1111);
        preparedStatement.setDouble(6, 3.5555);
        preparedStatement.setDouble(7, 3.6666);
        preparedStatement.setDouble(8, 3.4444);
        preparedStatement.setDouble(9, 3.9999);
        preparedStatement.setTimestamp(10, Timestamp.valueOf(LocalDateTime.of(2017,6,2,11,0,0)));

        preparedStatement .executeUpdate();
        preparedStatement.close();
        jdbcConnection.close();
    }

    @Dado("^que incluímos novos valores para essa moeda para um dia mais recente$")
    public void que_incluímos_novos_valores_para_essa_moeda_para_um_dia_mais_recente() throws Throwable {
        String insertTableSQL = "INSERT INTO currency_rate_interday"
                + "(idt_currency_rate, idt_currency, num_value_bid, num_value_ask, num_max_bid, num_min_bid, num_var_bid, num_varpct_bid, num_open_bid, dat_currency_rate) VALUES"
                + "(?,?,?,?,?,?,?,?,?,?)";

        Connection jdbcConnection = SessionFactoryUtils.getDataSource( sessionFactory).getConnection();

        PreparedStatement preparedStatement = jdbcConnection.prepareStatement(insertTableSQL);
        preparedStatement.setInt(1, 21641154);
        preparedStatement.setInt(2, 18);
        preparedStatement.setDouble(3, 3.2333);
        preparedStatement.setDouble(4, 3.3222);
        preparedStatement.setDouble(5, 3.1111);
        preparedStatement.setDouble(6, 3.5555);
        preparedStatement.setDouble(7, 3.6666);
        preparedStatement.setDouble(8, 3.4444);
        preparedStatement.setDouble(9, 3.9999);
        preparedStatement.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0)));

        preparedStatement .executeUpdate();
        preparedStatement.close();
        jdbcConnection.close();
    }

    @Dado("^que enviamos o item (\\d+)$")
    public void que_enviamos_o_item(int arg1) throws Throwable {
        contextTest.setParameterItem("item="+arg1);
    }

    @Dado("^que enviamos os campos: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
    public void que_enviamos_os_campos_e(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12) throws Throwable {
        StringBuilder fields = new StringBuilder("fields=")
                .append(arg1).append(",")
                .append(arg2).append(",")
                .append(arg3).append(",")
                .append(arg4).append(",")
                .append(arg5).append(",")
                .append(arg6).append(",")
                .append(arg7).append(",")
                .append(arg8).append(",")
                .append(arg9).append(",")
                .append(arg10).append(",")
                .append(arg11).append(",")
                .append(arg12);
        contextTest.setParemeterFields(fields.toString());
    }

    @Dado("^que enviamos o ponteiro prev com valor \"([^\"]*)\"")
    public void que_enviamos_o_ponteiro_prev_com_valor(String arg1) throws Throwable {        
        contextTest.setParameterPrev("prev="+contextTest.getParameterPrev());
    }

    @Dado("^que enviamos o ponteiro next com valor \"([^\"]*)\"")
    public void que_enviamos_o_ponteiro_next_com_valor(String arg1) throws Throwable {
        contextTest.setParameterNext("next="+contextTest.getParameterNext());
    }
    
    @Dado("^que enviamos o ponteiro prev com valor fixo \"([^\"]*)\"")
    public void que_enviamos_o_ponteiro_prev_com_valor_fixo(String arg1) throws Throwable {        
        contextTest.setParameterPrev("prev="+arg1);
    }

    @Dado("^que enviamos o ponteiro next com valor fixo \"([^\"]*)\"")
    public void que_enviamos_o_ponteiro_next_com_valor_fixo(String arg1) throws Throwable {
        contextTest.setParameterNext("next="+arg1);
    }

    @Entao("^deve exibir mensagem de erro com status \"([^\"]*)\" e causa \"(.*)\"$")
    public void deve_exibir_mensagem_de_erro_com_status_e_causa(String arg1, String arg2) throws Throwable {

        String result = contextTest.getResultActions()
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"HttpStatus\":" + arg1 + ",\"Timestamp\":\"";
        int sizeExpected = expected.length();

        assertEquals(expected, result.substring(0, sizeExpected));

        int timeStampEndIndex = sizeExpected+14;
        String timeStamp = result.substring(sizeExpected, timeStampEndIndex);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        assertEquals(LocalDate.now(), LocalDateTime.parse(timeStamp, dateFormat).toLocalDate()); // verifica se esta no formato correto e verifica que o dia em que aconteceu o erro eh hoje

        assertTrue(result.contains("\",\"Cause\":\""));
        for (String expectedArg : arg2.split("\\\"")) {
        	assertTrue(result.contains(expectedArg));	
		}
    }
    
    @Dado("^mantemos o valor do next gerado para a proxima pagina$")
    public void mantemos_o_valor_do_next_gerado_para_a_proxima_pagina() throws Throwable {
    	String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(result.replace("/**/list(", "").replace(");", ""));
        contextTest.setParameterNext(jsonObject.get("next").toString());
    }
    
    @Dado("^mantemos o valor do prev gerado para a pagina anterior$")
    public void mantemos_o_valor_do_prev_gerado_para_a_pagina_anterior() throws Throwable {
    	String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(result.replace("/**/list(", "").replace(");", ""));
        contextTest.setParameterPrev(jsonObject.get("prev").toString());
    }

    //inserir novo registro interday para bolsa de valores
    @Dado("^que incluimos novos valores para o item (\\d+) para um dia mais recente$")
    public void que_incluimos_novos_valores_para_o_item_para_um_dia_mais_recente(int arg1) throws Throwable {
        String insertTableSQL = "insert into asset_interday ("
                + " idt_asset_interday, num_price, idt_exchange_asset, num_high, num_low, num_open, num_volume, num_close, num_bid, num_ask, num_change, num_pct_change,dat_interday)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection jdbcConnection = SessionFactoryUtils.getDataSource( sessionFactory).getConnection();

        PreparedStatement preparedStatement = jdbcConnection.prepareStatement(insertTableSQL);
        preparedStatement.setInt(1, 123456790); // idt_asset_interday
        preparedStatement.setDouble(2, 5.55);// num_price
        preparedStatement.setInt(3, arg1);// idt_exchange_asset
        preparedStatement.setDouble(4, 5.66); //num_high
        preparedStatement.setDouble(5, 5.77);// num_low
        preparedStatement.setDouble(6, 5.88);// num_open
        preparedStatement.setDouble(7, 100.0);// num_volume
        preparedStatement.setDouble(8, 5.99);// num_close
        preparedStatement.setDouble(9, 5.56);// num_bid
        preparedStatement.setDouble(10, 5.67);// num_ask
        preparedStatement.setDouble(11, -0.05);// num_change
        preparedStatement.setDouble(12, -1.55);// num_pct_change
        preparedStatement.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0)));// dat_interday

        preparedStatement .executeUpdate();
        preparedStatement.close();
        jdbcConnection.close();
    }
    
}
