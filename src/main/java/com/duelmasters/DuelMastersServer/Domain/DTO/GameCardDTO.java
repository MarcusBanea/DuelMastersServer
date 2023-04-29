package com.duelmasters.DuelMastersServer.Domain.DTO;

import com.duelmasters.DuelMastersServer.Domain.Ability;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameCardDTO {

	private String name;

	private String cardRealm;

	private String cardClass;

	private Integer mana;

	private Integer power;

	private Integer breakerNumber;

	private String type;

	private Ability[] ability;
	
	//game attributes
	
	private boolean tapped;
	
	public GameCardDTO() {
		super();
	}
	
}
