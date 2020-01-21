package br.com.uol.cotacoes.webrest.mappers.exchangeasset;

import br.com.uol.cotacoes.core.model.entity.IndicatorAsset;
import org.springframework.stereotype.Component;

@Component
public class IndicatorAssetMapper extends ExchangeAssetMapper<IndicatorAsset> {
	
	@Override
	public Class<IndicatorAsset> getMappedClass() {
		return IndicatorAsset.class;
	}

}



