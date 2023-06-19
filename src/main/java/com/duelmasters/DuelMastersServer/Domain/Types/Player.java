package com.duelmasters.DuelMastersServer.Domain.Types;

import java.util.ArrayList;

import com.duelmasters.DuelMastersServer.Domain.DTO.GameCard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
	
	private ArrayList<GameCard> deck;
	private ArrayList<GameCard> hand;
	private ArrayList<GameCard> shields;
	
	private ArrayList<GameCard> battleZone;
	private ArrayList<GameCard> manaZone;
	private ArrayList<GameCard> graveyard;
	
	public Player() {};
	
}
