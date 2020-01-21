package br.com.uol.cotacoes.acoes;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vrx_mtoledo on 06/07/17.
 */
@Profile("test")
public class ResumoMultiploSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;

    @Dado("^que enviamos as moedas: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void que_enviamos_as_moedas(String arg1, String arg2, String arg3) throws Throwable {
        contextTest.setParameterCurrencies("currencies=" + arg1 + "," + arg2 + "," + arg3);    }

    @Quando("^processamos a solicitação do resumo multiplo$")
    public void processamos_a_solicitação_do_resumo_multiplo() throws Throwable {
        StringBuilder url = new StringBuilder("/mixed/summary?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParameterCurrencies())
                .append("&")
                .append(contextTest.getParameterItens());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON das moedas enviadas do resumo multiplo$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_das_moedas_enviadas_do_resumo_multiplo() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON das moedas enviadas do resumo multiplo$")
    public void deve_exibir_um_JSON_das_moedas_enviadas_do_resumo_multiplo() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON das moedas enviadas sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do resumo multiplo$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_das_moedas_enviadas_sem_o_campo_campo_deve_ser_ignorado_do_resumo_multiplo(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0},{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON das moedas enviadas sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do resumo multiplo$")
    public void deve_exibir_um_JSON_das_moedas_enviadas_sem_o_campo_campo_deve_ser_ignorado_do_resumo_multiplo(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0},{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0}]}";
        assertEquals(expected, result);
    }

    @Dado("^que incluímos novos valores para essas moedas no mesmo dia$")
    public void que_incluímos_novos_valores_para_essas_moedas_no_mesmo_dia() throws Throwable {
        String insertTableSQL = "INSERT INTO currency_rate_intraday"
                + "(idt_currency_rate, idt_currency, num_value_bid, num_value_ask, num_max_bid, num_min_bid, num_var_bid, num_varpct_bid, num_open_bid, dat_currency_rate) VALUES"
                + "(?,?,?,?,?,?,?,?,?,?)";

        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 2, 11, 0, 0);

        Connection jdbcConnection = SessionFactoryUtils.getDataSource( sessionFactory).getConnection();

        PreparedStatement primeiraMoeda = jdbcConnection.prepareStatement(insertTableSQL);
        primeiraMoeda.setInt(1, 20641301);
        primeiraMoeda.setInt(2, 4);
        primeiraMoeda.setDouble(3, 5.2222);
        primeiraMoeda.setDouble(4, 5.2222);
        primeiraMoeda.setDouble(5, 5.2222);
        primeiraMoeda.setDouble(6, 5.2222);
        primeiraMoeda.setDouble(7, 0.0008);
        primeiraMoeda.setDouble(8, 0.044);
        primeiraMoeda.setDouble(9, 0.0000);
        primeiraMoeda.setTimestamp(10, Timestamp.valueOf(dateTime));

        primeiraMoeda .executeUpdate();
        primeiraMoeda.close();

        PreparedStatement segundaMoeda = jdbcConnection.prepareStatement(insertTableSQL);
        segundaMoeda.setInt(1, 20641201);
        segundaMoeda.setInt(2, 5);
        segundaMoeda.setDouble(3, 4.2222);
        segundaMoeda.setDouble(4, 4.2222);
        segundaMoeda.setDouble(5, 4.2222);
        segundaMoeda.setDouble(6, 4.2222);
        segundaMoeda.setDouble(7, 0.0008);
        segundaMoeda.setDouble(8, 0.033);
        segundaMoeda.setDouble(9, 0.0000);
        segundaMoeda.setTimestamp(10, Timestamp.valueOf(dateTime));

        segundaMoeda .executeUpdate();
        segundaMoeda.close();

        PreparedStatement terceiraMoeda = jdbcConnection.prepareStatement(insertTableSQL);
        terceiraMoeda.setInt(1, 20641153);
        terceiraMoeda.setInt(2, 18);
        terceiraMoeda.setDouble(3, 3.2333);
        terceiraMoeda.setDouble(4, 3.3222);
        terceiraMoeda.setDouble(5, 3.1111);
        terceiraMoeda.setDouble(6, 3.5555);
        terceiraMoeda.setDouble(7, 3.6666);
        terceiraMoeda.setDouble(8, 3.4444);
        terceiraMoeda.setDouble(9, 3.9999);
        terceiraMoeda.setTimestamp(10, Timestamp.valueOf(dateTime));

        terceiraMoeda .executeUpdate();
        terceiraMoeda.close();
        jdbcConnection.close();
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON das moedas atualizadas do resumo multiplo$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_das_moedas_atualizadas_do_resumo_multiplo() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641301,\"name\":\"Euro\",\"bidvalue\":5.2222,\"askvalue\":5.2222,\"maxbid\":5.2222,\"minbid\":5.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602110000\"},{\"id\":20641201,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602110000\"},{\"id\":20641153,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\"20170602110000\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON das moedas atualizadas do resumo multiplo$")
    public void deve_exibir_um_JSON_das_moedas_atualizadas_do_resumo_multiplo() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641301,\"name\":\"Euro\",\"bidvalue\":5.2222,\"askvalue\":5.2222,\"maxbid\":5.2222,\"minbid\":5.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602110000\"},{\"id\":20641201,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602110000\"},{\"id\":20641153,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\"20170602110000\"}]}";
        assertEquals( expected, result);
    }

    @Dado("^entao deve exibir uma lista no formato JSONP com as moedas na mesma ordem de solicitação: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void entao_deve_exibir_uma_lista_no_formato_JSONP_com_as_moedas_na_mesma_ordem_de_solicitação(String arg1, String arg2, String arg3) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista no formato JSONP com as moedas na mesma ordem de solicitação: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void deve_exibir_uma_lista_no_formato_JSONP_com_as_moedas_na_mesma_ordem_de_solicitação(String arg1, String arg2, String arg3) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602105000\"}]});";
        assertEquals( expected, result);
    }

    @Dado("^entao deve exibir uma lista no formato JSON com as moedas na mesma ordem de solicitação: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void entao_deve_exibir_uma_lista_no_formato_JSON_com_as_moedas_na_mesma_ordem_de_solicitação(String arg1, String arg2, String arg3) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista no formato JSON com as moedas na mesma ordem de solicitação: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void deve_exibir_uma_lista_no_formato_JSON_com_as_moedas_na_mesma_ordem_de_solicitação(String arg1, String arg2, String arg3) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602105000\"}]}";
        assertEquals( expected, result);
    }

    @Dado("^que enviamos os itens: \"([^\"]*)\",\"([^\"]*)\"$")
    public void que_enviamos_os_itens(String arg1, String arg2) throws Throwable {
        contextTest.setParameterItens("itens=" + arg1 + "," + arg2);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos dos itens enviados do resumo multiplo$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_dos_itens_enviados_do_resumo_multiplo() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"id\":161122130,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos dos itens enviados do resumo multiplo$")
    public void deve_exibir_um_JSON_dos_campos_dos_itens_enviados_do_resumo_multiplo() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"id\":161122130,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos dos intens enviados sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do resumo multiplo$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_dos_intens_enviados_sem_o_campo_campo_deve_ser_ignorado_do_resumo_multiplo(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"id\":161122130,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";
        assertEquals( expected, result);
    }

    @Dado("^que incluímos novos valores para esses itens no mesmo dia$")
    public void que_incluímos_novos_valores_para_esses_itens_no_mesmo_dia() throws Throwable {
        String insertTableSQL = "insert into asset_intraday ("
                + " idt_asset_intraday, num_price, idt_exchange_asset, num_high, num_low, num_open, num_volume, num_close, num_bid, num_ask, num_change, num_pct_change,dat_last_update)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 13, 18, 0, 0);

        Connection jdbcConnection = SessionFactoryUtils.getDataSource( sessionFactory).getConnection();

        PreparedStatement primeiro = jdbcConnection.prepareStatement(insertTableSQL);
        primeiro.setInt(1, 161121868);
        primeiro.setDouble(2, 1.23);
        primeiro.setInt(3, 232);
        primeiro.setDouble(4, 3.21);
        primeiro.setDouble(5, 5.6);
        primeiro.setDouble(6, 5.6);
        primeiro.setDouble(7, 900.1);
        primeiro.setDouble(8, 5.7);
        primeiro.setDouble(9, 5.15);
        primeiro.setDouble(10, 5.65);
        primeiro.setDouble(11, -0.1);
        primeiro.setDouble(12, -3.00);
        primeiro.setTimestamp(13, Timestamp.valueOf(dateTime));

        primeiro .executeUpdate();
        primeiro.close();

        PreparedStatement segundo = jdbcConnection.prepareStatement(insertTableSQL);
        segundo.setInt(1, 161122131);
        segundo.setDouble(2, 9.87);
        segundo.setInt(3, 10);
        segundo.setDouble(4, 7.89);
        segundo.setDouble(5, 5.6);
        segundo.setDouble(6, 5.6);
        segundo.setDouble(7, 900.1);
        segundo.setDouble(8, 5.7);
        segundo.setDouble(9, 5.15);
        segundo.setDouble(10, 5.65);
        segundo.setDouble(11, -0.1);
        segundo.setDouble(12, -3.00);
        segundo.setTimestamp(13, Timestamp.valueOf(dateTime));

        segundo .executeUpdate();
        segundo.close();
        jdbcConnection.close();
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos dos itens atualizados do resumo multiplo$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_dos_itens_atualizados_do_resumo_multiplo() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121868,\"price\":1.23,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":3.21,\"low\":5.6,\"open\":5.6,\"volume\":900.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3},{\"id\":161122131,\"price\":9.87,\"exchangeasset\":\"INDX IND\",\"high\":7.89,\"low\":5.6,\"open\":5.6,\"volume\":900.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3}]});";
        assertEquals( expected, result);
    }

    @Dado("^entao deve exibir uma lista no formato JSONP com os itens na mesma ordem de solicitação: \"([^\"]*)\", \"([^\"]*)\"$")
    public void entao_deve_exibir_uma_lista_no_formato_JSONP_com_os_itens_na_mesma_ordem_de_solicitação(String arg1, String arg2) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"id\":161122130,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista no formato JSONP com os itens na mesma ordem de solicitação: \"([^\"]*)\", \"([^\"]*)\"$")
    public void deve_exibir_uma_lista_no_formato_JSONP_com_os_itens_na_mesma_ordem_de_solicitação(String arg1, String arg2) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":161122130,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos dos itens enviados sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do resumo multiplo$")
    public void deve_exibir_um_JSON_dos_campos_dos_itens_enviados_sem_o_campo_campo_deve_ser_ignorado_do_resumo_multiplo(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"id\":161122130,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos dos itens atualizados do resumo multiplo$")
    public void deve_exibir_um_JSON_dos_campos_dos_itens_atualizados_do_resumo_multiplo() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121868,\"price\":1.23,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":3.21,\"low\":5.6,\"open\":5.6,\"volume\":900.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3},{\"id\":161122131,\"price\":9.87,\"exchangeasset\":\"INDX IND\",\"high\":7.89,\"low\":5.6,\"open\":5.6,\"volume\":900.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3}]}";
        assertEquals( expected, result);
    }

    @Dado("^entao deve exibir uma lista no formato JSON com os itens na mesma ordem de solicitação: \"([^\"]*)\", \"([^\"]*)\"$")
    public void entao_deve_exibir_uma_lista_no_formato_JSON_com_os_itens_na_mesma_ordem_de_solicitação(String arg1, String arg2) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"id\":161122130,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista no formato JSON com os itens na mesma ordem de solicitação: \"([^\"]*)\", \"([^\"]*)\"$")
    public void deve_exibir_uma_lista_no_formato_JSON_com_os_itens_na_mesma_ordem_de_solicitação(String arg1, String arg2) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":161122130,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35}]}";
        assertEquals( expected, result);
    }

    @Dado("^que enviamos os campos: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
    public void que_enviamos_os_campos_e(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12, String arg13, String arg14, String arg15, String arg16, String arg17, String arg18, String arg19, String arg20, String arg21) throws Throwable {
        contextTest.setParemeterFields("fields="+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+","+arg11+","+arg12+","+arg13+","+arg14+","+arg15+","+arg16+","+arg17+","+arg18+","+arg19+","+arg20+","+arg21);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados do resumo multiplo$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_do_resumo_multiplo() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":161121867,\"date\":\"20170613170500\",\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"id\":161122130,\"date\":\"20170613172100\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados do resumo multiplo$")
    public void deve_exibir_um_JSON_dos_campos_enviados_do_resumo_multiplo() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":161121867,\"date\":\"20170613170500\",\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"id\":161122130,\"date\":\"20170613172100\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do resumo multiplo$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_campo_deve_ser_ignorado_do_resumo_multiplo(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0},{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0},{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"id\":161122130,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";
        assertEquals( expected, result);
    }

    @Dado("^que incluímos novos valores para essas moedas e indices no mesmo dia$")
    public void que_incluímos_novos_valores_para_essas_moedas_e_indices_no_mesmo_dia() throws Throwable {
        que_incluímos_novos_valores_para_essas_moedas_no_mesmo_dia();
        que_incluímos_novos_valores_para_esses_itens_no_mesmo_dia();
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos atualizados do resumo multiplo$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_atualizados_do_resumo_multiplo() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641301,\"name\":\"Euro\",\"bidvalue\":5.2222,\"askvalue\":5.2222,\"maxbid\":5.2222,\"minbid\":5.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602110000\"},{\"id\":20641201,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602110000\"},{\"id\":20641153,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\"20170602110000\"},{\"id\":161121868,\"date\":\"20170613180000\",\"price\":1.23,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":3.21,\"low\":5.6,\"open\":5.6,\"volume\":900.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3},{\"id\":161122131,\"date\":\"20170613180000\",\"price\":9.87,\"exchangeasset\":\"INDX IND\",\"high\":7.89,\"low\":5.6,\"open\":5.6,\"volume\":900.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3}]});";
        assertEquals( expected, result);
    }

    @Dado("^entao deve exibir uma lista no formato JSONP com as moedas na mesma ordem de solicitação: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e depois os itens na mesma ordem de solicitação: \"([^\"]*)\", \"([^\"]*)\"$")
    public void entao_deve_exibir_uma_lista_no_formato_JSONP_com_as_moedas_na_mesma_ordem_de_solicitação_e_depois_os_itens_na_mesma_ordem_de_solicitação(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":161121867,\"date\":\"20170613170500\",\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"id\":161122130,\"date\":\"20170613172100\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista no formato JSONP com as moedas e os itens na mesma ordem de solicitação:\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\", \"([^\"]*)\"$")
    public void deve_exibir_uma_lista_no_formato_JSONP_com_as_moedas_e_os_itens_na_mesma_ordem_de_solicitação(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":161122130,\"date\":\"20170613172100\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"id\":161121867,\"date\":\"20170613170500\",\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do resumo multiplo$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_campo_deve_ser_ignorado_do_resumo_multiplo(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0},{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0},{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"id\":161122130,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista com o JSON dos campos atualizados do resumo multiplo$")
    public void deve_exibir_uma_lista_com_o_JSON_dos_campos_atualizados_do_resumo_multiplo() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641301,\"name\":\"Euro\",\"bidvalue\":5.2222,\"askvalue\":5.2222,\"maxbid\":5.2222,\"minbid\":5.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602110000\"},{\"id\":20641201,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602110000\"},{\"id\":20641153,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\"20170602110000\"},{\"id\":161121868,\"date\":\"20170613180000\",\"price\":1.23,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":3.21,\"low\":5.6,\"open\":5.6,\"volume\":900.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3},{\"id\":161122131,\"date\":\"20170613180000\",\"price\":9.87,\"exchangeasset\":\"INDX IND\",\"high\":7.89,\"low\":5.6,\"open\":5.6,\"volume\":900.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3}]}";
        assertEquals( expected, result);
    }

    @Dado("^entao deve exibir uma lista no formato JSON com as moedas na mesma ordem de solicitação: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e depois os itens na mesma ordem de solicitação: \"([^\"]*)\", \"([^\"]*)\"$")
    public void entao_deve_exibir_uma_lista_no_formato_JSON_com_as_moedas_na_mesma_ordem_de_solicitação_e_depois_os_itens_na_mesma_ordem_de_solicitação(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":161121867,\"date\":\"20170613170500\",\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"id\":161122130,\"date\":\"20170613172100\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista no formato JSON com as moedas e os itens na mesma ordem de solicitação:\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\", \"([^\"]*)\"$")
    public void deve_exibir_uma_lista_no_formato_JSON_com_as_moedas_e_os_itens_na_mesma_ordem_de_solicitação(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641200,\"name\":\"Euro (R$)\",\"bidvalue\":4.2222,\"askvalue\":4.2222,\"maxbid\":4.2222,\"minbid\":4.2222,\"variationbid\":0.0008,\"variationpercentbid\":0.033,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641300,\"name\":\"Euro\",\"bidvalue\":4.1111,\"askvalue\":4.1111,\"maxbid\":4.1111,\"minbid\":4.1111,\"variationbid\":0.0008,\"variationpercentbid\":0.044,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":161122130,\"date\":\"20170613172100\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"id\":161121867,\"date\":\"20170613170500\",\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir os dados disponíveis da ultima cotação/negociação em (\\d+)/(\\d+)/(\\d+)$")
    public void deve_exibir_os_dados_disponíveis_da_ultima_cotação_negociação_em(int arg1, int arg2, int arg3) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641184,\"name\":\"Libra\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170710000000\"},{\"id\":21641184,\"name\":\"Libra\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170710000000\"},{\"id\":21641184,\"name\":\"Libra\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170710000000\"},{\"id\":123456784,\"date\":\"20170710000000\",\"price\":5.69,\"exchangeasset\":\"VARIG TRANSP ON 2\",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56},{\"id\":123456784,\"date\":\"20170710000000\",\"price\":5.69,\"exchangeasset\":\"VARIG TRANSP ON 2\",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir dados de multiplos vazios$")
    public void deve_exibir_dados_de_multiplos_vazios() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[]});";
        assertEquals( expected, result);
    }

}
