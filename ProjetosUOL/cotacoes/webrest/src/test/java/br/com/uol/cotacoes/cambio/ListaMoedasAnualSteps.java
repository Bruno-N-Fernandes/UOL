package br.com.uol.cotacoes.cambio;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

/**
 * Created by vrx_mtoledo on 06/06/17.
 */
@Profile("test")
public class ListaMoedasAnualSteps {

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

    private DateTimeFormatter dateFormatCSV = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private String yesterdayCSV = today.minusDays(1).format(dateFormatCSV);
    private String twoDaysBeforeCSV = today.minusDays(2).format(dateFormatCSV);
    private String oneWeekBeforeCSV = today.minusDays(7).format(dateFormatCSV);
    private String oneWeekAndOneDayBeforeCSV = today.minusDays(7).minusDays(1).format(dateFormatCSV);
    private String oneMonthBeforeCSV = today.minusMonths(1).format(dateFormatCSV);
    private String oneMonthAndOneDayBeforeCSV = today.minusMonths(1).minusDays(1).format(dateFormatCSV);
    private String threeMonthsBeforeCSV = today.minusMonths(3).format(dateFormatCSV);
    private String threeMonthsAndOneDayBeforeCSV = today.minusMonths(3).minusDays(1).format(dateFormatCSV);
    private String oneYearBeforeCSV = today.minusYears(1).format(dateFormatCSV);


    @Quando("^for processada a solicitação de Lista de Moedas Anual$")
    public void for_processada_a_solicitação_de_Lista_de_Moedas_Anual() throws Throwable {
        String url = "/currency/interday/list/year?"
                +contextTest.getParameterJsonp()
                +"&"
                +contextTest.getParemeterCurrency()
                +"&"
                +contextTest.getParemeterFields()
                +"&"
                +contextTest.getParameterFormat();
        contextTest.setResultActions( this.mockMvc.perform(get(url)) );
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados da Lista de Moedas Anual$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_da_Lista_de_Moedas_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
       String expected = "/**/list({\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        +twoDaysBefore
        +"\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneWeekBefore
        +"\"},{\"id\":21641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\""
        +oneWeekAndOneDayBefore
        +"\"},{\"id\":21641146,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneMonthBefore
        +"\"},{\"id\":21641144,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        +oneMonthAndOneDayBefore
        +"\"},{\"id\":21641142,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +threeMonthsBefore
        +"\"},{\"id\":21641140,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        +threeMonthsAndOneDayBefore
        +"\"},{\"id\":21641138,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneYearBefore
        +"\"}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados com novo valor no topo da Lista de Moedas Anual$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_com_novo_valor_no_topo_da_Lista_de_Moedas_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

       String expected = "/**/list({\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641154,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\""
        +yesterday
        +"\"},{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        +twoDaysBefore
        +"\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneWeekBefore
        +"\"},{\"id\":21641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\""
        +oneWeekAndOneDayBefore
        +"\"},{\"id\":21641146,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneMonthBefore
        +"\"},{\"id\":21641144,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        +oneMonthAndOneDayBefore
        +"\"},{\"id\":21641142,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +threeMonthsBefore
        +"\"},{\"id\":21641140,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        +threeMonthsAndOneDayBefore
        +"\"},{\"id\":21641138,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneYearBefore
        +"\"}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados da Lista de Moedas Anual$")
    public void deve_exibir_um_JSON_dos_campos_enviados_da_Lista_de_Moedas_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

