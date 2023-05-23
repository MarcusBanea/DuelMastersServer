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

	private String realm;

	private String cardClass;

	private String mana;

	private String power;

	private String rarity;
	
	private String[] ability;
	
	private String[] attackProperty;
	
	private String[] blockProperty;
	
	private String[] type;
	
	private String[] placement;
	
	private String[] endOfTurn;
	
	private String[] addedPower;

	private String imageId;

	public Card(String name, String realm, String cardClass, String mana, String power, String rarity, String[] ability,
			String[] attackProperty, String[] blockProperty, String[] type, String[] placement, String[] endOfTurn,
			String[] addedPower) {
		super();
		this.name = name;
		this.realm = realm;
		this.cardClass = cardClass;
		this.mana = mana;
		this.power = power;
		this.rarity = rarity;
		this.ability = ability;
		this.attackProperty = attackProperty;
		this.blockProperty = blockProperty;
		this.type = type;
		this.placement = placement;
		this.endOfTurn = endOfTurn;
		this.addedPower = addedPower;
	}
	
	public Card() {
		
	};
}
