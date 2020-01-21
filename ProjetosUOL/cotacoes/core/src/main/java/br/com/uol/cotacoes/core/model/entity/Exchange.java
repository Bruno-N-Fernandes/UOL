package br.com.uol.cotacoes.core.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

/**
 * Created by vrx_mtoledo on 07/06/17.
 */
@Entity(name = "exchange")
public class Exchange {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idt_exchange")
    private Integer id;

    @Getter
    @Column(name = "nam_exchange")
    private String name;

    @Getter
    @Column(name = "abv_exchange")
    private String abbreviation;

}
