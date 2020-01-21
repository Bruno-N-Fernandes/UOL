package br.com.uol.cotacoes.webrest.mappers;

import br.com.uol.cotacoes.core.model.entity.AssetHistory;

import java.util.HashMap;
import java.util.Map;

/**
 * Campos id,price,exchangeasset,abbreviation,high,low,open,volume,close,bid,ask,change,pctChange,date,relativepercent
 * Created by vrx_mtoledo on 09/06/17.
 */
public class AssetHistoryMapper {

    public Map<String, Object> generateMapFromEntity(final AssetHistory assetHistory) {

        Map<String,Object> map = new HashMap<>();

        map.put("id", assetHistory.getId());
        map.put("price", assetHistory.getPrice());
        map.put("exchangeasset", assetHistory.getExchangeAsset().getName());
        map.put("abbreviation", assetHistory.getExchangeAsset().getAbbreviation());
        map.put("high", assetHistory.getHigh());
        map.put("low", assetHistory.getLow());
        map.put("open", assetHistory.getOpen());
        map.put("volume", assetHistory.getVolume());
        map.put("close", assetHistory.getClose());
        map.put("bid", assetHistory.getBid());
        map.put("ask", assetHistory.getAsk());
        map.put("change", assetHistory.getChange());
        map.put("pctChange", assetHistory.getPctChange());
        map.put("relativepercent", assetHistory.getRelativePercentual());

        return map;
    }

}
