package br.com.uol.cotacoes.webrest.mappers.exchangeasset;

import br.com.uol.cotacoes.core.model.entity.CommodityAsset;
import org.springframework.stereotype.Component;

@Component
public class CommodityMapper extends ExchangeAssetMapper<CommodityAsset> {
	
	@Override
	public Class<CommodityAsset> getMappedClass() {
		return CommodityAsset.class;
	}

}