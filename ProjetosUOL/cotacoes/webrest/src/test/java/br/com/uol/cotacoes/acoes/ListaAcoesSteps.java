package br.com.uol.cotacoes.acoes;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.PostConstruct;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.uol.cotacoes.core.business.service.ExchangeAssetService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.test.web.servlet.MockMvc;

import br.com.uol.cotacoes.ContextTest;
import br.com.uol.cotacoes.core.business.service.filter.ExchangeAssetFilter;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;




/**
 * @author mzp_dferraz
 *
 */
@Profile("test")
public class ListaAcoesSteps {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContextTest contextTest;
    
    @Autowired
    private ExchangeAssetFilter filter;

    @Autowired
    private ExchangeAssetService service;
    
    @PostConstruct
    public void init(){
    	filter.init();
    }
    
    @Dado("^os campos: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
    public void os_campos_e(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) throws Throwable {
        StringBuilder fields = new StringBuilder("fields=")
                .append(arg1).append(",")
                .append(arg2).append(",")
                .append(arg3).append(",")
                .append(arg4).append(",")
                .append(arg5).append(",")
                .append(arg6);
        contextTest.setParemeterFields(fields.toString());
    }

    @Dado("^os campos: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
    public void os_campos_e(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7) throws Throwable {
        StringBuilder fields = new StringBuilder("fields=")
                .append(arg1).append(",")
                .append(arg2).append(",")
                .append(arg3).append(",")
                .append(arg4).append(",")
                .append(arg5).append(",")
                .append(arg6).append(",")
                .append(arg7);
        contextTest.setParemeterFields(fields.toString());
    }
    
    @Dado("^as letras \"([^\"]*)\"$")
    public void e_as_letras(String arg1) throws Throwable {
        StringBuilder fields = new StringBuilder("letters=")
                .append(arg1);
        contextTest.setParameterLetters(fields.toString());
    }

    @Quando("^processamos a solicitação de todas Lista Acao$")
    public void processamos_a_solicitação_de_todas_Lista_Acao() throws Throwable {
        StringBuilder url = new StringBuilder("/asset/list?")
        		.append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParemeterFields());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }
    
    @Quando("^processamos a solicitação de Lista Acao$")
    public void processamos_a_solicitação_de_Lista_Acao() throws Throwable {
        StringBuilder url = new StringBuilder("/asset/list?")
        		.append(contextTest.getParameterJsonp())
                .append("&")
                .append(contextTest.getParemeterFields())
                .append("&")
                .append(contextTest.getParameterLetters())
                .append("&")
                .append(contextTest.getParemeterSize());
        contextTest.setResultActions( this.mockMvc.perform(get(url.toString())) );
    }

    @Entao("^deve exibir um JSON com os campos enviados da Lista Acao$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_da_Lista_Acoes() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":18,\"name\":\"ABC BRASIL PN   \",\"companies\":[\"Banco ABC Brasil SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"ABCB4.SA\",\"type\":\"S\"},{\"id\":19,\"name\":\"ABC BRASIL ON   \",\"companies\":[\"Banco ABC Brasil SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"ABCB3.SA\",\"type\":\"S\"},{\"id\":20,\"name\":\"ABNOTE ON\",\"companies\":[\"Banco ABC Brasil SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"ABNB3.SA\",\"type\":\"S\"},{\"id\":84,\"name\":\"VARIG TRANSP ON 2\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA2\",\"type\":\"S\"},{\"id\":85,\"name\":\"VARIG TRANSP ON 3\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA3\",\"type\":\"S\"},{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\",\"type\":\"S\"},{\"id\":233,\"name\":\"COTEMINAS ON    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM3.SA\",\"type\":\"S\"},{\"id\":645,\"name\":\"TELE NORT CL PN \",\"companies\":[\"Tele Norte Celular Participacoes SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"TNCP4.SA\",\"type\":\"S\"},{\"id\":699,\"name\":\"VARIG TRANSP ON\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA\",\"type\":\"S\"},{\"id\":718,\"name\":\"FIP BRASCAN CI  \",\"companies\":[\"FIP BRASCAN CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BRCP11.SA\",\"type\":\"S\"},{\"id\":1027,\"name\":\"ISHARES BOVA CI \",\"companies\":[\"ISHARES BOVA CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BOVA11.SA\",\"type\":\"S\"},{\"id\":1205,\"name\":\"FII BB CORP CI  \",\"companies\":[\"FII BB CORP CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BBRC11.SA\",\"type\":\"S\"},{\"id\":1313,\"name\":\"AFLUENTE PNA\",\"companies\":[],\"exchange\":\"BOVESPA\",\"abbreviation\":\"AFLU5.SA\",\"type\":\"S\"}]}";
        assertEquals( expected, result);
    }
    
    @Entao("^deve exibir um JSON dos campos enviados sem o campo TIPE da Lista Acao$")
    public void deve_exibir_um_JSON_com_os_campos_enviados_sem_o_campo_TIPE_da_Lista_Acoes() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":18,\"name\":\"ABC BRASIL PN   \",\"companies\":[\"Banco ABC Brasil SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"ABCB4.SA\"},{\"id\":19,\"name\":\"ABC BRASIL ON   \",\"companies\":[\"Banco ABC Brasil SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"ABCB3.SA\"},{\"id\":20,\"name\":\"ABNOTE ON\",\"companies\":[\"Banco ABC Brasil SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"ABNB3.SA\"},{\"id\":84,\"name\":\"VARIG TRANSP ON 2\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA2\"},{\"id\":85,\"name\":\"VARIG TRANSP ON 3\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA3\"},{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\"},{\"id\":233,\"name\":\"COTEMINAS ON    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM3.SA\"},{\"id\":645,\"name\":\"TELE NORT CL PN \",\"companies\":[\"Tele Norte Celular Participacoes SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"TNCP4.SA\"},{\"id\":699,\"name\":\"VARIG TRANSP ON\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA\"},{\"id\":718,\"name\":\"FIP BRASCAN CI  \",\"companies\":[\"FIP BRASCAN CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BRCP11.SA\"},{\"id\":1027,\"name\":\"ISHARES BOVA CI \",\"companies\":[\"ISHARES BOVA CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BOVA11.SA\"},{\"id\":1205,\"name\":\"FII BB CORP CI  \",\"companies\":[\"FII BB CORP CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BBRC11.SA\"},{\"id\":1313,\"name\":\"AFLUENTE PNA\",\"companies\":[],\"exchange\":\"BOVESPA\",\"abbreviation\":\"AFLU5.SA\"}]}";
        assertEquals( expected, result);
    }
    
