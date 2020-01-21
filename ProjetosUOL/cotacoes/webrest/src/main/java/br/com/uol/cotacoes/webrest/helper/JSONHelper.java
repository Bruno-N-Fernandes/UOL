/**
 * 
 */
package br.com.uol.cotacoes.webrest.helper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
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
public class JSONHelper {

	@Autowired
	private PaginationHelper paginationHelper;
	
	/**
	 * Mapa com o mapeamento das entidades que a aplicação utiliza
	 */
	private Map<Class, ClassMapper> mappers;

	/**
	 * Cria a pagina inicial de uma listagem paginada a partir da lista de objetos passada, 
	 * ele não considera que exista uma pagina anterior(prev), apenas verifica se existe uma próxima(next) pagina.
	 * 
	 * @param listObjectToConvert
	 * @param fields
	 * @param size
	 * @return
	 */
	public <E> ObjectNode formatJSONFirstPage( final List<E> listObjectToConvert, final String fields, final int size){
		return generatepageWithNext(listObjectToConvert, fields, size, null);	
	}	

	/**
	 * Cria a próxima página de um JSON considerando que exista um prev e verificando se ainda deve ter um next.
	 * 
	 * @param listObjectToConvert
	 * @param fields
	 * @param size
	 * @return
	 */
	public <E> ObjectNode formatJSONNextPage( final List<E> listObjectToConvert, final String fields, final int size){
		
		String prev = paginationHelper.createPrev(listObjectToConvert);
		
		return generatepageWithNext(listObjectToConvert, fields, size, prev);	

	}
	
	/**
	 * Cria a página anterior de um JSON considerando que exista um next e verificando se ainda deve ter um prev.
	 * 
	 * @param listObjectToConvert
	 * @param fields
	 * @param size
	 * @return
	 */
	public <E> ObjectNode formatJSONPreviousPage(final  List<E> listObjectToConvert, final String fields, final int size){
		
		String next = paginationHelper.createNext(listObjectToConvert);
		
		int sizeToValidate = size+1;
		if( listSizeIsBiggerOrEqualsThan(listObjectToConvert, sizeToValidate) ){				
			return generatePageWithPrevious(listObjectToConvert, fields, next, sizeToValidate);
		}		

		return formatJSON(listObjectToConvert, fields, null, next);
	}

	/**
	 * Cria uma listagem paginada a partir da lista de objetos passada,
	 * ele considera que exista uma pagina anterior(prev) e verifica se existe uma próxima(next) pagina.
	 * 
	 * @param listObjectToConvert
	 * @param fields
	 * @param size
	 * @param prev
	 * @return
	 */
	private <E> ObjectNode generatepageWithNext(final List<E> listObjectToConvert, final String fields, final int size, String prev) {
		if( listSizeIsBiggerOrEqualsThan(listObjectToConvert, (size+1)) ){				
			return generateWithNextPage(listObjectToConvert, fields, size, prev);
		}
		
		return formatJSON( listObjectToConvert, fields, prev, null);
	}


	/**
	 * Verifica se a lista está maior ou igual ao tamanho definido para a listagem.
	 * @param listObjectToConvert
	 * @param size
	 * @return Boolean
	 */
	private <E> boolean listSizeIsBiggerOrEqualsThan(final List<E> listObjectToConvert, final int size) {
		return listObjectToConvert.size() >= size;
	}

	
	/**
	 * Gera o JSON da lista de objeto considerando as informações de prev e next. 
	 * Como existe next ele desconsidera o ultimo item da lista na criação do JSON.
	 * 
	 * @param listObjectToConvert
	 * @param fields
	 * @param size
	 * @param prev
	 * @return
	 */
	private <E> ObjectNode generateWithNextPage(final List<E> listObjectToConvert, final String fields, final int size, final String prev) {
		List<E> subList = listObjectToConvert.subList(0,size);
		String next = paginationHelper.createNext(subList);
		return formatJSON( subList, fields, prev, next);
	}	
	
	/**
	 * Gera o JSON da lista de objeto considerando as informações de prev e next. 
	 * Como existe prev ele desconsidera o primeiro item da lista na criação do JSON.
	 * 
	 * @param listObjectToConvert
	 * @param fields
	 * @param next
	 * @param sizeToValidate
	 * @return
	 */
	private <E> ObjectNode generatePageWithPrevious(final List<E> listObjectToConvert, final String fields,
			final String next, final int sizeToValidate) {
		List<E> subList = listObjectToConvert.subList(1, sizeToValidate);
		String prev = paginationHelper.createPrev(subList);
		return formatJSON(subList, fields, prev, next);
	}

	/**
	 * Formata uma lista de objetos com os campos passados no formato JSON. Não cria valor nos indices de navegação no JSON de resposta
	 * @param listObjectToConvert
	 * @param fields
	 * @return
	 */
	public <E> ObjectNode formatJSON(final List<E> listObjectToConvert, final String fields){
		return formatJSON( listObjectToConvert, fields, null, null);
	}

	/**
	 * Formata uma lista de objetos com os campos passados no formato JSON. 
	 * Cria os indices de navegação com os valores passado como parametro.
	 * 
	 * @param listObjectToConvert
	 * @param fields
	 * @param prev
	 * @param next
	 * @return
	 */
	private <E> ObjectNode formatJSON(final List<E> listObjectToConvert, final String fields, final String prev, final String next){
		ObjectNode toResponse = JsonNodeFactory.instance.objectNode();

		toResponse.put("prev", prev);
		toResponse.put("next", next);

		ArrayNode arrayNode = toResponse.putArray("docs");
		arrayNode.addAll( convertListObjectToJSON(listObjectToConvert, fields) );

		return toResponse;
	}

