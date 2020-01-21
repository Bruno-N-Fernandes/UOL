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

import lombok.Getter;

/**
 * Created by vrx_mtoledo on 07/06/17.
 */
@Entity(name = "company")
public class Company implements DomainEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idt_company")
    private Integer id;

    @Getter
    @Column(name = "nam_company")
    private String name;

    @Getter
    @Column(name = "nam_company_resume")
    private String nameResume;

    @Getter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "stock_asset", joinColumns = @JoinColumn(name = "idt_company"), inverseJoinColumns = @JoinColumn(name = "idt_exchange_asset"))
    private Set<ExchangeAsset> exchanges;

}
