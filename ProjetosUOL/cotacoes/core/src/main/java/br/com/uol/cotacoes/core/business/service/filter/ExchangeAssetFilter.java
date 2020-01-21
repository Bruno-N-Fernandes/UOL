package br.com.uol.cotacoes.core.business.service.filter;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.uol.cotacoes.core.business.service.ExchangeAssetService;
import br.com.uol.cotacoes.core.model.entity.ExchangeAsset;
import br.com.uol.cotacoes.core.model.entity.view.ViewExchangeAsset;
import br.com.uol.cotacoes.core.model.repository.ExchangeAssetRepository;

/**
 * Filtro das ações que a aplicação pode carregar com a consulta por letras
 * 
 * @author mzp_dferraz
 *
 */
@Component
public class ExchangeAssetFilter {
		
	@Autowired
	private ExchangeAssetService service;
	
	@Autowired
    private Logger logger;
	
	private HashMap<String, Set<ExchangeAssetIndex>> assets;
		
	private List<ExchangeAsset> emptyAssetList = new ArrayList<>();
	
	/**
	 * Carrega todas as ações em memória e indexa elas pelas letras que são disponibilizadas
	 * pelo objeto de indice.
	 * O indice é composto de um mapa, onde a chave é a letra e o valor é a lista de itens
	 * que possuem essa letra
	 */
	@PostConstruct
	public void init(){
		loadAssetIndex();
	}

	/**
	 * Carrega as ações em memória.<br>
	 * <br>
	 * 
	 */
	public void loadAssetIndex() {
		HashMap<String, Set<ExchangeAssetIndex>> assets = new HashMap<>();
		long start = System.currentTimeMillis();
						
		loadValidAssets(assets, ExchangeAssetRepository.ASSET_TYPE_ASSET);
		
		loadValidAssets(assets, ExchangeAssetRepository.ASSET_TYPE_INDEX);
		
		this.assets = assets;
		
		long end = System.currentTimeMillis();	
		logger.info("Time to load assets in memory {}", (end-start));
	}

	/**
	 * Carrega e mapeia as inforamçoes de um tipo de asset<br>
	 * <br>
	 * O carregamento foi separado em listagem de todas os exchange assets<br>
	 * Listagem dos IDs de exchange asset no interday com ultima data de atualização<br>
	 * Listagem dos IDs de exchange asset no interday com ultima data de atualização <br>
	 * <br>
	 * Foi feita a separação em três processos de consulta ao BD para melhorar a performance do carregamento das ações em memória.<br>
	 * <br>
	 * Tambem gera a lista de serviços disponiveis para os assets que serão consultados<br>
	 * <br>
	 * @param assets
	 * @param assetType
	 */
	private void loadValidAssets(HashMap<String, Set<ExchangeAssetIndex>> assets, String assetType) {
		
		List<? extends ExchangeAsset> assetsList = service.listAssetsBy(assetType);
		
		List<ViewExchangeAsset> assetsIntraday = service.listAssetsByIntraday(assetType);
		
		populateViewList(assetsList, assetsIntraday);
		
		for (ViewExchangeAsset viewAsset : assetsIntraday) {			
			mapAssetOnIndex(assets, viewAsset);
			viewAsset.generateServicesList();
		}
		
		List<ViewExchangeAsset> assetsInterday = service.listAssetsByInterday(assetType);
		
		populateViewList(assetsList, assetsInterday);
		
		for (ViewExchangeAsset viewAsset : assetsInterday) {			
			if(LocalDate.now().minusYears(1).compareTo(viewAsset.getDate()) <= 0 && !assetsIntraday.contains(viewAsset)){
				mapAssetOnIndex(assets, viewAsset);				
			}			
			viewAsset.generateServicesList();
		}
	}

	/**
	 * Popula a lista de view com os valores dos exchange assets que ela possui 
	 * 
	 * @param assetsList
	 * @param assetsView
	 */
	private void populateViewList(List<? extends ExchangeAsset> assetsList, List<ViewExchangeAsset> assetsView) {
		for (ViewExchangeAsset viewExchangeAsset : assetsView) {
			viewExchangeAsset.setExchangeAssetFromList(assetsList);
		}
	}

	/**
	 * Realiza o mapeamento de um Exchange asset a partir de um item de view
	 * 
	 * @param assets
	 * @param viewAsset
	 */
	private void mapAssetOnIndex(HashMap<String, Set<ExchangeAssetIndex>> assets, ViewExchangeAsset viewAsset) {
		ExchangeAsset asset = viewAsset.getExchangeAsset();
		ExchangeAssetIndex exchangeAssetIndex = new ExchangeAssetIndex(asset);
		HashSet<String> lettersSet = new HashSet<>(Arrays.asList(exchangeAssetIndex.getAbbreviationSplit()));
		lettersSet.addAll(Arrays.asList(exchangeAssetIndex.getNameSplit()));
		
		for (String keyLetter : lettersSet) {
			if(assets.get(keyLetter) == null){
				assets.put(keyLetter, new HashSet<ExchangeAssetIndex>());
			}
			assets.get(keyLetter).add(exchangeAssetIndex);				
		}
	}
	
	/**
	 * Procura os itens na lista pelas letras da consulta.
	 * 
	 * O algoritimo de busca funciona assim:
	 * 1 - Separa as letras que serão consultadas
	 * 2 - Verifica letra por letra utilizando o indice, quais registros estão possuem a letra
	 * 3 - Da segunda letra em diante, após pegar os itens ele verifica se os itens da lista localizada já estão na lista que foi gerada anteriormente
	 * 4 - Ele mantem na lista dos consultados, apenas aqueles que itens que já estavam na lista e remove os itens que não estão na nova lista consultada
	 * 5 - Após carregar todos os itens, ele verifica se o código ou o nome dos itens consultados possuem as letras que foram consultadas na ordem exata
	 * 6 - Ele ordena os itens localizados por id e retorna os itens.
	 * 
	 * @param letters Letras que serão consultadas
	 * @return Lista dos itens que possuem a palavra consultada
	 */
	public List<ExchangeAsset> findBy(String letters, Integer size){
		
		long start = System.currentTimeMillis();
		String normalizedLetters = Normalizer.normalize(letters.toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		String[] keyLetters = normalizedLetters.split("");
		
		List<ExchangeAssetIndex> filtredItens = null;
		
		for (String keyLetter : keyLetters) {			
			Set<ExchangeAssetIndex> letterItens = assets.get(keyLetter);
			if(letterItens != null){
				if(filtredItens == null){
					filtredItens = new ArrayList<>(letterItens);
				} else {
					filtredItens = filtredItens.stream().filter(item -> letterItens.contains(item)).collect(Collectors.toList());
				}
			} else {
				long end = System.currentTimeMillis();		
				logger.info("Time to not found asset {}", (end-start));
				return emptyAssetList;
			}
		}
				
		List<ExchangeAsset> filteredAssetList = filtredItens.stream()
				.filter(item -> item.getAbbreviationIndex().contains(normalizedLetters) || item.getNameIndex().contains(normalizedLetters))
				.map(item -> item.getAsset()).sorted((ExchangeAsset asset1, ExchangeAsset asset2) -> asset1.getAbbreviation().compareTo(asset2.getAbbreviation())).collect(Collectors.toList());
				
		long end = System.currentTimeMillis();		
		logger.info("Assets founded in {}", (end-start));
		
		return filteredAssetList.subList(0, size > filteredAssetList.size() ? filteredAssetList.size() : size);
	}

}
