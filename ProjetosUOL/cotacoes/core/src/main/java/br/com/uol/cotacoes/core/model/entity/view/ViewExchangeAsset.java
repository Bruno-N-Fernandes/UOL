package br.com.uol.cotacoes.core.model.entity.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.uol.cotacoes.core.model.entity.ExchangeAsset;
import br.com.uol.cotacoes.core.model.entity.view.chain.ChainListService;
import br.com.uol.cotacoes.core.model.entity.view.chain.Intraday;
import br.com.uol.cotacoes.core.model.entity.view.chain.Week;
import lombok.Getter;

public class ViewExchangeAsset {
	
	@Getter
	private ExchangeAsset exchangeAsset;
	
	@Getter
	private LocalDateTime dateTime;
	
	@Getter
	private LocalDate date;
	
	@Getter
	private Integer id;
	
	ChainListService serviceChain = new Intraday();

	public ViewExchangeAsset(Integer id, LocalDateTime dateTime) {
		super();
		this.dateTime = dateTime;
		this.id = id;
	}
	
	public ViewExchangeAsset(Integer id, LocalDate date) {
		super();
		this.date = date;
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		ViewExchangeAsset view = (ViewExchangeAsset) obj;
		return view.getExchangeAsset().getId().equals(this.exchangeAsset.getId());		
		
	}
	
	public void setExchangeAssetFromList(List<? extends ExchangeAsset> assetsList){
		for (ExchangeAsset exchangeAsset : assetsList) {
			if(exchangeAsset.getId().equals(this.id)){
				this.exchangeAsset = exchangeAsset;			
				break;
			}
		}
	}
	
	public void generateServicesList(){	
		this.exchangeAsset.addServices(serviceChain.start(this.date));				
	}
	
}
