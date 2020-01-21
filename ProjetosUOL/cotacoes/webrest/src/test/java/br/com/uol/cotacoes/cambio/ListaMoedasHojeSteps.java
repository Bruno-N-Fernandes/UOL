package br.com.uol.cotacoes.cambio;

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
 * Created by vrx_mtoledo on 01/06/17.
 */
@Profile("test")
public class ListaMoedasHojeSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;

    @Quando("^processamos a solicitação da Lista de Moedas De Hoje$")
    public void processamos_a_solicitação_da_Lista_de_Moedas_De_Hoje() throws Throwable {
        StringBuilder url = new StringBuilder("/currency/intraday/list?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParameterFormat())
                .append("&")
                .append(contextTest.getParemeterCurrency())
                .append("&")
                .append(contextTest.getParemeterFields());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir uma Lista de Moedas De Hoje com a funcao de callback contendo o JSON dos campos enviados$")
    public void deve_exibir_uma_Lista_de_Moedas_De_Hoje_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602104500\"},{\"id\":20641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\"20170602104000\"},{\"id\":20641149,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602103500\"},{\"id\":20641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\"20170602103000\"}]});";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista de Moedas De Hoje com o JSON dos campos enviados$")
    public void deve_exibir_uma_Lista_de_Moedas_De_Hoje_com_o_JSON_dos_campos_enviados() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602104500\"},{\"id\":20641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\"20170602104000\"},{\"id\":20641149,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602103500\"},{\"id\":20641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\"20170602103000\"}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista de Moedas De Hoje com o JSON dos campos enviados sem o campo DATA \\(campo deve ser ignorado\\)$")
    public void deve_exibir_uma_Lista_de_Moedas_De_Hoje_com_o_JSON_dos_campos_enviados_sem_o_campo_DATA_campo_deve_ser_ignorado() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0},{\"id\":20641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0},{\"id\":20641149,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0},{\"id\":20641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0}]}";

        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma Lista de Moedas De Hoje com a funcao de callback contendo o JSON dos campos enviados sem o campo DATA \\(campo deve ser ignorado\\)$")
    public void deve_exibir_uma_Lista_de_Moedas_De_Hoje_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_sem_o_campo_DATA_campo_deve_ser_ignorado() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0},{\"id\":20641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0},{\"id\":20641149,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0},{\"id\":20641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0}]});";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um CSV contendo os campos enviados da Lista de Moedas De Hoje$")
    public void deve_exibir_um_CSV_contendo_os_campos_enviados_da_Lista_de_Moedas_De_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected="Identificação interna;Nome;Venda;Compra;Máximo;Mínimo;Variação(R$);Variação(%);Abertura;Data;\r\n" +
                "20641152;Dólar Australiano;3.2295;3.2310;3.2295;3.2295;0.0008;0.025;0.0000;02/06/2017 10:50:00;\r\n" +
                "20641151;Dólar Australiano;3.2095;3.2115;3.2295;3.2095;-0.0187;-0.579;0.0000;02/06/2017 10:45:00;\r\n" +
                "20641150;Dólar Australiano;3.2074;3.2086;3.2295;3.2074;-0.0216;-0.669;0.0000;02/06/2017 10:40:00;\r\n" +
                "20641149;Dólar Australiano;3.2100;3.2115;3.2295;3.2074;-0.0187;-0.579;0.0000;02/06/2017 10:35:00;\r\n" +
                "20641148;Dólar Australiano;3.2122;3.2147;3.2295;3.2074;-0.0155;-0.480;0.0000;02/06/2017 10:30:00;\r\n";

        assertEquals(expected, result);
    }

    //CORRIGIR O SERVIÇO
    @Entao("^deve exibir um CSV dos campos enviados sem o campo DATA \\(campo deve ser ignorado\\) da Lista de Moedas De Hoje$")
    public void deve_exibir_um_CSV_dos_campos_enviados_sem_o_campo_DATA_campo_deve_ser_ignorado_da_Lista_de_Moedas_De_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected="Identificação interna;Nome;Venda;Compra;Máximo;Mínimo;Variação(R$);Variação(%);Abertura;\r\n" +
                "20641152;Dólar Australiano;3.2295;3.2310;3.2295;3.2295;0.0008;0.025;0.0000;\r\n" +
                "20641151;Dólar Australiano;3.2095;3.2115;3.2295;3.2095;-0.0187;-0.579;0.0000;\r\n" +
                "20641150;Dólar Australiano;3.2074;3.2086;3.2295;3.2074;-0.0216;-0.669;0.0000;\r\n" +
                "20641149;Dólar Australiano;3.2100;3.2115;3.2295;3.2074;-0.0187;-0.579;0.0000;\r\n" +
                "20641148;Dólar Australiano;3.2122;3.2147;3.2295;3.2074;-0.0155;-0.480;0.0000;\r\n";

        assertEquals(expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados com novo valor no topo da Lista de Moedas De Hoje$")
    public void deve_exibir_um_JSON_dos_campos_enviados_com_novo_valor_no_topo_da_Lista_de_Moedas_De_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641153,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\"20170602110000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602104500\"},{\"id\":20641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\"20170602104000\"},{\"id\":20641149,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602103500\"},{\"id\":20641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\"20170602103000\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados com novo valor no topo da Lista de Moedas De Hoje$")
    public void deve_exibir_uma_lista_com_a_funcao_de_callback_contendo_o_JSON_dos_campos_enviados_com_novo_valor_no_topo_da_Lista_de_Moedas_De_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/list({\"prev\":null,\"next\":null,\"docs\":[{\"id\":20641153,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2333,\"askvalue\":3.3222,\"maxbid\":3.1111,\"minbid\":3.5555,\"variationbid\":3.6666,\"variationpercentbid\":3.444,\"openbidvalue\":3.9999,\"date\":\"20170602110000\"},{\"id\":20641152,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2295,\"askvalue\":3.231,\"maxbid\":3.2295,\"minbid\":3.2295,\"variationbid\":0.0008,\"variationpercentbid\":0.025,\"openbidvalue\":0,\"date\":\"20170602105000\"},{\"id\":20641151,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2095,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2095,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602104500\"},{\"id\":20641150,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2074,\"askvalue\":3.2086,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0216,\"variationpercentbid\":-0.669,\"openbidvalue\":0,\"date\":\"20170602104000\"},{\"id\":20641149,\"name\":\"Dólar Australiano\",\"bidvalue\":3.21,\"askvalue\":3.2115,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0187,\"variationpercentbid\":-0.579,\"openbidvalue\":0,\"date\":\"20170602103500\"},{\"id\":20641148,\"name\":\"Dólar Australiano\",\"bidvalue\":3.2122,\"askvalue\":3.2147,\"maxbid\":3.2295,\"minbid\":3.2074,\"variationbid\":-0.0155,\"variationpercentbid\":-0.48,\"openbidvalue\":0,\"date\":\"20170602103000\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um CSV contendo os campos enviados com o novo valor no topo da Lista de Moedas De Hoje$")
    public void deve_exibir_um_CSV_contendo_os_campos_enviados_com_o_novo_valor_no_topo_da_Lista_de_Moedas_De_Hoje() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "Identificação interna;Nome;Venda;Compra;Máximo;Mínimo;Variação(R$);Variação(%);Abertura;Data;\r\n" +
                "20641153;Dólar Australiano;3.2333;3.3222;3.1111;3.5555;3.6666;3.444;3.9999;02/06/2017 11:00:00;\r\n" +
                "20641152;Dólar Australiano;3.2295;3.2310;3.2295;3.2295;0.0008;0.025;0.0000;02/06/2017 10:50:00;\r\n" +
                "20641151;Dólar Australiano;3.2095;3.2115;3.2295;3.2095;-0.0187;-0.579;0.0000;02/06/2017 10:45:00;\r\n" +
                "20641150;Dólar Australiano;3.2074;3.2086;3.2295;3.2074;-0.0216;-0.669;0.0000;02/06/2017 10:40:00;\r\n" +
                "20641149;Dólar Australiano;3.2100;3.2115;3.2295;3.2074;-0.0187;-0.579;0.0000;02/06/2017 10:35:00;\r\n" +
                "20641148;Dólar Australiano;3.2122;3.2147;3.2295;3.2074;-0.0155;-0.480;0.0000;02/06/2017 10:30:00;\r\n";
        assertEquals( expected, result);
    }
}
