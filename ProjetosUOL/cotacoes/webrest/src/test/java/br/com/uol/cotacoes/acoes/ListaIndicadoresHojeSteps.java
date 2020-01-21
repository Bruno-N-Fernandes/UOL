package br.com.uol.cotacoes.acoes;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vrx_mtoledo on 26/06/17.
 */
public class ListaIndicadoresHojeSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;


    @Quando("^processamos a solicitação de Lista Indicadores Hoje$")
    public void processamos_a_solicitação_de_Lista_Indicadores_Hoje() throws Throwable {
        StringBuilder url = new StringBuilder("/inflation/intraday/list?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterItem())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParameterFormat());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir um JSON com os campos enviados da Lista Indicadores Hoje$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_da_Lista_Indicadores_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":145884083,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32},{\"id\":145884077,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32},{\"id\":145884076,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo CHANGED da Lista Indicadores Hoje$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_CHANGED_da_Lista_Indicadores_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":145884083,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"pctChange\":112.32},{\"id\":145884077,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"pctChange\":112.32},{\"id\":145884076,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"pctChange\":112.32}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um CSV com os campos enviados da Lista Indicadores Hoje$")
    public void deve_exibir_um_CSV_com_os_campos_enviados_da_Lista_Indicadores_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Identificação interna;Preço;Ativo;Máximo(R$);Mínimo(R$);Abertura(R$);Volume;Fechamento(R$);Venda;Compra;Variação(R$);Variação(%);\r\n" +
                "145884083;112.321;GLOBAL 40;0.000;0.000;0.000;0.000;0.000;112.321;112.917;0.000;112.32;\r\n" +
                "145884077;112.321;GLOBAL 40;0.000;0.000;0.000;0.000;0.000;112.321;112.917;0.000;112.32;\r\n" +
                "145884076;112.321;GLOBAL 40;0.000;0.000;0.000;0.000;0.000;112.321;112.917;0.000;112.32;\r\n";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um CSV dos campos enviados sem o campo CHANGED da Lista Indicadores Hoje$")
    public void deve_exibir_um_CSV_dos_campos_enviados_sem_o_campo_CHANGED_da_Lista_Indicadores_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Identificação interna;Preço;Ativo;Máximo(R$);Mínimo(R$);Abertura(R$);Volume;Fechamento(R$);Venda;Compra;Variação(%);\r\n" +
                "145884083;112.321;GLOBAL 40;0.000;0.000;0.000;0.000;0.000;112.321;112.917;112.32;\r\n" +
                "145884077;112.321;GLOBAL 40;0.000;0.000;0.000;0.000;0.000;112.321;112.917;112.32;\r\n" +
                "145884076;112.321;GLOBAL 40;0.000;0.000;0.000;0.000;0.000;112.321;112.917;112.32;\r\n";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista Indicadores Hoje com a funcao de callback contendo o JSON dos campos enviados$")
    public void deve_exibir_uma_Lista_Indicadores_Hoje_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":145884083,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32},{\"id\":145884077,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32},{\"id\":145884076,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista Indicadores Hoje com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED$")
    public void deve_exibir_uma_Lista_Indicadores_Hoje_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_CHANGED() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":145884083,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"pctChange\":112.32},{\"id\":145884077,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"pctChange\":112.32},{\"id\":145884076,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"pctChange\":112.32}]});";
        assertEquals( expected, result);
    }

}
