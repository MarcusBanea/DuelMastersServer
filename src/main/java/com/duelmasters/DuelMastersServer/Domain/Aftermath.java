package com.duelmasters.DuelMastersServer.Domain;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Aftermath {
	
	private String card1State;
	
	private String card2State;
	
	private ArrayList<String> triggeredAbilities;
	
	private String extras;
	
	public Aftermath() {
		this.card1State = "";
		this.card2State = "";
		this.triggeredAbilities = new ArrayList<>();
		this.extras = "";
	}
	
	public void addAbility(String ability) { 
		this.triggeredAbilities.add(ability);
	}
	
}
