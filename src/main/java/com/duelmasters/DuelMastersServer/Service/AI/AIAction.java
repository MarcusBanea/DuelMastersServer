package com.duelmasters.DuelMastersServer.Service.AI;

import java.util.Random;

public enum AIAction {
	NO_ACTION,
	DRAW_CARD,
	MOVE_CARD_TO_MANA,
	MOVE_CARD_TO_BATTLEZONE,
	SELECT_CARD_FOR_ATTACK;
	
	private static Random rand = new Random();
	
	public static AIAction getRandomAction() {
		return AIAction.values()[rand.nextInt(AIAction.values().length)];
	}
	
}
