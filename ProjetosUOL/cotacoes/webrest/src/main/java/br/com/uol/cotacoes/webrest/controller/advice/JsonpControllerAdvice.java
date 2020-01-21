package br.com.uol.cotacoes.webrest.controller.advice;

import java.nio.charset.Charset;

import br.com.uol.cotacoes.webrest.controller.MixedController;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import br.com.uol.cotacoes.webrest.controller.AssetController;
import br.com.uol.cotacoes.webrest.controller.CurrencyController;

/**
 * Formata a resposta no formato JSONP de acordo com o parametro jsonp passado na URL de requisicao
 *
 * Created by vrx_mtoledo on 11/05/17.
 */
@ControllerAdvice(assignableTypes = {CurrencyController.class, AssetController.class, MixedController.class})
public class JsonpControllerAdvice extends AbstractJsonpResponseBodyAdvice {

    public JsonpControllerAdvice() {
        super("jsonp");
    }
    
    @Override
    protected MediaType getContentType(MediaType contentType, ServerHttpRequest request, ServerHttpResponse response) {
    	return new MediaType("application", "javascript", Charset.forName("utf-8"));
    }

}
