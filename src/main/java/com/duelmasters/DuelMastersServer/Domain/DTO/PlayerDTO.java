package com.duelmasters.DuelMastersServer.Domain.DTO;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PlayerDTO {

	private ArrayList<GameCardDTO> deck;
	private ArrayList<GameCardDTO> hand;
	private ArrayList<GameCardDTO> shields;
	
	private ArrayList<GameCardDTO> battleZone;
	private ArrayList<GameCardDTO> manaZone;
	private ArrayList<GameCardDTO> graveyard;
	
	public PlayerDTO() {};
	
}
