package com.duelmasters.DuelMastersServer.Domain.Entity.cards;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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

	@Field("type")
	private String type;

	private String ability;

	@Field("rarity")
	private String rarity;

	private String imageId;

	public Card(String name, String cardRealm, String cardClass, Integer mana, Integer power, Integer breakerNumber,
			String type, String ability, String rarity, String imageId) {
		super();
		this.name = name;
		this.cardRealm = cardRealm;
		this.cardClass = cardClass;
		this.mana = mana;
		this.power = power;
		this.breakerNumber = breakerNumber;
		this.type = type;
		this.ability = ability;
		this.rarity = rarity;
		this.imageId = imageId;
	}

	@Override
	public String toString() {
		return "Card [name=" + name + ", rarity=" + rarity + "]\n";
	}

//	@Override
//	public String toString() {
//		return "Card: [name = " + name + "]\n";
//	}

//	@Override
//	public String toString() {
//		return "Card [name = " + name + "\ncardRealm = " + cardRealm + "\ncardClass = " + cardClass + "\nmana = " + mana
//				+ "\npower = " + power + "\nbreakerNumber = " + breakerNumber + "\ntype = " + type + "\nability = " + ability
//				+ "\nrarity = " + rarity + "]\n";
//	}
	
	
	
	
	
	

}
