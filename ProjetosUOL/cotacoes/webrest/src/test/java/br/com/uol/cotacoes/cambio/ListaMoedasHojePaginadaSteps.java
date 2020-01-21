package br.com.uol.cotacoes.cambio;

import br.com.uol.cotacoes.ContextTest;
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
 * Created by vrx_mtoledo on 02/06/17.
 */
@Profile("test")
public class ListaMoedasHojePaginadaSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;
    

    @Quando("^processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página$")
    public void for_processada_a_solicitação_de_Lista_de_Moedas_de_Hoje_para_a_Primeira_Página() throws Throwable {
        StringBuilder url = new StringBuilder("/currency/intraday/list/paged?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterFormat())
                .append("&")
                .append(contextTest.getParemeterCurrency())
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

    @Quando("^processamos a solicitação de Lista de Moedas de Hoje para a Página Anterior$")
    public void for_processada_a_solicitação_de_Lista_de_Moedas_de_Hoje_para_a_Página_Anterior() throws Throwable {
        StringBuilder url = new StringBuilder("/currency/intraday/list/paged?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterFormat())
                .append("&")
                .append(contextTest.getParemeterCurrency())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParemeterSize())
                .append("&")
                .append(contextTest.getParameterPrev());
        contextTest.setResultActions(this.mockMvc.perform(get(url.toString())));
    }

    @Quando("^processamos a solicitação de Lista de Moedas de Hoje para a Próxima Página$")
    public void for_processada_a_solicitação_de_Lista_de_Moedas_de_Hoje_para_a_Próxima_Página() throws Throwable {
        StringBuilder url = new StringBuilder("/currency/intraday/list/paged?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterFormat())
                .append("&")
                .append(contextTest.getParemeterCurrency())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParemeterSize())
                .append("&")
                .append(contextTest.getParameterNext());
        contextTest.setResultActions(this.mockMvc.perform(get(url.toString())));
    }

    @Entao("^deve exibir uma lista paginada com a funcao de callback contendo o JSON dos campos enviados e valor em next da Lista de Moedas De Hoje$")
    public void deve_exibir_uma_lista_paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_e_valor_em_next_da_Lista_de_Moedas_De_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"20641150N\",\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602104500\"},{\"id\":20641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\"20170602104000\"}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma lista paginada com o JSON dos campos enviados e valor em next da Lista de Moedas De Hoje$")
    public void deve_exibir_uma_lista_paginada_com_o_JSON_dos_campos_enviados_e_valor_em_next_da_Lista_de_Moedas_De_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"20641150N\",\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602104500\"},{\"id\":20641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\"20170602104000\"}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma lista paginada com a funcao de callback contendo o JSON dos campos enviados e valor em next sem o campo DATA \\(campo deve ser ignorado\\) da Lista de Moedas De Hoje$")
    public void deve_exibir_uma_lista_paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_e_valor_em_next_sem_o_campo_DATA_campo_deve_ser_ignorado_da_Lista_de_Moedas_De_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"20641150N\",\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0},{\"id\":20641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma lista paginada com o JSON dos campos enviados sem o campo DATA e valor em next \\(campo deve ser ignorado\\) da Lista de Moedas De Hoje$")
    public void deve_exibir_uma_lista_paginada_com_o_JSON_dos_campos_enviados_sem_o_campo_DATA_e_valor_em_next_campo_deve_ser_ignorado_da_Lista_de_Moedas_De_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"20641150N\",\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0},{\"id\":20641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma lista paginada com a funcao de callback contendo o JSON dos campos enviados com novo valor no topo e valor em \"([^\"]*)\" da Lista de Moedas De Hoje$")
    public void deve_exibir_uma_lista_paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_com_novo_valor_no_topo_e_valor_em_da_Lista_de_Moedas_De_Hoje(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"20641151N\",\"docs\":[{\"id\":20641153,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\"20170602110000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602104500\"}]});";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma lista paginada com o JSON dos campos enviados com novo valor no topo e valor em \"([^\"]*)\" da Lista de Moedas De Hoje$")
    public void deve_exibir_uma_lista_paginada_com_o_JSON_dos_campos_enviados_com_novo_valor_no_topo_e_valor_em_da_Lista_de_Moedas_De_Hoje(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"20641151N\",\"docs\":[{\"id\":20641153,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\"20170602110000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602104500\"}]}";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSONP da Lista de Moedas de Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_de_Moedas_de_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"20641150P\",\"next\":\"20641149N\",\"docs\":[{\"id\":20641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\"20170602104000\"},{\"id\":20641149,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602103500\"}]});";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSONP da Lista de Moedas de Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSONP_da_Lista_de_Moedas_de_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"20641148P\",\"next\":null,\"docs\":[{\"id\":20641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\"20170602103000\"}]});";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSON da Lista de Moedas de Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_de_Moedas_de_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"20641150P\",\"next\":\"20641149N\",\"docs\":[{\"id\":20641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\"20170602104000\"},{\"id\":20641149,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602103500\"}]}";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSON da Lista de Moedas de Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSON_da_Lista_de_Moedas_de_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"20641148P\",\"next\":null,\"docs\":[{\"id\":20641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\"20170602103000\"}]}";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSONP da Lista de Moedas de Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSONP_da_Lista_de_Moedas_de_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"20641150P\",\"next\":\"20641149N\",\"docs\":[{\"id\":20641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\"20170602104000\"},{\"id\":20641149,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602103500\"}]});";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para a proxima pag\\. no formato JSONP da Lista de Moedas de Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_de_Moedas_de_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"20641151N\",\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602104500\"}]});";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSON da Lista de Moedas de Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSON_da_Lista_de_Moedas_de_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"20641150P\",\"next\":\"20641149N\",\"docs\":[{\"id\":20641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\"20170602104000\"},{\"id\":20641149,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602103500\"}]}";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para a proxima pag\\. no formato JSON da Lista de Moedas de Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_de_Moedas_de_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"20641151N\",\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602104500\"}]}";
        assertEquals(expected, result);
    }
    
    @Entao("^deve exibir o ponteiro prev diferente de nulo e ponteiro next para a proxima pag\\. no formato JSONP da Lista de Moedas de Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_diferente_de_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_de_Moedas_de_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"20641152P\",\"next\":\"20641151N\",\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602104500\"}]});";
        
        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev diferente de nulo e ponteiro next para a proxima pag\\. no formato JSON da Lista de Moedas de Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_diferente_de_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_de_Moedas_de_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"20641152P\",\"next\":\"20641151N\",\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602104500\"}]}";
        
        assertEquals(expected, result);
    }
    
    @Entao("^deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag\\. no formato JSONP da Lista de Moedas de Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_igual_a_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_de_Moedas_de_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"20641152N\",\"docs\":[{\"id\":20641153,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\"20170602110000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"}]});";
        
        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag\\. no formato JSON da Lista de Moedas de Hoje Paginada$")
    public void deve_exibir_o_ponteiro_prev_igual_a_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_de_Moedas_de_Hoje_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"20641152N\",\"docs\":[{\"id\":20641153,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\"20170602110000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"}]}";
        
        assertEquals(expected, result);
    }

}