	/**
	 * Convert uma lista de objetos em um {@link ArrayNode} desde que os itens da lista estejam
	 * configurados no mapeamento para um {@link ObjectNode}
	 * 
	 * @param listObjectToConvert Lista de objetos para serem convertidos
	 * @param fields Lista separada por virgula com os nomes dos campos que serão adicionado ao JSON
	 * @return Item {@link ArrayNode} com a lista de objetos 
	 */
	private <E> ArrayNode convertListObjectToJSON(final List<E> listObjectToConvert, final String fields) {
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		final ArrayNode arrayNode = objectNode.putArray("");

		listObjectToConvert.forEach((element) -> arrayNode.add(convertSingleObjectToJSON(element, fields)) );

		return arrayNode;
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
	private <E> ObjectNode convertSingleObjectToJSON(final E object, final String fields) {
		
		Map<String, Object> map = mappers.get(object.getClass()).generateMapFromEntity(object);

		ObjectNode node = JsonNodeFactory.instance.objectNode();

		String[] splitedFields = fields.split(",");
		
		for (String splitedField : splitedFields) {
			proccessValue(map, node, splitedField);
		}

		return node;
	}


	/**
	 * Verifica se o campo solicitado existe no mapa de valores do objeto.
	 * Caso o campo exista ele realiza a conversão para o objeto {@link ObjectNode} na seguinte ordem.
	 * Verifica se o campo existe
	 * Verifica se é {@link BigDecimal}
	 * Verifica se é {@link String}
	 * Verifica se é {@link Integer}
	 * Verifica se é {@link Boolean}
	 * Caso não caia em nenhum dos casos acima ele cham o toString do objeto
	 * 
	 * @param objectMap Mapa com os campo do objeto que podem ser utilizados
	 * @param jsonNode Objeto JSON que será populado
	 * @param field Campo que está sendo processado
	 */
	private void proccessValue(final Map<String, Object> objectMap, final ObjectNode jsonNode, final String field) {
		
		Object mappedField =  objectMap.get(field);
		
		if(fieldNotExists(mappedField)){			
			return;
		}
		
		if (fieldIsBigDecimal(mappedField)) {
			jsonNode.put(field, (BigDecimal) mappedField);
			return;
		}
		
		if (fieldIsString(mappedField)) {
			jsonNode.put(field, (String) mappedField);
			return;
		}
		
		if (fieldIsInteger(mappedField)) {
			jsonNode.put(field, (Integer) mappedField);
			return;
		}
		
		if (fieldIsBoolean(mappedField)) {
			jsonNode.put(field, (Boolean) mappedField);
			return;
		}
				
		if (fieldsIsArrayNode(mappedField)){
			jsonNode.set(field, (ArrayNode)mappedField);
			return;
		}
		
		jsonNode.put(field, objectMap.get(field).toString());
	}


	/**
	 * Verifica se o não campo existe no mapeamento
	 * 
	 * @param mappedField Campo mapeado
	 * @return Retorna {@code TRUE} caso o campo não exista no mapeamento
	 */
	private boolean fieldNotExists(final Object mappedField) {
		return mappedField == null;
	}
			
	/**
	 * Verifica se o campo é do tipo {@link Boolean}
	 * 
	 * @param mappedField Campo mapeado
	 * @return Retorna {@code TRUE} caso o campo seja do tipo {@link Boolean}
	 */
	private boolean fieldIsBoolean(final Object mappedField) {
		return mappedField instanceof Boolean;
	}

	/**
	 * Verifica se o campo é do tipo {@link Integer}
	 * 
	 * @param mappedField Campo mapeado
	 * @return Retorna {@code TRUE} caso o campo seja do tipo {@link Integer}
	 */
	private boolean fieldIsInteger(final Object mappedField) {
		return mappedField instanceof Integer;
	}

	/**
	 * Verifica se o campo é do tipo {@link BigDecimal}
	 * 
	 * @param mappedField Campo mapeado
	 * @return Retorna {@code TRUE} caso o campo seja do tipo {@link BigDecimal}
	 */
	private boolean fieldIsBigDecimal(final Object mappedField) {
		return mappedField instanceof BigDecimal;
	}
	
	/**
	 * Verifica se o campo é do tipo {@link String}
	 * 
	 * @param mappedField Campo mapeado
	 * @return Retorna {@code TRUE} caso o campo seja do tipo {@link String}
	 */
	private boolean fieldIsString(final Object mappedField) {
		return mappedField instanceof String;
	}
	
	/**
	 * Verifica se o campo é do tipo {@link ArrayNode}
	 * 
	 * @param mappedField Campo mapeado
	 * @return Retorna {@code TRUE} caso o campo seja do tipo {@link ArrayNode}
	 */
	private boolean fieldsIsArrayNode(final Object mappedField) {
		return mappedField instanceof ArrayNode;
	}

	@Autowired
	public void setMappers(List<ClassMapper> mappers){
		this.mappers = new HashMap<>();
		mappers.forEach((mapper) -> this.mappers.put(mapper.getMappedClass(), mapper) );
	}

}
