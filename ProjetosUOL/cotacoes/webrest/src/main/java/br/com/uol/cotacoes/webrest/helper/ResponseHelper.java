package br.com.uol.cotacoes.webrest.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author mzp_dferraz
 *
 */
@Component
public class ResponseHelper {
	
    @Autowired
    private JSONHelper jsonHelper;
    
    @Autowired
    private CSVHelper csvHelper;
    
    private static final String CSV_EXTENSION = "csv";

	/**
	 * Gera o retorno das requisições identificando o formato da resposta que deve ser utilizado
	 * Formatos:
	 * {@code JSON}
	 * {@code CSV}
	 * 
	 * @param fields
	 * @param extension
	 * @param currenciesOfToday
	 * @return
	 */
	public <E> Object generateReturnByFormat(final List<E> currenciesOfToday, final String fields, final String extension ) {
		if(extension != null && CSV_EXTENSION.equalsIgnoreCase(extension)){
        	return csvHelper.formatCSV(currenciesOfToday, fields);
        }        
        return  jsonHelper.formatJSON(currenciesOfToday, fields);
	}
	
	/**
	 * Gera o retorno de uma primira página de uma lista
	 * 
	 * @param listObjectToConvert
	 * @param fields
	 * @param size
	 * @return
	 */
	public <E> ObjectNode generateReturnFirstPage( final List<E> listObjectToConvert, final String fields, final int size){
		return jsonHelper.formatJSONFirstPage(listObjectToConvert, fields, size);	
	}	
	
	/**
	 * Gera o retorno de uma página do tipo {@code NEXT} próxima
	 * 
	 * @param listObjectToConvert
	 * @param fields
	 * @param size
	 * @return
	 */
	public <E> ObjectNode generateReturnNextPage( final List<E> listObjectToConvert, final String fields, final int size){		
		return jsonHelper.formatJSONNextPage(listObjectToConvert, fields, size);		
	}
	
	/**
	 * Gera o retorno de uma página do tipo {@code PREV} anterior
	 * 
	 * @param listObjectToConvert
	 * @param fields
	 * @param size
	 * @return
	 */
	public <E> ObjectNode generateReturnPreviousPage(final  List<E> listObjectToConvert, final String fields, final int size){
		return  jsonHelper.formatJSONPreviousPage(listObjectToConvert, fields, size);
	}
	
	/**
	 * Gera o retorno de uma lista de objetos
	 * 
	 * @param listObjectToConvert
	 * @param fields
	 * @return
	 */
	public <E> ObjectNode generateReturn(final List<E> listObjectToConvert, final String fields){
		return  (ObjectNode) generateReturnByFormat(listObjectToConvert, fields, null);
	}

}
