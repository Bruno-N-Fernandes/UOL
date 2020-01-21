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
public class ListaInflacaoHojeSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;


    @Quando("^processamos a solicitação de Lista Inflacao Hoje$")
    public void processamos_a_solicitação_de_Lista_Inflacao_Hoje() throws Throwable {
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

    @Entao("^deve exibir um JSON com os campos enviados da Lista Inflacao Hoje$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_da_Lista_Inflacao_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":161122153,\"price\":1004.13,\"exchangeasset\":\"IPC\",\"high\":1004.57,\"low\":1001.32,\"open\":1001.32,\"volume\":69452,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":2.83,\"pctChange\":0.28},{\"id\":161122151,\"price\":1003.9,\"exchangeasset\":\"IPC\",\"high\":1003.9,\"low\":1001.32,\"open\":1001.32,\"volume\":38811,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":2.6,\"pctChange\":0.26},{\"id\":161122149,\"price\":1002.84,\"exchangeasset\":\"IPC\",\"high\":1002.84,\"low\":1001.32,\"open\":1001.32,\"volume\":28831,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.54,\"pctChange\":0.15},{\"id\":161122147,\"price\":1002.57,\"exchangeasset\":\"IPC\",\"high\":1002.57,\"low\":1001.32,\"open\":1001.32,\"volume\":26416,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.27,\"pctChange\":0.13},{\"id\":161122145,\"price\":1002.16,\"exchangeasset\":\"IPC\",\"high\":1002.2,\"low\":1001.32,\"open\":1001.32,\"volume\":25607,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":0.86,\"pctChange\":0.09}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo CHANGED da Lista Inflacao Hoje$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_CHANGED_da_Lista_Inflacao_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":161122153,\"price\":1004.13,\"exchangeasset\":\"IPC\",\"high\":1004.57,\"low\":1001.32,\"open\":1001.32,\"volume\":69452,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.28},{\"id\":161122151,\"price\":1003.9,\"exchangeasset\":\"IPC\",\"high\":1003.9,\"low\":1001.32,\"open\":1001.32,\"volume\":38811,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.26},{\"id\":161122149,\"price\":1002.84,\"exchangeasset\":\"IPC\",\"high\":1002.84,\"low\":1001.32,\"open\":1001.32,\"volume\":28831,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.15},{\"id\":161122147,\"price\":1002.57,\"exchangeasset\":\"IPC\",\"high\":1002.57,\"low\":1001.32,\"open\":1001.32,\"volume\":26416,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.13},{\"id\":161122145,\"price\":1002.16,\"exchangeasset\":\"IPC\",\"high\":1002.2,\"low\":1001.32,\"open\":1001.32,\"volume\":25607,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.09}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um CSV com os campos enviados da Lista Inflacao Hoje$")
    public void deve_exibir_um_CSV_com_os_campos_enviados_da_Lista_Inflacao_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Identificação interna;Preço;Ativo;Máximo(R$);Mínimo(R$);Abertura(R$);Volume;Fechamento(R$);Venda;Compra;Variação(R$);Variação(%);\r\n" +
                "161122153;1004.130;IPC;1004.570;1001.320;1001.320;69452.000;1001.300;0.000;0.000;2.830;0.28;\r\n" +
                "161122151;1003.900;IPC;1003.900;1001.320;1001.320;38811.000;1001.300;0.000;0.000;2.600;0.26;\r\n" +
                "161122149;1002.840;IPC;1002.840;1001.320;1001.320;28831.000;1001.300;0.000;0.000;1.540;0.15;\r\n" +
                "161122147;1002.570;IPC;1002.570;1001.320;1001.320;26416.000;1001.300;0.000;0.000;1.270;0.13;\r\n" +
                "161122145;1002.160;IPC;1002.200;1001.320;1001.320;25607.000;1001.300;0.000;0.000;0.860;0.09;\r\n";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um CSV dos campos enviados sem o campo CHANGED da Lista Inflacao Hoje$")
    public void deve_exibir_um_CSV_dos_campos_enviados_sem_o_campo_CHANGED_da_Lista_Inflacao_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Identificação interna;Preço;Ativo;Máximo(R$);Mínimo(R$);Abertura(R$);Volume;Fechamento(R$);Venda;Compra;Variação(%);\r\n" +
                "161122153;1004.130;IPC;1004.570;1001.320;1001.320;69452.000;1001.300;0.000;0.000;0.28;\r\n" +
                "161122151;1003.900;IPC;1003.900;1001.320;1001.320;38811.000;1001.300;0.000;0.000;0.26;\r\n" +
                "161122149;1002.840;IPC;1002.840;1001.320;1001.320;28831.000;1001.300;0.000;0.000;0.15;\r\n" +
                "161122147;1002.570;IPC;1002.570;1001.320;1001.320;26416.000;1001.300;0.000;0.000;0.13;\r\n" +
                "161122145;1002.160;IPC;1002.200;1001.320;1001.320;25607.000;1001.300;0.000;0.000;0.09;\r\n";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista Inflacao Hoje com a funcao de callback contendo o JSON dos campos enviados$")
    public void deve_exibir_uma_Lista_Inflacao_Hoje_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":161122153,\"price\":1004.13,\"exchangeasset\":\"IPC\",\"high\":1004.57,\"low\":1001.32,\"open\":1001.32,\"volume\":69452,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":2.83,\"pctChange\":0.28},{\"id\":161122151,\"price\":1003.9,\"exchangeasset\":\"IPC\",\"high\":1003.9,\"low\":1001.32,\"open\":1001.32,\"volume\":38811,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":2.6,\"pctChange\":0.26},{\"id\":161122149,\"price\":1002.84,\"exchangeasset\":\"IPC\",\"high\":1002.84,\"low\":1001.32,\"open\":1001.32,\"volume\":28831,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.54,\"pctChange\":0.15},{\"id\":161122147,\"price\":1002.57,\"exchangeasset\":\"IPC\",\"high\":1002.57,\"low\":1001.32,\"open\":1001.32,\"volume\":26416,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":1.27,\"pctChange\":0.13},{\"id\":161122145,\"price\":1002.16,\"exchangeasset\":\"IPC\",\"high\":1002.2,\"low\":1001.32,\"open\":1001.32,\"volume\":25607,\"close\":1001.3,\"bid\":0,\"ask\":0,\"change\":0.86,\"pctChange\":0.09}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista Inflacao Hoje com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED$")
    public void deve_exibir_uma_Lista_Inflacao_Hoje_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_CHANGED() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":161122153,\"price\":1004.13,\"exchangeasset\":\"IPC\",\"high\":1004.57,\"low\":1001.32,\"open\":1001.32,\"volume\":69452,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.28},{\"id\":161122151,\"price\":1003.9,\"exchangeasset\":\"IPC\",\"high\":1003.9,\"low\":1001.32,\"open\":1001.32,\"volume\":38811,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.26},{\"id\":161122149,\"price\":1002.84,\"exchangeasset\":\"IPC\",\"high\":1002.84,\"low\":1001.32,\"open\":1001.32,\"volume\":28831,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.15},{\"id\":161122147,\"price\":1002.57,\"exchangeasset\":\"IPC\",\"high\":1002.57,\"low\":1001.32,\"open\":1001.32,\"volume\":26416,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.13},{\"id\":161122145,\"price\":1002.16,\"exchangeasset\":\"IPC\",\"high\":1002.2,\"low\":1001.32,\"open\":1001.32,\"volume\":25607,\"close\":1001.3,\"bid\":0,\"ask\":0,\"pctChange\":0.09}]});";
        assertEquals( expected, result);
    }

}
