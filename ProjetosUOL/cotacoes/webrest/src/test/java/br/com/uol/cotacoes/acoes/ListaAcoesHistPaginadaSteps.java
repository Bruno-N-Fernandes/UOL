package br.com.uol.cotacoes.acoes;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vrx_mtoledo on 27/06/17.
 */
@Profile("test")
public class ListaAcoesHistPaginadaSteps {

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


    @Quando("^processamos a solicitação de Lista Acao Hist para a Primeira Página$")
    public void processamos_a_solicitação_de_Lista_Acao_Hist_para_a_Primeira_Página() throws Throwable {
        StringBuilder url = new StringBuilder("/asset/interday/list/paged?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterItem())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParemeterSize());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir um JSON com os campos enviados e valor em next da Lista Acao Hist Paginada$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_e_valor_em_next_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"123456785N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
       + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":10100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Acao Hist Paginada$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_CHANGED_e_valor_em_next_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"123456785N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"pctChange\":-1.56},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"pctChange\":0},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":10100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"pctChange\":0}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista Acao Hist Paginada com a funcao de callback contendo o JSON dos campos enviados e valor em next da Lista Acao Hist Paginada$")
    public void deve_exibir_uma_Lista_Acao_Hist_Paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_e_valor_em_next_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":\"123456785N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":10100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0}]});";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista Acao Hist Paginada com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Acao Hist Paginada$")
    public void deve_exibir_uma_Lista_Acao_Hist_Paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_CHANGED_e_valor_em_next_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":\"123456785N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"pctChange\":-1.56},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"pctChange\":0},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":10100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"pctChange\":0}]});";

        assertEquals( expected, result);
    }

    @Quando("^processamos a solicitação de Lista Acao Hist para a Página Anterior$")
    public void processamos_a_solicitação_de_Lista_Acao_Hist_para_a_Página_Anterior() throws Throwable {
        String url = "/asset/interday/list/paged?"
                + contextTest.getParameterJsonp()
                + "&"
                + contextTest.getParameterItem()
                + "&"
                + contextTest.getParemeterFields()
                + "&"
                + contextTest.getParemeterSize()
                + "&"
                + contextTest.getParameterPrev();
        contextTest.setResultActions( this.mockMvc.perform(get(url)) );
    }

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSONP da Lista Acao Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"123456783P\",\"next\":\"123456779N\",\"docs\":["
        + "{\"date\":\""
        + oneMonthBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15100,\"close\":5.78,\"bid\":5.65,\"ask\":5.78,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + oneMonthAndOneDayBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15200,\"close\":5.78,\"bid\":5.66,\"ask\":5.79,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + threeMonthsBefore
        + "\",\"price\":5.84,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.84,\"low\":5.69,\"open\":5.69,\"volume\":24100,\"close\":5.78,\"bid\":5.66,\"ask\":5.85,\"change\":0.06,\"pctChange\":1.04}]});";

        assertEquals( expected, result);
    }

    @Quando("^processamos a solicitação de Lista Acao Hist para a Próxima Página$")
    public void processamos_a_solicitação_de_Lista_Acao_Hist_para_a_Próxima_Página() throws Throwable {
        String url = "/asset/interday/list/paged?"
                +contextTest.getParameterJsonp()
                + "&"
                + contextTest.getParameterItem()
                + "&"
                + contextTest.getParemeterFields()
                + "&"
                + contextTest.getParemeterSize()
                + "&"
                + contextTest.getParameterNext();
        contextTest.setResultActions( this.mockMvc.perform(get(url)) );
    }

    @Entao("^deve exibir o ponteiro prev diferente de nulo e ponteiro next para a proxima pag\\. no formato JSONP da Lista Acao Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_diferente_de_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/list({\"prev\":\"123456789P\",\"next\":\"123456785N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":10100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0}]});";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag\\. no formato JSONP da Lista Acao Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_igual_a_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/list({\"prev\":null,\"next\":\"123456787N\",\"docs\":["
        + "{\"date\":\""
        + yesterday
        + "\",\"price\":5.55,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.66,\"low\":5.77,\"open\":5.88,\"volume\":100,\"close\":5.99,\"bid\":5.56,\"ask\":5.67,\"change\":-0.05,\"pctChange\":-1.55},"
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0}]});";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSONP da Lista Acao Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSONP_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"123456777P\",\"next\":null,\"docs\":["
        + "{\"date\":\""
        + threeMonthsAndOneDayBefore
        + "\",\"price\":5.88,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31200,\"close\":5.78,\"bid\":5.7,\"ask\":5.85,\"change\":0.1,\"pctChange\":1.73},"
        + "{\"date\":\""
        + oneYearBefore
        + "\",\"price\":5.7,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31700,\"close\":5.78,\"bid\":5.56,\"ask\":5.8,\"change\":-0.08,\"pctChange\":-1.38},"
        + "{\"date\":\""
        + oneYearAndOneDayBefore
        + "\",\"price\":5.67,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.67,\"low\":5.6,\"open\":5.6,\"volume\":8000,\"close\":5.7,\"bid\":5.83,\"ask\":5.83,\"change\":-0.03,\"pctChange\":-0.53}]});";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSON da Lista Acao Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"123456783P\",\"next\":\"123456779N\",\"docs\":["
        + "{\"date\":\""
        + oneMonthBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15100,\"close\":5.78,\"bid\":5.65,\"ask\":5.78,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + oneMonthAndOneDayBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15200,\"close\":5.78,\"bid\":5.66,\"ask\":5.79,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + threeMonthsBefore
        + "\",\"price\":5.84,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.84,\"low\":5.69,\"open\":5.69,\"volume\":24100,\"close\":5.78,\"bid\":5.66,\"ask\":5.85,\"change\":0.06,\"pctChange\":1.04}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev diferente de nulo e ponteiro next para a proxima pag\\. no formato JSON da Lista Acao Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_diferente_de_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "{\"prev\":\"123456789P\",\"next\":\"123456785N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":10100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag\\. no formato JSON da Lista Acao Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_igual_a_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "{\"prev\":null,\"next\":\"123456787N\",\"docs\":["
        + "{\"date\":\""
        + yesterday
        + "\",\"price\":5.55,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.66,\"low\":5.77,\"open\":5.88,\"volume\":100,\"close\":5.99,\"bid\":5.56,\"ask\":5.67,\"change\":-0.05,\"pctChange\":-1.55},"
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSON da Lista Acao Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSON_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"123456777P\",\"next\":null,\"docs\":["
        + "{\"date\":\""
        + threeMonthsAndOneDayBefore
        + "\",\"price\":5.88,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31200,\"close\":5.78,\"bid\":5.7,\"ask\":5.85,\"change\":0.1,\"pctChange\":1.73},"
        + "{\"date\":\""
        + oneYearBefore
        + "\",\"price\":5.7,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31700,\"close\":5.78,\"bid\":5.56,\"ask\":5.8,\"change\":-0.08,\"pctChange\":-1.38},"
        + "{\"date\":\""
        + oneYearAndOneDayBefore
        + "\",\"price\":5.67,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.67,\"low\":5.6,\"open\":5.6,\"volume\":8000,\"close\":5.7,\"bid\":5.83,\"ask\":5.83,\"change\":-0.03,\"pctChange\":-0.53}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSONP da Lista Acao Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSONP_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"123456783P\",\"next\":\"123456779N\",\"docs\":["
        + "{\"date\":\""
        + oneMonthBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15100,\"close\":5.78,\"bid\":5.65,\"ask\":5.78,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + oneMonthAndOneDayBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15200,\"close\":5.78,\"bid\":5.66,\"ask\":5.79,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + threeMonthsBefore
        + "\",\"price\":5.84,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.84,\"low\":5.69,\"open\":5.69,\"volume\":24100,\"close\":5.78,\"bid\":5.66,\"ask\":5.85,\"change\":0.06,\"pctChange\":1.04}]});";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para proxima pag\\. no formato JSONP da Lista Acao Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_proxima_pag_no_formato_JSONP_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"123456785N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":10100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0}]});";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSON da Lista Acao Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSON_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"123456783P\",\"next\":\"123456779N\",\"docs\":["
        + "{\"date\":\""
        + oneMonthBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15100,\"close\":5.78,\"bid\":5.65,\"ask\":5.78,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + oneMonthAndOneDayBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15200,\"close\":5.78,\"bid\":5.66,\"ask\":5.79,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + threeMonthsBefore
        + "\",\"price\":5.84,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.84,\"low\":5.69,\"open\":5.69,\"volume\":24100,\"close\":5.78,\"bid\":5.66,\"ask\":5.85,\"change\":0.06,\"pctChange\":1.04}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para proxima pag\\. no formato JSON da Lista Acao Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_proxima_pag_no_formato_JSON_da_Lista_Acao_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"123456785N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":10100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0}]}";

        assertEquals( expected, result);
    }

}
