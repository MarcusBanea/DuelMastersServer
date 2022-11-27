package com.duelmasters.DuelMastersServer.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameCard {

	private String name;

	private String cardRealm;

	private String cardClass;

	private Integer mana;

	private Integer power;

	private Integer breakerNumber;

	private String type;

	private String ability;

	private byte[] image;
	
	public GameCard() {
		super();
	}
	
}
