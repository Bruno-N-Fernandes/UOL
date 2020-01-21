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
 * Created by vrx_mtoledo on 28/06/17.
 */
public class ListaIndicadoresHistPaginadaSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;
    
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
    private String twoDaysBefore = today.minusDays(2).format(dateFormat);
    private String oneWeekBefore = today.minusDays(7).format(dateFormat);
    private String oneWeekAndOneDayBefore = today.minusDays(7).minusDays(1).format(dateFormat);
    private String oneMonthBefore = today.minusMonths(1).format(dateFormat);
    private String oneMonthAndOneDayBefore = today.minusMonths(1).minusDays(1).format(dateFormat);
    private String threeMonthsBefore = today.minusMonths(3).format(dateFormat);
    private String threeMonthsAndOneDayBefore = today.minusMonths(3).minusDays(1).format(dateFormat);
    private String oneYearBefore = today.minusYears(1).format(dateFormat);
    private String oneYearAndOneDayBefore = today.minusYears(1).minusDays(1).format(dateFormat);


    @Quando("^processamos a solicitação de Lista Indicadores Hist para a Primeira Página$")
    public void processamos_a_solicitação_de_Lista_Indicadores_Hist_para_a_Primeira_Página() throws Throwable {
        String url = "/indicators/interday/list/paged?"
                + contextTest.getParameterJsonp()
                + "&"
                + contextTest.getParameterItem()
                + "&"
                + contextTest.getParemeterFields()
                + "&"
                + contextTest.getParemeterSize();
        contextTest.setResultActions(this.mockMvc.perform(get(url)));
    }

    @Entao("^deve exibir um JSON com os campos enviados e valor em next da Lista Indicadores Hist Paginada$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_e_valor_em_next_da_Lista_Indicadores_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "{\"prev\":null,\"next\":\"123456705N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Indicadores Hist Paginada$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_CHANGED_e_valor_em_next_da_Lista_Indicadores_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "{\"prev\":null,\"next\":\"123456705N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"pctChange\":112.32},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"pctChange\":112.32},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"pctChange\":112.32}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma Lista Indicadores Hist Paginada com a funcao de callback contendo o JSON dos campos enviados e valor em next da Lista Indicadores Hist Paginada$")
    public void deve_exibir_uma_Lista_Indicadores_Hist_Paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_e_valor_em_next_da_Lista_Indicadores_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/callback({\"prev\":null,\"next\":\"123456705N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma Lista Indicadores Hist Paginada com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Indicadores Hist Paginada$")
    public void deve_exibir_uma_Lista_Indicadores_Hist_Paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_CHANGED_e_valor_em_next_da_Lista_Indicadores_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/callback({\"prev\":null,\"next\":\"123456705N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"pctChange\":112.32},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"pctChange\":112.32},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"pctChange\":112.32}]});";

        assertEquals(expected, result);
    }

    @Quando("^processamos a solicitação de Lista Indicadores Hist para a Página Anterior$")
    public void processamos_a_solicitação_de_Lista_Indicadores_Hist_para_a_Página_Anterior() throws Throwable {
        String url = "/indicators/interday/list/paged?"
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

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSONP da Lista Indicadores Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_Indicadores_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/list({\"prev\":\"123456703P\",\"next\":\"123456699N\",\"docs\":["
        + "{\"date\":\""
        + oneMonthBefore
        + "\",\"price\":0.042,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":0.042,\"ask\":0,\"change\":0,\"pctChange\":0.04},"
        + "{\"date\":\""
        + oneMonthAndOneDayBefore
        + "\",\"price\":10.14,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.14},"
        + "{\"date\":\""
        + threeMonthsBefore
        + "\",\"price\":10.15,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.15}]});";

        assertEquals(expected, result);
    }

    @Quando("^processamos a solicitação de Lista Indicadores Hist para a Próxima Página$")
    public void processamos_a_solicitação_de_Lista_Indicadores_Hist_para_a_Próxima_Página() throws Throwable {
        String url = "/indicators/interday/list/paged?"
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

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSONP da Lista Indicadores Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSONP_da_Lista_Indicadores_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/list({\"prev\":\"123456697P\",\"next\":null,\"docs\":["
        + "{\"date\":\""
        + threeMonthsAndOneDayBefore
        + "\",\"price\":10.16,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.16},"
        + "{\"date\":\""
        + oneYearBefore
        + "\",\"price\":10.17,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.17},"
        + "{\"date\":\""
        + oneYearAndOneDayBefore
        + "\",\"price\":10.18,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.18}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSON da Lista Indicadores Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_Indicadores_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "{\"prev\":\"123456703P\",\"next\":\"123456699N\",\"docs\":["
        + "{\"date\":\""
        + oneMonthBefore
        + "\",\"price\":0.042,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":0.042,\"ask\":0,\"change\":0,\"pctChange\":0.04},"
        + "{\"date\":\""
        + oneMonthAndOneDayBefore
        + "\",\"price\":10.14,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.14},"
        + "{\"date\":\""
        + threeMonthsBefore
        + "\",\"price\":10.15,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.15}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSON da Lista Indicadores Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSON_da_Lista_Indicadores_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "{\"prev\":\"123456697P\",\"next\":null,\"docs\":["
        + "{\"date\":\""
        + threeMonthsAndOneDayBefore
        + "\",\"price\":10.16,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.16},"
        + "{\"date\":\""
        + oneYearBefore
        + "\",\"price\":10.17,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.17},"
        + "{\"date\":\""
        + oneYearAndOneDayBefore
        + "\",\"price\":10.18,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.18}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSONP da Lista Indicadores Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSONP_da_Lista_Indicadores_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/list({\"prev\":\"123456703P\",\"next\":\"123456699N\",\"docs\":["
        + "{\"date\":\""
        + oneMonthBefore
        + "\",\"price\":0.042,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":0.042,\"ask\":0,\"change\":0,\"pctChange\":0.04},"
        + "{\"date\":\""
        + oneMonthAndOneDayBefore
        + "\",\"price\":10.14,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.14},"
        + "{\"date\":\""
        + threeMonthsBefore
        + "\",\"price\":10.15,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.15}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para proxima pag\\. no formato JSONP da Lista Indicadores Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_proxima_pag_no_formato_JSONP_da_Lista_Indicadores_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/list({\"prev\":null,\"next\":\"123456705N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSON da Lista Indicadores Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSON_da_Lista_Indicadores_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "{\"prev\":\"123456703P\",\"next\":\"123456699N\",\"docs\":["
        + "{\"date\":\""
        + oneMonthBefore
        + "\",\"price\":0.042,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":0.042,\"ask\":0,\"change\":0,\"pctChange\":0.04},"
        + "{\"date\":\""
        + oneMonthAndOneDayBefore
        + "\",\"price\":10.14,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.14},"
        + "{\"date\":\""
        + threeMonthsBefore
        + "\",\"price\":10.15,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.15}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para proxima pag\\. no formato JSON da Lista Indicadores Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_proxima_pag_no_formato_JSON_da_Lista_Indicadores_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "{\"prev\":null,\"next\":\"123456705N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32}]}";

        assertEquals(expected, result);
    }

}
