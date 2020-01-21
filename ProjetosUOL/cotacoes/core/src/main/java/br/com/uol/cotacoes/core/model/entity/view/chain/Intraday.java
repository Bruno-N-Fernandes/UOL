package br.com.uol.cotacoes.core.model.entity.view.chain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intraday implements ChainListService {

	@Override
	public String getService(LocalDate dateToVerify) {
		if(dateToVerify == null)
			return INTRADAY;
		return CONTINUE_CHAIN_WITHOUT_ADD;
	}

	@Override
	public ChainListService next() {
		return new Week();
	}

}
