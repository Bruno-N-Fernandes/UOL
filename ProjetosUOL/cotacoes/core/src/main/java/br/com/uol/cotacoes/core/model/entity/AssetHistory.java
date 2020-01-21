package br.com.uol.cotacoes.core.model.entity;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by vrx_mtoledo on 07/06/17.
 */
@MappedSuperclass
public abstract class AssetHistory  implements DomainEntity {

    @Column(name = "num_price", precision = 10, scale = 3)
    @Getter
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idt_exchange_asset")
    @Getter
    private ExchangeAsset exchangeAsset;

    @Column(name = "num_high", precision = 10, scale = 3)
    @Getter
    private BigDecimal high;

    @Column(name = "num_low", precision = 10, scale = 3)
    @Getter
    private BigDecimal low;

    @Column(name = "num_open", precision = 10, scale = 3)
    @Getter
    private BigDecimal open;

    @Column(name = "num_volume", precision = 14, scale = 3)
    @Getter
    private BigDecimal volume;

    @Column(name = "num_close", precision = 10, scale = 3)
    @Getter
    private BigDecimal close;

    @Column(name = "num_bid", precision = 10, scale = 3)
    @Getter
    private BigDecimal bid;

    @Column(name = "num_ask", precision = 10, scale = 3)
    @Getter
    private BigDecimal ask;

    @Column(name = "num_change", precision = 10, scale = 3)
    @Getter
    private BigDecimal change;

    @Column(name = "num_pct_change", precision = 5, scale = 2)
    @Getter
    private BigDecimal pctChange;
    
    /**
     * Percentual relativo da lista solicitada da entidade
     */
    @Getter
    @Transient
    protected BigDecimal relativePercentual;
    
	/**
	 * Carrega o percentual relativo da ação no tipo de listagem especifico em que ela está sendo realizada
	 * 
	 * Formula do calculo utilizado para chegar ao valor percentual<br>
	 * <br>
	 * Var% = 100 * (ULT - F) / F;<br>
	 * <br>
	 * Onde: ULT = Última cotação <br>
	 * F = Fechamento do período (dia, hora etc.) anterior. <br>
	 * Os dados foram tirados de acordo com: http://www.mundotrade.com.br/node/91087<br>
	 * Aguardando aprovação dos editores para este calculo, ou uma outra fórmula escolhida.<br>
	 * 
	 * @param lastCloseValue
	 */
	public void updateRelativePecentual(BigDecimal referenceCloseValue) {
		
		if(BigDecimal.ZERO.compareTo(getPrice()) >= 0){
			this.relativePercentual = BigDecimal.ZERO;
			return;
		}
		
		if(BigDecimal.ZERO.compareTo(referenceCloseValue) >= 0){
			this.relativePercentual = BigDecimal.ZERO;
			return;
		}
		
		BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

		this.relativePercentual = ONE_HUNDRED.multiply(getPrice().subtract(referenceCloseValue))
				.divide(referenceCloseValue, RoundingMode.HALF_UP);

	}
}
