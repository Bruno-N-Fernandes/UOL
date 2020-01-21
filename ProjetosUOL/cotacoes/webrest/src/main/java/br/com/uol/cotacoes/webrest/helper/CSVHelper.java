/**
 * 
 */
package br.com.uol.cotacoes.webrest.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.uol.cotacoes.core.model.entity.CurrencyRateIntraday;
import br.com.uol.cotacoes.webrest.mappers.ClassMapper;

/**
 * Realiza as funções de conversão de objetos para JSON. Auxilia em funções para
 * que fique mais facil trabalhar com JSON.
 * 
 * @author mzp_dferraz
 *
 */
@Component
public class CSVHelper {

	private static final String DATE_FIELD = "date";
	private static final String COLUMN_SEPARATOR = ";";
	private static final String NEW_LINE = "\r\n";
	
	private DateTimeFormatter jsonDateTimeFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	private DateTimeFormatter csvDateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	private HashMap<String, Object> headerMap = new HashMap<>();

	/**
	 * Mapa com o mapeamento das entidades que a aplicação utiliza
	 */
	private Map<Class, ClassMapper> mappers;


	public CSVHelper(){
		//Campos moedas
		headerMap.put("id", "Identificação interna");
		headerMap.put("name", "Nome");
		headerMap.put("bidvalue", "Venda");
		headerMap.put("askvalue", "Compra");
		headerMap.put("maxbid", "Máximo");
		headerMap.put("minbid", "Mínimo");
		headerMap.put("variationbid", "Variação(R$)");
		headerMap.put("variationpercentbid", "Variação(%)");
		headerMap.put("openbidvalue", "Abertura");
		headerMap.put(DATE_FIELD, "Data");
		
		//Campos ativos
        headerMap.put("price", "Preço");
        headerMap.put("exchangeasset", "Ativo");
        headerMap.put("high", "Máximo(R$)");
        headerMap.put("low", "Mínimo(R$)");
        headerMap.put("open", "Abertura(R$)");
        headerMap.put("volume", "Volume");
        headerMap.put("close", "Fechamento(R$)");
        headerMap.put("bid", "Venda");
        headerMap.put("ask", "Compra");
        headerMap.put("change", "Variação(R$)");
        headerMap.put("pctChange", "Variação(%)");
	}


	public <E> String formatCSV(final List<E> listObjectToConvert, final String fields){
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(createHeader(fields)).append(NEW_LINE);
		
		listObjectToConvert.forEach((element) -> builder.append(convertSingleObjectToCSV(element, fields)).append(NEW_LINE) );
		
		return builder.toString();
	}
	
	
	/**
	 * Converte um objeto que esteja configurado no mapeamento para um {@link ObjectNode}
	 * 
	 * Itens configurados
	 * {@link CurrencyRateIntraday }
	 * 
	 * @param fields Lista separada por virgula com os nomes dos campos que serão adicionado ao JSON
	 * @return Item {@link ObjectNode} com o objeto convertido para item de JSON 
	 */
	private <E> String createHeader(final String fields) {
		
		StringBuilder header = new StringBuilder();

		String[] fieldsKeys = fields.split(",");
		
		for (String fieldKey : fieldsKeys) {

			Object headerKey = headerMap.get(fieldKey);
			if( headerKey != null ) {
				header.append(headerKey).append(COLUMN_SEPARATOR);
			}
		}

		return header.toString();
	}
	
	
	/**
	 * Converte um objeto que esteja configurado no mapeamento para um {@link ObjectNode}
	 * 
	 * Itens configurados
	 * {@link CurrencyRateIntraday }
	 * 
	 * @param object Objeto mapeado para ser convertido
	 * @param fields Lista separada por virgula com os nomes dos campos que serão adicionado ao JSON
	 * @return Item {@link ObjectNode} com o objeto convertido para item de JSON 
	 */
	private <E> String convertSingleObjectToCSV(final E object, final String fields) {
		
		Map<String, Object> map = mappers.get(object.getClass()).generateMapFromEntity(object);

		StringBuilder node = new StringBuilder();

		String[] splitedFields = fields.split(",");
		
		for (String splitedField : splitedFields) {
			proccessValue(map, node, splitedField);
		}

		return node.toString();
	}
	
	/**
	 * Verifica se o campo solicitado existe no mapa de valores do objeto.
	 * Caso o campo exista ele realiza a conversão para o objeto {@link ObjectNode} na seguinte ordem.
	 * Verifica se o campo existe
	 * Verifica se é o campo {@link  }
	 * Caso não caia em nenhum dos casos acima ele cham o toString do objeto
	 * 
	 * @param objectMap Mapa com os campo do objeto que podem ser utilizados
	 * @param jsonNode Objeto JSON que será populado
	 * @param field Campo que está sendo processado
	 */
	private void proccessValue(final Map<String, Object> objectMap, final StringBuilder jsonNode, final String field) {

		Object objectField = objectMap.get(field);

		if( objectField == null ) {
			return;
		}

		String mappedField =  objectField.toString();
		
		if(DATE_FIELD.equals(field)){
			LocalDateTime dateTime = LocalDateTime.parse(mappedField, jsonDateTimeFormat);
			jsonNode.append(dateTime.format(csvDateTimeFormat)).append(COLUMN_SEPARATOR);		
			return;
		}
		
		jsonNode.append(mappedField).append(COLUMN_SEPARATOR);
	}
	
	
	@Autowired
	public void setMappers(List<ClassMapper> mappers){
		this.mappers = new HashMap<>();
		mappers.forEach((mapper) -> this.mappers.put(mapper.getMappedClass(), mapper) );
	}

}
