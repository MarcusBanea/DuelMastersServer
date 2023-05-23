package com.duelmasters.DuelMastersServer.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameCard {

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
	
	private byte[] image;
	
	public GameCard() {
		super();
	}
	
}
