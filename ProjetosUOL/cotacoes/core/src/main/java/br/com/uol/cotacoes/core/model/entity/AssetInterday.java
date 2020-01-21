package br.com.uol.cotacoes.core.model.entity;

import lombok.Getter;

import javax.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * Created by vrx_mtoledo on 07/06/17.
 */
@Entity(name = "asset_interday")
public class AssetInterday extends AssetHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_asset_interday", precision = 9)
	@Getter
	private Integer id;

	@Column(name = "dat_interday")
	@Getter
	private LocalDate date;

}
