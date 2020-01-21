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
public class ListaInflacaoHojePaginadaSteps  {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;
    
    @Quando("^processamos a solicitação de Lista Inflacao Hoje Paginada$")
    public void processamos_a_solicitação_de_Lista_Inflacao_Hoje_Paginada() throws Throwable {
        StringBuilder url = new StringBuilder("/inflation/intraday/list/paged?")
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

    @Entao("^deve exibir um JSON com os campos enviados e valor em next da Lista Inflacao Hoje Paginada$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_e_valor_em_next_da_Lista_Inflacao_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"161122145N\",\"docs\":[{\"date\":\"20170614040600\",\"price\":1004.13,\"exchangeasset\":\"IPC\",\"high\":1004.57,\"low\":1001.32,\"open\":1001.32,\"volume\":69452,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":2.83,\"pctChange\":0.28},{\"date\":\"20170614040500\",\"price\":1003.9,\"exchangeasset\":\"IPC\",\"high\":1003.9,\"low\":1001.32,\"open\":1001.32,\"volume\":38811,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":2.6,\"pctChange\":0.26},{\"date\":\"20170614040200\",\"price\":1002.84,\"exchangeasset\":\"IPC\",\"high\":1002.84,\"low\":1001.32,\"open\":1001.32,\"volume\":28831,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.54,\"pctChange\":0.15},{\"date\":\"20170614040100\",\"price\":1002.57,\"exchangeasset\":\"IPC\",\"high\":1002.57,\"low\":1001.32,\"open\":1001.32,\"volume\":26416,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.27,\"pctChange\":0.13},{\"date\":\"20170614040000\",\"price\":1002.16,\"exchangeasset\":\"IPC\",\"high\":1002.2,\"low\":1001.32,\"open\":1001.32,\"volume\":25607,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":0.86,\"pctChange\":0.09}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Inflacao Hoje Paginada$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_CHANGED_e_valor_em_next_da_Lista_Inflacao_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"161122145N\",\"docs\":[{\"date\":\"20170614040600\",\"price\":1004.13,\"exchangeasset\":\"IPC\",\"high\":1004.57,\"low\":1001.32,\"open\":1001.32,\"volume\":69452,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.28},{\"date\":\"20170614040500\",\"price\":1003.9,\"exchangeasset\":\"IPC\",\"high\":1003.9,\"low\":1001.32,\"open\":1001.32,\"volume\":38811,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.26},{\"date\":\"20170614040200\",\"price\":1002.84,\"exchangeasset\":\"IPC\",\"high\":1002.84,\"low\":1001.32,\"open\":1001.32,\"volume\":28831,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.15},{\"date\":\"20170614040100\",\"price\":1002.57,\"exchangeasset\":\"IPC\",\"high\":1002.57,\"low\":1001.32,\"open\":1001.32,\"volume\":26416,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.13},{\"date\":\"20170614040000\",\"price\":1002.16,\"exchangeasset\":\"IPC\",\"high\":1002.2,\"low\":1001.32,\"open\":1001.32,\"volume\":25607,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.09}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma Lista Inflacao Hoje Paginada com a funcao de callback contendo o JSON dos campos enviados e valor em next da Lista Inflacao Hoje Paginada$")
    public void deve_exibir_uma_Lista_Inflacao_Hoje_Paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_e_valor_em_next_da_Lista_Inflacao_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":\"161122145N\",\"docs\":[{\"date\":\"20170614040600\",\"price\":1004.13,\"exchangeasset\":\"IPC\",\"high\":1004.57,\"low\":1001.32,\"open\":1001.32,\"volume\":69452,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":2.83,\"pctChange\":0.28},{\"date\":\"20170614040500\",\"price\":1003.9,\"exchangeasset\":\"IPC\",\"high\":1003.9,\"low\":1001.32,\"open\":1001.32,\"volume\":38811,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":2.6,\"pctChange\":0.26},{\"date\":\"20170614040200\",\"price\":1002.84,\"exchangeasset\":\"IPC\",\"high\":1002.84,\"low\":1001.32,\"open\":1001.32,\"volume\":28831,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.54,\"pctChange\":0.15},{\"date\":\"20170614040100\",\"price\":1002.57,\"exchangeasset\":\"IPC\",\"high\":1002.57,\"low\":1001.32,\"open\":1001.32,\"volume\":26416,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.27,\"pctChange\":0.13},{\"date\":\"20170614040000\",\"price\":1002.16,\"exchangeasset\":\"IPC\",\"high\":1002.2,\"low\":1001.32,\"open\":1001.32,\"volume\":25607,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":0.86,\"pctChange\":0.09}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma Lista Inflacao Hoje Paginada com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Inflacao Hoje Paginada$")
    public void deve_exibir_uma_Lista_Inflacao_Hoje_Paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_CHANGED_e_valor_em_next_da_Lista_Inflacao_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":\"161122145N\",\"docs\":[{\"date\":\"20170614040600\",\"price\":1004.13,\"exchangeasset\":\"IPC\",\"high\":1004.57,\"low\":1001.32,\"open\":1001.32,\"volume\":69452,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.28},{\"date\":\"20170614040500\",\"price\":1003.9,\"exchangeasset\":\"IPC\",\"high\":1003.9,\"low\":1001.32,\"open\":1001.32,\"volume\":38811,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.26},{\"date\":\"20170614040200\",\"price\":1002.84,\"exchangeasset\":\"IPC\",\"high\":1002.84,\"low\":1001.32,\"open\":1001.32,\"volume\":28831,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.15},{\"date\":\"20170614040100\",\"price\":1002.57,\"exchangeasset\":\"IPC\",\"high\":1002.57,\"low\":1001.32,\"open\":1001.32,\"volume\":26416,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.13},{\"date\":\"20170614040000\",\"price\":1002.16,\"exchangeasset\":\"IPC\",\"high\":1002.2,\"low\":1001.32,\"open\":1001.32,\"volume\":25607,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.09}]});";

