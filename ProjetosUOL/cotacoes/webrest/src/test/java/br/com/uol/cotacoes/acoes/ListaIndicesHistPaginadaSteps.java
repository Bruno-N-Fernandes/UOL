package br.com.uol.cotacoes.acoes;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vrx_mtoledo on 27/06/17.
 */
public class ListaIndicesHistPaginadaSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
    private String yesterday  = today.minusDays(1).format(dateFormat);
    private String twoDaysBefore = today.minusDays(2).format(dateFormat);
    private String oneWeekBefore = today.minusDays(7).format(dateFormat);
    private String oneWeekAndOneDayBefore = today.minusDays(7).minusDays(1).format(dateFormat);
    private String oneMonthBefore = today.minusMonths(1).format(dateFormat);
    private String oneMonthAndOneDayBefore = today.minusMonths(1).minusDays(1).format(dateFormat);
    private String threeMonthsBefore = today.minusMonths(3).format(dateFormat);


    @Quando("^processamos a solicitação de Lista Indice Hist para a Primeira Página$")
    public void processamos_a_solicitação_de_Lista_Indice_Hist_para_a_Primeira_Página() throws Throwable {
        String url = "/index/interday/list/paged?"
                + contextTest.getParameterJsonp()
                + "&"
                + contextTest.getParameterItem()
                + "&"
                + contextTest.getParemeterFields()
                + "&"
                + contextTest.getParemeterSize();
        contextTest.setResultActions( this.mockMvc.perform(get(url)) );
    }

    @Entao("^deve exibir um JSON com os campos enviados e valor em next da Lista Indice Hist Paginada$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_e_valor_em_next_da_Lista_Indice_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"123456765N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore 
        + "\",\"price\":12561.68,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12561.68,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-5.342,\"pctChange\":-0.04},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":12558.717,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-8.305,\"pctChange\":-0.07},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":12562.851,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-4.171,\"pctChange\":-0.03}]}";
        
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Indice Hist Paginada$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_CHANGED_e_valor_em_next_da_Lista_Indice_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "{\"prev\":null,\"next\":\"123456765N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore 
        + "\",\"price\":12561.68,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12561.68,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"pctChange\":-0.04},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":12558.717,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"pctChange\":-0.07},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":12562.851,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"pctChange\":-0.03}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista Indice Hist Paginada com a funcao de callback contendo o JSON dos campos enviados e valor em next da Lista Indice Hist Paginada$")
    public void deve_exibir_uma_Lista_Indice_Hist_Paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_e_valor_em_next_da_Lista_Indice_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/callback({\"prev\":null,\"next\":\"123456765N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore 
        + "\",\"price\":12561.68,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12561.68,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-5.342,\"pctChange\":-0.04},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":12558.717,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-8.305,\"pctChange\":-0.07},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":12562.851,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-4.171,\"pctChange\":-0.03}]});";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista Indice Hist Paginada com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Indice Hist Paginada$")
    public void deve_exibir_uma_Lista_Indice_Hist_Paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_CHANGED_e_valor_em_next_da_Lista_Indice_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/callback({\"prev\":null,\"next\":\"123456765N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore 
        + "\",\"price\":12561.68,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12561.68,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"pctChange\":-0.04},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":12558.717,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"pctChange\":-0.07},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":12562.851,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"pctChange\":-0.03}]});";

        assertEquals( expected, result);
    }

    @Quando("^processamos a solicitação de Lista Indice Hist para a Próxima Página$")
    public void processamos_a_solicitação_de_Lista_Indice_Hist_para_a_Próxima_Página() throws Throwable {
        String url = "/index/interday/list/paged?"
                + contextTest.getParameterJsonp()
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

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSONP da Lista Indice Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_Indice_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/list({\"prev\":\"123456763P\",\"next\":\"123456759N\",\"docs\":["
        + "{\"date\":\""
        + oneMonthBefore
        + "\",\"price\":12554.574,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12544.605,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-12.448,\"pctChange\":-0.1},"
        + "{\"date\":\""
        + oneMonthAndOneDayBefore
        + "\",\"price\":12565.147,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12544.605,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-1.875,\"pctChange\":-0.01},"
        + "{\"date\":\""
        + threeMonthsBefore
        + "\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";

        assertEquals( expected, result);
    }

    @Quando("^processamos a solicitação de Lista Indice Hist para a Página Anterior$")
    public void processamos_a_solicitação_de_Lista_Indice_Hist_para_a_Página_Anterior() throws Throwable {
        String url = "/index/interday/list/paged?"
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

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSONP da Lista Indice Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSONP_da_Lista_Indice_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/list({\"prev\":\"123456757P\",\"next\":null,\"docs\":["
        + "{\"date\":\""
        + "\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},"
        + "{\"date\":\""
        + "\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},"
        + "{\"date\":\""
        + "\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSON da Lista Indice Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_Indice_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "{\"prev\":\"123456763P\",\"next\":\"123456759N\",\"docs\":["
        + "{\"date\":\""
        + "\",\"price\":12554.574,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12544.605,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-12.448,\"pctChange\":-0.1},"
        + "{\"date\":\""
        + "\",\"price\":12565.147,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12544.605,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-1.875,\"pctChange\":-0.01},"
        + "{\"date\":\""
        + "\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSON da Lista Indice Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSON_da_Lista_Indice_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "{\"prev\":\"123456757P\",\"next\":null,\"docs\":["
        + "{\"date\":\""
        + "\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},"
        + "{\"date\":\""
        + "\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},"
        + "{\"date\":\""
        + "\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSONP da Lista Indice Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSONP_da_Lista_Indice_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/list({\"prev\":\"123456763P\",\"next\":\"123456759N\",\"docs\":["
        + "{\"date\":\""
        + "\",\"price\":12554.574,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12544.605,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-12.448,\"pctChange\":-0.1},"
        + "{\"date\":\""
        + "\",\"price\":12565.147,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12544.605,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-1.875,\"pctChange\":-0.01},"
        + "{\"date\":\""
        + "\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para proxima pag\\. no formato JSONP da Lista Indice Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_proxima_pag_no_formato_JSONP_da_Lista_Indice_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/list({\"prev\":null,\"next\":\"123456765N\",\"docs\":["
        + "{\"date\":\""
        + "\",\"price\":12561.68,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12561.68,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-5.342,\"pctChange\":-0.04},"
        + "{\"date\":\""
        + "\",\"price\":12558.717,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-8.305,\"pctChange\":-0.07},"
        + "{\"date\":\""
        + "\",\"price\":12562.851,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-4.171,\"pctChange\":-0.03}]});";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSON da Lista Indice Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSON_da_Lista_Indice_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "{\"prev\":\"123456763P\",\"next\":\"123456759N\",\"docs\":["
        + "{\"date\":\""
        + "\",\"price\":12554.574,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12544.605,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-12.448,\"pctChange\":-0.1},"
        + "{\"date\":\""
        + "\",\"price\":12565.147,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12544.605,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-1.875,\"pctChange\":-0.01},"
        + "{\"date\":\""
        + "\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para proxima pag\\. no formato JSON da Lista Indice Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_proxima_pag_no_formato_JSON_da_Lista_Indice_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "{\"prev\":null,\"next\":\"123456765N\",\"docs\":["
        + "{\"date\":\""
        + "\",\"price\":12561.68,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12561.68,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-5.342,\"pctChange\":-0.04},"
        + "{\"date\":\""
        + "\",\"price\":12558.717,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-8.305,\"pctChange\":-0.07},"
        + "{\"date\":\""
        + "\",\"price\":12562.851,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-4.171,\"pctChange\":-0.03}]}";

        assertEquals( expected, result);
    }


    @Entao("^deve exibir o ponteiro prev diferente de nulo e ponteiro next para a proxima pag\\. no formato JSONP da Lista Indice Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_diferente_de_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_Indice_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/list({\"prev\":\"123456769P\",\"next\":\"123456765N\",\"docs\":["
        + "{\"date\":\""
        + twoDaysBefore 
        + "\",\"price\":12561.68,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12561.68,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-5.342,\"pctChange\":-0.04},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":12558.717,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-8.305,\"pctChange\":-0.07},"
        + "{\"date\":\""
        + oneWeekAndOneDayBefore
        + "\",\"price\":12562.851,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-4.171,\"pctChange\":-0.03}]});";

        assertEquals( expected, result);
   }

    @Entao("^deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag\\. no formato JSONP da Lista Indice Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_igual_a_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_Indice_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =  "/**/list({\"prev\":null,\"next\":\"123456767N\",\"docs\":["
        + "{\"date\":\""
        + yesterday
        + "\",\"price\":5.55,\"exchangeasset\":\"INDX IND\",\"high\":5.66,\"low\":5.77,\"open\":5.88,\"volume\":100,\"close\":5.99,\"bid\":5.56,\"ask\":5.67,\"change\":-0.05,\"pctChange\":-1.55},"
        + "{\"date\":\""
        + twoDaysBefore
        + "\",\"price\":12561.68,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12561.68,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-5.342,\"pctChange\":-0.04},"
        + "{\"date\":\""
        + oneWeekBefore
        + "\",\"price\":12558.717,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12558.717,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-8.305,\"pctChange\":-0.07}]});";

        assertEquals( expected, result);
    }

}
