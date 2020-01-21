package br.com.uol.cotacoes.webrest.mappers.exchangeasset;

import br.com.uol.cotacoes.core.model.entity.Asset;
import org.springframework.stereotype.Component;

@Component
public class AssetMapper extends ExchangeAssetMapper<Asset> {
	
	@Override
	public Class<Asset> getMappedClass() {
		return Asset.class;
	}

}



