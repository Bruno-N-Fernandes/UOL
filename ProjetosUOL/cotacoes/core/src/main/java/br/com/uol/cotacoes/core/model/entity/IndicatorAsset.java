package br.com.uol.cotacoes.core.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Classe de Indicadores
 *
 * Created by vrx_mtoledo on 07/06/17.
 */
@Entity(name = "indicators_asset")
@DiscriminatorValue(value="N")
public class IndicatorAsset extends ExchangeAsset {
}
