package com.duelmasters.DuelMastersServer.Service.AI;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.duelmasters.DuelMastersServer.Domain.DTO.GameCardDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.PlayerDTO;

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
		Random rand = new Random();
		
		while(this.getPossibleActionsThisTurn().size() != 0) {
			AIMove aiMove = new AIMove();
			
			if(player.getHand().size() == 0) {
				this.possibleActionsThisTurn.remove(AIAction.MOVE_CARD_TO_MANA);
				this.possibleActionsThisTurn.remove(AIAction.MOVE_CARD_TO_BATTLEZONE);
			}
			if(player.getDeck().size() == 0) {
				this.possibleActionsThisTurn.remove(AIAction.DRAW_CARD);
			}
			if(player.getBattleZone().size() == 0) {
				this.possibleActionsThisTurn.remove(AIAction.SELECT_CARD_FOR_ATTACK);
			}
			else {
				boolean hasUntappedCardsInBattleZone = false;
				for(GameCardDTO card : player.getBattleZone()) {
					if(!card.isTapped()) {
						hasUntappedCardsInBattleZone = true;
					}
				}
				if(!hasUntappedCardsInBattleZone) {
					this.possibleActionsThisTurn.remove(AIAction.SELECT_CARD_FOR_ATTACK);
				}
			}
			
			AIAction randomAction = possibleActionsThisTurn.get(rand.nextInt(possibleActionsThisTurn.size()));
			
			switch(randomAction) {
				case DRAW_CARD : {
					
					aiMove.addCard("player2 deck hand 0");
					aiMove.setAction("MTH");
					
					player.getHand().add(player.getDeck().get(0));
					player.getDeck().remove(0);
					
					this.possibleActionsThisTurn.remove(AIAction.DRAW_CARD);
					break;
				}
				case MOVE_CARD_TO_MANA: {
					//get random card from hand
					int randomCardIndex = rand.nextInt(player.getHand().size());
					aiMove.addCard("player2 hand manaZone " + randomCardIndex);
					aiMove.setAction("MTM");
					
					player.getManaZone().add(player.getHand().get(randomCardIndex));
					player.getHand().remove(randomCardIndex);
					
					this.manaAvailableThisTurn++;
					this.possibleActionsThisTurn.remove(AIAction.MOVE_CARD_TO_MANA);
					break;
				}
				case MOVE_CARD_TO_BATTLEZONE: {
					//get all cards that can be put into battle zone
					//considering available mana
					ArrayList<GameCardDTO> validHandCards = new ArrayList<>();
					for(GameCardDTO card : player.getHand()) {
						if (Integer.parseInt(card.getMana()) <= this.manaAvailableThisTurn) {
							validHandCards.add(card);
						}
					}
					
					//get random card from hand
					int nrOfCardsInHand = validHandCards.size();
					int randomCardIndex = rand.nextInt(nrOfCardsInHand);
					randomCardIndex = player.getHand().indexOf(validHandCards.get(randomCardIndex));
					
					aiMove.addCard("player2 hand battleZone " + randomCardIndex);
					aiMove.setAction("MTB");
					
					//update mana available and tap the creature
					this.manaAvailableThisTurn -= Integer.parseInt(player.getHand().get(randomCardIndex).getMana());
					//TODO - check if speed attacker
					player.getHand().get(randomCardIndex).setTapped(true);

					player.getBattleZone().add(player.getHand().get(randomCardIndex));
					player.getHand().remove(randomCardIndex);
					
					if(player.getHand().size() == 0) {
						break;
					}
					
					//check if there is any card in hand that can be put into battlezone
					//considering available mana
					boolean hasEnoughManaForMove = false;
					for(GameCardDTO card : player.getHand()) {
						if(Integer.parseInt(card.getMana()) <= this.manaAvailableThisTurn) {
							hasEnoughManaForMove = true;
						}
					}
					
					if(!hasEnoughManaForMove) {
						this.possibleActionsThisTurn.remove(AIAction.MOVE_CARD_TO_BATTLEZONE);
					}
					
					break;
				}
				case SELECT_CARD_FOR_ATTACK: {
					
					//get all untapped cards in the battle zone
					//that can attack
					//check attack properties
					ArrayList<GameCardDTO> untappedCardsInBattleZone = new ArrayList<>();
					for(GameCardDTO card : player.getBattleZone()) {
						if(!card.isTapped()) {
							
							untappedCardsInBattleZone.add(card);
						}
					}
					
					
					
					break;
				}
				case NO_ACTION: {
					possibleActionsThisTurn = new ArrayList<>();
					return aiMoves;
				}
			}
			aiMoves.add(aiMove);
		}
		possibleActionsThisTurn = new ArrayList<>();
		return aiMoves;
	}
}
