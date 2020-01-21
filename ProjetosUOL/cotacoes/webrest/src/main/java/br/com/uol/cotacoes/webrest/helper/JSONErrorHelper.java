package br.com.uol.cotacoes.webrest.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Cria JSON de resposta a erros.
 *
 * Created by vrx_mtoledo on 15/05/17.
 */
@Service
public class JSONErrorHelper {

    public ObjectNode formatJSON(final HttpStatus httpStatus, final String errorCause){
        ObjectNode toResponse = JsonNodeFactory.instance.objectNode();

        toResponse.put("HttpStatus", httpStatus.value() );

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String date = LocalDateTime.now().format(dateFormat);
        toResponse.put("Timestamp", date);

        toResponse.put("Cause", errorCause );

        return toResponse;
    }
}
