package br.com.uol.cotacoes.acoes;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vrx_mtoledo on 27/06/17.
 */
public class ListaCommoditiesHistPaginadaSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
    private String yesterday = today.minusDays(1).format(dateFormat);
    private String twoDaysBefore = today.minusDays(2).format(dateFormat);
    private String oneWeekBefore = today.minusDays(7).format(dateFormat);
    private String oneWeekAndOneDayBefore = today.minusDays(7).minusDays(1).format(dateFormat);
    private String oneMonthBefore = today.minusMonths(1).format(dateFormat);
    private String oneMonthAndOneDayBefore = today.minusMonths(1).minusDays(1).format(dateFormat);
    private String threeMonthsBefore = today.minusMonths(3).format(dateFormat);
    private String threeMonthsAndOneDayBefore = today.minusMonths(3).minusDays(1).format(dateFormat);
    private String oneYearBefore = today.minusYears(1).format(dateFormat);
    private String oneYearAndOneDayBefore = today.minusYears(1).minusDays(1).format(dateFormat);


    @Quando("^processamos a solicitação de Lista Commodities Hist Paginada para a Primeira Página$")
    public void processamos_a_solicitação_de_Lista_Commodities_Hist_Paginada_para_a_Primeira_Página() throws Throwable {
        String url = "/commodities/interday/list/paged?"
                + contextTest.getParameterJsonp()
                + "&"
                + contextTest.getParameterItem()
                + "&"
                + contextTest.getParemeterFields()
                + "&"
                + contextTest.getParemeterSize();
        contextTest.setResultActions(this.mockMvc.perform(get(url)));
    }

    @Quando("^processamos a solicitação de Lista Commodities Hist para a Próxima Página$")
    public void processamos_a_solicitação_de_Lista_Commodities_Hist_para_a_Próxima_Página() throws Throwable {
        String url = "/commodities/interday/list/paged?"
                + contextTest.getParameterJsonp()
                + "&"
                + contextTest.getParameterItem()
                + "&"
                + contextTest.getParemeterFields()
                + "&"
                + contextTest.getParemeterSize()
                + "&"
                + contextTest.getParameterNext();
        contextTest.setResultActions(this.mockMvc.perform(get(url)));
    }

    @Quando("^processamos a solicitação de Lista Commodities Hist para a Página Anterior$")
    public void processamos_a_solicitação_de_Lista_Commodities_Hist_para_a_Página_Anterior() throws Throwable {
        String url = "/commodities/interday/list/paged?"
                + contextTest.getParameterJsonp()
                + "&"
                + contextTest.getParameterItem()
                + "&"
                + contextTest.getParemeterFields()
                + "&"
                + contextTest.getParemeterSize()
                + "&"
                + contextTest.getParameterPrev();
        contextTest.setResultActions(this.mockMvc.perform(get(url)));
    }

    @Entao("^deve exibir um JSON com os campos enviados e valor em next da Lista Commodities Hist Paginada$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_e_valor_em_next_da_Lista_Commodities_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"123456745N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":1266.97,\"low\":1266.26,\"open\":0,\"volume\":0,\"close\":1265.35,\"bid\":1266.64,\"ask\":1266.82,\"change\":-0.06,\"pctChange\":0},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":16.88,\"low\":16.838,\"open\":0,\"volume\":0,\"close\":16.86,\"bid\":16.849,\"ask\":16.855,\"change\":0.01,\"pctChange\":0.06},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":927.3,\"low\":927,\"open\":0,\"volume\":0,\"close\":923.5,\"bid\":924.9,\"ask\":929.1,\"change\":0.1,\"pctChange\":0.01}]}";

        assertEquals(expected, result);

    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Commodities Hist Paginada$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_CHANGED_e_valor_em_next_da_Lista_Commodities_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"123456745N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":1266.97,\"low\":1266.26,\"open\":0,\"volume\":0,\"close\":1265.35,\"bid\":1266.64,\"ask\":1266.82,\"pctChange\":0},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":16.88,\"low\":16.838,\"open\":0,\"volume\":0,\"close\":16.86,\"bid\":16.849,\"ask\":16.855,\"pctChange\":0.06},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":927.3,\"low\":927,\"open\":0,\"volume\":0,\"close\":923.5,\"bid\":924.9,\"ask\":929.1,\"pctChange\":0.01}]}";

        assertEquals(expected, result);

    }

    @Entao("^deve exibir uma Lista Commodities Hist Paginada com a funcao de callback contendo o JSON dos campos enviados e valor em next da Lista Commodities Hist Paginada$")
    public void deve_exibir_uma_Lista_Commodities_Hist_Paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_e_valor_em_next_da_Lista_Commodities_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":\"123456745N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":1266.97,\"low\":1266.26,\"open\":0,\"volume\":0,\"close\":1265.35,\"bid\":1266.64,\"ask\":1266.82,\"change\":-0.06,\"pctChange\":0},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":16.88,\"low\":16.838,\"open\":0,\"volume\":0,\"close\":16.86,\"bid\":16.849,\"ask\":16.855,\"change\":0.01,\"pctChange\":0.06},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":927.3,\"low\":927,\"open\":0,\"volume\":0,\"close\":923.5,\"bid\":924.9,\"ask\":929.1,\"change\":0.1,\"pctChange\":0.01}]});";

        assertEquals(expected, result);

    }

    @Entao("^deve exibir uma Lista Commodities Hist Paginada com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Commodities Hist Paginada$")
    public void deve_exibir_uma_Lista_Commodities_Hist_Paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_CHANGED_e_valor_em_next_da_Lista_Commodities_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":\"123456745N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":1266.97,\"low\":1266.26,\"open\":0,\"volume\":0,\"close\":1265.35,\"bid\":1266.64,\"ask\":1266.82,\"pctChange\":0},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":16.88,\"low\":16.838,\"open\":0,\"volume\":0,\"close\":16.86,\"bid\":16.849,\"ask\":16.855,\"pctChange\":0.06},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":927.3,\"low\":927,\"open\":0,\"volume\":0,\"close\":923.5,\"bid\":924.9,\"ask\":929.1,\"pctChange\":0.01}]});";

        assertEquals(expected, result);

    }

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSONP da Lista Commodities Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_Commodities_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"123456743P\",\"next\":\"123456739N\",\"docs\":["
        + "{\"date\":\""
        + oneMonthBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":883.5,\"low\":886.5,\"open\":0,\"volume\":0,\"close\":882.5,\"bid\":882.6,\"ask\":888.6,\"change\":-4.9,\"pctChange\":-0.55},"
        + "{\"date\":\""
        + oneMonthAndOneDayBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":46.39,\"ask\":46.41,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + threeMonthsBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":1266.97,\"low\":1266.26,\"open\":0,\"volume\":0,\"close\":1265.35,\"bid\":1266.64,\"ask\":1266.82,\"change\":-0.06,\"pctChange\":0}]});";

        assertEquals(expected, result);

    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSONP da Lista Commodities Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSONP_da_Lista_Commodities_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"123456737P\",\"next\":null,\"docs\":["
        + "{\"date\":\""
        + threeMonthsAndOneDayBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":927.3,\"low\":927,\"open\":0,\"volume\":0,\"close\":923.5,\"bid\":924.9,\"ask\":929.1,\"change\":0.1,\"pctChange\":0.01},"
        + "{\"date\":\""
        + oneYearBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":883.5,\"low\":886.5,\"open\":0,\"volume\":0,\"close\":882.5,\"bid\":882.6,\"ask\":888.6,\"change\":-4.9,\"pctChange\":-0.55},"
        + "{\"date\":\""
        + oneYearAndOneDayBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":46.39,\"ask\":46.41,\"change\":0,\"pctChange\":0}]});";

        assertEquals(expected, result);

    }

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSON da Lista Commodities Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_Commodities_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"123456743P\",\"next\":\"123456739N\",\"docs\":["
        + "{\"date\":\""
        + oneMonthBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":883.5,\"low\":886.5,\"open\":0,\"volume\":0,\"close\":882.5,\"bid\":882.6,\"ask\":888.6,\"change\":-4.9,\"pctChange\":-0.55},"
        + "{\"date\":\""
        + oneMonthAndOneDayBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":46.39,\"ask\":46.41,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + threeMonthsBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":1266.97,\"low\":1266.26,\"open\":0,\"volume\":0,\"close\":1265.35,\"bid\":1266.64,\"ask\":1266.82,\"change\":-0.06,\"pctChange\":0}]}";

        assertEquals(expected, result);

    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSON da Lista Commodities Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSON_da_Lista_Commodities_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"123456737P\",\"next\":null,\"docs\":["
        + "{\"date\":\""
        + threeMonthsAndOneDayBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":927.3,\"low\":927,\"open\":0,\"volume\":0,\"close\":923.5,\"bid\":924.9,\"ask\":929.1,\"change\":0.1,\"pctChange\":0.01},"
        + "{\"date\":\""
        + oneYearBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":883.5,\"low\":886.5,\"open\":0,\"volume\":0,\"close\":882.5,\"bid\":882.6,\"ask\":888.6,\"change\":-4.9,\"pctChange\":-0.55},"
        + "{\"date\":\""
        + oneYearAndOneDayBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":46.39,\"ask\":46.41,\"change\":0,\"pctChange\":0}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSONP da Lista Commodities Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSONP_da_Lista_Commodities_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"123456743P\",\"next\":\"123456739N\",\"docs\":["
        + "{\"date\":\""
        + oneMonthBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":883.5,\"low\":886.5,\"open\":0,\"volume\":0,\"close\":882.5,\"bid\":882.6,\"ask\":888.6,\"change\":-4.9,\"pctChange\":-0.55},"
        + "{\"date\":\""
        + oneMonthAndOneDayBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":46.39,\"ask\":46.41,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + threeMonthsBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":1266.97,\"low\":1266.26,\"open\":0,\"volume\":0,\"close\":1265.35,\"bid\":1266.64,\"ask\":1266.82,\"change\":-0.06,\"pctChange\":0}]});";

        assertEquals(expected, result);

    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para proxima pag\\. no formato JSONP da Lista Commodities Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_proxima_pag_no_formato_JSONP_da_Lista_Commodities_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"123456745N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":1266.97,\"low\":1266.26,\"open\":0,\"volume\":0,\"close\":1265.35,\"bid\":1266.64,\"ask\":1266.82,\"change\":-0.06,\"pctChange\":0},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":16.88,\"low\":16.838,\"open\":0,\"volume\":0,\"close\":16.86,\"bid\":16.849,\"ask\":16.855,\"change\":0.01,\"pctChange\":0.06},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":927.3,\"low\":927,\"open\":0,\"volume\":0,\"close\":923.5,\"bid\":924.9,\"ask\":929.1,\"change\":0.1,\"pctChange\":0.01}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSON da Lista Commodities Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSON_da_Lista_Commodities_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"123456743P\",\"next\":\"123456739N\",\"docs\":["
        + "{\"date\":\""
        + oneMonthBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":883.5,\"low\":886.5,\"open\":0,\"volume\":0,\"close\":882.5,\"bid\":882.6,\"ask\":888.6,\"change\":-4.9,\"pctChange\":-0.55},"
        + "{\"date\":\""
        + oneMonthAndOneDayBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":46.39,\"ask\":46.41,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + threeMonthsBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":1266.97,\"low\":1266.26,\"open\":0,\"volume\":0,\"close\":1265.35,\"bid\":1266.64,\"ask\":1266.82,\"change\":-0.06,\"pctChange\":0}]}";

        assertEquals(expected, result);

    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para proxima pag\\. no formato JSON da Lista Commodities Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_proxima_pag_no_formato_JSON_da_Lista_Commodities_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"123456745N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":1266.97,\"low\":1266.26,\"open\":0,\"volume\":0,\"close\":1265.35,\"bid\":1266.64,\"ask\":1266.82,\"change\":-0.06,\"pctChange\":0},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":16.88,\"low\":16.838,\"open\":0,\"volume\":0,\"close\":16.86,\"bid\":16.849,\"ask\":16.855,\"change\":0.01,\"pctChange\":0.06},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":0,\"exchangeasset\":\"OURO\",\"high\":927.3,\"low\":927,\"open\":0,\"volume\":0,\"close\":923.5,\"bid\":924.9,\"ask\":929.1,\"change\":0.1,\"pctChange\":0.01}]}";

        assertEquals(expected, result);
    }

}
