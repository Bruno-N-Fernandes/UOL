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
 * Created by vrx_mtoledo on 02/06/17.
 */
@Profile("test")
public class ListaMoedasSemanalSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
    private String yesterday = today.minusDays(1).format(dateFormat);
    private String twoDaysBefore = today.minusDays(2).format(dateFormat);
    private String oneWeekBefore = today.minusDays(7).format(dateFormat);

    private DateTimeFormatter dateFormatCSV = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private String yesterdayCSV = today.minusDays(1).format(dateFormatCSV);
    private String twoDaysBeforeCSV = today.minusDays(2).format(dateFormatCSV);
    private String oneWeekBeforeCSV = today.minusDays(7).format(dateFormatCSV);


    @Quando("^processamos a solicitação de Lista de Moedas Semanal$")
    public void processamos_a_solicitação_de_Lista_de_Moedas_Semanal() throws Exception {
        String url = "/currency/interday/list/week?"
                + contextTest.getParameterJsonp()
                + "&"
                + contextTest.getParemeterCurrency()
                + "&"
                + contextTest.getParameterFormat()
                + "&"
                + contextTest.getParemeterFields();
        contextTest.setResultActions( this.mockMvc.perform(get(url)) );
    }

    @Entao("^deve exibir uma Lista de Moedas da Semana com a funcao de callback contendo o JSON dos campos enviados$")
    public void deve_exibir_uma_Lista_de_Moedas_da_Semana_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        + twoDaysBefore
        + "\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        + oneWeekBefore
        + "\"}]});";
                
        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON da Lista de Moedas da Semana com os campos enviados$")
    public void deve_exibir_um_JSON_da_Lista_de_Moedas_da_Semana_com_os_campos_enviados() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        + twoDaysBefore
        + "\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        + oneWeekBefore
        + "\"}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma Lista de Moedas da Semana com a funcao de callback contendo o JSON dos campos enviados sem o campo DATA \\(campo deve ser ignorado\\)$")
    public void deve_exibir_uma_Lista_de_Moedas_da_Semana_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_DATA_campo_deve_ser_ignorado() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0}]});";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma Lista de Moedas da Semana com a funcao de callback contendo o JSON dos campos enviados com novo valor no topo$")
    public void deve_exibir_uma_Lista_de_Moedas_da_Semana_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_com_novo_valor_no_topo() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641154,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\""
        + yesterday
        + "\"},{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        + twoDaysBefore
        + "\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        + oneWeekBefore
        + "\"}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um CSV contendo os campos enviados da Lista de Moedas Semanal$")
    public void deve_exibir_um_CSV_contendo_os_campos_enviados_da_Lista_de_Moedas_Semanal() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Identificação interna;Nome;Venda;Compra;Máximo;Mínimo;Variação(R$);Variação(%);Abertura;Data;\r\n"
        + "21641152;Dólar Australiano;3.2295;3.2310;3.2295;3.2295;0.0008;0.025;0.0000;"
        + twoDaysBeforeCSV
        + ";\r\n"
        + "21641150;Dólar Australiano;3.2095;3.2115;3.2295;3.2095;-0.0187;-0.579;0.0000;"
        + oneWeekBeforeCSV
        + ";\r\n";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um CSV contendo os campos enviados com novo valor no topo da Lista de Moedas Semanal$")
    public void deve_exibir_um_CSV_contendo_os_campos_enviados_com_novo_valor_no_topo_da_Lista_de_Moedas_Semanal() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Identificação interna;Nome;Venda;Compra;Máximo;Mínimo;Variação(R$);Variação(%);Abertura;Data;\r\n"
        + "21641154;Dólar Australiano;3.2333;3.3222;3.1111;3.5555;3.6666;3.444;3.9999;"
        + yesterdayCSV
        + ";\r\n"
        + "21641152;Dólar Australiano;3.2295;3.2310;3.2295;3.2295;0.0008;0.025;0.0000;"
        + twoDaysBeforeCSV
        + ";\r\n"
        + "21641150;Dólar Australiano;3.2095;3.2115;3.2295;3.2095;-0.0187;-0.579;0.0000;"
        + oneWeekBeforeCSV
        + ";\r\n";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um CSV dos campos enviados sem o campo DATA \\(campo deve ser ignorado\\) da Lista de Moedas Semanal$")
    public void deve_exibir_um_CSV_dos_campos_enviados_sem_o_campo_DATA_campo_deve_ser_ignorado_da_Lista_de_Moedas_Semanal() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Identificação interna;Nome;Venda;Compra;Máximo;Mínimo;Variação(R$);Variação(%);Abertura;\r\n" +
                "21641152;Dólar Australiano;3.2295;3.2310;3.2295;3.2295;0.0008;0.025;0.0000;\r\n" +
                "21641150;Dólar Australiano;3.2095;3.2115;3.2295;3.2095;-0.0187;-0.579;0.0000;\r\n";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados da Lista de Moedas Semanal$")
    public void deve_exibir_um_JSON_dos_campos_enviados_da_Lista_de_Moedas_Semanal() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        + twoDaysBefore
        + "\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        + oneWeekBefore
        + "\"}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados com novo valor no topo da Lista de Moedas Semanal$")
    public void deve_exibir_um_JSON_dos_campos_enviados_com_novo_valor_no_topo_da_Lista_de_Moedas_Semanal() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641154,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\""
        + yesterday
        + "\"},{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\""
        + twoDaysBefore
        + "\"},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\""
        + oneWeekBefore
        + "\"}]}";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo DATA \\(campo deve ser ignorado\\) da Lista de Moedas Semanal$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_DATA_campo_deve_ser_ignorado_da_Lista_de_Moedas_Semanal() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":21641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0},{\"id\":21641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0}]}";
        assertEquals(expected, result);
    }

}
