package br.com.uol.cotacoes;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.Profile;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Created by vrx_mtoledo on 25/04/17.
 */
@RunWith(Cucumber.class)
@Profile("test")
public class CucumberTest {
    //testes de cambio
    @CucumberOptions(features = "src/test/resources/ListaMoedas.feature")
    public static class ListaMoedasTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ResumoMoeda.feature")
    public static class ResumoMoedaTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaMoedasHoje.feature")
    public static class ListaMoedasHojeTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaMoedasHojePaginada.feature")
    public static class ListaMoedasHojePaginadaTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaMoedasSemanal.feature")
    public static class ListaMoedasSemanalTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaMoedasMensal.feature")
    public static class ListaMoedasMensalTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaMoedasTrimestral.feature")
    public static class ListaMoedasTrimestralTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaMoedasAnual.feature")
    public static class ListaMoedasAnualTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaMoedasHistPag.feature")
    public static class ListaMoedasHistPagTest extends CucumberTest{
    }

    //testes de Bolsa de valores
    @CucumberOptions(features = "src/test/resources/ListaIndicesTrimestral.feature")
    public static class ListaIndicesTrimestralTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaIndicesAnual.feature")
    public static class ListaIndicesAnualTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaIndicesMensal.feature")
    public static class ListaIndicesMensalTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaIndicesSemanal.feature")
    public static class ListaIndicesSemanalTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaAcoes.feature")
    public static class ListaAcoesTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaAcoesAnual.feature")
    public static class ListaAcoesAnualTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaAcoesTrimestral.feature")
    public static class ListaAcoesTrimestralTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaAcoesMensal.feature")
    public static class ListaAcoesMensalTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaAcoesSemanal.feature")
    public static class ListaAcoesSemanalTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ResumoMultiplo.feature")
    public static class ResumoMultiploTest extends CucumberTest{
    }


    @CucumberOptions(features = "src/test/resources/TopAcoesNegociadas.feature")
    public static class TopAcoesNegociadasTest extends CucumberTest{
    }


    @CucumberOptions(features = "src/test/resources/TopAcoesAltas.feature")
    public static class TopAcoesAltasTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/TopAcoesBaixas.feature")
    public static class TopAcoesBaixasTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaAcoesHistPaginada.feature")
    public static class ListaAcoesHistPaginadaTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaIndicesHistPaginada.feature")
    public static class ListaIndicesHistPaginadaTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaInflacaoHistPaginada.feature")
    public static class ListaInflacaoHistPaginadaTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaIndicadoresHistPaginada.feature")
    public static class ListaIndicadoresHistPaginadaTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaCommoditiesHistPaginada.feature")
    public static class ListaCommoditiesHistPaginadaTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaAcoesHoje.feature")
    public static class ListaAcoesHojeTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaCommoditiesHoje.feature")
    public static class ListaCommoditiesHojeStepsTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaInflacaoHoje.feature")
    public static class ListaInflacaoHojeTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaIndicadoresHoje.feature")
    public static class ListaIndicadoresHojeTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaIndices.feature")
    public static class ListaIndicesTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaIndicesHoje.feature")
    public static class ListaIndicesHojeTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaCommodities.feature")
    public static class ListaCommoditiesTest extends CucumberTest{
    }
    
    @CucumberOptions(features = "src/test/resources/ListaIndicadores.feature")
    public static class ListaIndicatorsTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ListaInflacao.feature")
    public static class ListaInflacaoTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ResumoAcoes.feature")
    public static class ResumoAcoesTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ResumoIndices.feature")
    public static class ResumoIndicesTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ResumoCommodities.feature")
    public static class ResumoCommoditiesTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ResumoInflacao.feature")
    public static class ResumoInflacaoTest extends CucumberTest{
    }

    @CucumberOptions(features = "src/test/resources/ResumoIndicadores.feature")
    public static class ResumoIndicadoresTest extends CucumberTest{
    }
    
    @CucumberOptions(features = "src/test/resources/ListaAcoesHojePaginada.feature")
    public static class ListaAcoesHojePaginadaTest extends CucumberTest{
    }
    
    @CucumberOptions(features = "src/test/resources/ListaIndicesHojePaginada.feature")
    public static class ListaIndicesHojePaginadaTest extends CucumberTest{
    }    

    @CucumberOptions(features = "src/test/resources/ListaCommoditiesHojePaginada.feature")
    public static class ListaCommoditiesHojePaginadaTest extends CucumberTest{
    }
    
    @CucumberOptions(features = "src/test/resources/ListaIndicadoresHojePaginada.feature")
    public static class ListaIndicadoresHojePaginadaTest extends CucumberTest{
    }
    
    @CucumberOptions(features = "src/test/resources/ListaInflacaoHojePaginada.feature")
    public static class ListaInflacaoHojePaginadaTest extends CucumberTest{
    }

}