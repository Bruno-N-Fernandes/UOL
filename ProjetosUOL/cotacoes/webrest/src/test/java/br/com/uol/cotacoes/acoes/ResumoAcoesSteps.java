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
 * Created by vrx_mtoledo on 13/06/17.
 */
@Profile("test")
public class ResumoAcoesSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;

    @Quando("^processamos a solicitação de resumo de acao$")
    public void processamos_a_solicitação_de_resumo_de_acao() throws Throwable {
        StringBuilder url = new StringBuilder("/asset/summary?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParameterItem());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados do resumo de ação$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_do_resumo_de_ação() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados do resumo de ação$")
    public void deve_exibir_um_JSON_dos_campos_enviados_do_resumo_de_ação() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do resumo de ação$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_campo_deve_ser_ignorado_do_resumo_de_ação(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"change\":-0.02,\"pctChange\":-0.35}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo \"([^\"]*)\" \\(campo deve ser ignorado\\) do resumo de ação$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_campo_deve_ser_ignorado_do_resumo_de_ação(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"change\":-0.02,\"pctChange\":-0.35}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir os dados disponíveis da ultima negociacao da acao em (\\d+)/(\\d+)/(\\d+)$")
    public void deve_exibir_os_dados_disponíveis_da_ultima_negociacao_da_acao_em(int arg1, int arg2, int arg3) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":123456784,\"price\":5.69,\"exchangeasset\":\"VARIG TRANSP ON 2\",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir dados de acoes vazios$")
    public void deve_exibir_dados_de_acoes_vazios() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[]});";
        assertEquals( expected, result);
    }
}
