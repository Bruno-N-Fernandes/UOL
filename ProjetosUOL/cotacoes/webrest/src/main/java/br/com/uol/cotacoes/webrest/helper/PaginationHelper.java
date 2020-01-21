package br.com.uol.cotacoes.webrest.helper;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uol.cotacoes.core.model.entity.DomainEntity;

/**
 * Classe que auxilia a paginar listas de objetos da classe CurrencyRateIntraday
 *
 * Created by vrx_mtoledo on 15/05/17.
 */
@Service
public class PaginationHelper {

    /**
     * cria o ponteiro NEXT da pagina atual a partir do ID do ultimo elemento da pagina
     *
     * @param intradayList
     * @return Ponteiro next da pagina atual
     */
    public <E> String createNext(final List<E> intradayList){
        String nextPointer = ((DomainEntity) intradayList.get(intradayList.size()-1)).getId().toString();
        return nextPointer + "N";
    }

    /**
     * cria o ponteiro PREV da pagina atual a partir do ID do primeiro elemento da pagina
     *
     * @param intradayList
     * @return Ponteiro next da pagina atual
     */
    public <E> String createPrev(final List<E> intradayList){
        String prevPointer = ((DomainEntity) intradayList.get(0)).getId().toString();
        return prevPointer + "P";
    }

}
