package br.com.uol.cotacoes.core.model.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by vrx_mtoledo on 26/04/17.
 */
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
public abstract class CurrencyRateHistory implements DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idt_currency_rate", precision = 15)
    @Getter
    @Setter
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idt_currency")
    @Getter
    @Setter
    private Currency currency;

    /**
     * Valor do lance (Compra)
     */
    @Column(name = "num_value_bid", precision=10, scale=4)
    @Getter
    @Setter
    private BigDecimal bidValue;

    /**
     * Valor pedido (Venda)
     */
    @Column(name = "num_value_ask", precision=10, scale=4)
    @Getter
    @Setter
    private BigDecimal askValue;

    /**
     * Oferta máxima
     */
    @Column(name = "num_max_bid", precision=10, scale=4)
    @Getter
    @Setter
    private BigDecimal maxBid;

    /**
     * Oferta mínima
     */
    @Column(name = "num_min_bid", precision=10, scale=4)
    @Getter
    @Setter
    private BigDecimal minBid;

    /**
     * Variação do valor de oferta
     */
    @Column(name = "num_var_bid", precision=10, scale=4)
    @Getter
    @Setter
    private BigDecimal variationBid;

    /**
     * Variação percentual do valor de oferta
     */
    @Column(name = "num_varpct_bid", precision=7, scale=3)
    @Getter
    @Setter
    private BigDecimal variationPercentBid;

    /**
     * Valor do lance de abertura
     */
    @Column(name = "num_open_bid", precision=8, scale=4)
    @Getter
    @Setter
    private BigDecimal openBidValue;
    
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
		
		if(BigDecimal.ZERO.compareTo(getAskValue()) >= 0){
			this.relativePercentual = BigDecimal.ZERO;
			return;
		}
		
		if(BigDecimal.ZERO.compareTo(referenceCloseValue) >= 0){
			this.relativePercentual = BigDecimal.ZERO;
			return;
		}

		BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
    	
    	this.relativePercentual = ONE_HUNDRED.multiply(getAskValue().subtract(referenceCloseValue)).divide(referenceCloseValue, RoundingMode.HALF_UP);
		
	}


}
