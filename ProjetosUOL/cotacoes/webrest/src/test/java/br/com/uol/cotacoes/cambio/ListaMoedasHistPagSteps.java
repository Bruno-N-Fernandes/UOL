package br.com.uol.cotacoes.cambio;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vrx_mtoledo on 06/06/17.
 */
@Profile("test")
public class ListaMoedasHistPagSteps {

    @Autowired
    private ContextTest contextTest;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    SessionFactory sessionFactory;

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
    private String yesterday = today.minusDays(1).format(dateFormat);
    private String twoDaysBefore = today.minusDays(2).format(dateFormat);
    private String oneWeekBefore = today.minusDays(7).format(dateFormat);
    private String oneWeekAndOneDayBefore = today.minusDays(7).minusDays(1).format(dateFormat);
    private String oneMonthBefore = today.minusMonths(1).format(dateFormat);
    private String oneMonthAndOneDayBefore = today.minusMonths(1).minusDays(1).format(dateFormat);
    private String threeMonthsBefore = today.minusMonths(3).format(dateFormat);
    private String threeMonthsAndOneDayBefore = today.minusMonths(3).minusDays(1).format(dateFormat);
    private String oneYearBefore = today.minusYears(1).format(dateFormat);
    private String oneYearAndOneDayBefore = today.minusYears(1).minusDays(1).format(dateFormat);


    @Quando("^processamos a solicitação de Lista de Moedas Histórica para a primeira página$")
    public void processamos_a_solicitação_de_Lista_de_Moedas_Histórica_para_a_primeira_página() throws Throwable {
        String url = "/currency/interday/list/paged?"
                +contextTest.getParameterJsonp()
                +"&"
                +contextTest.getParemeterCurrency()
                +"&"
                +contextTest.getParemeterFields()
                +"&"
                +contextTest.getParameterFormat()
                +"&"
                +contextTest.getParemeterSize();
        contextTest.setResultActions( this.mockMvc.perform(get(url)) );        
    }

    @Quando("^processamos a solicitação de Lista de Moedas Histórica para a página anterior$")
    public void processamos_a_solicitação_de_Lista_de_Moedas_Histórica_para_a_página_anterior() throws Throwable {
        String url = "/currency/interday/list/paged?"
                +contextTest.getParameterJsonp()
                +"&"
                +contextTest.getParemeterCurrency()
                +"&"
                +contextTest.getParemeterFields()
                +"&"
                +contextTest.getParameterFormat()
                +"&"
                +contextTest.getParemeterSize()
                +"&"
                +contextTest.getParameterPrev();
        contextTest.setResultActions( this.mockMvc.perform(get(url)) );
    }

    @Quando("^processamos a solicitação de Lista de Moedas Histórica para a próxima página$")
    public void processamos_a_solicitação_de_Lista_de_Moedas_Histórica_para_a_próxima_página() throws Throwable {
        String url = "/currency/interday/list/paged?"
                +contextTest.getParameterJsonp()
                +"&"
                +contextTest.getParemeterCurrency()
                +"&"
                +contextTest.getParemeterFields()
                +"&"
                +contextTest.getParameterFormat()
                +"&"
                +contextTest.getParemeterSize()
                +"&"
                +contextTest.getParameterNext();
        contextTest.setResultActions( this.mockMvc.perform(get(url)) );
    }

