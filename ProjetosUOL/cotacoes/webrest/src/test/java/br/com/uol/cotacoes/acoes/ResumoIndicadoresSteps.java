package br.com.uol.cotacoes.acoes;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

/**
 * Created by vrx_mtoledo on 14/06/17.
 */
@Profile("test")
public class ResumoIndicadoresSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;
    @Quando("^processamos a solicitação de resumo de Indicadores$")
    public void processamos_a_solicitação_de_resumo_de_Indicadores() throws Throwable {
        StringBuilder url = new StringBuilder("/indicators/summary?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParameterItem());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados do resumo de Indicadores$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_do_resumo_de_Indicadores() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":145884083,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados do resumo de Indicadores$")
    public void deve_exibir_um_JSON_dos_campos_enviados_do_resumo_de_Indicadores() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":145884083,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"ask\":112.917,\"change\":0,\"pctChange\":112.32}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do resumo de Indicadores$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_campo_deve_ser_ignorado_do_resumo_de_Indicadores(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":145884083,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"change\":0,\"pctChange\":112.32}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do resumo de Indicadores$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_campo_deve_ser_ignorado_do_resumo_de_Indicadores(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":145884083,\"price\":112.321,\"exchangeasset\":\"GLOBAL 40\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":112.321,\"change\":0,\"pctChange\":112.32}]}";
        assertEquals( expected, result);
    }
}
