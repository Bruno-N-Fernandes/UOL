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
public class ListaCommoditiesHojeSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;


    @Quando("^processamos a solicitação de Lista Commodities Hoje$")
    public void processamos_a_solicitação_de_Lista_Commodities_Hoje() throws Throwable {
        StringBuilder url = new StringBuilder("/commodities/intraday/list?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterItem())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParameterFormat());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir um JSON com os campos enviados da Lista Commodities Hoje$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_da_Lista_Commodities_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":145884082,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":46.39,\"ask\":46.41,\"change\":0,\"pctChange\":0},{\"id\":145884081,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":883.5,\"low\":886.5,\"open\":0,\"volume\":0,\"close\":882.5,\"bid\":882.6,\"ask\":888.6,\"change\":-4.9,\"pctChange\":-0.55},{\"id\":145884080,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":927.3,\"low\":927,\"open\":0,\"volume\":0,\"close\":923.5,\"bid\":924.9,\"ask\":929.1,\"change\":0.1,\"pctChange\":0.01},{\"id\":145884079,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":16.88,\"low\":16.838,\"open\":0,\"volume\":0,\"close\":16.86,\"bid\":16.849,\"ask\":16.855,\"change\":0.01,\"pctChange\":0.06},{\"id\":145884078,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":1266.97,\"low\":1266.26,\"open\":0,\"volume\":0,\"close\":1265.35,\"bid\":1266.64,\"ask\":1266.82,\"change\":-0.06,\"pctChange\":0}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo CHANGED da Lista Commodities Hoje$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_CHANGED_da_Lista_Commodities_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":145884082,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":46.39,\"ask\":46.41,\"pctChange\":0},{\"id\":145884081,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":883.5,\"low\":886.5,\"open\":0,\"volume\":0,\"close\":882.5,\"bid\":882.6,\"ask\":888.6,\"pctChange\":-0.55},{\"id\":145884080,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":927.3,\"low\":927,\"open\":0,\"volume\":0,\"close\":923.5,\"bid\":924.9,\"ask\":929.1,\"pctChange\":0.01},{\"id\":145884079,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":16.88,\"low\":16.838,\"open\":0,\"volume\":0,\"close\":16.86,\"bid\":16.849,\"ask\":16.855,\"pctChange\":0.06},{\"id\":145884078,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":1266.97,\"low\":1266.26,\"open\":0,\"volume\":0,\"close\":1265.35,\"bid\":1266.64,\"ask\":1266.82,\"pctChange\":0}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um CSV com os campos enviados da Lista Commodities Hoje$")
    public void deve_exibir_um_CSV_com_os_campos_enviados_da_Lista_Commodities_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Identificação interna;Preço;Ativo;Máximo(R$);Mínimo(R$);Abertura(R$);Volume;Fechamento(R$);Venda;Compra;Variação(R$);Variação(%);\r\n" +
                "145884082;0.000;OURO;0.000;0.000;0.000;0.000;0.000;46.390;46.410;0.000;0.00;\r\n" +
                "145884081;0.000;OURO;883.500;886.500;0.000;0.000;882.500;882.600;888.600;-4.900;-0.55;\r\n" +
                "145884080;0.000;OURO;927.300;927.000;0.000;0.000;923.500;924.900;929.100;0.100;0.01;\r\n" +
                "145884079;0.000;OURO;16.880;16.838;0.000;0.000;16.860;16.849;16.855;0.010;0.06;\r\n" +
                "145884078;0.000;OURO;1266.970;1266.260;0.000;0.000;1265.350;1266.640;1266.820;-0.060;0.00;\r\n";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um CSV dos campos enviados sem o campo CHANGED da Lista Commodities Hoje$")
    public void deve_exibir_um_CSV_dos_campos_enviados_sem_o_campo_CHANGED_da_Lista_Commodities_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Identificação interna;Preço;Ativo;Máximo(R$);Mínimo(R$);Abertura(R$);Volume;Fechamento(R$);Venda;Compra;Variação(%);\r\n" +
                "145884082;0.000;OURO;0.000;0.000;0.000;0.000;0.000;46.390;46.410;0.00;\r\n" +
                "145884081;0.000;OURO;883.500;886.500;0.000;0.000;882.500;882.600;888.600;-0.55;\r\n" +
                "145884080;0.000;OURO;927.300;927.000;0.000;0.000;923.500;924.900;929.100;0.01;\r\n" +
                "145884079;0.000;OURO;16.880;16.838;0.000;0.000;16.860;16.849;16.855;0.06;\r\n" +
                "145884078;0.000;OURO;1266.970;1266.260;0.000;0.000;1265.350;1266.640;1266.820;0.00;\r\n";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista Commodities Hoje com a funcao de callback contendo o JSON dos campos enviados$")
    public void deve_exibir_uma_Lista_Commodities_Hoje_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":145884082,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":46.39,\"ask\":46.41,\"change\":0,\"pctChange\":0},{\"id\":145884081,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":883.5,\"low\":886.5,\"open\":0,\"volume\":0,\"close\":882.5,\"bid\":882.6,\"ask\":888.6,\"change\":-4.9,\"pctChange\":-0.55},{\"id\":145884080,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":927.3,\"low\":927,\"open\":0,\"volume\":0,\"close\":923.5,\"bid\":924.9,\"ask\":929.1,\"change\":0.1,\"pctChange\":0.01},{\"id\":145884079,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":16.88,\"low\":16.838,\"open\":0,\"volume\":0,\"close\":16.86,\"bid\":16.849,\"ask\":16.855,\"change\":0.01,\"pctChange\":0.06},{\"id\":145884078,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":1266.97,\"low\":1266.26,\"open\":0,\"volume\":0,\"close\":1265.35,\"bid\":1266.64,\"ask\":1266.82,\"change\":-0.06,\"pctChange\":0}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista Commodities Hoje com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED$")
    public void deve_exibir_uma_Lista_Commodities_Hoje_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_CHANGED() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":145884082,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":0,\"low\":0,\"open\":0,\"volume\":0,\"close\":0,\"bid\":46.39,\"ask\":46.41,\"pctChange\":0},{\"id\":145884081,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":883.5,\"low\":886.5,\"open\":0,\"volume\":0,\"close\":882.5,\"bid\":882.6,\"ask\":888.6,\"pctChange\":-0.55},{\"id\":145884080,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":927.3,\"low\":927,\"open\":0,\"volume\":0,\"close\":923.5,\"bid\":924.9,\"ask\":929.1,\"pctChange\":0.01},{\"id\":145884079,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":16.88,\"low\":16.838,\"open\":0,\"volume\":0,\"close\":16.86,\"bid\":16.849,\"ask\":16.855,\"pctChange\":0.06},{\"id\":145884078,\"price\":0,\"exchangeasset\":\"OURO\",\"high\":1266.97,\"low\":1266.26,\"open\":0,\"volume\":0,\"close\":1265.35,\"bid\":1266.64,\"ask\":1266.82,\"pctChange\":0}]});";
        assertEquals( expected, result);
    }

}
