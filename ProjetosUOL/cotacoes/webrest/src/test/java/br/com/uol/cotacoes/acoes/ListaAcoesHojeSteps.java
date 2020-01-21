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
public class ListaAcoesHojeSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;


    @Quando("^processamos a solicitação de Lista Acao Hoje$")
    public void processamos_a_solicitação_de_Lista_Acao_Hoje() throws Throwable {
        StringBuilder url = new StringBuilder("/asset/intraday/list?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterItem())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParameterFormat());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir um JSON com os campos enviados da Lista Acao Hoje$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_da_Lista_Acao_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"id\":161101078,\"price\":5.6,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":14100,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-1.75},{\"id\":161079383,\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":14000,\"close\":5.7,\"bid\":5.6,\"ask\":5.69,\"change\":-0.01,\"pctChange\":-0.18}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo CHANGED da Lista Acao Hoje$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_CHANGED_da_Lista_Acao_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"pctChange\":-0.35},{\"id\":161101078,\"price\":5.6,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":14100,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"pctChange\":-1.75},{\"id\":161079383,\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":14000,\"close\":5.7,\"bid\":5.6,\"ask\":5.69,\"pctChange\":-0.18}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um CSV com os campos enviados da Lista Acao Hoje$")
    public void deve_exibir_um_CSV_com_os_campos_enviados_da_Lista_Acao_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Identificação interna;Preço;Ativo;Máximo(R$);Mínimo(R$);Abertura(R$);Volume;Fechamento(R$);Venda;Compra;Variação(R$);Variação(%);\r\n" +
                "161121867;5.680;COTEMINAS PN    ;5.690;5.600;5.600;19100.000;5.700;5.300;5.680;-0.020;-0.35;\r\n" +
                "161101078;5.600;COTEMINAS PN    ;5.690;5.600;5.600;14100.000;5.700;5.150;5.650;-0.100;-1.75;\r\n" +
                "161079383;5.690;COTEMINAS PN    ;5.690;5.600;5.600;14000.000;5.700;5.600;5.690;-0.010;-0.18;\r\n";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um CSV dos campos enviados sem o campo CHANGED da Lista Acao Hoje$")
    public void deve_exibir_um_CSV_dos_campos_enviados_sem_o_campo_CHANGED_da_Lista_Acao_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Identificação interna;Preço;Ativo;Máximo(R$);Mínimo(R$);Abertura(R$);Volume;Fechamento(R$);Venda;Compra;Variação(%);\r\n" +
                "161121867;5.680;COTEMINAS PN    ;5.690;5.600;5.600;19100.000;5.700;5.300;5.680;-0.35;\r\n" +
                "161101078;5.600;COTEMINAS PN    ;5.690;5.600;5.600;14100.000;5.700;5.150;5.650;-1.75;\r\n" +
                "161079383;5.690;COTEMINAS PN    ;5.690;5.600;5.600;14000.000;5.700;5.600;5.690;-0.18;\r\n";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista Acao Hoje com a funcao de callback contendo o JSON dos campos enviados$")
    public void deve_exibir_uma_Lista_Acao_Hoje_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"change\":-0.02,\"pctChange\":-0.35},{\"id\":161101078,\"price\":5.6,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":14100,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"change\":-0.1,\"pctChange\":-1.75},{\"id\":161079383,\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":14000,\"close\":5.7,\"bid\":5.6,\"ask\":5.69,\"change\":-0.01,\"pctChange\":-0.18}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista Acao Hoje com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED$")
    public void deve_exibir_uma_Lista_Acao_Hoje_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_CHANGED() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":161121867,\"price\":5.68,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":19100,\"close\":5.7,\"bid\":5.3,\"ask\":5.68,\"pctChange\":-0.35},{\"id\":161101078,\"price\":5.6,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":14100,\"close\":5.7,\"bid\":5.15,\"ask\":5.65,\"pctChange\":-1.75},{\"id\":161079383,\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.6,\"open\":5.6,\"volume\":14000,\"close\":5.7,\"bid\":5.6,\"ask\":5.69,\"pctChange\":-0.18}]});";
        assertEquals( expected, result);
    }

}
