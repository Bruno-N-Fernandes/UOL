package br.com.uol.cotacoes.core.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Classe de Índices
 *
 * Created by vrx_mtoledo on 07/06/17.
 */
@Entity(name = "index_asset")
@DiscriminatorValue(value="I")
public class IndexAsset extends ExchangeAsset{
}
