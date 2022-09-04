package com.duelmasters.DuelMastersServer.Domain.Entity.cards;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Card {

	@Id
	private String id;

	@Indexed(unique = true)
	private String name;

	private String cardRealm;

	private String cardClass;

	private Integer mana;

	private Integer power;

	private Integer breakerNumber;

	private Boolean isEvolution;

	private String ability;

	private byte[] image;

	public Card(String name, String cardRealm, String cardClass, Integer mana, Integer power, Integer breakerNumber,
			Boolean isEvolution, String ability, byte[] image) {
		super();
		this.name = name;
		this.cardRealm = cardRealm;
		this.cardClass = cardClass;
		this.mana = mana;
		this.power = power;
		this.breakerNumber = breakerNumber;
		this.isEvolution = isEvolution;
		this.ability = ability;
		this.image = image;
	}

}
