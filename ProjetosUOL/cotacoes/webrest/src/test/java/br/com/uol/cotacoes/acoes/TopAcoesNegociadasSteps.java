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
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vrx_mtoledo on 05/07/17.
 */
@Profile("test")
public class TopAcoesNegociadasSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private final String today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).format(dateFormat);
    private final String todayHour11 = LocalDateTime.now().withHour(11).withMinute(0).withSecond(0).format(dateFormat);
    private final String todayHour12 = LocalDateTime.now().withHour(12).withMinute(0).withSecond(0).format(dateFormat);


    @Quando("^processamos a solicitação de top acoes negociadas$")
    public void processamos_a_solicitação_de_top_acoes_negociadas() throws Throwable {
        final String url = "/asset/traded/top?"
                + contextTest.getParameterJsonp()
                + "&"
                + contextTest.getParemeterFields()
                + "&"
                + contextTest.getParemeterSize();
        contextTest.setResultActions( this.mockMvc.perform(get(url)) );
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados do top acoes negociadas$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_do_top_acoes_negociadas() throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"date\":\""
        + today
        + "\",\"price\":5.68,\"exchangeasset\":\"ISHARES BOVA CI \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"date\":\""
        + todayHour11
        + "\",\"price\":6,\"exchangeasset\":\"ABNOTE ON\",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":600.5,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":1.5},{\"date\":\""
        + todayHour11
        + "\",\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":500.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3}]});";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados do top acoes negociadas$")
    public void deve_exibir_um_JSON_dos_campos_enviados_do_top_acoes_negociadas() throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"date\":\""
        + today
        + "\",\"price\":5.68,\"exchangeasset\":\"ISHARES BOVA CI \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"date\":\""
        + todayHour11
        + "\",\"price\":6,\"exchangeasset\":\"ABNOTE ON\",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":600.5,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":1.5},{\"date\":\""
        + todayHour11
        + "\",\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":500.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do top acoes negociadas$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_campo_deve_ser_ignorado_do_top_acoes_negociadas(final String arg1) throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"price\":5.68,\"exchangeasset\":\"ISHARES BOVA CI \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"price\":6,\"exchangeasset\":\"ABNOTE ON\",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":600.5,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":1.5},{\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":500.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3}]});";
        assertEquals( expected, result);
    }

    @Dado("^validamos a ordem das (\\d+) primeiras ações: \"([^\"]*)\" e \"([^\"]*)\" de top acoes negociadas no formato JSONP$")
    public void validamos_a_ordem_das_primeiras_ações_e_de_top_acoes_negociadas_no_formato_JSONP(int arg1, final String arg2, final String arg3) throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"date\":\""
        + today
        + "\",\"price\":5.68,\"exchangeasset\":\"ISHARES BOVA CI \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"date\":\""
        + todayHour11
        + "\",\"price\":6,\"exchangeasset\":\"ABNOTE ON\",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":600.5,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":1.5},{\"date\":\""
        + todayHour11
        + "\",\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":500.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3}]});";

        assertEquals( expected, result);
    }

    @Dado("^que incluímos novos valores para a ação \"([^\"]*)\" acima dos valores de \"([^\"]*)\" \\(\"([^\"]*)\" com mais negociação que \"([^\"]*)\"\\)$")
    public void que_incluímos_novos_valores_para_a_ação_acima_dos_valores_de_com_mais_negociação_que(final String arg1, final String arg2, final String arg3, final String arg4) throws Throwable {
        final String insertTableSQL = "insert into asset_intraday ("
                + " idt_asset_intraday, num_price, idt_exchange_asset, num_high, num_low, num_open, num_volume, num_close, num_bid, num_ask, num_change, num_pct_change,dat_last_update)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection jdbcConnection = SessionFactoryUtils.getDataSource( sessionFactory).getConnection();

        //antigo segundo:
        //  <asset_intraday idt_asset_intraday="333333333" num_price="3.00" idt_exchange_asset="233" num_high="5.69" num_low="5.6" num_open="5.6" num_volume="500.1" num_close="5.7" num_bid="5.15" num_ask="5.65" num_change="-0.1" num_pct_change="-3.00" dat_last_update="dateTimeReplace0"/>
        PreparedStatement firstTop = jdbcConnection.prepareStatement(insertTableSQL);
        firstTop.setInt(1, 333333334);
        firstTop.setDouble(2, 3.00);
        firstTop.setInt(3, 233);
        firstTop.setDouble(4, 5.69);
        firstTop.setDouble(5, 5.6);
        firstTop.setDouble(6, 5.6);
        firstTop.setDouble(7, 900.1);
        firstTop.setDouble(8, 5.7);
        firstTop.setDouble(9, 5.15);
        firstTop.setDouble(10, 5.65);
        firstTop.setDouble(11, -0.1);
        firstTop.setDouble(12, -3.00);
        firstTop.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0)));

        firstTop .executeUpdate();
        firstTop.close();

        //antigo primeiro:
        //  <asset_intraday idt_asset_intraday="666666666" num_price="6.00" idt_exchange_asset="20" num_high="5.69" num_low="5.6" num_open="5.6" num_volume="600.5" num_close="5.7" num_bid="5.15" num_ask="5.65" num_change="-0.1" num_pct_change="1.50" dat_last_update="dateTimeReplace0"/>
        PreparedStatement secondTop = jdbcConnection.prepareStatement(insertTableSQL);
        secondTop.setInt(1, 666666667);
        secondTop.setDouble(2, 6.00);
        secondTop.setInt(3, 20);
        secondTop.setDouble(4, 5.69);
        secondTop.setDouble(5, 5.6);
        secondTop.setDouble(6, 5.6);
        secondTop.setDouble(7, 800.5);
        secondTop.setDouble(8, 5.7);
        secondTop.setDouble(9, 5.15);
        secondTop.setDouble(10, 5.65);
        secondTop.setDouble(11, -0.1);
        secondTop.setDouble(12, 1.50);
        secondTop.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0)));

        secondTop .executeUpdate();
        secondTop.close();
        jdbcConnection.close();
    }

    @Entao("^deve exibir uma lista no formato JSONP de top acoes negociadas com nova ordem: \"([^\"]*)\" e \"([^\"]*)\"$")
    public void deve_exibir_uma_lista_no_formato_JSONP_de_top_acoes_negociadas_com_nova_ordem_e(final String arg1, final String arg2) throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"date\":\""
        + today
        + "\",\"price\":5.68,\"exchangeasset\":\"ISHARES BOVA CI \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"date\":\""
        + todayHour12
        + "\",\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":900.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3},{\"date\":\""
        + todayHour12
        + "\",\"price\":6,\"exchangeasset\":\"ABNOTE ON\",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":800.5,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":1.5}]});";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista contendo o JSON dos campos enviados sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do top acoes negociadas$")
    public void deve_exibir_uma_lista_contendo_o_JSON_dos_campos_enviados_sem_o_campo_campo_deve_ser_ignorado_do_top_acoes_negociadas(final String arg1) throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"price\":5.68,\"exchangeasset\":\"ISHARES BOVA CI \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"price\":6,\"exchangeasset\":\"ABNOTE ON\",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":600.5,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":1.5},{\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":500.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3}]}";
        assertEquals( expected, result);
    }

    @Dado("^validamos a ordem das (\\d+) primeiras ações: \"([^\"]*)\" e \"([^\"]*)\" de top acoes negociadas no formato JSON$")
    public void validamos_a_ordem_das_primeiras_ações_e_de_top_acoes_negociadas_no_formato_JSON(int arg1, final String arg2, final String arg3) throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"date\":\""
        + today
        + "\",\"price\":5.68,\"exchangeasset\":\"ISHARES BOVA CI \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"date\":\""
        + todayHour11
        + "\",\"price\":6,\"exchangeasset\":\"ABNOTE ON\",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":600.5,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":1.5},{\"date\":\""
        + todayHour11
        + "\",\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":500.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista no formato JSON de top acoes negociadas com nova ordem: \"([^\"]*)\" e \"([^\"]*)\"$")
    public void deve_exibir_uma_lista_no_formato_JSON_de_top_acoes_negociadas_com_nova_ordem_e(final String arg1, final String arg2) throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"date\":\""
        + today
        + "\",\"price\":5.68,\"exchangeasset\":\"ISHARES BOVA CI \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"date\":\""
        + todayHour12
        + "\",\"price\":3,\"exchangeasset\":\"COTEMINAS ON    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":900.1,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-3},{\"date\":\""
        + todayHour12
        + "\",\"price\":6,\"exchangeasset\":\"ABNOTE ON\",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":800.5,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":1.5}]}";

        assertEquals( expected, result);
    }

}
