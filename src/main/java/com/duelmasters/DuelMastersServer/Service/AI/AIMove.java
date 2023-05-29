package com.duelmasters.DuelMastersServer.Service.AI;

import java.util.ArrayList;

import lombok.Data;

@Data
public class AIMove {

	private ArrayList<String> cards;
	
	private String action;
	
	public AIMove() {
		this.cards = new ArrayList<>();
	}
	
	public void addCard(String card) {
		cards.add(card);
	}
	
}