        assertEquals(expected, result);
    }

    @Dado("^processamos a solicitação de Lista Inflacao Hoje para a Primeira Página$")
    public void processamos_a_solicitação_de_Lista_Inflacao_Hoje_para_a_Primeira_Página() throws Throwable {
        StringBuilder url = new StringBuilder("/inflation/intraday/list/paged?")
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

    @Quando("^processamos a solicitação de Lista Inflacao Hoje para a Próxima Página$")
    public void processamos_a_solicitação_de_Lista_Inflacao_Hoje_para_a_Próxima_Página() throws Throwable {
        StringBuilder url = new StringBuilder("/inflation/intraday/list/paged?")
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

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSONP da Lista Inflacao Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_Inflacao_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"161122149P\",\"next\":\"161122147N\",\"docs\":[{\"date\":\"20170614040200\",\"price\":1002.84,\"exchangeasset\":\"IPC\",\"high\":1002.84,\"low\":1001.32,\"open\":1001.32,\"volume\":28831,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.54,\"pctChange\":0.15},{\"date\":\"20170614040100\",\"price\":1002.57,\"exchangeasset\":\"IPC\",\"high\":1002.57,\"low\":1001.32,\"open\":1001.32,\"volume\":26416,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.27,\"pctChange\":0.13}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSONP da Lista Inflacao Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSONP_da_Lista_Inflacao_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"161122145P\",\"next\":null,\"docs\":[{\"date\":\"20170614040000\",\"price\":1002.16,\"exchangeasset\":\"IPC\",\"high\":1002.2,\"low\":1001.32,\"open\":1001.32,\"volume\":25607,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":0.86,\"pctChange\":0.09},{\"date\":\"20170613080000\",\"price\":1002.1,\"exchangeasset\":\"IPC\",\"high\":1002.2,\"low\":1001.32,\"open\":1001.32,\"volume\":25607,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":0.86,\"pctChange\":0.09}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSON da Lista Inflacao Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_Inflacao_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"161122149P\",\"next\":\"161122147N\",\"docs\":[{\"date\":\"20170614040200\",\"price\":1002.84,\"exchangeasset\":\"IPC\",\"high\":1002.84,\"low\":1001.32,\"open\":1001.32,\"volume\":28831,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.54,\"pctChange\":0.15},{\"date\":\"20170614040100\",\"price\":1002.57,\"exchangeasset\":\"IPC\",\"high\":1002.57,\"low\":1001.32,\"open\":1001.32,\"volume\":26416,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.27,\"pctChange\":0.13}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSON da Lista Inflacao Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSON_da_Lista_Inflacao_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"161122145P\",\"next\":null,\"docs\":[{\"date\":\"20170614040000\",\"price\":1002.16,\"exchangeasset\":\"IPC\",\"high\":1002.2,\"low\":1001.32,\"open\":1001.32,\"volume\":25607,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":0.86,\"pctChange\":0.09},{\"date\":\"20170613080000\",\"price\":1002.1,\"exchangeasset\":\"IPC\",\"high\":1002.2,\"low\":1001.32,\"open\":1001.32,\"volume\":25607,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":0.86,\"pctChange\":0.09}]}";

        assertEquals(expected, result);
    }

    @Dado("^processamos a solicitação de Lista Inflacao Hoje para a Última Página$")
    public void processamos_a_solicitação_de_Lista_Inflacao_Hoje_para_a_Última_Página() throws Throwable {
        StringBuilder url = new StringBuilder("/inflation/intraday/list/paged?")
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

    @Quando("^processamos a solicitação de Lista Inflacao Hoje para a Página Anterior$")
    public void processamos_a_solicitação_de_Lista_Inflacao_Hoje_para_a_Página_Anterior() throws Throwable {
        StringBuilder url = new StringBuilder("/inflation/intraday/list/paged?")
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

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSONP da Lista Inflacao Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSONP_da_Lista_Inflacao_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"161122149P\",\"next\":\"161122147N\",\"docs\":[{\"date\":\"20170614040200\",\"price\":1002.84,\"exchangeasset\":\"IPC\",\"high\":1002.84,\"low\":1001.32,\"open\":1001.32,\"volume\":28831,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.54,\"pctChange\":0.15},{\"date\":\"20170614040100\",\"price\":1002.57,\"exchangeasset\":\"IPC\",\"high\":1002.57,\"low\":1001.32,\"open\":1001.32,\"volume\":26416,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.27,\"pctChange\":0.13}]});";

        assertEquals(expected, result);
    }


    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para proxima pag\\. no formato JSONP da Lista Inflacao Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_proxima_pag_no_formato_JSONP_da_Lista_Inflacao_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"161122151N\",\"docs\":[{\"date\":\"20170614040600\",\"price\":1004.13,\"exchangeasset\":\"IPC\",\"high\":1004.57,\"low\":1001.32,\"open\":1001.32,\"volume\":69452,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":2.83,\"pctChange\":0.28},{\"date\":\"20170614040500\",\"price\":1003.9,\"exchangeasset\":\"IPC\",\"high\":1003.9,\"low\":1001.32,\"open\":1001.32,\"volume\":38811,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":2.6,\"pctChange\":0.26}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSON da Lista Inflacao Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSON_da_Lista_Inflacao_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"161122149P\",\"next\":\"161122147N\",\"docs\":[{\"date\":\"20170614040200\",\"price\":1002.84,\"exchangeasset\":\"IPC\",\"high\":1002.84,\"low\":1001.32,\"open\":1001.32,\"volume\":28831,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.54,\"pctChange\":0.15},{\"date\":\"20170614040100\",\"price\":1002.57,\"exchangeasset\":\"IPC\",\"high\":1002.57,\"low\":1001.32,\"open\":1001.32,\"volume\":26416,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.27,\"pctChange\":0.13}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para proxima pag\\. no formato JSON da Lista Inflacao Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_proxima_pag_no_formato_JSON_da_Lista_Inflacao_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"161122151N\",\"docs\":[{\"date\":\"20170614040600\",\"price\":1004.13,\"exchangeasset\":\"IPC\",\"high\":1004.57,\"low\":1001.32,\"open\":1001.32,\"volume\":69452,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":2.83,\"pctChange\":0.28},{\"date\":\"20170614040500\",\"price\":1003.9,\"exchangeasset\":\"IPC\",\"high\":1003.9,\"low\":1001.32,\"open\":1001.32,\"volume\":38811,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":2.6,\"pctChange\":0.26}]}";

        assertEquals(expected, result);
    }

    
}
