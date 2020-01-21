package br.com.uol.cotacoes.acoes;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;

import br.com.uol.cotacoes.ContextTest;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

/**
 * Created by vrx_mtoledo on 09/06/17.
 */
@Profile("test")
public class ListaIndicesSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;


    @Dado("^os campos: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
    public void os_campos_e(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {
        StringBuilder fields = new StringBuilder("fields=")
                .append(arg1).append(",")
                .append(arg2).append(",")
                .append(arg3).append(",")
                .append(arg4).append(",")
                .append(arg5);
        contextTest.setParemeterFields(fields.toString());
    }

    @Quando("^processamos a solicitação de Lista Indice$")
    public void processamos_a_solicitação_de_Lista_Indice() throws Throwable {
        StringBuilder url = new StringBuilder("/index/list?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParemeterFields());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir um JSON com os campos enviados da Lista Indice$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_da_Lista_Indice() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":10,\"name\":\"INDX IND\",\"exchange\":\"BOVESPA\",\"abbreviation\":\".INDX\",\"type\":\"I\"},{\"id\":11,\"name\":\"Lima General Inx\",\"exchange\":\"Bolsa de Valores de Lima\",\"abbreviation\":\".IGRA\",\"type\":\"I\"},{\"id\":12,\"name\":\"PX-PRAGUE SE IND\",\"exchange\":\"Bolsa de Valores de Praga\",\"abbreviation\":\".PX\",\"type\":\"I\"},{\"id\":13,\"name\":\"SASE Gral Index\",\"exchange\":\"Bolsa de Valores de Santiago\",\"abbreviation\":\".IGPA\",\"type\":\"I\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo TIPE da Lista Indice$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_TIPE_da_Lista_Indice() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":10,\"name\":\"INDX IND\",\"exchange\":\"BOVESPA\",\"abbreviation\":\".INDX\"},{\"id\":11,\"name\":\"Lima General Inx\",\"exchange\":\"Bolsa de Valores de Lima\",\"abbreviation\":\".IGRA\"},{\"id\":12,\"name\":\"PX-PRAGUE SE IND\",\"exchange\":\"Bolsa de Valores de Praga\",\"abbreviation\":\".PX\"},{\"id\":13,\"name\":\"SASE Gral Index\",\"exchange\":\"Bolsa de Valores de Santiago\",\"abbreviation\":\".IGPA\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSONP com os campos enviados da Lista Indice$")
    public void deve_exibir_um_JSONP_com_os_campos_enviados_da_Lista_Indice() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":10,\"name\":\"INDX IND\",\"exchange\":\"BOVESPA\",\"abbreviation\":\".INDX\",\"type\":\"I\"},{\"id\":11,\"name\":\"Lima General Inx\",\"exchange\":\"Bolsa de Valores de Lima\",\"abbreviation\":\".IGRA\",\"type\":\"I\"},{\"id\":12,\"name\":\"PX-PRAGUE SE IND\",\"exchange\":\"Bolsa de Valores de Praga\",\"abbreviation\":\".PX\",\"type\":\"I\"},{\"id\":13,\"name\":\"SASE Gral Index\",\"exchange\":\"Bolsa de Valores de Santiago\",\"abbreviation\":\".IGPA\",\"type\":\"I\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSONP dos campos enviados sem o campo TIPE da Lista Indice$")
    public void deve_exibir_um_JSONP_dos_campos_enviados_sem_o_campo_TIPE_da_Lista_Indice() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":10,\"name\":\"INDX IND\",\"exchange\":\"BOVESPA\",\"abbreviation\":\".INDX\"},{\"id\":11,\"name\":\"Lima General Inx\",\"exchange\":\"Bolsa de Valores de Lima\",\"abbreviation\":\".IGRA\"},{\"id\":12,\"name\":\"PX-PRAGUE SE IND\",\"exchange\":\"Bolsa de Valores de Praga\",\"abbreviation\":\".PX\"},{\"id\":13,\"name\":\"SASE Gral Index\",\"exchange\":\"Bolsa de Valores de Santiago\",\"abbreviation\":\".IGPA\"}]});";
        assertEquals( expected, result);
    }

}
