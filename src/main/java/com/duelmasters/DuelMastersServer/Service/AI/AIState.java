package com.duelmasters.DuelMastersServer.Service.AI;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.duelmasters.DuelMastersServer.Domain.PlayerDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.GameCardDTO;

import lombok.Data;

@Data
@Service
public class AIState {
	
	//@Autowired
	private AIEngine aiEngine;

	private PlayerDTO player;
	
	private ArrayList<AIAction> possibleActionsThisTurn;
	private int manaAvailableThisTurn;
	
	public AIState() {
		this.possibleActionsThisTurn = new ArrayList<>();
	}
	
	public void initNewTurn() {
		this.manaAvailableThisTurn = player.getManaZone().size();
		
		//untap cards in battle zone and mana
		for(GameCardDTO card : player.getBattleZone()) {
			card.setTapped(false);
		}
		for(GameCardDTO card : player.getManaZone()) {
			card.setTapped(false);
		}
		
		//initialize all possible actions at the start of a new turn
		//as all actions are possible at this moment
		//more checks later
		for(AIAction action : AIAction.values()) {
			possibleActionsThisTurn.add(action);
		}
	}
	
	public ArrayList<AIMove> makeRandomAction() {
		ArrayList<AIMove> aiMoves = new ArrayList<>();
		AIMove aiMove = new AIMove();
		
		AIAction randomAction = AIAction.getRandomAction();
		
		Random rand = new Random();
		
		switch(randomAction) {
			case DRAW_CARD : {
				
				//build AIMove object that will be sent to the client
				aiMove.addCard("player2 deck hand 0");
				aiMove.setAction("MTH");
				
				player.getHand().add(player.getDeck().get(0));
				player.getDeck().remove(0);
				break;
			}
			case MOVE_CARD_TO_MANA: {
				//get random card from hand
				int randomCardIndex = rand.nextInt(player.getHand().size());
				aiMove.addCard("player2 hand manaZone " + randomCardIndex);
				aiMove.setAction("MTM");
				
				player.getManaZone().add(player.getHand().get(randomCardIndex));
				player.getHand().remove(randomCardIndex);
				
				if(player.getHand().size() == 0) {
					this.possibleActionsThisTurn.remove(AIAction.MOVE_CARD_TO_BATTLEZONE);
				}
				
				this.manaAvailableThisTurn++;
				this.possibleActionsThisTurn.remove(AIAction.MOVE_CARD_TO_MANA);
				break;
			}
			case MOVE_CARD_TO_BATTLEZONE: {
				//get random card from hand
				int randomCardIndex = rand.nextInt(player.getHand().size());
				aiMove.addCard("player2 hand battleZone " + randomCardIndex);
				aiMove.setAction("MTB");
				
				//update mana available and tap the creature
				this.manaAvailableThisTurn -= Integer.parseInt(player.getHand().get(randomCardIndex).getMana());
				//TODO - check if speed attacker
				player.getHand().get(randomCardIndex).setTapped(true);

				player.getBattleZone().add(player.getHand().get(randomCardIndex));
				player.getHand().remove(randomCardIndex);
				
				if(player.getHand().size() == 0) {
					this.possibleActionsThisTurn.remove(AIAction.MOVE_CARD_TO_BATTLEZONE);
					this.possibleActionsThisTurn.remove(AIAction.MOVE_CARD_TO_MANA);
				}
				
				break;
			}
			/*
			case SELECT_CARD_FOR_ATTACK: {
				
				break;
			}
			*/
			case NO_ACTION: {
				
				break;
			}
		}
		aiMoves.add(aiMove);
		return aiMoves;
	}
}
