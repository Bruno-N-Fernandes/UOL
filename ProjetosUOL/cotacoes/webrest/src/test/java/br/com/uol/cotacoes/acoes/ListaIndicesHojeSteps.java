package br.com.uol.cotacoes.acoes;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.pt.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vrx_mtoledo on 26/06/17.
 */
public class ListaIndicesHojeSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;


    @Quando("^processamos a solicitação de Lista Indice Hoje$")
    public void processamos_a_solicitação_de_Lista_Indice_Hoje() throws Throwable {
        StringBuilder url = new StringBuilder("/index/intraday/list?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterItem())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParameterFormat());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir um JSON com os campos enviados da Lista Indice Hoje$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_da_Lista_Indice_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":161122130,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"id\":161122106,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"id\":161122091,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"id\":161122076,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"id\":161122047,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo CHANGED da Lista Indice Hoje$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_CHANGED_da_Lista_Indice_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":161122130,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"id\":161122106,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"id\":161122091,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"id\":161122076,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"id\":161122047,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um CSV com os campos enviados da Lista Indice Hoje$")
    public void deve_exibir_um_CSV_com_os_campos_enviados_da_Lista_Indice_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Identificação interna;Preço;Ativo;Máximo(R$);Mínimo(R$);Abertura(R$);Volume;Fechamento(R$);Venda;Compra;Variação(R$);Variação(%);\r\n" +
                "161122130;12527.167;INDX IND;12542.208;12465.031;12485.535;0.000;12485.770;0.000;0.000;41.398;0.33;\r\n" +
                "161122106;12527.167;INDX IND;12542.208;12465.031;12485.535;0.000;12485.770;0.000;0.000;41.398;0.33;\r\n" +
                "161122091;12527.167;INDX IND;12542.208;12465.031;12485.535;0.000;12485.770;0.000;0.000;41.398;0.33;\r\n" +
                "161122076;12527.167;INDX IND;12542.208;12465.031;12485.535;0.000;12485.770;0.000;0.000;41.398;0.33;\r\n" +
                "161122047;12527.167;INDX IND;12542.208;12465.031;12485.535;0.000;12485.770;0.000;0.000;41.398;0.33;\r\n";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um CSV dos campos enviados sem o campo CHANGED da Lista Indice Hoje$")
    public void deve_exibir_um_CSV_dos_campos_enviados_sem_o_campo_CHANGED_da_Lista_Indice_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Identificação interna;Preço;Ativo;Máximo(R$);Mínimo(R$);Abertura(R$);Volume;Fechamento(R$);Venda;Compra;Variação(%);\r\n" +
                "161122130;12527.167;INDX IND;12542.208;12465.031;12485.535;0.000;12485.770;0.000;0.000;0.33;\r\n" +
                "161122106;12527.167;INDX IND;12542.208;12465.031;12485.535;0.000;12485.770;0.000;0.000;0.33;\r\n" +
                "161122091;12527.167;INDX IND;12542.208;12465.031;12485.535;0.000;12485.770;0.000;0.000;0.33;\r\n" +
                "161122076;12527.167;INDX IND;12542.208;12465.031;12485.535;0.000;12485.770;0.000;0.000;0.33;\r\n" +
                "161122047;12527.167;INDX IND;12542.208;12465.031;12485.535;0.000;12485.770;0.000;0.000;0.33;\r\n";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista Indice Hoje com a funcao de callback contendo o JSON dos campos enviados$")
    public void deve_exibir_uma_Lista_Indice_Hoje_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":161122130,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"id\":161122106,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"id\":161122091,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"id\":161122076,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33},{\"id\":161122047,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"change\":41.398,\"pctChange\":0.33}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista Indice Hoje com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED$")
    public void deve_exibir_uma_Lista_Indice_Hoje_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_CHANGED() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":161122130,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"id\":161122106,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"id\":161122091,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"id\":161122076,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33},{\"id\":161122047,\"price\":12527.167,\"exchangeasset\":\"INDX IND\",\"high\":12542.208,\"low\":12465.031,\"open\":12485.535,\"volume\":0,\"close\":12485.77,\"bid\":0,\"ask\":0,\"pctChange\":0.33}]});";
        assertEquals( expected, result);
    }

}
