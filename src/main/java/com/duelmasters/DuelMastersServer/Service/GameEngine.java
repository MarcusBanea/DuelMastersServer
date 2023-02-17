package com.duelmasters.DuelMastersServer.Service;

import org.springframework.stereotype.Service;

import com.duelmasters.DuelMastersServer.Domain.PlayerDTO;

import lombok.Data;

@Service
@Data
//@Scope("singleton")
public class GameEngine {

	private PlayerDTO player1;
	
	private PlayerDTO player2;
	
	
	//TODO
	public void executeAbility(String ability) {

	}
	
	//test
	public void drawCard(String player) {
		switch(player) {
			//player 1
			case "player1" : {
				player1.getHand().add(player1.getDeck().get(0));
				player1.getDeck().remove(0);
				break;
			}
			//player2
			default: {
				player2.getHand().add(player2.getDeck().get(0));
				player2.getDeck().remove(0);
				break;
			}
		}
	}
	
}
