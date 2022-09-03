package com.duelmasters.DuelMastersServer.Domain.Entity.cards;

import lombok.Data;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Indexed(unique = true)
    private String name;

    private String cardRealm;

    private String cardClass;

    private Integer mana;

    private Integer power;

    private Integer breakerNumber;

    private Boolean isEvolution;

    private String description;

    public Card(String name, String cardRealm, String cardClass, Integer mana, Integer power, Integer breakerNumber, Boolean isEvolution, String description) {
        this.name = name;
        this.cardRealm = cardRealm;
        this.cardClass = cardClass;
        this.mana = mana;
        this.power = power;
        this.breakerNumber = breakerNumber;
        this.isEvolution = isEvolution;
        this.description = description;
    }
}
