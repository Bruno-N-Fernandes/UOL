package br.com.uol.cotacoes.webrest.controller.advice;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.uol.cotacoes.webrest.helper.JSONErrorHelper;

/**
 * Classe captura exceções e devolve JSON com HTTP Status, o horario do erro e o motivo do erro.
 * O status 500 (INTERNAL_SERVER_ERROR) é o status default.
 *
 * Created by vrx_mtoledo on 12/05/17.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    private Logger logger;

    @Autowired
    private JSONErrorHelper helper;

    private static final String ERRO = "Erro.";


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ObjectNode catchException(HttpMessageNotReadableException e, HttpServletResponse response) {
        logger.error(ERRO, e);

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        response.setStatus(httpStatus.value());

        return helper.formatJSON( httpStatus, e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ObjectNode catchException(BindException e, HttpServletResponse response) {
        logger.error(ERRO, e);

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        response.setStatus(httpStatus.value());

        return helper.formatJSON( httpStatus, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ObjectNode catchException(MethodArgumentNotValidException e, HttpServletResponse response) {
        logger.error(ERRO, e);

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        response.setStatus(httpStatus.value());

        return helper.formatJSON( httpStatus, e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ObjectNode catchException(MissingServletRequestParameterException e, HttpServletResponse response) {
        logger.error(ERRO, e);

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        response.setStatus(httpStatus.value());

        return helper.formatJSON( httpStatus, e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseBody
    public ObjectNode catchException(MissingServletRequestPartException e, HttpServletResponse response) {
        logger.error(ERRO, e);

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        response.setStatus(httpStatus.value());

        return helper.formatJSON( httpStatus, e.getMessage());
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    public ObjectNode catchException(TypeMismatchException e, HttpServletResponse response) {
        logger.error(ERRO, e);

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        response.setStatus(httpStatus.value());

        return helper.formatJSON( httpStatus, e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ObjectNode catchException(HttpRequestMethodNotSupportedException e, HttpServletResponse response) {
        logger.error(ERRO, e);

        HttpStatus httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
        response.setStatus(httpStatus.value());

        return helper.formatJSON( httpStatus, e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    public ObjectNode catchException(HttpMediaTypeNotAcceptableException e, HttpServletResponse response) {
        logger.error(ERRO, e);

        HttpStatus httpStatus = HttpStatus.NOT_ACCEPTABLE;
        response.setStatus(httpStatus.value());

        return helper.formatJSON( httpStatus, e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ObjectNode catchException(HttpMediaTypeNotSupportedException e, HttpServletResponse response) {
        logger.error(ERRO, e);

        HttpStatus httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        response.setStatus(httpStatus.value());

        return helper.formatJSON( httpStatus, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ObjectNode catchException(Exception e, HttpServletResponse response) {
        logger.error(ERRO, e);

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        response.setStatus(httpStatus.value());

        return helper.formatJSON( httpStatus, e.getMessage());
    }

}