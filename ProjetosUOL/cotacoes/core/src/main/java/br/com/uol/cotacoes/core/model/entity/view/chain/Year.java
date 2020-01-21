package br.com.uol.cotacoes.core.model.entity.view.chain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Year implements ChainListService {

	@Override
	public String getService(LocalDate dateToVerify) {
		if(dateToVerify != null && LocalDate.now().minusYears(1).compareTo(dateToVerify) <= 0)
			return INTERDAY_YEAR;
		return CONTINUE_CHAIN_WITHOUT_ADD;
	}

	@Override
	public ChainListService next() {
		return null;
	}

}
