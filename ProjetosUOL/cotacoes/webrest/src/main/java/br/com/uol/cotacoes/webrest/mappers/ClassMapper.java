/**
 * 
 */
package br.com.uol.cotacoes.webrest.mappers;

import java.util.Map;

import br.com.uol.cotacoes.webrest.helper.JSONHelper;

/**
 * 
 * Interface que indica o contrato para um entidade ser convertida no helper {@link JSONHelper}
 * @author mzp_dferraz
 *
 */
public interface ClassMapper<E> {

	/**
	 * Classe da entidade mapeada
	 * @return Classe que foi mapeada
	 */
	Class<E> getMappedClass();
	
	/**
	 * Converte o objeto recebido em um mapa com seus atributos que podem ser exportados
	 * 
	 * @param entity Objeto que será convertido
	 * @return Mapa do objeto convertido
	 */
	Map<String, Object> generateMapFromEntity(E entity);
	
}