       String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        +twoDaysBefore
        +"\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneWeekBefore
        +"\"},{\"id\":21641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\""
        +oneWeekAndOneDayBefore
        +"\"},{\"id\":21641146,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneMonthBefore
        +"\"},{\"id\":21641144,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        +oneMonthAndOneDayBefore
        +"\"},{\"id\":21641142,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +threeMonthsBefore
        +"\"},{\"id\":21641140,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        +threeMonthsAndOneDayBefore
        +"\"},{\"id\":21641138,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneYearBefore
        +"\"}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados sem o campo DATA \\(campo deve ser ignorado\\) da Lista de Moedas Anual$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_DATA_campo_deve_ser_ignorado_da_Lista_de_Moedas_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected ="/**/list({\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0},{\"id\":21641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0},{\"id\":21641146,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0},{\"id\":21641144,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0},{\"id\":21641142,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0},{\"id\":21641140,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0},{\"id\":21641138,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0}]});";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir um CSV contendo os campos enviados da Lista de Moedas Anual$")
    public void deve_exibir_um_CSV_contendo_os_campos_enviados_da_Lista_de_Moedas_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

       String expected = "Identificação interna;Nome;Venda;Compra;Máximo;Mínimo;Variação(R$);Variação(%);Abertura;Data;\r\n"
        +"21641152;Dólar Australiano;3.2295;3.2310;3.2295;3.2295;0.0008;0.025;0.0000;"
        +twoDaysBeforeCSV
        +";\r\n"
        +"21641150;Dólar Australiano;3.2095;3.2115;3.2295;3.2095;-0.0187;-0.579;0.0000;"
        +oneWeekBeforeCSV
        +";\r\n"
        +"21641148;Dólar Australiano;3.2074;3.2086;3.2295;3.2074;-0.0216;-0.669;0.0000;"
        +oneWeekAndOneDayBeforeCSV
        +";\r\n"
        +"21641146;Dólar Australiano;3.2100;3.2115;3.2295;3.2074;-0.0187;-0.579;0.0000;"
        +oneMonthBeforeCSV
        +";\r\n"
        +"21641144;Dólar Australiano;3.2122;3.2147;3.2295;3.2074;-0.0155;-0.480;0.0000;"
        +oneMonthAndOneDayBeforeCSV
        +";\r\n"
        +"21641142;Dólar Australiano;3.2100;3.2115;3.2295;3.2074;-0.0187;-0.579;0.0000;"
        +threeMonthsBeforeCSV
        +";\r\n"
        +"21641140;Dólar Australiano;3.2122;3.2147;3.2295;3.2074;-0.0155;-0.480;0.0000;"
        +threeMonthsAndOneDayBeforeCSV
        +";\r\n"
        +"21641138;Dólar Australiano;3.2100;3.2115;3.2295;3.2074;-0.0187;-0.579;0.0000;"
        +oneYearBeforeCSV
        +";\r\n";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um CSV contendo os campos enviados com novo valor no topo da Lista de Moedas Anual$")
    public void deve_exibir_um_CSV_contendo_os_campos_enviados_com_novo_valor_no_topo_da_Lista_de_Moedas_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

       String expected = "Identificação interna;Nome;Venda;Compra;Máximo;Mínimo;Variação(R$);Variação(%);Abertura;Data;\r\n"
        +"21641154;Dólar Australiano;3.2333;3.3222;3.1111;3.5555;3.6666;3.444;3.9999;"
        +yesterdayCSV
        +";\r\n"
        +"21641152;Dólar Australiano;3.2295;3.2310;3.2295;3.2295;0.0008;0.025;0.0000;"
        +twoDaysBeforeCSV
        +";\r\n"
        +"21641150;Dólar Australiano;3.2095;3.2115;3.2295;3.2095;-0.0187;-0.579;0.0000;"
        +oneWeekBeforeCSV
        +";\r\n"
        +"21641148;Dólar Australiano;3.2074;3.2086;3.2295;3.2074;-0.0216;-0.669;0.0000;"
        +oneWeekAndOneDayBeforeCSV
        +";\r\n"
        +"21641146;Dólar Australiano;3.2100;3.2115;3.2295;3.2074;-0.0187;-0.579;0.0000;"
        +oneMonthBeforeCSV
        +";\r\n"
        +"21641144;Dólar Australiano;3.2122;3.2147;3.2295;3.2074;-0.0155;-0.480;0.0000;"
        +oneMonthAndOneDayBeforeCSV
        +";\r\n"
        +"21641142;Dólar Australiano;3.2100;3.2115;3.2295;3.2074;-0.0187;-0.579;0.0000;"
        +threeMonthsBeforeCSV
        +";\r\n"
        +"21641140;Dólar Australiano;3.2122;3.2147;3.2295;3.2074;-0.0155;-0.480;0.0000;"
        +threeMonthsAndOneDayBeforeCSV
        +";\r\n"
        +"21641138;Dólar Australiano;3.2100;3.2115;3.2295;3.2074;-0.0187;-0.579;0.0000;"
        +oneYearBeforeCSV
        +";\r\n";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um CSV dos campos enviados sem o campo DATA \\(campo deve ser ignorado\\) da Lista de Moedas Anual$")
    public void deve_exibir_um_CSV_dos_campos_enviados_sem_o_campo_DATA_campo_deve_ser_ignorado_da_Lista_de_Moedas_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected ="Identificação interna;Nome;Venda;Compra;Máximo;Mínimo;Variação(R$);Variação(%);Abertura;\r\n" +
                "21641152;Dólar Australiano;3.2295;3.2310;3.2295;3.2295;0.0008;0.025;0.0000;\r\n" +
                "21641150;Dólar Australiano;3.2095;3.2115;3.2295;3.2095;-0.0187;-0.579;0.0000;\r\n" +
                "21641148;Dólar Australiano;3.2074;3.2086;3.2295;3.2074;-0.0216;-0.669;0.0000;\r\n" +
                "21641146;Dólar Australiano;3.2100;3.2115;3.2295;3.2074;-0.0187;-0.579;0.0000;\r\n" +
                "21641144;Dólar Australiano;3.2122;3.2147;3.2295;3.2074;-0.0155;-0.480;0.0000;\r\n" +
                "21641142;Dólar Australiano;3.2100;3.2115;3.2295;3.2074;-0.0187;-0.579;0.0000;\r\n" +
                "21641140;Dólar Australiano;3.2122;3.2147;3.2295;3.2074;-0.0155;-0.480;0.0000;\r\n" +
                "21641138;Dólar Australiano;3.2100;3.2115;3.2295;3.2074;-0.0187;-0.579;0.0000;\r\n";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados com novo valor no topo da Lista de Moedas Anual$")
    public void deve_exibir_um_JSON_dos_campos_enviados_com_novo_valor_no_topo_da_Lista_de_Moedas_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

       String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641154,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\""
        +yesterday
        +"\"},{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        +twoDaysBefore
        +"\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneWeekBefore
        +"\"},{\"id\":21641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\""
        +oneWeekAndOneDayBefore
        +"\"},{\"id\":21641146,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneMonthBefore
        +"\"},{\"id\":21641144,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        +oneMonthAndOneDayBefore
        +"\"},{\"id\":21641142,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +threeMonthsBefore
        +"\"},{\"id\":21641140,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\""
        +threeMonthsAndOneDayBefore
        +"\"},{\"id\":21641138,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        +oneYearBefore
        +"\"}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo DATA \\(campo deve ser ignorado\\) da Lista de Moedas Anual$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_DATA_campo_deve_ser_ignorado_da_Lista_de_Moedas_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected ="{\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0},{\"id\":21641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0},{\"id\":21641146,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0},{\"id\":21641144,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0},{\"id\":21641142,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0},{\"id\":21641140,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0},{\"id\":21641138,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0}]}";
        assertEquals(expected, result);
    }

}
