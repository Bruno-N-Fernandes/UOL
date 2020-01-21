package br.com.uol.cotacoes.webrest.mappers.exchangeasset;

import br.com.uol.cotacoes.core.model.entity.IndexAsset;
import org.springframework.stereotype.Component;

@Component
public class IndexAssetMapper extends ExchangeAssetMapper<IndexAsset> {
	
	@Override
	public Class<IndexAsset> getMappedClass() {
		return IndexAsset.class;
	}

}



