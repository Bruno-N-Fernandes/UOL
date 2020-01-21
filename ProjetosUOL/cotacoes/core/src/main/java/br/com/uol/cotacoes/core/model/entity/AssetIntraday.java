package br.com.uol.cotacoes.core.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by vrx_mtoledo on 07/06/17.
 */
@Entity(name = "asset_intraday")
public class AssetIntraday extends AssetHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_asset_intraday", precision = 15)
	@Getter
	private Integer id;

	@Column(name = "dat_last_update")
	@Getter
	private LocalDateTime date;

}
