package br.com.uol.cotacoes.webrest.mappers;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.uol.cotacoes.core.model.entity.AssetIntraday;

/**
 * 
 * Campos id,price,exchangeasset,high,low,open,volume,close,bid,ask,change,pctChange,date
 * Created by vrx_mtoledo on 09/06/17.
 */
@Component
public class AssetIntradayMapper extends AssetHistoryMapper implements ClassMapper<AssetIntraday> {

    @Override
    public Class<AssetIntraday> getMappedClass() {
        return AssetIntraday.class;
    }

    @Override
    public Map<String, Object> generateMapFromEntity(AssetIntraday assetIntraday) {
        Map<String, Object> map = super.generateMapFromEntity(assetIntraday);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String date = assetIntraday.getDate().format(dateFormat);
        map.put("date", date);

        return map;
    }

}
