package br.com.uol.cotacoes.cambio;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.test.web.servlet.MockMvc;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

/**
 * Created by vrx_mtoledo on 29/05/17.
 */
@Profile("test")
public class ResumoMoedaSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;

    @Quando("^processamos a solicitação de Resumo da Moeda$")
    public void processamos_a_solicitação_de_Resumo_da_Moeda() throws Throwable {
        StringBuilder url = new StringBuilder("/currency/summary?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParemeterCurrency())
                .append("&")
                .append(contextTest.getParemeterFields());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados do Resumo da Moeda$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_do_Resumo_da_Moeda() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados do Resumo da Moeda$")
    public void deve_exibir_um_JSON_dos_campos_enviados_do_Resumo_da_Moeda() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do Resumo da Moeda$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_campo_deve_ser_ignorado_do_Resumo_da_Moeda(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo DATA \\(campo deve ser ignorado\\) do Resumo da Moeda$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_DATA_campo_deve_ser_ignorado_do_Resumo_da_Moeda() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0}]}";

        assertEquals(expected, result);

    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos atualizados do Resumo da Moeda$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_atualizados_do_Resumo_da_Moeda() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641153,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\"20170602110000\"}]});";

        assertEquals(expected, result);
    }

    @Dado("^que incluímos novos valores para essa moeda no dia seguinte$")
    public void que_incluímos_novos_valores_para_essa_moeda_no_dia_seguinte() throws SQLException {

        String insertTableSQL = "INSERT INTO currency_rate_intraday"
                + "(idt_currency_rate, idt_currency, num_value_bid, num_value_ask, num_max_bid, num_min_bid, num_var_bid, num_varpct_bid, num_open_bid, dat_currency_rate) VALUES"
                + "(?,?,?,?,?,?,?,?,?,?)";

        Connection jdbcConnection = SessionFactoryUtils.getDataSource( sessionFactory).getConnection();

        PreparedStatement preparedStatement = jdbcConnection.prepareStatement(insertTableSQL);
        preparedStatement.setInt(1, 20641154);
        preparedStatement.setInt(2, 18);
        preparedStatement.setDouble(3, 4.2333);
        preparedStatement.setDouble(4, 4.3222);
        preparedStatement.setDouble(5, 4.1111);
        preparedStatement.setDouble(6, 4.5555);
        preparedStatement.setDouble(7, 4.6666);
        preparedStatement.setDouble(8, 4.4444);
        preparedStatement.setDouble(9, 4.9999);
        preparedStatement.setTimestamp(10, Timestamp.valueOf(LocalDateTime.of(2017,6,3,1,0,0)));

        preparedStatement .executeUpdate();
        preparedStatement.close();
        jdbcConnection.close();
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos atualizados do dia seguinte do Resumo da Moeda$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_atualizados_do_dia_seguinte_do_Resumo_da_Moeda() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641154,\"name\":\"Dólar Australiano\",\"bidvalue\":4.2333,\"askvalue\":4.3222,\"maxbid\":4.1111,\"minbid\":4.5555,\"variationbid\":4.6666,\"variationpercentbid\":4.444,\"openbidvalue\":4.9999,\"date\":\"20170603010000\"}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir os dados disponíveis da ultima cotação da moeda em (\\d+)/(\\d+)/(\\d+)$")
    public void deve_exibir_os_dados_disponíveis_da_ultima_cotação_da_moeda_em(int arg1, int arg2, int arg3) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641184,\"name\":\"Libra\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170710000000\"}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir dados de moedas vazios$")
    public void deve_exibir_dados_de_moedas_vazios() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[]});";

        assertEquals(expected, result);
     }

}