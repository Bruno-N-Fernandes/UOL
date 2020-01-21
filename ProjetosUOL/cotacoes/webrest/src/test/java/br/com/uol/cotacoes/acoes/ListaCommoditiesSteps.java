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
public class ListaCommoditiesSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;

    @Quando("^processamos a solicitação de Lista Commodities$")
    public void processamos_a_solicitação_de_Lista_Commodities() throws Throwable {
        StringBuilder url = new StringBuilder("/commodities/list?")
                .append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParemeterFields());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir um JSON com os campos enviados da Lista Commodities$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_da_Lista_Commodities() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":1163,\"name\":\"OURO\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XAU=X\",\"type\":\"C\"},{\"id\":1164,\"name\":\"PRATA\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XAG=X\",\"type\":\"C\"},{\"id\":1165,\"name\":\"PLATINA\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XPT=X\",\"type\":\"C\"},{\"id\":1166,\"name\":\"PALADIO\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XPD=X\",\"type\":\"C\"},{\"id\":1167,\"name\":\"PETROLEO\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"BRT-\",\"type\":\"C\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON dos campos enviados sem o campo TIPE da Lista Commodities$")
    public void deve_exibir_um_JSON_dos_campos_enviados_sem_o_campo_TIPE_da_Lista_Commodities() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":1163,\"name\":\"OURO\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XAU=X\"},{\"id\":1164,\"name\":\"PRATA\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XAG=X\"},{\"id\":1165,\"name\":\"PLATINA\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XPT=X\"},{\"id\":1166,\"name\":\"PALADIO\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XPD=X\"},{\"id\":1167,\"name\":\"PETROLEO\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"BRT-\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSONP com os campos enviados da Lista Commodities$")
    public void deve_exibir_um_JSONP_com_os_campos_enviados_da_Lista_Commodities() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":1163,\"name\":\"OURO\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XAU=X\",\"type\":\"C\"},{\"id\":1164,\"name\":\"PRATA\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XAG=X\",\"type\":\"C\"},{\"id\":1165,\"name\":\"PLATINA\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XPT=X\",\"type\":\"C\"},{\"id\":1166,\"name\":\"PALADIO\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XPD=X\",\"type\":\"C\"},{\"id\":1167,\"name\":\"PETROLEO\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"BRT-\",\"type\":\"C\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSONP dos campos enviados sem o campo TIPE da Lista Commodities$")
    public void deve_exibir_um_JSONP_dos_campos_enviados_sem_o_campo_TIPE_da_Lista_Commodities() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":1163,\"name\":\"OURO\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XAU=X\"},{\"id\":1164,\"name\":\"PRATA\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XAG=X\"},{\"id\":1165,\"name\":\"PLATINA\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XPT=X\"},{\"id\":1166,\"name\":\"PALADIO\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"XPD=X\"},{\"id\":1167,\"name\":\"PETROLEO\",\"exchange\":\"COMMODITIES\",\"abbreviation\":\"BRT-\"}]});";
        assertEquals( expected, result);
    }

}
