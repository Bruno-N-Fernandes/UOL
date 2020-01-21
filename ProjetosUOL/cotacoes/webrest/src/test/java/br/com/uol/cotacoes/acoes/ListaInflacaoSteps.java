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
public class ListaInflacaoSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;

    @Quando("^processamos a solicitação de Lista Inflacao$")
    public void processamos_a_solicitação_de_Lista_Inflacao() throws Throwable {
        StringBuilder url = new StringBuilder("/inflation/list?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParemeterFields());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir um JSON com os campos enviados da Lista Inflacao$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_da_Lista_Inflacao() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":1172,\"name\":\"IPC\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIPC=RR\",\"type\":\"F\"},{\"id\":1173,\"name\":\"IGPDI\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIGPDI=RR\",\"type\":\"F\"},{\"id\":1174,\"name\":\"IGPM\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIGPM=RR\",\"type\":\"F\"},{\"id\":1175,\"name\":\"IPCA\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIPCA=RR\",\"type\":\"F\"},{\"id\":1176,\"name\":\"INPC\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRINPC=RR\",\"type\":\"F\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo TIPE da Lista Inflacao$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_TIPE_da_Lista_Inflacao() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":1172,\"name\":\"IPC\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIPC=RR\"},{\"id\":1173,\"name\":\"IGPDI\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIGPDI=RR\"},{\"id\":1174,\"name\":\"IGPM\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIGPM=RR\"},{\"id\":1175,\"name\":\"IPCA\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIPCA=RR\"},{\"id\":1176,\"name\":\"INPC\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRINPC=RR\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSONP com os campos enviados da Lista Inflacao$")
    public void deve_exibir_um_JSONP_com_os_campos_enviados_da_Lista_Inflacao() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":1172,\"name\":\"IPC\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIPC=RR\",\"type\":\"F\"},{\"id\":1173,\"name\":\"IGPDI\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIGPDI=RR\",\"type\":\"F\"},{\"id\":1174,\"name\":\"IGPM\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIGPM=RR\",\"type\":\"F\"},{\"id\":1175,\"name\":\"IPCA\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIPCA=RR\",\"type\":\"F\"},{\"id\":1176,\"name\":\"INPC\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRINPC=RR\",\"type\":\"F\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSONP dos campos enviados sem o campo TIPE da Lista Inflacao$")
    public void deve_exibir_um_JSONP_dos_campos_enviados_sem_o_campo_TIPE_da_Lista_Inflacao() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":1172,\"name\":\"IPC\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIPC=RR\"},{\"id\":1173,\"name\":\"IGPDI\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIGPDI=RR\"},{\"id\":1174,\"name\":\"IGPM\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIGPM=RR\"},{\"id\":1175,\"name\":\"IPCA\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRIPCA=RR\"},{\"id\":1176,\"name\":\"INPC\",\"exchange\":\"INDICADORES\",\"abbreviation\":\"BRINPC=RR\"}]});";
        assertEquals( expected, result);
    }

}
