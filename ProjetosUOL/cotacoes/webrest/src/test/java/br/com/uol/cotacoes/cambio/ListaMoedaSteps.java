package br.com.uol.cotacoes.cambio;

import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

@Profile("test")
public class ListaMoedaSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;

    @Dado("^que enviamos os campos: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
    public void que_enviamos_os_campos_e(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7) throws Throwable {
        final String fields = "fields=" +
                arg1 + "," +
                arg2 + "," +
                arg3 + "," +
                arg4 + "," +
                arg5 + "," +
                arg6 + "," +
                arg7;
        contextTest.setParemeterFields(fields);
    }

    @Quando("^for processada a solicitação sem callback da Lista de Moedas$")
    public void for_processada_a_solicitação_sem_callback_da_Lista_de_Moedas() throws Throwable {
        final String url = "/currency/list?" + contextTest.getParemeterFields();
        contextTest.setResultActions(this.mockMvc.perform(get(url)));
    }

    @Quando("^for processada a solicitação com callback da Lista de Moedas$")
    public void for_processada_a_solicitação_com_callback_da_Lista_de_Moedas() throws Throwable {
        final String url = "/currency/list?" + contextTest.getParemeterFields() + "&" + contextTest.getParameterJsonp();
        contextTest.setResultActions(this.mockMvc.perform(get(url)));
    }

    @Entao("^deve exibir um JSON com os campos enviados da Lista de Moedas$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_da_Lista_de_Moedas() throws Throwable {
        final String[] euroCountries = new String[]{"Espanha", "Grécia", "Luxemburgo", "Irlanda", "Portugal", "Finlândia", "Malta", "União Européia", "Chipre", "Eslováquia", "Eslovênia", "Mônaco", "Holanda"};

        contextTest.getResultActions()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.prev").value((String) null))
                .andExpect(jsonPath("$.next").value((String) null))
                .andExpect(jsonPath("$.docs.length()").value(5))

                .andExpect(jsonPath("$.docs[0].id").value(4))
                .andExpect(jsonPath("$.docs[0].name").value("Euro"))
                .andExpect(jsonPath("$.docs[0].codinvestor").value("EUR"))
                .andExpect(jsonPath("$.docs[0].codunit").value(""))
                .andExpect(jsonPath("$.docs[0].codconversion").value(""))
                .andExpect(jsonPath("$.docs[0].converted").value(true))
                .andExpect(jsonPath("$.docs[0].countries").isArray())
                .andExpect(jsonPath("$.docs[0].countries", Matchers.containsInAnyOrder(euroCountries)))

                .andExpect(jsonPath("$.docs[1].id").value(5))
                .andExpect(jsonPath("$.docs[1].name").value("Euro (R$)"))
                .andExpect(jsonPath("$.docs[1].codinvestor").value("EUR"))
                .andExpect(jsonPath("$.docs[1].codunit").value(""))
                .andExpect(jsonPath("$.docs[1].codconversion").value("BRL"))
                .andExpect(jsonPath("$.docs[1].converted").value(true))
                .andExpect(jsonPath("$.docs[1].countries").isArray())
                .andExpect(jsonPath("$.docs[1].countries", Matchers.containsInAnyOrder(euroCountries)))

                .andExpect(jsonPath("$.docs[2].id").value(18))
                .andExpect(jsonPath("$.docs[2].name").value("Dólar Australiano"))
                .andExpect(jsonPath("$.docs[2].codinvestor").value("AUD"))
                .andExpect(jsonPath("$.docs[2].codunit").value(""))
                .andExpect(jsonPath("$.docs[2].codconversion").value(""))
                .andExpect(jsonPath("$.docs[2].converted").value(true))
                .andExpect(jsonPath("$.docs[2].countries").isArray())
                .andExpect(jsonPath("$.docs[2].countries").value( "Austrália"))

                .andExpect(jsonPath("$.docs[3].id").value(84))
                .andExpect(jsonPath("$.docs[3].name").value("Libra"))
                .andExpect(jsonPath("$.docs[3].codinvestor").value("LIB"))
                .andExpect(jsonPath("$.docs[3].codunit").value(""))
                .andExpect(jsonPath("$.docs[3].codconversion").value(""))
                .andExpect(jsonPath("$.docs[3].converted").value(true))
                .andExpect(jsonPath("$.docs[3].countries").isArray())
                .andExpect(jsonPath("$.docs[3].countries").value("Inglaterra"))

                .andExpect(jsonPath("$.docs[4].id").value(85))
                .andExpect(jsonPath("$.docs[4].name").value("Peso"))
                .andExpect(jsonPath("$.docs[4].codinvestor").value("PES"))
                .andExpect(jsonPath("$.docs[4].codunit").value(""))
                .andExpect(jsonPath("$.docs[4].codconversion").value(""))
                .andExpect(jsonPath("$.docs[4].converted").value(true))
                .andExpect(jsonPath("$.docs[4].countries").isArray())
                .andExpect(jsonPath("$.docs[4].countries").value("Argentina"));
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo \"([^\"]*)\" da Lista de Moedas$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_da_Lista_de_Moedas(String arg1) throws Throwable {
        contextTest.getResultActions()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.prev").value((String) null))
                .andExpect(jsonPath("$.next").value((String) null))
                .andExpect(jsonPath("$.docs.length()").value(5))

                .andExpect(jsonPath("$.docs[0].id").value(4))
                .andExpect(jsonPath("$.docs[0].name").value("Euro"))
                .andExpect(jsonPath("$.docs[0].codinvestor").value("EUR"))
                .andExpect(jsonPath("$.docs[0].codunit").value(""))
                .andExpect(jsonPath("$.docs[0].codconversion").value(""))
                .andExpect(jsonPath("$.docs[0].converted").value(true))
                .andExpect(jsonPath("$.docs[0].COUNTRY").doesNotExist())

                .andExpect(jsonPath("$.docs[1].id").value(5))
                .andExpect(jsonPath("$.docs[1].name").value("Euro (R$)"))
                .andExpect(jsonPath("$.docs[1].codinvestor").value("EUR"))
                .andExpect(jsonPath("$.docs[1].codunit").value(""))
                .andExpect(jsonPath("$.docs[1].codconversion").value("BRL"))
                .andExpect(jsonPath("$.docs[1].converted").value(true))
                .andExpect(jsonPath("$.docs[1].COUNTRY").doesNotExist())

                .andExpect(jsonPath("$.docs[2].id").value(18))
                .andExpect(jsonPath("$.docs[2].name").value("Dólar Australiano"))
                .andExpect(jsonPath("$.docs[2].codinvestor").value("AUD"))
                .andExpect(jsonPath("$.docs[2].codunit").value(""))
                .andExpect(jsonPath("$.docs[2].codconversion").value(""))
                .andExpect(jsonPath("$.docs[2].converted").value(true))
                .andExpect(jsonPath("$.docs[2].COUNTRY").doesNotExist())

                .andExpect(jsonPath("$.docs[3].id").value(84))
                .andExpect(jsonPath("$.docs[3].name").value("Libra"))
                .andExpect(jsonPath("$.docs[3].codinvestor").value("LIB"))
                .andExpect(jsonPath("$.docs[3].codunit").value(""))
                .andExpect(jsonPath("$.docs[3].codconversion").value(""))
                .andExpect(jsonPath("$.docs[3].converted").value(true))
                .andExpect(jsonPath("$.docs[3].COUNTRY").doesNotExist())

                .andExpect(jsonPath("$.docs[4].id").value(85))
                .andExpect(jsonPath("$.docs[4].name").value("Peso"))
                .andExpect(jsonPath("$.docs[4].codinvestor").value("PES"))
                .andExpect(jsonPath("$.docs[4].codunit").value(""))
                .andExpect(jsonPath("$.docs[4].codconversion").value(""))
                .andExpect(jsonPath("$.docs[4].converted").value(true))
                .andExpect(jsonPath("$.docs[3].COUNTRY").doesNotExist());
    }

    @Entao("^deve exibir um JSONP com os campos enviados da Lista de Moedas$")
    public void deve_exibir_um_JSONP_com_os_campos_enviados_da_Lista_de_Moedas() throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(result, Matchers.equalTo( "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":4,\"name\":\"Euro\",\"codinvestor\":\"EUR\",\"codunit\":\"\",\"codconversion\":\"\",\"converted\":true,\"countries\":[\"Chipre\",\"Finlândia\",\"Grécia\",\"Irlanda\",\"Luxemburgo\",\"Malta\",\"Mônaco\",\"Holanda\",\"Portugal\",\"Eslováquia\",\"Eslovênia\",\"Espanha\",\"União Européia\"]},{\"id\":5,\"name\":\"Euro (R$)\",\"codinvestor\":\"EUR\",\"codunit\":\"\",\"codconversion\":\"BRL\",\"converted\":true,\"countries\":[\"Chipre\",\"Finlândia\",\"Grécia\",\"Irlanda\",\"Luxemburgo\",\"Malta\",\"Mônaco\",\"Holanda\",\"Portugal\",\"Eslováquia\",\"Eslovênia\",\"Espanha\",\"União Européia\"]},{\"id\":18,\"name\":\"Dólar Australiano\",\"codinvestor\":\"AUD\",\"codunit\":\"\",\"codconversion\":\"\",\"converted\":true,\"countries\":[\"Austrália\"]},{\"id\":84,\"name\":\"Libra\",\"codinvestor\":\"LIB\",\"codunit\":\"\",\"codconversion\":\"\",\"converted\":true,\"countries\":[\"Inglaterra\"]},{\"id\":85,\"name\":\"Peso\",\"codinvestor\":\"PES\",\"codunit\":\"\",\"codconversion\":\"\",\"converted\":true,\"countries\":[\"Argentina\"]}]});"));
    }

    @Entao("^deve exibir um JSONP dos campos enviados sem o campo \"([^\"]*)\" da Lista de Moedas$")
    public void deve_exibir_um_JSONP_dos_campos_enviados_sem_o_campo_da_Lista_de_Moedas(String arg1) throws Throwable {
        final String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(result, Matchers.equalTo("/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":4,\"name\":\"Euro\",\"codinvestor\":\"EUR\",\"codunit\":\"\",\"codconversion\":\"\",\"converted\":true},{\"id\":5,\"name\":\"Euro (R$)\",\"codinvestor\":\"EUR\",\"codunit\":\"\",\"codconversion\":\"BRL\",\"converted\":true},{\"id\":18,\"name\":\"Dólar Australiano\",\"codinvestor\":\"AUD\",\"codunit\":\"\",\"codconversion\":\"\",\"converted\":true},{\"id\":84,\"name\":\"Libra\",\"codinvestor\":\"LIB\",\"codunit\":\"\",\"codconversion\":\"\",\"converted\":true},{\"id\":85,\"name\":\"Peso\",\"codinvestor\":\"PES\",\"codunit\":\"\",\"codconversion\":\"\",\"converted\":true}]});"));
    }

}
