package br.com.uol.cotacoes.core.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Getter;

/**
 * Classe abstrata que representa todos os tipos de assets existentes: ações, índices, indicadores, inflação e commodities
 *
 * Created by vrx_mtoledo on 07/06/17.
 */
@Entity(name = "exchange_asset")

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="ind_asset_type", discriminatorType=DiscriminatorType.STRING )
public abstract class ExchangeAsset implements DomainEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idt_exchange_asset")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idt_exchange")
    @Getter
    private Exchange exchange;

    @Getter
    @Column(name = "nam_exchange_asset")
    private String name;

    @Getter
    @Column(name = "abv_exchange_asset")
    private String abbreviation;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "exchanges")
    private Set<Company> companies;

    @Getter
    @Column(name = "ind_asset_type", insertable = false, updatable = false)
    private String type;
    
    @Getter    
    @Transient
    public List<String> servicesList;
    
    public void addServices(List<String> services){
    	if(this.servicesList == null) this.servicesList = new ArrayList<>();
    	for (String service : services) 
    		if(!this.servicesList.contains(service))
    			this.servicesList.add(service);	
    	
    }
    
    
    

}
