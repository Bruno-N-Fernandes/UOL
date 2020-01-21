package br.com.uol.cotacoes.core.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Classe de Inflação
 *
 * Created by vrx_mtoledo on 07/06/17.
 */
@Entity(name = "inflation_asset")
@DiscriminatorValue(value="F")
public class InflationAsset extends ExchangeAsset {
}
