package br.com.uol.cotacoes.core.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "country")
@EqualsAndHashCode(of = {"id"})
public class Country implements DomainEntity {

	@Getter
	@Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cod_iso_country")
    private Integer id;

    @Getter
    @Setter
    @Column(name = "nam_Country")
    private String name;

    @Getter
    @Column(name = "nam_country_english")
    private String englishName;

    @Getter
    @Column(name = "abv2_iso_country")
    private String abrevIsoCountry2;

    @Getter
    @Column(name = "abv3_iso_country")
    private String abrevIsoCountry3;

    @Getter
    @Column(name = "flg_postal_base")
    private Integer flagPostalBase;

    @Getter
    @Column(name = "num_ddI_country")
    private Integer DDICountry;

    @Getter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "currency_country", joinColumns = @JoinColumn(name = "cod_iso_country"), inverseJoinColumns = @JoinColumn(name = "idt_currency"))
    private Set<Currency> currencies;

    
    
	
}
