package br.com.uol.cotacoes.core.model.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by vrx_mtoledo on 26/04/17.
 */
@Entity(name = "currency_rate_interday")
public class CurrencyRateInterday extends CurrencyRateHistory {

    @Column(name = "dat_currency_rate")
    @Getter
    @Setter
    private LocalDate date;


    
    
}
