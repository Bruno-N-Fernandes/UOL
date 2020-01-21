package br.com.uol.cotacoes.webrest.mappers;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.uol.cotacoes.core.model.entity.AssetInterday;

/**
 * Created by vrx_mtoledo on 09/06/17.
 */
@Component
public class AssetInterdayMapper extends AssetHistoryMapper implements ClassMapper<AssetInterday> {

    @Override
    public Class<AssetInterday> getMappedClass() {
        return AssetInterday.class;
    }

    @Override
    public Map<String, Object> generateMapFromEntity(AssetInterday assetInterday) {

        Map<String, Object> map = super.generateMapFromEntity(assetInterday);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd000000");
        String date = assetInterday.getDate().format(dateFormat);
        map.put("date", date);

        return map;
    }

}
