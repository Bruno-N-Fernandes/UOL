package br.com.uol.cotacoes.acoes;

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
 * Created by vrx_mtoledo on 12/06/17.
 */
@Profile("test")
public class ListaIndicatorsSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;

    @Quando("^processamos a solicitação de Lista Indicadores$")
    public void processamos_a_solicitação_de_Lista_Indicadores() throws Throwable {
        StringBuilder url = new StringBuilder("/indicators/list?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParemeterFields());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir um JSON com os campos enviados da Lista Indicadores$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_da_Lista_Indicadores() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":1168,\"name\":\"SELIC\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRSELIC=RR\",\"type\":\"N\"},{\"id\":1169,\"name\":\"GLOBAL 40\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRAGLB40=RR\",\"type\":\"N\"},{\"id\":1170,\"name\":\"TR\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRTR=BRCB\",\"type\":\"N\"},{\"id\":1171,\"name\":\"CDI\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRCDICETIP=RR\",\"type\":\"N\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo TIPE da Lista Indicadores$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_TIPE_da_Lista_Indicadores() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":1168,\"name\":\"SELIC\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRSELIC=RR\"},{\"id\":1169,\"name\":\"GLOBAL 40\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRAGLB40=RR\"},{\"id\":1170,\"name\":\"TR\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRTR=BRCB\"},{\"id\":1171,\"name\":\"CDI\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRCDICETIP=RR\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSONP com os campos enviados da Lista Indicadores$")
    public void deve_exibir_um_JSONP_com_os_campos_enviados_da_Lista_Indicadores() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":1168,\"name\":\"SELIC\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRSELIC=RR\",\"type\":\"N\"},{\"id\":1169,\"name\":\"GLOBAL 40\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRAGLB40=RR\",\"type\":\"N\"},{\"id\":1170,\"name\":\"TR\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRTR=BRCB\",\"type\":\"N\"},{\"id\":1171,\"name\":\"CDI\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRCDICETIP=RR\",\"type\":\"N\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSONP dos campos enviados sem o campo TIPE da Lista Indicadores$")
    public void deve_exibir_um_JSONP_dos_campos_enviados_sem_o_campo_TIPE_da_Lista_Indicadores() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":1168,\"name\":\"SELIC\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRSELIC=RR\"},{\"id\":1169,\"name\":\"GLOBAL 40\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRAGLB40=RR\"},{\"id\":1170,\"name\":\"TR\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRTR=BRCB\"},{\"id\":1171,\"name\":\"CDI\",\"exchange\":\"INFLACAO\",\"abbreviation\":\"BRCDICETIP=RR\"}]});";
        assertEquals( expected, result);
    }


}
