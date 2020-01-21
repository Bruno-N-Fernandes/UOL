package br.com.uol.cotacoes.cambio;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.pt.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vrx_mtoledo on 05/07/17.
 */
@Profile("test")
public class TopAcoesBaixasSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private final String todayHour11 = LocalDateTime.now().withHour(11).withMinute(0).withSecond(0).format(dateFormat);
    private final String todayHour12 = LocalDateTime.now().withHour(12).withMinute(0).withSecond(0).format(dateFormat);


    @Quando("^processamos a solicitação de top acoes baixas$")
    public void processamos_a_solicitação_de_top_acoes_baixas() throws Throwable {
        final String url = "/asset/variation/bottom?"
                + contextTest.getParameterJsonp()
                + "&"
                + contextTest.getParemeterFields()
                + "&"
                + contextTest.getParemeterSize();
        contextTest.setResultActions( this.mockMvc.perform(get(url)) );
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados do top acoes baixas$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_do_top_acoes_baixas() throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"date\":\""
        + todayHour11
        + "\",\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":500.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3},{\"date\":\""
        + todayHour11
        + "\",\"price\":2,\"exchangeasset\":\"ABC BRASIL ON   \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":100.5,\"close\":5.7,\"bid\":5.6,\"ask\":5.69,\"change\":-0.01,\"pctChange\":-2.5},{\"date\":\""
        + todayHour11
        + "\",\"price\":1,\"exchangeasset\":\"ABC BRASIL PN   \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":200.1,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados do top acoes baixas$")
    public void deve_exibir_um_JSON_dos_campos_enviados_do_top_acoes_baixas() throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"date\":\""
        + todayHour11
        + "\",\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":500.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3},{\"date\":\""
        + todayHour11
        + "\",\"price\":2,\"exchangeasset\":\"ABC BRASIL ON   \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":100.5,\"close\":5.7,\"bid\":5.6,\"ask\":5.69,\"change\":-0.01,\"pctChange\":-2.5},{\"date\":\""
        + todayHour11
        + "\",\"price\":1,\"exchangeasset\":\"ABC BRASIL PN   \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":200.1,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do top acoes baixas$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_campo_deve_ser_ignorado_do_top_acoes_baixas(final String arg1) throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":500.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3},{\"price\":2,\"exchangeasset\":\"ABC BRASIL ON   \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":100.5,\"close\":5.7,\"bid\":5.6,\"ask\":5.69,\"change\":-0.01,\"pctChange\":-2.5},{\"price\":1,\"exchangeasset\":\"ABC BRASIL PN   \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":200.1,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1}]});";
        assertEquals( expected, result);
    }

    @Dado("^validamos a ordem das (\\d+) primeiras ações: \"([^\"]*)\" e \"([^\"]*)\" de top acoes baixas no formato JSONP$")
    public void validamos_a_ordem_das_primeiras_ações_e_de_top_acoes_baixas_no_formato_JSONP(int arg1, final String arg2, final String arg3) throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"date\":\""
        + todayHour11
        + "\",\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":500.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3},{\"date\":\""
        + todayHour11
        + "\",\"price\":2,\"exchangeasset\":\"ABC BRASIL ON   \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":100.5,\"close\":5.7,\"bid\":5.6,\"ask\":5.69,\"change\":-0.01,\"pctChange\":-2.5},{\"date\":\""
        + todayHour11
        + "\",\"price\":1,\"exchangeasset\":\"ABC BRASIL PN   \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":200.1,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1}]});";

        assertEquals( expected, result);
    }

    @Dado("^que incluímos novos valores para a ação \"([^\"]*)\" acima dos valores de \"([^\"]*)\" \\(\"([^\"]*)\" com maior variação negativa que \"([^\"]*)\"\\)$")
    public void que_incluímos_novos_valores_para_a_ação_acima_dos_valores_de_com_maior_variação_negativa_que(final String arg1, final String arg2, final String arg3, final String arg4) throws Throwable {
        final String insertTableSQL = "insert into asset_intraday ("
                + " idt_asset_intraday, num_price, idt_exchange_asset, num_high, num_low, num_open, num_volume, num_close, num_bid, num_ask, num_change, num_pct_change,dat_last_update)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection jdbcConnection = SessionFactoryUtils.getDataSource( sessionFactory).getConnection();

        PreparedStatement firstTop = jdbcConnection.prepareStatement(insertTableSQL);
        firstTop.setInt(1, 222222223);
        firstTop.setDouble(2, 2.00);
        firstTop.setInt(3, 19);
        firstTop.setDouble(4, 5.69);
        firstTop.setDouble(5, 5.6);
        firstTop.setDouble(6, 5.6);
        firstTop.setDouble(7, 100.5);
        firstTop.setDouble(8, 5.7);
        firstTop.setDouble(9, 5.6);
        firstTop.setDouble(10, 5.69);
        firstTop.setDouble(11, -0.01);
        firstTop.setDouble(12, -9.5);
        firstTop.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0)));

        firstTop .executeUpdate();
        firstTop.close();

        PreparedStatement secondTop = jdbcConnection.prepareStatement(insertTableSQL);
        secondTop.setInt(1, 333333334);
        secondTop.setDouble(2, 3.00);
        secondTop.setInt(3, 233);
        secondTop.setDouble(4, 5.69);
        secondTop.setDouble(5, 5.6);
        secondTop.setDouble(6, 5.6);
        secondTop.setDouble(7, 500.1);
        secondTop.setDouble(8, 5.7);
        secondTop.setDouble(9, 5.15);
        secondTop.setDouble(10, 5.65);
        secondTop.setDouble(11, -0.1);
        secondTop.setDouble(12, -8.5);
        secondTop.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0)));

        secondTop .executeUpdate();
        secondTop.close();
        jdbcConnection.close();
    }

    @Entao("^deve exibir uma lista no formato JSONP de top acoes baixas com nova ordem: \"([^\"]*)\" e \"([^\"]*)\"$")
    public void deve_exibir_uma_lista_no_formato_JSONP_de_top_acoes_baixas_com_nova_ordem_e(final String arg1, final String arg2) throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"date\":\""
        + todayHour12
        + "\",\"price\":2,\"exchangeasset\":\"ABC BRASIL ON   \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":100.5,\"close\":5.7,\"bid\":5.6,\"ask\":5.69,\"change\":-0.01,\"pctChange\":-9.5},{\"date\":\""
        + todayHour12
        + "\",\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":500.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-8.5},{\"date\":\""
        + todayHour11
        + "\",\"price\":1,\"exchangeasset\":\"ABC BRASIL PN   \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":200.1,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista contendo o JSON dos campos enviados sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do top acoes baixas$")
    public void deve_exibir_uma_lista_contendo_o_JSON_dos_campos_enviados_sem_o_campo_campo_deve_ser_ignorado_do_top_acoes_baixas(final String arg1) throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":500.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3},{\"price\":2,\"exchangeasset\":\"ABC BRASIL ON   \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":100.5,\"close\":5.7,\"bid\":5.6,\"ask\":5.69,\"change\":-0.01,\"pctChange\":-2.5},{\"price\":1,\"exchangeasset\":\"ABC BRASIL PN   \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":200.1,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1}]}";
        assertEquals( expected, result);
    }

    @Dado("^validamos a ordem das (\\d+) primeiras ações: \"([^\"]*)\" e \"([^\"]*)\" de top acoes baixas no formato JSON$")
    public void validamos_a_ordem_das_primeiras_ações_e_de_top_acoes_baixas_no_formato_JSON(int arg1, final String arg2, final String arg3) throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"date\":\""
        + todayHour11
        + "\",\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":500.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3},{\"date\":\""
        + todayHour11
        + "\",\"price\":2,\"exchangeasset\":\"ABC BRASIL ON   \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":100.5,\"close\":5.7,\"bid\":5.6,\"ask\":5.69,\"change\":-0.01,\"pctChange\":-2.5},{\"date\":\""
        + todayHour11
        + "\",\"price\":1,\"exchangeasset\":\"ABC BRASIL PN   \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":200.1,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista no formato JSON de top acoes baixas com nova ordem: \"([^\"]*)\" e \"([^\"]*)\"$")
    public void deve_exibir_uma_lista_no_formato_JSON_de_top_acoes_baixas_com_nova_ordem_e(final String arg1, final String arg2) throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"date\":\""
        + todayHour12
        + "\",\"price\":2,\"exchangeasset\":\"ABC BRASIL ON   \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":100.5,\"close\":5.7,\"bid\":5.6,\"ask\":5.69,\"change\":-0.01,\"pctChange\":-9.5},{\"date\":\""
        + todayHour12
        + "\",\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":500.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-8.5},{\"date\":\""
        + todayHour11
        + "\",\"price\":1,\"exchangeasset\":\"ABC BRASIL PN   \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":200.1,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1}]}";

        assertEquals( expected, result);
    }

}
