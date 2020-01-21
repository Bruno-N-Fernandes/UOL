package br.com.uol.cotacoes.core.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Classe de Commodities
 *
 * Created by vrx_mtoledo on 07/06/17.
 */
@Entity(name = "commodities_asset")
@DiscriminatorValue(value="C")
public class CommodityAsset extends ExchangeAsset{
}
