package br.com.uol.cotacoes.acoes;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.es.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author mzp_dferraz
 *
 */
@Profile("test")
public class ListaIndicadoresHojePaginadaSteps  {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;
    
    @Quando("^processamos a solicitação de Lista Indicadores Hoje Paginada$")
    public void processamos_a_solicitação_de_Lista_Indicadores_Hoje_Paginada() throws Throwable {
        StringBuilder url = new StringBuilder("/indicators/intraday/list/paged?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterFormat())
                .append("&")
                .append(contextTest.getParameterItem())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParemeterSize());
        contextTest.setResultActions(this.mockMvc.perform(get(url.toString())));
    }

    @Entao("^deve exibir um JSON com os campos enviados e valor em next da Lista Indicadores Hoje Paginada$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_e_valor_em_next_da_Lista_Indicadores_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"145884086N\",\"docs\":[{\"date\":\"20170614111924\",\"price\":10.19,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.19},{\"date\":\"20170614111834\",\"price\":10.18,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.18},{\"date\":\"20170614111742\",\"price\":10.17,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.17},{\"date\":\"20170614111654\",\"price\":10.16,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.16},{\"date\":\"20170614111549\",\"price\":10.15,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.15}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Indicadores Hoje Paginada$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_CHANGED_e_valor_em_next_da_Lista_Indicadores_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"145884086N\",\"docs\":[{\"date\":\"20170614111924\",\"price\":10.19,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"pctChange\":10.19},{\"date\":\"20170614111834\",\"price\":10.18,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"pctChange\":10.18},{\"date\":\"20170614111742\",\"price\":10.17,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"pctChange\":10.17},{\"date\":\"20170614111654\",\"price\":10.16,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"pctChange\":10.16},{\"date\":\"20170614111549\",\"price\":10.15,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"pctChange\":10.15}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma Lista Indicadores Hoje Paginada com a funcao de callback contendo o JSON dos campos enviados e valor em next da Lista Indicadores Hoje Paginada$")
    public void deve_exibir_uma_Lista_Indicadores_Hoje_Paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_e_valor_em_next_da_Lista_Indicadores_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":\"145884086N\",\"docs\":[{\"date\":\"20170614111924\",\"price\":10.19,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.19},{\"date\":\"20170614111834\",\"price\":10.18,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.18},{\"date\":\"20170614111742\",\"price\":10.17,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.17},{\"date\":\"20170614111654\",\"price\":10.16,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.16},{\"date\":\"20170614111549\",\"price\":10.15,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.15}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma Lista Indicadores Hoje Paginada com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Indicadores Hoje Paginada$")
    public void deve_exibir_uma_Lista_Indicadores_Hoje_Paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_CHANGED_e_valor_em_next_da_Lista_Indicadores_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":\"145884086N\",\"docs\":[{\"date\":\"20170614111924\",\"price\":10.19,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"pctChange\":10.19},{\"date\":\"20170614111834\",\"price\":10.18,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"pctChange\":10.18},{\"date\":\"20170614111742\",\"price\":10.17,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"pctChange\":10.17},{\"date\":\"20170614111654\",\"price\":10.16,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"pctChange\":10.16},{\"date\":\"20170614111549\",\"price\":10.15,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"pctChange\":10.15}]});";

        assertEquals(expected, result);
    }

    @Dado("^processamos a solicitação de Lista Indicadores Hoje para a Primeira Página$")
    public void processamos_a_solicitação_de_Lista_Indicadores_Hoje_para_a_Primeira_Página() throws Throwable {
        StringBuilder url = new StringBuilder("/indicators/intraday/list/paged?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterFormat())
                .append("&")
                .append(contextTest.getParameterItem())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParemeterSize());
        contextTest.setResultActions(this.mockMvc.perform(get(url.toString())));
    }

    @Quando("^processamos a solicitação de Lista Indicadores Hoje para a Próxima Página$")
    public void processamos_a_solicitação_de_Lista_Indicadores_Hoje_para_a_Próxima_Página() throws Throwable {
        StringBuilder url = new StringBuilder("/indicators/intraday/list/paged?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterFormat())
                .append("&")
                .append(contextTest.getParameterItem())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParemeterSize())
                .append("&")
                .append(contextTest.getParameterNext());
        contextTest.setResultActions(this.mockMvc.perform(get(url.toString())));
    }

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSONP da Lista Indicadores Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_Indicadores_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"145884088P\",\"next\":\"145884087N\",\"docs\":[{\"date\":\"20170614111742\",\"price\":10.17,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.17},{\"date\":\"20170614111654\",\"price\":10.16,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.16}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSONP da Lista Indicadores Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSONP_da_Lista_Indicadores_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"145884086P\",\"next\":null,\"docs\":[{\"date\":\"20170614111549\",\"price\":10.15,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.15},{\"date\":\"20170614111444\",\"price\":10.14,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.14}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSON da Lista Indicadores Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_Indicadores_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"145884088P\",\"next\":\"145884087N\",\"docs\":[{\"date\":\"20170614111742\",\"price\":10.17,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.17},{\"date\":\"20170614111654\",\"price\":10.16,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.16}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSON da Lista Indicadores Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSON_da_Lista_Indicadores_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"145884086P\",\"next\":null,\"docs\":[{\"date\":\"20170614111549\",\"price\":10.15,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.15},{\"date\":\"20170614111444\",\"price\":10.14,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.14}]}";

        assertEquals(expected, result);
    }

    @Dado("^processamos a solicitação de Lista Indicadores Hoje para a Última Página$")
    public void processamos_a_solicitação_de_Lista_Indicadores_Hoje_para_a_Última_Página() throws Throwable {
        StringBuilder url = new StringBuilder("/indicators/intraday/list/paged?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterFormat())
                .append("&")
                .append(contextTest.getParameterItem())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParemeterSize())
                .append("&")
                .append(contextTest.getParameterNext());
        contextTest.setResultActions(this.mockMvc.perform(get(url.toString())));
    }

    @Quando("^processamos a solicitação de Lista Indicadores Hoje para a Página Anterior$")
    public void processamos_a_solicitação_de_Lista_Indicadores_Hoje_para_a_Página_Anterior() throws Throwable {
        StringBuilder url = new StringBuilder("/indicators/intraday/list/paged?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterFormat())
                .append("&")
                .append(contextTest.getParameterItem())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParemeterSize())
                .append("&")
                .append(contextTest.getParameterPrev());
        contextTest.setResultActions(this.mockMvc.perform(get(url.toString())));
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSONP da Lista Indicadores Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSONP_da_Lista_Indicadores_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"145884088P\",\"next\":\"145884087N\",\"docs\":[{\"date\":\"20170614111742\",\"price\":10.17,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.17},{\"date\":\"20170614111654\",\"price\":10.16,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.16}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para proxima pag\\. no formato JSONP da Lista Indicadores Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_proxima_pag_no_formato_JSONP_da_Lista_Indicadores_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"145884089N\",\"docs\":[{\"date\":\"20170614111924\",\"price\":10.19,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.19},{\"date\":\"20170614111834\",\"price\":10.18,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.18}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSON da Lista Indicadores Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSON_da_Lista_Indicadores_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"145884088P\",\"next\":\"145884087N\",\"docs\":[{\"date\":\"20170614111742\",\"price\":10.17,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.17},{\"date\":\"20170614111654\",\"price\":10.16,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.16}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para proxima pag\\. no formato JSON da Lista Indicadores Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_proxima_pag_no_formato_JSON_da_Lista_Indicadores_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"145884089N\",\"docs\":[{\"date\":\"20170614111924\",\"price\":10.19,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.19},{\"date\":\"20170614111834\",\"price\":10.18,\"exchangeasset\":\"CDI\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":10.14,\"ask\":0,\"change\":0,\"pctChange\":10.18}]}";

        assertEquals(expected, result);
    }

    
}
