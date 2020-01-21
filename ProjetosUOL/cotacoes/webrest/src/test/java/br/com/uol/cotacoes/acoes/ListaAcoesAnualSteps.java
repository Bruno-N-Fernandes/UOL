package br.com.uol.cotacoes.acoes;

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
 * Created by vrx_mtoledo on 10/07/17.
 */
@Profile("test")
public class ListaAcoesAnualSteps {

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

    @Quando("^for processada a solicitação de Lista de Acoes Anual$")
    public void for_processada_a_solicitação_de_Lista_de_Acoes_Anual() throws Throwable {
        StringBuilder url = new StringBuilder("/asset/interday/list/year?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterItem())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParameterFormat());
        contextTest.setResultActions(this.mockMvc.perform(get(url.toString())));
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados da Lista de Acoes Anual$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_da_Lista_de_Acoes_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":null,\"docs\":[{\"date\":\"" +
                twoDaysBefore +
                "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56},{\"date\":\"" +
                oneWeekBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneWeekAndOneDayBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":10100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneMonthBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15100,\"close\":5.78,\"bid\":5.65,\"ask\":5.78,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneMonthAndOneDayBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15200,\"close\":5.78,\"bid\":5.66,\"ask\":5.79,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                threeMonthsBefore +
                "\",\"price\":5.84,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.84,\"low\":5.69,\"open\":5.69,\"volume\":24100,\"close\":5.78,\"bid\":5.66,\"ask\":5.85,\"change\":0.06,\"pctChange\":1.04},{\"date\":\"" +
                threeMonthsAndOneDayBefore +
                "\",\"price\":5.88,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31200,\"close\":5.78,\"bid\":5.7,\"ask\":5.85,\"change\":0.1,\"pctChange\":1.73},{\"date\":\"" +
                oneYearBefore +
                "\",\"price\":5.7,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31700,\"close\":5.78,\"bid\":5.56,\"ask\":5.8,\"change\":-0.08,\"pctChange\":-1.38}]});";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados com novo valor no topo da Lista de Acoes Anual$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_com_novo_valor_no_topo_da_Lista_de_Acoes_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":null,\"docs\":[{\"date\":\"" +
                yesterday +
                "\",\"price\":5.55,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.66,\"low\":5.77,\"open\":5.88,\"volume\":100,\"close\":5.99,\"bid\":5.56,\"ask\":5.67,\"change\":-0.05,\"pctChange\":-1.55},{\"date\":\"" +
                twoDaysBefore +
                "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56},{\"date\":\"" +
                oneWeekBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneWeekAndOneDayBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":10100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneMonthBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15100,\"close\":5.78,\"bid\":5.65,\"ask\":5.78,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneMonthAndOneDayBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15200,\"close\":5.78,\"bid\":5.66,\"ask\":5.79,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                threeMonthsBefore +
                "\",\"price\":5.84,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.84,\"low\":5.69,\"open\":5.69,\"volume\":24100,\"close\":5.78,\"bid\":5.66,\"ask\":5.85,\"change\":0.06,\"pctChange\":1.04},{\"date\":\"" +
                threeMonthsAndOneDayBefore +
                "\",\"price\":5.88,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31200,\"close\":5.78,\"bid\":5.7,\"ask\":5.85,\"change\":0.1,\"pctChange\":1.73},{\"date\":\"" +
                oneYearBefore +
                "\",\"price\":5.7,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31700,\"close\":5.78,\"bid\":5.56,\"ask\":5.8,\"change\":-0.08,\"pctChange\":-1.38}]});";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados da Lista de Acoes Anual$")
    public void deve_exibir_um_JSON_dos_campos_enviados_da_Lista_de_Acoes_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"date\":\"" +
                twoDaysBefore +
                "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56},{\"date\":\"" +
                oneWeekBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneWeekAndOneDayBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":10100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneMonthBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15100,\"close\":5.78,\"bid\":5.65,\"ask\":5.78,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneMonthAndOneDayBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15200,\"close\":5.78,\"bid\":5.66,\"ask\":5.79,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                threeMonthsBefore +
                "\",\"price\":5.84,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.84,\"low\":5.69,\"open\":5.69,\"volume\":24100,\"close\":5.78,\"bid\":5.66,\"ask\":5.85,\"change\":0.06,\"pctChange\":1.04},{\"date\":\"" +
                threeMonthsAndOneDayBefore +
                "\",\"price\":5.88,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31200,\"close\":5.78,\"bid\":5.7,\"ask\":5.85,\"change\":0.1,\"pctChange\":1.73},{\"date\":\"" +
                oneYearBefore +
                "\",\"price\":5.7,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31700,\"close\":5.78,\"bid\":5.56,\"ask\":5.8,\"change\":-0.08,\"pctChange\":-1.38}]}";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados sem o campo ASKED \\(campo deve ser ignorado\\) da Lista de Acoes Anual$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_ASKED_campo_deve_ser_ignorado_da_Lista_de_Acoes_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":null,\"docs\":[{\"date\":\"" +
                twoDaysBefore +
                "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"change\":-0.09,\"pctChange\":-1.56},{\"date\":\"" +
                oneWeekBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneWeekAndOneDayBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":10100,\"close\":5.78,\"bid\":5.56,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneMonthBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15100,\"close\":5.78,\"bid\":5.65,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneMonthAndOneDayBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15200,\"close\":5.78,\"bid\":5.66,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                threeMonthsBefore +
                "\",\"price\":5.84,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.84,\"low\":5.69,\"open\":5.69,\"volume\":24100,\"close\":5.78,\"bid\":5.66,\"change\":0.06,\"pctChange\":1.04},{\"date\":\"" +
                threeMonthsAndOneDayBefore +
                "\",\"price\":5.88,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31200,\"close\":5.78,\"bid\":5.7,\"change\":0.1,\"pctChange\":1.73},{\"date\":\"" +
                oneYearBefore +
                "\",\"price\":5.7,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31700,\"close\":5.78,\"bid\":5.56,\"change\":-0.08,\"pctChange\":-1.38}]});";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir um CSV contendo os campos enviados da Lista de Acoes Anual$")
    public void deve_exibir_um_CSV_contendo_os_campos_enviados_da_Lista_de_Acoes_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Data;Preço;Ativo;Máximo(R$);Mínimo(R$);Abertura(R$);Volume;Fechamento(R$);Venda;Compra;Variação(R$);Variação(%);\r\n" +
                twoDaysBeforeCSV +
                ";5.690;COTEMINAS PN    ;5.690;5.690;5.690;100.000;5.780;5.550;5.700;-0.090;-1.56;\r\n" +
                oneWeekBeforeCSV +
                ";5.780;COTEMINAS PN    ;5.780;5.690;5.690;5100.000;5.780;5.560;5.780;0.000;0.00;\r\n" +
                oneWeekAndOneDayBeforeCSV +
                ";5.780;COTEMINAS PN    ;5.780;5.690;5.690;10100.000;5.780;5.560;5.780;0.000;0.00;\r\n" +
                oneMonthBeforeCSV +
                ";5.780;COTEMINAS PN    ;5.780;5.690;5.690;15100.000;5.780;5.650;5.780;0.000;0.00;\r\n" +
                oneMonthAndOneDayBeforeCSV +
                ";5.780;COTEMINAS PN    ;5.780;5.690;5.690;15200.000;5.780;5.660;5.790;0.000;0.00;\r\n" +
                threeMonthsBeforeCSV +
                ";5.840;COTEMINAS PN    ;5.840;5.690;5.690;24100.000;5.780;5.660;5.850;0.060;1.04;\r\n" +
                threeMonthsAndOneDayBeforeCSV +
                ";5.880;COTEMINAS PN    ;5.880;5.690;5.690;31200.000;5.780;5.700;5.850;0.100;1.73;\r\n" +
                oneYearBeforeCSV +
                ";5.700;COTEMINAS PN    ;5.880;5.690;5.690;31700.000;5.780;5.560;5.800;-0.080;-1.38;\r\n";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir um CSV contendo os campos enviados com novo valor no topo da Lista de Acoes Anual$")
    public void deve_exibir_um_CSV_contendo_os_campos_enviados_com_novo_valor_no_topo_da_Lista_de_Acoes_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Data;Preço;Ativo;Máximo(R$);Mínimo(R$);Abertura(R$);Volume;Fechamento(R$);Venda;Compra;Variação(R$);Variação(%);\r\n" +
                yesterdayCSV +
                ";5.550;COTEMINAS PN    ;5.660;5.770;5.880;100.000;5.990;5.560;5.670;-0.050;-1.55;\r\n" +
                twoDaysBeforeCSV +
                ";5.690;COTEMINAS PN    ;5.690;5.690;5.690;100.000;5.780;5.550;5.700;-0.090;-1.56;\r\n" +
                oneWeekBeforeCSV +
                ";5.780;COTEMINAS PN    ;5.780;5.690;5.690;5100.000;5.780;5.560;5.780;0.000;0.00;\r\n" +
                oneWeekAndOneDayBeforeCSV +
                ";5.780;COTEMINAS PN    ;5.780;5.690;5.690;10100.000;5.780;5.560;5.780;0.000;0.00;\r\n" +
                oneMonthBeforeCSV +
                ";5.780;COTEMINAS PN    ;5.780;5.690;5.690;15100.000;5.780;5.650;5.780;0.000;0.00;\r\n" +
                oneMonthAndOneDayBeforeCSV +
                ";5.780;COTEMINAS PN    ;5.780;5.690;5.690;15200.000;5.780;5.660;5.790;0.000;0.00;\r\n" +
                threeMonthsBeforeCSV +
                ";5.840;COTEMINAS PN    ;5.840;5.690;5.690;24100.000;5.780;5.660;5.850;0.060;1.04;\r\n" +
                threeMonthsAndOneDayBeforeCSV +
                ";5.880;COTEMINAS PN    ;5.880;5.690;5.690;31200.000;5.780;5.700;5.850;0.100;1.73;\r\n" +
                oneYearBeforeCSV +
                ";5.700;COTEMINAS PN    ;5.880;5.690;5.690;31700.000;5.780;5.560;5.800;-0.080;-1.38;\r\n";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir um CSV dos campos enviados sem o campo ASKED \\(campo deve ser ignorado\\) da Lista de Acoes Anual$")
    public void deve_exibir_um_CSV_dos_campos_enviados_sem_o_campo_ASKED_campo_deve_ser_ignorado_da_Lista_de_Acoes_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Data;Preço;Ativo;Máximo(R$);Mínimo(R$);Abertura(R$);Volume;Fechamento(R$);Venda;Variação(R$);Variação(%);\r\n" +
                twoDaysBeforeCSV +
                ";5.690;COTEMINAS PN    ;5.690;5.690;5.690;100.000;5.780;5.550;-0.090;-1.56;\r\n" +
                oneWeekBeforeCSV +
                ";5.780;COTEMINAS PN    ;5.780;5.690;5.690;5100.000;5.780;5.560;0.000;0.00;\r\n" +
                oneWeekAndOneDayBeforeCSV +
                ";5.780;COTEMINAS PN    ;5.780;5.690;5.690;10100.000;5.780;5.560;0.000;0.00;\r\n" +
                oneMonthBeforeCSV +
                ";5.780;COTEMINAS PN    ;5.780;5.690;5.690;15100.000;5.780;5.650;0.000;0.00;\r\n" +
                oneMonthAndOneDayBeforeCSV +
                ";5.780;COTEMINAS PN    ;5.780;5.690;5.690;15200.000;5.780;5.660;0.000;0.00;\r\n" +
                threeMonthsBeforeCSV +
                ";5.840;COTEMINAS PN    ;5.840;5.690;5.690;24100.000;5.780;5.660;0.060;1.04;\r\n" +
                threeMonthsAndOneDayBeforeCSV +
                ";5.880;COTEMINAS PN    ;5.880;5.690;5.690;31200.000;5.780;5.700;0.100;1.73;\r\n" +
                oneYearBeforeCSV +
                ";5.700;COTEMINAS PN    ;5.880;5.690;5.690;31700.000;5.780;5.560;-0.080;-1.38;\r\n";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados com novo valor no topo da Lista de Acoes Anual$")
    public void deve_exibir_um_JSON_dos_campos_enviados_com_novo_valor_no_topo_da_Lista_de_Acoes_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"date\":\"" +
                yesterday +
                "\",\"price\":5.55,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.66,\"low\":5.77,\"open\":5.88,\"volume\":100,\"close\":5.99,\"bid\":5.56,\"ask\":5.67,\"change\":-0.05,\"pctChange\":-1.55},{\"date\":\"" +
                twoDaysBefore +
                "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"ask\":5.7,\"change\":-0.09,\"pctChange\":-1.56},{\"date\":\"" +
                oneWeekBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneWeekAndOneDayBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":10100,\"close\":5.78,\"bid\":5.56,\"ask\":5.78,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneMonthBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15100,\"close\":5.78,\"bid\":5.65,\"ask\":5.78,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneMonthAndOneDayBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15200,\"close\":5.78,\"bid\":5.66,\"ask\":5.79,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                threeMonthsBefore +
                "\",\"price\":5.84,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.84,\"low\":5.69,\"open\":5.69,\"volume\":24100,\"close\":5.78,\"bid\":5.66,\"ask\":5.85,\"change\":0.06,\"pctChange\":1.04},{\"date\":\"" +
                threeMonthsAndOneDayBefore +
                "\",\"price\":5.88,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31200,\"close\":5.78,\"bid\":5.7,\"ask\":5.85,\"change\":0.1,\"pctChange\":1.73},{\"date\":\"" +
                oneYearBefore +
                "\",\"price\":5.7,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31700,\"close\":5.78,\"bid\":5.56,\"ask\":5.8,\"change\":-0.08,\"pctChange\":-1.38}]}";
        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo ASKED \\(campo deve ser ignorado\\) da Lista de Acoes Anual$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_ASKED_campo_deve_ser_ignorado_da_Lista_de_Acoes_Anual() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"date\":\"" +
                twoDaysBefore +
                "\",\"price\":5.69,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.69,\"low\":5.69,\"open\":5.69,\"volume\":100,\"close\":5.78,\"bid\":5.55,\"change\":-0.09,\"pctChange\":-1.56},{\"date\":\"" +
                oneWeekBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":5100,\"close\":5.78,\"bid\":5.56,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneWeekAndOneDayBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":10100,\"close\":5.78,\"bid\":5.56,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneMonthBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15100,\"close\":5.78,\"bid\":5.65,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                oneMonthAndOneDayBefore +
                "\",\"price\":5.78,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.78,\"low\":5.69,\"open\":5.69,\"volume\":15200,\"close\":5.78,\"bid\":5.66,\"change\":0,\"pctChange\":0},{\"date\":\"" +
                threeMonthsBefore +
                "\",\"price\":5.84,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.84,\"low\":5.69,\"open\":5.69,\"volume\":24100,\"close\":5.78,\"bid\":5.66,\"change\":0.06,\"pctChange\":1.04},{\"date\":\"" +
                threeMonthsAndOneDayBefore +
                "\",\"price\":5.88,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31200,\"close\":5.78,\"bid\":5.7,\"change\":0.1,\"pctChange\":1.73},{\"date\":\"" +
                oneYearBefore +
                "\",\"price\":5.7,\"exchangeasset\":\"COTEMINAS PN    \",\"high\":5.88,\"low\":5.69,\"open\":5.69,\"volume\":31700,\"close\":5.78,\"bid\":5.56,\"change\":-0.08,\"pctChange\":-1.38}]}";
        assertEquals(expected, result);
    }

}
