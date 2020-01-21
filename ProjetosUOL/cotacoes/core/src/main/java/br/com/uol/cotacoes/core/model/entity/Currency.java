package br.com.uol.cotacoes.core.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrx_mtoledo on 18/04/17.
 */
@Entity(name = "currency")
@EqualsAndHashCode(of={"id"})
public class Currency implements DomainEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "idt_currency", precision = 9)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "nam_currency")
    @Getter
    @Setter
    private String name;

    @Column(name = "cod_investor")
    @Getter
    @Setter
    private String codInvestor;

    @Column(name = "cod_unit")
    @Getter
    @Setter
    private String codUnit;

    @Column(name = "cod_conversion")
    @Getter
    @Setter
    private String codConversion;

    @Column(name = "ind_currency_converted")
    @Getter
    @Setter
    private Boolean converted;
    
    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "currencies")
    private List<Country> countries;
    
    /**
     * Adiciona um {@link Country} para a lista de countries da entidade
     * 
     * @param country
     */
    public void addCountry(Country country){
    	if(countries == null){
    		countries = new ArrayList<>();
    	}
    	countries.add(country);
    }

}
