package br.com.uol.cotacoes.core.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by vrx_mtoledo on 26/04/17.
 */
@Entity(name = "currency_rate_intraday")
public class CurrencyRateIntraday extends CurrencyRateHistory {

    @Column(name = "dat_currency_rate")
    @Getter
    @Setter
    private LocalDateTime date;

}
