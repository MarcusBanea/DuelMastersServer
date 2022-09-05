package com.duelmasters.DuelMastersServer.Domain.Entity.cards;

import java.util.List;

import lombok.Data;

@Data
public class Deck {
	
	private String name;
	
	private List<Card> cards;
	
	
}
