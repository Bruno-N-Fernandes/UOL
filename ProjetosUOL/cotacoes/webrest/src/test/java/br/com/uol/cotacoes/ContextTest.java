package br.com.uol.cotacoes;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.ResultActions;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe para ser usada como contexto de teste
 *
 * Created by vrx_mtoledo on 02/05/17.
 */
@Service
@Profile("test")
public class ContextTest {

    @Setter
    @Getter
    String parameterJsonp;

    @Setter
    @Getter
    String parameterFormat;

    @Setter
    @Getter
    String paremeterCurrency;

    @Setter
    @Getter
    String paremeterFields;

    @Setter
    @Getter
    String paremeterSize;
    
    @Setter
    @Getter
    String parameterLetters;

    @Setter
    @Getter
    String parameterItem;

    @Setter
    @Getter
    String parameterPrev;

    @Setter
    @Getter
    String parameterNext;

    @Setter
    @Getter
    String parameterCurrencies;

    @Setter
    @Getter
    String parameterItens;


    @Setter
    @Getter
    ResultActions resultActions;
    
    public void clear(){
    	parameterJsonp = "";
    	parameterFormat = "";
    	paremeterCurrency = "";
    	paremeterFields = "";
    	paremeterSize = "";
    	parameterLetters = "";
        parameterItem = "";
        parameterPrev = "";
        parameterNext = "";
        parameterCurrencies = "";
        parameterItens = "";
    }

}
