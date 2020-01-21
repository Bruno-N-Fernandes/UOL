package br.com.uol.cotacoes.core.model.entity.view.chain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Months implements ChainListService {
	
	ChainListService chain = new Year();


	@Override
	public String getService(LocalDate dateToVerify) {
		if(dateToVerify != null && LocalDate.now().minusMonths(3).compareTo(dateToVerify) <= 0)
			return INTERDAY_MONTHS;
		return CONTINUE_CHAIN_WITHOUT_ADD;
	}

	@Override
	public ChainListService next() {
		return chain;
	}

}