    @Entao("^deve exibir uma lista paginada com a funcao de callback contendo o JSON dos campos enviados e valor em \"([^\"]*)\" da Lista de Moedas Históricas$")
    public void deve_exibir_uma_lista_paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_e_valor_em_da_Lista_de_Moedas_Históricas(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"21641148N\",\"docs\":["
        +"{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        +twoDaysBefore
        +"\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneWeekBefore
        +"\"},{\"id\":21641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\""
        +oneWeekAndOneDayBefore
        +"\"}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados e valor em \"([^\"]*)\" da Lista de Moedas Histórica Paginada$")
    public void deve_exibir_um_JSON_dos_campos_enviados_e_valor_em_da_Lista_de_Moedas_Histórica_Paginada(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"21641148N\",\"docs\":["
        +"{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        +twoDaysBefore
        +"\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneWeekBefore
        +"\"},{\"id\":21641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\""
        +oneWeekAndOneDayBefore
        +"\"}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma lista paginada com a funcao de callback contendo o JSON dos campos enviados sem o campo DATA \\(campo deve ser ignorado\\) e valor em \"([^\"]*)\" da Lista de Moedas Histórica Paginada$")
    public void deve_exibir_uma_lista_paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_DATA_campo_deve_ser_ignorado_e_valor_em_da_Lista_de_Moedas_Histórica_Paginada(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"21641148N\",\"docs\":[{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0},{\"id\":21641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0}]});";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo DATA \\(campo deve ser ignorado\\) e valor em \"([^\"]*)\" da Lista de Moedas Histórica Paginada$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_DATA_campo_deve_ser_ignorado_e_valor_em_da_Lista_de_Moedas_Histórica_Paginada(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"21641148N\",\"docs\":[{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0},{\"id\":21641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0}]}";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSONP da Lista de Moedas Histórica Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_de_Moedas_Histórica_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"21641146P\",\"next\":\"21641142N\",\"docs\":["
        +"{\"id\":21641146,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneMonthBefore
        +"\"},{\"id\":21641144,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        +oneMonthAndOneDayBefore
        +"\"},{\"id\":21641142,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +threeMonthsBefore
        +"\"}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSONP da Lista de Moedas Histórica Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSONP_da_Lista_de_Moedas_Histórica_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"21641140P\",\"next\":null,\"docs\":["
        +"{\"id\":21641140,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        + threeMonthsAndOneDayBefore
        +"\"},{\"id\":21641138,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        + oneYearBefore
        +"\"},{\"id\":21641136,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        + oneYearAndOneDayBefore
        +"\"}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a primeira pag\\. e ponteiro next para a proxima pag\\. no formato JSON da Lista de Moedas Histórica Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_primeira_pag_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_de_Moedas_Histórica_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"21641146P\",\"next\":\"21641142N\",\"docs\":["
        +"{\"id\":21641146,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneMonthBefore
        +"\"},{\"id\":21641144,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        +oneMonthAndOneDayBefore
        +"\"},{\"id\":21641142,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +threeMonthsBefore
        +"\"}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next nulo no formato JSON da Lista de Moedas Histórica$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_nulo_no_formato_JSON_da_Lista_de_Moedas_Histórica() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"21641140P\",\"next\":null,\"docs\":["
        +"{\"id\":21641140,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        +threeMonthsAndOneDayBefore
        +"\"},{\"id\":21641138,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneYearBefore
        +"\"},{\"id\":21641136,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        +oneYearAndOneDayBefore
        +"\"}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSONP da Lista de Moedas Histórica Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSONP_da_Lista_de_Moedas_Histórica_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":\"21641146P\",\"next\":\"21641142N\",\"docs\":["
        +"{\"id\":21641146,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneMonthBefore
        +"\"},{\"id\":21641144,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        +oneMonthAndOneDayBefore
        +"\"},{\"id\":21641142,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +threeMonthsBefore
        +"\"}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para a proxima pag\\. no formato JSONP da Lista de Moedas Histórica Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_de_Moedas_Histórica_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"21641148N\",\"docs\":["
        +"{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        +twoDaysBefore
        +"\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneWeekBefore
        +"\"},{\"id\":21641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\""
        +oneWeekAndOneDayBefore
        +"\"}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev para a pag\\. anterior e ponteiro next para a ultima pag\\. no formato JSON da Lista de Moedas Histórica Paginada$")
    public void deve_exibir_o_ponteiro_prev_para_a_pag_anterior_e_ponteiro_next_para_a_ultima_pag_no_formato_JSON_da_Lista_de_Moedas_Histórica_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":\"21641146P\",\"next\":\"21641142N\",\"docs\":["
        +"{\"id\":21641146,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneMonthBefore
        +"\"},{\"id\":21641144,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        +oneMonthAndOneDayBefore
        +"\"},{\"id\":21641142,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +threeMonthsBefore
        +"\"}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev nulo e ponteiro next para a proxima pag\\. no formato JSON da Lista de Moedas Histórica Paginada$")
    public void deve_exibir_o_ponteiro_prev_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_de_Moedas_Histórica_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"21641148N\",\"docs\":["
        +"{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        +twoDaysBefore
        +"\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneWeekBefore
        +"\"},{\"id\":21641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\""
        +oneWeekAndOneDayBefore
        +"\"}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma lista paginada com a funcao de callback contendo o JSON dos campos enviados com novo valor no topo e valor em \"([^\"]*)\" da Lista de Moedas Históricas$")
    public void deve_exibir_uma_lista_paginada_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_com_novo_valor_no_topo_e_valor_em_da_Lista_de_Moedas_Históricas(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"21641150N\",\"docs\":["
        +"{\"id\":21641154,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\""
        +yesterday
        +"\"},{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        +twoDaysBefore
        +"\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneWeekBefore
        +"\"}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma lista paginada com o JSON dos campos enviados com novo valor no topo e valor em \"([^\"]*)\" da Lista de Moedas Históricas$")
    public void deve_exibir_uma_lista_paginada_com_o_JSON_dos_campos_enviados_com_novo_valor_no_topo_e_valor_em_da_Lista_de_Moedas_Históricas(String arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"21641150N\",\"docs\":["
        +"{\"id\":21641154,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\""
        +yesterday
        +"\"},{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        +twoDaysBefore
        +"\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneWeekBefore
        +"\"}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag\\. no formato JSONP da Lista de Moedas de Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_igual_a_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSONP_da_Lista_de_Moedas_de_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":\"21641150N\",\"docs\":["
        +"{\"id\":21641154,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\""
        +yesterday
        +"\"},{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        +twoDaysBefore
        +"\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneWeekBefore
        +"\"}]});";

        assertEquals(expected, result);
    }
    
    @Entao("^deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag\\. no formato JSON da Lista de Moedas de Hist Paginada$")
    public void deve_exibir_o_ponteiro_prev_igual_a_nulo_e_ponteiro_next_para_a_proxima_pag_no_formato_JSON_da_Lista_de_Moedas_de_Hist_Paginada() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":\"21641150N\",\"docs\":["
        +"{\"id\":21641154,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\""
        +yesterday
        +"\"},{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        +twoDaysBefore
        +"\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneWeekBefore
        +"\"}]}";

        assertEquals(expected, result);
    }

}
