package br.com.uol.cotacoes.core.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Classe de Ações
 *
 * Created by vrx_mtoledo on 07/06/17.
 */
@Entity
@DiscriminatorValue(value="S")
public class Asset extends ExchangeAsset {
}
