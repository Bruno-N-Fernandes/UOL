package br.com.uol.cotacoes.core.model.entity.view.chain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Month implements ChainListService {

	ChainListService chain = new Months();

	@Override
	public String getService(LocalDate dateToVerify) {
		if(dateToVerify != null && LocalDate.now().minusMonths(1).compareTo(dateToVerify) <= 0)
			return INTERDAY_MONTH;
		return CONTINUE_CHAIN_WITHOUT_ADD;
	}

	@Override
	public ChainListService next() {
		return chain;
	}

}