    @Entao("^deve exibir (\\d+) resultados com COTEMINAS da Lista Acao$")
    public void deve_exibir_resultados_com_COTEMINAS_da_Lista_Acao(int arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":233,\"name\":\"COTEMINAS ON    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM3.SA\",\"type\":\"S\"},{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\",\"type\":\"S\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir (\\d+) resultados com Companhia de Tecidos Norte de Minas da Lista Acao$")
    public void deve_exibir_resultados_com_Companhia_de_Tecidos_Norte_de_Minas_da_Lista_Acao(int arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":233,\"name\":\"COTEMINAS ON    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM3.SA\",\"type\":\"S\"},{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\",\"type\":\"S\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir (\\d+) resultados com CTNM da Lista Acao$")
    public void deve_exibir_resultados_com_CTNM_da_Lista_Acao(int arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":233,\"name\":\"COTEMINAS ON    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM3.SA\",\"type\":\"S\"},{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\",\"type\":\"S\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir (\\d+) resultado com CTNM(\\d+) da Lista Acao$")
    public void deve_exibir_resultado_com_CTNM_da_Lista_Acao(int arg1, int arg2) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\",\"type\":\"S\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir (\\d+) resultado com CTNM(\\d+)\\.SA da Lista Acao$")
    public void deve_exibir_resultado_com_CTNM_SA_da_Lista_Acao(int arg1, int arg2) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\",\"type\":\"S\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir todos os resultados com Participacoes da Lista Acao$")
    public void deve_exibir_todos_os_resultados_com_Participacoes_da_Lista_Acao() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":645,\"name\":\"TELE NORT CL PN \",\"companies\":[\"Tele Norte Celular Participacoes SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"TNCP4.SA\",\"type\":\"S\"},{\"id\":699,\"name\":\"VARIG TRANSP ON\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA\",\"type\":\"S\"},{\"id\":84,\"name\":\"VARIG TRANSP ON 2\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA2\",\"type\":\"S\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSONP com os campos enviados da Lista Acao$")
    public void deve_exibir_um_JSONP_com_os_campos_enviados_da_Lista_Acao() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":18,\"name\":\"ABC BRASIL PN   \",\"companies\":[\"Banco ABC Brasil SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"ABCB4.SA\",\"type\":\"S\"},{\"id\":19,\"name\":\"ABC BRASIL ON   \",\"companies\":[\"Banco ABC Brasil SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"ABCB3.SA\",\"type\":\"S\"},{\"id\":20,\"name\":\"ABNOTE ON\",\"companies\":[\"Banco ABC Brasil SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"ABNB3.SA\",\"type\":\"S\"},{\"id\":84,\"name\":\"VARIG TRANSP ON 2\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA2\",\"type\":\"S\"},{\"id\":85,\"name\":\"VARIG TRANSP ON 3\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA3\",\"type\":\"S\"},{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\",\"type\":\"S\"},{\"id\":233,\"name\":\"COTEMINAS ON    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM3.SA\",\"type\":\"S\"},{\"id\":645,\"name\":\"TELE NORT CL PN \",\"companies\":[\"Tele Norte Celular Participacoes SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"TNCP4.SA\",\"type\":\"S\"},{\"id\":699,\"name\":\"VARIG TRANSP ON\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA\",\"type\":\"S\"},{\"id\":718,\"name\":\"FIP BRASCAN CI  \",\"companies\":[\"FIP BRASCAN CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BRCP11.SA\",\"type\":\"S\"},{\"id\":1027,\"name\":\"ISHARES BOVA CI \",\"companies\":[\"ISHARES BOVA CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BOVA11.SA\",\"type\":\"S\"},{\"id\":1205,\"name\":\"FII BB CORP CI  \",\"companies\":[\"FII BB CORP CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BBRC11.SA\",\"type\":\"S\"},{\"id\":1313,\"name\":\"AFLUENTE PNA\",\"companies\":[],\"exchange\":\"BOVESPA\",\"abbreviation\":\"AFLU5.SA\",\"type\":\"S\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSONP dos campos enviados sem o campo TIPE da Lista Acao$")
    public void deve_exibir_um_JSONP_dos_campos_enviados_sem_o_campo_TIPE_da_Lista_Acao() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":18,\"name\":\"ABC BRASIL PN   \",\"companies\":[\"Banco ABC Brasil SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"ABCB4.SA\"},{\"id\":19,\"name\":\"ABC BRASIL ON   \",\"companies\":[\"Banco ABC Brasil SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"ABCB3.SA\"},{\"id\":20,\"name\":\"ABNOTE ON\",\"companies\":[\"Banco ABC Brasil SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"ABNB3.SA\"},{\"id\":84,\"name\":\"VARIG TRANSP ON 2\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA2\"},{\"id\":85,\"name\":\"VARIG TRANSP ON 3\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA3\"},{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\"},{\"id\":233,\"name\":\"COTEMINAS ON    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM3.SA\"},{\"id\":645,\"name\":\"TELE NORT CL PN \",\"companies\":[\"Tele Norte Celular Participacoes SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"TNCP4.SA\"},{\"id\":699,\"name\":\"VARIG TRANSP ON\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA\"},{\"id\":718,\"name\":\"FIP BRASCAN CI  \",\"companies\":[\"FIP BRASCAN CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BRCP11.SA\"},{\"id\":1027,\"name\":\"ISHARES BOVA CI \",\"companies\":[\"ISHARES BOVA CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BOVA11.SA\"},{\"id\":1205,\"name\":\"FII BB CORP CI  \",\"companies\":[\"FII BB CORP CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BBRC11.SA\"},{\"id\":1313,\"name\":\"AFLUENTE PNA\",\"companies\":[],\"exchange\":\"BOVESPA\",\"abbreviation\":\"AFLU5.SA\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir (\\d+) JSONP com COTEMINAS da Lista Acao$")
    public void deve_exibir_JSONP_com_COTEMINAS_da_Lista_Acao(int arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":233,\"name\":\"COTEMINAS ON    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM3.SA\",\"type\":\"S\"},{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\",\"type\":\"S\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir (\\d+) JSONP com Companhia de Tecidos Norte de Minas da Lista Acao$")
    public void deve_exibir_JSONP_com_Companhia_de_Tecidos_Norte_de_Minas_da_Lista_Acao(int arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":233,\"name\":\"COTEMINAS ON    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM3.SA\",\"type\":\"S\"},{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\",\"type\":\"S\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir (\\d+) JSONP com CTNM da Lista Acao$")
    public void deve_exibir_JSONP_com_CTNM_da_Lista_Acao(int arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":233,\"name\":\"COTEMINAS ON    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM3.SA\",\"type\":\"S\"},{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\",\"type\":\"S\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir (\\d+) JSONP com CTNM(\\d+) da Lista Acao$")
    public void deve_exibir_JSONP_com_CTNM_da_Lista_Acao(int arg1, int arg2) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\",\"type\":\"S\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir (\\d+) JSONP com CTNM(\\d+)\\.SA da Lista Acao$")
    public void deve_exibir_JSONP_com_CTNM_SA_da_Lista_Acao(int arg1, int arg2) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\",\"type\":\"S\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir todos os JSONP com Participacoes da Lista Acao$")
    public void deve_exibir_todos_os_JSONP_com_Participacoes_da_Lista_Acao() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":645,\"name\":\"TELE NORT CL PN \",\"companies\":[\"Tele Norte Celular Participacoes SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"TNCP4.SA\",\"type\":\"S\"},{\"id\":699,\"name\":\"VARIG TRANSP ON\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA\",\"type\":\"S\"},{\"id\":84,\"name\":\"VARIG TRANSP ON 2\",\"companies\":[\"VARIG PARTICIPACOES EM TRANSPORTES AEREOS SA\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"VPTA3.SA2\",\"type\":\"S\"}]});";
        assertEquals( expected, result);
    }

 //   <exchange_asset idt_exchange_asset="232" idt_exchange="1" nam_exchange_asset="COTEMINAS PN    " abv_exchange_asset="CTNM4.SA" ind_asset_type="S"/>

    @Dado("^que incluimos nova acao CTNM na Lista Acao$")
    public void incluimos_nova_acao_CTNM_na_Lista_Acao() throws Throwable {
        String insertTableSQL = "insert into exchange_asset ("
        + " idt_exchange_asset, idt_exchange, nam_exchange_asset, abv_exchange_asset, ind_asset_type)"
        + " values (?, ?, ?, ?, ?)";

        Connection jdbcConnection = SessionFactoryUtils.getDataSource( sessionFactory).getConnection();

        PreparedStatement preparedStatement = jdbcConnection.prepareStatement(insertTableSQL);
        preparedStatement.setInt(1, 234);
        preparedStatement.setInt(2, 1);
        preparedStatement.setString(3, "COTEMINAS PN    ");
        preparedStatement.setString(4, "CTNM5.SA");
        preparedStatement.setString(5, "S");

        preparedStatement .executeUpdate();
        preparedStatement.close();
        jdbcConnection.close();
    }

    @Quando("^chamamos o job de atualizar as ações por letra$")
    public void chamamos_o_job_de_atualizar_as_ações_por_letra() throws Throwable {
        service.updateAssetsIndex();
    }

    @Entao("^deve exibir (\\d+) JSONP com CTNM da Lista Acao incluindo a ação nova que foi inserida$")
    public void deve_exibir_JSONP_com_CTNM_da_Lista_Acao_incluindo_a_ação_nova_que_foi_inserida(int arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":233,\"name\":\"COTEMINAS ON    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM3.SA\",\"type\":\"S\"},{\"id\":232,\"name\":\"COTEMINAS PN    \",\"companies\":[\"Companhia de Tecidos Norte de Minas - COTEMINAS\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"CTNM4.SA\",\"type\":\"S\"}]});";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSON com os dados da acao BBRC(\\d+)\\.SA$")
    public void deve_exibir_um_JSON_com_os_dados_da_acao_BBRC_SA(int arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":1205,\"name\":\"FII BB CORP CI  \",\"companies\":[\"FII BB CORP CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BBRC11.SA\",\"type\":\"S\"}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir o JSONP com os dados da acao BBRC(\\d+)\\.SA$")
    public void deve_exibir_o_JSONP_com_os_dados_da_acao_BBRC_SA(int arg1) throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":1205,\"name\":\"FII BB CORP CI  \",\"companies\":[\"FII BB CORP CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BBRC11.SA\",\"type\":\"S\"}]});";
        assertEquals( expected, result);

    }

    @Entao("^deve exibir um JSON com os dados da acao em todos os periodos$")
    public void deve_exibir_um_JSON_com_os_dados_da_acao_em_todos_os_periodos() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":1027,\"name\":\"ISHARES BOVA CI \",\"companies\":[\"ISHARES BOVA CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BOVA11.SA\",\"type\":\"S\",\"services\":[\"/asset/intraday/list\",\"/asset/interday/list/week\",\"/asset/interday/list/month\",\"/asset/interday/list/months\",\"/asset/interday/list/year\"]}]}";
        assertEquals( expected, result);
    }

    @Entao("^deve exibir um JSONP com os dados da acao em todos os periodos$")
    public void deve_exibir_um_JSONP_com_os_dados_da_acao_em_todos_os_periodos() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "/**/callback({\"prev\":null,\"next\":null,\"docs\":[{\"id\":1027,\"name\":\"ISHARES BOVA CI \",\"companies\":[\"ISHARES BOVA CI\"],\"exchange\":\"BOVESPA\",\"abbreviation\":\"BOVA11.SA\",\"type\":\"S\",\"services\":[\"/asset/intraday/list\",\"/asset/interday/list/week\",\"/asset/interday/list/month\",\"/asset/interday/list/months\",\"/asset/interday/list/year\"]}]});";
        assertEquals( expected, result);
    }
    @Entao("^deve exibir um JSON com os dados da acao afluente com apenas a informacao de ano$")
    public void deve_exibir_um_JSON_com_os_dados_da_acao_afluente_com_apenas_a_informacao_de_ano() throws Throwable {
        String result = contextTest.getResultActions()
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\"prev\":null,\"next\":null,\"docs\":[{\"id\":1313,\"name\":\"AFLUENTE PNA\",\"companies\":[],\"exchange\":\"BOVESPA\",\"abbreviation\":\"AFLU5.SA\",\"type\":\"S\",\"services\":[\"/asset/interday/list/year\"]}]}";
        assertEquals( expected, result);
    }
}
