package br.com.uol.cotacoes.acoes;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.es.Dado;
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
 * @author mzp_dferraz
 *
 */
@Profile("test")
public class ListaIndicesHojePaginadaSteps  {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;

    @Quando("^processamos a solicitação de Lista Indice Hoje Paginada$")
    public void processamos_a_solicitação_de_Lista_Indice_Hoje_Paginada() throws Throwable {
        StringBuilder url = new StringBuilder("/index/intraday/list/paged?")
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
                .append(contextTest.getParameterPrev())
                .append("&")
                .append(contextTest.getParameterNext());
        contextTest.setResultActions(this.mockMvc.perform(get(url.toString())));
    }

    @Entao("^deve exibir um JSON com os campos enviados e valor em next da Lista Indice Hoje Paginada$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_e_valor_em_next_da_Lista_Indice_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"161122047N\",\"docs\":[{\"date\":\"20170613172100\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170613171500\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170613171400\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170613171300\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170613171200\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Indice Hoje Paginada$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_CHANGED_e_valor_em_next_da_Lista_Indice_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"161122047N\",\"docs\":[{\"date\":\"20170613172100\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"date\":\"20170613171500\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"date\":\"20170613171400\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"date\":\"20170613171300\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"date\":\"20170613171200\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma Lista Indice Hoje Paginada com a funcao de callback contendo o JSON dos campos enviados e valor em next da Lista Indice Hoje Paginada$")
    public void deve_exibir_uma_Lista_Indice_Hoje_Paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_e_valor_em_next_da_Lista_Indice_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":\"161122047N\",\"docs\":[{\"date\":\"20170613172100\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170613171500\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170613171400\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170613171300\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170613171200\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma Lista Indice Hoje Paginada com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Indice Hoje Paginada$")
    public void deve_exibir_uma_Lista_Indice_Hoje_Paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_CHANGED_e_valor_em_next_da_Lista_Indice_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":\"161122047N\",\"docs\":[{\"date\":\"20170613172100\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"date\":\"20170613171500\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"date\":\"20170613171400\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"date\":\"20170613171300\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"date\":\"20170613171200\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33}]});";

        assertEquals(expected, result);
    }

    @Dado("^processamos a solicitação de Lista Indice Hoje para a Primeira Página$")
    public void processamos_a_solicitação_de_Lista_Indice_Hoje_para_a_Primeira_Página() throws Throwable {
        StringBuilder url = new StringBuilder("/index/intraday/list/paged?")
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

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSONP da Lista Indice Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_Indice_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"161122091P\",\"next\":\"161122076N\",\"docs\":[{\"date\":\"20170613171400\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170613171300\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";

        assertEquals(expected, result);
    }

    @Dado("^processamos a solicitação de Lista Indice Hoje para a Próxima Página$")
    public void processamos_a_solicitação_de_Lista_Indice_Hoje_para_a_Próxima_Página() throws Throwable {
        StringBuilder url = new StringBuilder("/index/intraday/list/paged?")
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

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSONP da Lista Indice Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSONP_da_Lista_Indice_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"161122047P\",\"next\":null,\"docs\":[{\"date\":\"20170613171200\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170612100000\",\"price\":12561.68,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12561.68,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-5.342,\"pctChange\":-0.04}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSON da Lista Indice Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_Indice_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"161122091P\",\"next\":\"161122076N\",\"docs\":[{\"date\":\"20170613171400\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170613171300\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSON da Lista Indice Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSON_da_Lista_Indice_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"161122047P\",\"next\":null,\"docs\":[{\"date\":\"20170613171200\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170612100000\",\"price\":12561.68,\"exchangeasset\":\"INDX IND\",\"high\":12567.132,\"low\":12561.68,\"open\":12567.132,\"volume\":0,\"close\":12567.022,\"bid\":0,\"ask\":0,\"change\":-5.342,\"pctChange\":-0.04}]}";

        assertEquals(expected, result);
    }

    @Dado("^processamos a solicitação de Lista Indice Hoje para a Última Página$")
    public void processamos_a_solicitação_de_Lista_Indice_Hoje_para_a_Última_Página() throws Throwable {
        StringBuilder url = new StringBuilder("/index/intraday/list/paged?")
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

    @Quando("^processamos a solicitação de Lista Indice Hoje para a Página Anterior$")
    public void processamos_a_solicitação_de_Lista_Indice_Hoje_para_a_Página_Anterior() throws Throwable {
        StringBuilder url = new StringBuilder("/index/intraday/list/paged?")
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

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSONP da Lista Indice Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSONP_da_Lista_Indice_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"161122091P\",\"next\":\"161122076N\",\"docs\":[{\"date\":\"20170613171400\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170613171300\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para proxima pag\\. no formato JSONP da Lista Indice Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_proxima_pag_no_formato_JSONP_da_Lista_Indice_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"161122106N\",\"docs\":[{\"date\":\"20170613172100\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170613171500\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSON da Lista Indice Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSON_da_Lista_Indice_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"161122091P\",\"next\":\"161122076N\",\"docs\":[{\"date\":\"20170613171400\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170613171300\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para proxima pag\\. no formato JSON da Lista Indice Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_proxima_pag_no_formato_JSON_da_Lista_Indice_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"161122106N\",\"docs\":[{\"date\":\"20170613172100\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"date\":\"20170613171500\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";

        assertEquals(expected, result);
    }
    
    @Dado("^que incluímos novos valores para esse indice no mesmo dia$")
    public void que_incluímos_novos_valores_para_esse_indice_no_mesmo_dia() throws Throwable {

        String insertTableSQL = "insert into asset_intraday ("
        		+ " idt_asset_intraday, num_price, idt_exchange_asset, num_high, num_low, num_open, num_volume, num_close, num_bid, num_ask, num_change, num_pct_change,dat_last_update)"        		
        		+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection jdbcConnection = SessionFactoryUtils.getDataSource( sessionFactory).getConnection();

        PreparedStatement preparedStatement = jdbcConnection.prepareStatement(insertTableSQL);
        preparedStatement.setInt(1, 161122131);
        preparedStatement.setDouble(2, 5.65);
        preparedStatement.setInt(3, 10);
        preparedStatement.setDouble(4, 5.69);
        preparedStatement.setDouble(5, 5.69);
        preparedStatement.setDouble(6, 100.0);
        preparedStatement.setDouble(7, 5.78);
        preparedStatement.setDouble(8, 5.55);
        preparedStatement.setDouble(9, 5.7);
        preparedStatement.setDouble(10, -0.12);
        preparedStatement.setDouble(11, -2.65);
        preparedStatement.setDouble(12, 3.9999);
        preparedStatement.setTimestamp(13, Timestamp.valueOf(LocalDateTime.of(2017,6,13,17,30,0)));

        preparedStatement .executeUpdate();
        preparedStatement.close();
        jdbcConnection.close();
    }

    @Entao("^deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag\\. no formato JSONP da Lista Indice Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_igual_a_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_Indice_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"161122130N\",\"docs\":[{\"date\":\"20170613173000\",\"price\":5.65,\"exchangeasset\":\"INDX IND\",\"high\":5.69,\"low\":5.69,\"open\":100,\"volume\":5.78,\"close\":5.55,\"bid\":5.7,\"ask\":-0.12,\"change\":-2.65,\"pctChange\":4},{\"date\":\"20170613172100\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag\\. no formato JSON da Lista Indice Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_igual_a_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_Indice_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"161122130N\",\"docs\":[{\"date\":\"20170613173000\",\"price\":5.65,\"exchangeasset\":\"INDX IND\",\"high\":5.69,\"low\":5.69,\"open\":100,\"volume\":5.78,\"close\":5.55,\"bid\":5.7,\"ask\":-0.12,\"change\":-2.65,\"pctChange\":4},{\"date\":\"20170613172100\",\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";

        assertEquals(expected, result);
    }

}
