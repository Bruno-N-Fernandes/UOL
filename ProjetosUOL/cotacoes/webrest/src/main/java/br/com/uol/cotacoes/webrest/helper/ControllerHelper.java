package br.com.uol.cotacoes.webrest.helper;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Hepler para as funções comuns nos controllers da aplicação 
 * 
 * @author mzp_dferraz
 *
 */
@Component
public class ControllerHelper {
	
	
	/**
	 * Cria o ponteiro conforme a necessidade da busca
	 * 
	 * @param pointerKey Chave do ponteiro a ser validado
	 * @return Retorna a chave passada como parametro sem o ultimo caracter
	 */
	public Integer createPointer(String pointerKey) {
		return Integer.valueOf(pointerKey.substring(0, pointerKey.length() - 1));
	}
	
	/**
	 * Verifica se deve ser retornada a primeira página com todos os registro mais atualizados.
	 * 
	 * @param size
	 * @param currenciesOfToday
	 * @return
	 */
	public <E> boolean hasToReturnoFirstPage(final int size, List<E> currenciesOfToday) {
		return currenciesOfToday.size() < size;
	}

}
