package br.com.uol.cotacoes.webrest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.uol.cotacoes.webrest.helper.JSONErrorHelper;

/**
 * Created by vrx_mtoledo on 11/05/17.
 */
@Controller
public class ExceptionController implements ErrorController {

    @Autowired
    private Logger logger;

    @Autowired
    private JSONErrorHelper helper;


    @Override
    public String getErrorPath() {
        return "/error";
    }


    /**
     * trata todas as URLs que não estão mapeadas nos Controllers
     * @param response
     * @return JSON com HTTP Status 500, o horario do erro e o motivo do erro
     */
    @RequestMapping(value="/error")
    @ResponseBody
    public ObjectNode handleURLsNotMapped (HttpServletRequest request, HttpServletResponse response) {
        String msg = "Error on request processing.";
        logger.error(msg);

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        response.setStatus( httpStatus.value() );
        return helper.formatJSON( httpStatus, msg);
    }

}
