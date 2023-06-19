package com.duelmasters.DuelMastersServer.Service.Game;

import com.duelmasters.DuelMastersServer.Domain.Types.Aftermath;

public interface GameEngine {

	public void drawCard(String player);
	
	public Aftermath moveCard(String playerIndicator, String zoneFrom, String zoneTo, int index);
	
	public void updateServerStateAfterAbilityExecution(String[] cards, String action);
	
}
