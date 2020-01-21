package br.com.uol.cotacoes.webrest.mappers.exchangeasset;

import br.com.uol.cotacoes.core.model.entity.InflationAsset;
import org.springframework.stereotype.Component;

@Component
public class InflationAssetMapper extends ExchangeAssetMapper<InflationAsset> {
	
	@Override
	public Class<InflationAsset> getMappedClass() {
		return InflationAsset.class;
	}

}



