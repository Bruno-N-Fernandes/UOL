package br.com.uol.cotacoes.core.business.service.filter;

import br.com.uol.cotacoes.core.model.entity.ExchangeAsset;
import lombok.Getter;

/**
 * Classe que representa o indice das ações.
 * Ele possui o item da ação, e o nome e código da ação separados e em lower case para busca das consultas
 * 
 * @author mzp_dferraz
 *
 */
public class ExchangeAssetIndex {
	
	@Getter
	private ExchangeAsset asset;
	@Getter
	private String abbreviationIndex;
	@Getter
	private String nameIndex;
	@Getter
	private String abbreviationSplit[];
	@Getter
	private String nameSplit[];
		
	public ExchangeAssetIndex(ExchangeAsset asset) {
		super();
		this.asset = asset;
		
		this.abbreviationIndex = asset.getAbbreviation().toLowerCase();
		this.abbreviationSplit = this.abbreviationIndex.split("");
		
		if(!asset.getCompanies().isEmpty()){
			this.nameIndex = asset.getCompanies().iterator().next().getName().toLowerCase();
		} else {
			this.nameIndex = asset.getName().toLowerCase();
		}		
		this.nameSplit = this.nameIndex.split("");		
		
	}
	
	

}
