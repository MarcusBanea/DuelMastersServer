package com.duelmasters.DuelMastersServer.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duelmasters.DuelMastersServer.Domain.Abilities;
import com.duelmasters.DuelMastersServer.Domain.ActionCard;
import com.duelmasters.DuelMastersServer.Domain.Aftermath;
import com.duelmasters.DuelMastersServer.Domain.PlayerDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.GameCardDTO;
import com.duelmasters.DuelMastersServer.Service.AI.AIState;

import lombok.Data;

@Service
@Data
//@Scope("singleton")
public class GameEngine {

	private PlayerDTO player1;

	private PlayerDTO player2;
	
	@Autowired
	public AIState aiState;
	
	public PlayerDTO getPlayer(String player) {
		return player.equals("player1") ? this.player1 : this.player2;
	}
	
	// test
	public void drawCard(String player) {
		switch (player) {
		// player 1
		case "player1": {
			player1.getHand().add(player1.getDeck().get(0));
			player1.getDeck().remove(0);
			break;
		}
		// player2
		default: {
			player2.getHand().add(player2.getDeck().get(0));
			player2.getDeck().remove(0);
			break;
		}
		}
	}
	
	public Aftermath moveCard(String playerIndicator, String zoneFrom, String zoneTo, int index) {	
		
		Aftermath aftermath = null;
		
		GameCardDTO card = getCardInZone(playerIndicator, zoneFrom, index);
		
		//check if there is a placement ability triggered for current card
		//at this moment, only battleZone-placement abilities exist
		//also, cards can have only 1 placement abilities
		if(zoneFrom.equals("hand") && zoneTo.equals("battleZone")) {
			if(card.getPlacement().length != 0) {
				aftermath = new Aftermath();
				aftermath.addAbility(playerIndicator + "#" + Abilities.valueOf(card.getPlacement()[0]).getAbility());
			}
		}
		
		addCardInZone(playerIndicator, zoneTo, card);
		removeCardInZone(playerIndicator, zoneFrom, index);
		
		return aftermath;
		
	}
	
	public void updateServerStateAfterAbilityExecution(String[] cards, String action) {
		//sort cards descending, so that the indexes in zones remain consistent
		Arrays.sort(cards, Collections.reverseOrder());
		//check action
		String zoneTo = "";
		String specialAction = "";
		//check for moving-type ability
		if(action.startsWith("MT") || action.equals("DES")) {
			switch(action) { 
				case "DES" : {
					zoneTo = "graveyard";
					break;
				}
				case "MTM" : {
					zoneTo = "manaZone";
					break;
				}
				case "MTH" : {
					zoneTo = "hand";
					break;
				}
				case "MTS" : {
					zoneTo = "shields";
					break;
				}
				case "MTD" : {
					zoneTo = "deck";
					break;
				}
				default : {
					break;
				}
			}
		}
		else {
			specialAction = action;
		}
		
		for(String encodedCard : cards) {
			String[] decodedCard = encodedCard.split(" ");
			/* decodedCard[0] - player
			 * decodedCard[1] - zone
			 * decodedCard[2] - index
			*/
			GameCardDTO card = getCardInZone(decodedCard[0], decodedCard[1], Integer.parseInt(decodedCard[2]));
			//if there is a moving-type ability, move the card
			if(!zoneTo.equals("")) {
				addCardInZone(decodedCard[0], zoneTo, card);
				removeCardInZone(decodedCard[0], decodedCard[1], Integer.parseInt(decodedCard[2]));
			}
			else {
				if(specialAction.equals("TAP")) {
					//TODO
				}
			}
		}
	}
	
	public ArrayList<GameCardDTO> getCardsInZone(String playerIndicator, String zone) {
		PlayerDTO player = playerIndicator.equals("player1") ? this.player1 : this.player2;
		
		switch(zone) {
			case "battleZone" : {
				return player.getBattleZone();
			}
			case "manaZone" : {
				return player.getManaZone();
			}
			case "hand" : {
				return player.getHand();
			}
			case "shields" : {
				return player.getShields();
			}
			case "graveyard" : {
				return player.getGraveyard();
			}
			default : {
				return null;
			}
		}
	}
	
	public GameCardDTO getCardInZone(String playerIndicator, String zone, int index) {
		PlayerDTO player = playerIndicator.equals("player1") ? this.player1 : this.player2;
		
		GameCardDTO card = null;
		switch(zone) {
			case "battleZone" : {
				card = player.getBattleZone().get(index);
				break;
			}
			case "manaZone" : {
				card = player.getManaZone().get(index);
				break;
			}
			case "hand" : {
				card = player.getHand().get(index);
				break;
			}
			case "shields" : {
				card = player.getShields().get(index);
				break;
			}
			case "graveyard" : {
				card = player.getGraveyard().get(index);
				break;
			}
			case "deck" : {
				card = player.getDeck().get(index);
				break;
			}
			default : {
				break;
			}
		}
		return card;
	}
	
	public void removeCardInZone(String playerIndicator, String zone, int index) {
		PlayerDTO player = playerIndicator.equals("player1") ? this.player1 : this.player2;
		
		switch(zone) {
			case "battleZone" : {
				player.getBattleZone().remove(index);
				break;
			}
			case "manaZone" : {
				player.getManaZone().remove(index);
				break;
			}
			case "hand" : {
				player.getHand().remove(index);
				break;
			}
			case "shields" : {
				player.getShields().remove(index);
				break;
			}
			case "graveyard" : {
				player.getGraveyard().remove(index);
				break;
			}
			case "deck" : {
				player.getDeck().remove(index);
				break;
			}
			default : {
				break;
			}
		}
	}
	
	public void addCardInZone(String playerIndicator, String zone, GameCardDTO card) {
		PlayerDTO player = playerIndicator.equals("player1") ? this.player1 : this.player2;
		
		switch(zone) {
			case "battleZone" : {
				player.getBattleZone().add(card);
				break;
			}
			case "manaZone" : {
				player.getManaZone().add(card);
				break;
			}
			case "hand" : {
				player.getHand().add(card);
				break;
			}
			case "shields" : {
				player.getShields().add(card);
				break;
			}
			case "graveyard" : {
				player.getGraveyard().add(card);
				break;
			}
			default : {
				break;
			}
		}
	}
	
	public ArrayList<String> getAttackOptionsForCard(String player, String zone, int index) {
		GameCardDTO card = getCardInZone(player, zone, index);
		
		ArrayList<String> attackOptions = new ArrayList<>();
		
		String opponentIndicator = player.equals("player1") ? "player2" : "player1";
		
		ArrayList<GameCardDTO> opponentShields = getCardsInZone(opponentIndicator, "shields");
		ArrayList<GameCardDTO> opponentBattleZone = getCardsInZone(opponentIndicator, "battleZone");
		
		String[] attackProperty = card.getAttackProperty();
		for(String property : attackProperty) {
			//check "This creature can't attack" property
			if(property.indexOf("CNA") == 0) {
				String encodedProperty = Abilities.valueOf(property).getAbility();
				switch(encodedProperty) {
					case "CNA_0" : {
						attackOptions.add("NONE");
						break;
					}
					case "CNA_1" : {
						for(int i = 0; i < opponentBattleZone.size(); i++) {
							if(canCardBeAttacked(card, opponentBattleZone.get(i))) {
								attackOptions.add("battleZone_" + i);
							}	
						}
						break;
					}
					case "CNA_2" : {
						for(int i = 0; i < opponentShields.size(); i++) {
							attackOptions.add("shields_" + i);
						}
						break;
					}
					default : {
						break;
					}
				}
			}
			//check "This creature can attack" property
			//TODO
			if(property.indexOf("CA") == 0) {
				String encodedProperty = Abilities.valueOf(property).getAbility();
			}
		}
		if(attackOptions.size() == 0) {
			attackOptions.add("ALL");
		}
		
		return attackOptions;
	}
	
	private boolean canCardBeAttacked(GameCardDTO attackingCard, GameCardDTO attackedCard) {
		//check "This creature can't be attacked" property
		boolean canBeAttacked = true;
		
		for(String property : attackedCard.getAttackProperty()) {
			if(property.indexOf("CBA") == 0) {
				String[] encodedProperty = Abilities.valueOf(property).getAbility().split("_");
				if(encodedProperty[0].equals("0")) {
					canBeAttacked = false;
				}
				else {
					if(encodedProperty[2].equals("1")) {
						//check card realm
						String[] realms = encodedProperty[2].split("/");
						for(String realm : realms) {
							if(realm.equals(attackingCard.getRealm())) {
								canBeAttacked = false;
							}
						}
					}
					else if(encodedProperty[2].equals("2")) {
						//check card class
						String[] classes = encodedProperty[2].split("/");
						for(String cardClass : classes) {
							//the class filter may represent the whole class like Mecha del Sol
							//or parts of the class, like Dragon
							if(cardClass.indexOf(attackingCard.getCardClass()) != -1) {
								canBeAttacked = false;
							}
						}
					}
				}
			}
		}
		
		return canBeAttacked;
	}
		
	public Aftermath execute2CardTypeAction(ActionCard actionCard1, ActionCard actionCard2, String actionType) {
		Aftermath aftermath = new Aftermath();
		
		GameCardDTO card1 = getCardInZone(actionCard1.getPlayer(), actionCard1.getZone(), actionCard1.getIndex());
		GameCardDTO card2 = getCardInZone(actionCard2.getPlayer(), actionCard2.getZone(), actionCard2.getIndex());

		switch(actionType) {
			case "block" : 
			case "attack" : {
				
				//check if the defending player has at least one blocker
				//in that case, fill only the "extras" attribute of the aftermath object and return it
				//TODO
//				if(actionType.equals("attack")) {
//					for(GameCardDTO card : getPlayer(actionCard2.getPlayer()).getBattleZone()) {
//						
//					}
//					aftermath.setExtras("");
//				}
				
				//check for trigger on attack
				for(String attackAbility : card1.getAttackProperty()) {
					if(attackAbility.indexOf("WTCA") != -1) {
						aftermath.addAbility(actionCard1.getPlayer() + "#" + Abilities.valueOf(attackAbility).getAbility());
					}
				}
				
				//if a shield was attacked, then no battle happens
				//the shield-card will be moved to player's hand
				//the other card is tapped
				if(actionCard2.getZone().equals("shields")) {
					moveCard(actionCard2.getPlayer(), actionCard2.getZone(), "hand", actionCard2.getIndex());
					aftermath.setCard1State("");
					aftermath.setCard2State("SMTH");
				}
				else {
					//check if the attacking card has slayer type
					boolean attackingCardIsSlayer = false;
					for(String type : card1.getType()) {
						if(type.equals("SL0")) {
							attackingCardIsSlayer = true;
						}
					}
					
					//check if the defending cards has slayer type
					boolean defendingCardIsSlayer = false;
					for(String type : card2.getType()) {
						if(type.equals("SL0")) {
							defendingCardIsSlayer = true;
						}
					}
					
					//if both cards have type slayer, not battle happens
					if(attackingCardIsSlayer && defendingCardIsSlayer) {
						aftermath.setCard1State("");
						aftermath.setCard2State("");
						
						return aftermath;
					}
					
					//if attacking card is slayer and the defending card is not a slayer, then the defending card is destroyed
					else if(attackingCardIsSlayer && !defendingCardIsSlayer) {
						aftermath.setCard1State("");
						aftermath.setCard2State("destroyed");
						
						moveCard(actionCard2.getPlayer(), "battleZone", "graveyard", actionCard2.getIndex());
					}
					
					//if defending card is slayer and the attacking card is not a slayer, then the attacking card is destroyed
					else if(!attackingCardIsSlayer && defendingCardIsSlayer) {
						aftermath.setCard1State("destroyed");
						aftermath.setCard2State("");
						
						moveCard(actionCard1.getPlayer(), "battleZone", "graveyard", actionCard1.getIndex());
					}

					else {
						//get cards power level (when attacking)
						int attackingCardPower = 0;
						int defendingCardPower = 0;
						if(card1.getPower().indexOf("+") != -1) {
							attackingCardPower = Integer.parseInt(card1.getPower().substring(0, card1.getPower().length() - 1));
							//check for "PA" attack property
							for(String attackProp : card1.getAttackProperty()) {
								if(attackProp.startsWith("PA")) {
									attackingCardPower += Integer.parseInt(Abilities.valueOf(attackProp).getAbility());
								}
							}
							attackingCardPower += getAddedPowerForCard(card1, actionCard1.getPlayer(), false);
						}
						else {
							attackingCardPower = Integer.parseInt(card1.getPower());
						}
						
						if(card2.getPower().indexOf("+") != -1) {
							defendingCardPower = Integer.parseInt(card2.getPower().substring(0, card2.getPower().length() - 1));
							defendingCardPower += getAddedPowerForCard(card2, actionCard2.getPlayer(), true);
						}
						else {
							defendingCardPower = Integer.parseInt(card2.getPower());
						}
						
						//execute action attack
						//move cards to appropriate zones and modify their states (power level, speed attacker, ...)
						//check for triggered abilities after the execution
						if(attackingCardPower > defendingCardPower) {
							
							//card2 will be destroyed
							moveCard(actionCard2.getPlayer(), "battleZone", "graveyard", actionCard2.getIndex());
							
							aftermath.setCard1State("");
							aftermath.setCard2State("destroyed");
							
						}
						else if(attackingCardPower < defendingCardPower) {
							
							aftermath.setCard1State("destroyed");
							aftermath.setCard2State("");
							
							//card1 will be destroyed
							moveCard(actionCard1.getPlayer(), "battleZone", "graveyard", actionCard1.getIndex());
						}
						else {
							
							aftermath.setCard1State("destroyed");
							aftermath.setCard2State("destroyed");
							
							//both cards will be destroyed
							moveCard(actionCard1.getPlayer(), "battleZone", "graveyard", actionCard1.getIndex());
							moveCard(actionCard2.getPlayer(), "battleZone", "graveyard", actionCard2.getIndex());
						}
					}
					
					//check for trigger on destroyed card
					if(aftermath.getCard1State().equals("destroyed")) {
						for(String attackAbility : card1.getAttackProperty()) {
							if(attackAbility.indexOf("WTCID") != -1) {
								String abilityWhenDestroyed = Abilities.valueOf(attackAbility).getAbility();
								//instead of being destroyed, the creature will be moved to another zone
								//no need to add this ability to the aftermath
								//card status will be replaced
								if(abilityWhenDestroyed.startsWith("MT")) {
									//get card from graveyard
									switch(abilityWhenDestroyed) {
										case "MTH" : {
											moveCard(actionCard1.getPlayer(), "graveyard", "hand",
													getLastDestroyedCardIndexInGraveyard(actionCard1.getPlayer()));
											aftermath.setCard1State("MTH");
											break;
										}
										case "MTM" : {
											moveCard(actionCard1.getPlayer(), "graveyard", "mana",
													getLastDestroyedCardIndexInGraveyard(actionCard1.getPlayer()));
											aftermath.setCard1State("MTM");
											break;
										}
										case "MTS" : {
											moveCard(actionCard1.getPlayer(), "graveyard", "shields",
													getLastDestroyedCardIndexInGraveyard(actionCard1.getPlayer()));
											aftermath.setCard1State("MTS");
											break;
										}
										case "MTD" : {
											moveCard(actionCard1.getPlayer(), "graveyard", "deck",
													getLastDestroyedCardIndexInGraveyard(actionCard1.getPlayer()));
											aftermath.setCard1State("MTD");
											break;
										}
										default : {
											break;
										}
									}
								}
								else {
									aftermath.addAbility(actionCard1.getPlayer() + "#"
											+ abilityWhenDestroyed);
								}
							}
						}
					}
					
					if(aftermath.getCard2State().equals("destroyed")) {
						for(String attackAbility : card2.getAttackProperty()) {
							if(attackAbility.indexOf("WTCID") != -1) {
								String abilityWhenDestroyed = Abilities.valueOf(attackAbility).getAbility();
								//instead of being destroyed, the creature will be moved to another zone
								//no need to add this ability to the aftermath
								//card status will be replaced
								if(abilityWhenDestroyed.startsWith("MT")) {
									//get card from graveyard
									switch(abilityWhenDestroyed) {
										case "MTH" : {
											moveCard(actionCard2.getPlayer(), "graveyard", "hand",
													getLastDestroyedCardIndexInGraveyard(actionCard2.getPlayer()));
											aftermath.setCard2State("MTH");
											break;
										}
										case "MTM" : {
											moveCard(actionCard2.getPlayer(), "graveyard", "mana",
													getLastDestroyedCardIndexInGraveyard(actionCard2.getPlayer()));
											aftermath.setCard2State("MTM");
											break;
										}
										case "MTS" : {
											moveCard(actionCard2.getPlayer(), "graveyard", "shields",
													getLastDestroyedCardIndexInGraveyard(actionCard2.getPlayer()));
											aftermath.setCard2State("MTS");
											break;
										}
										case "MTD" : {
											moveCard(actionCard2.getPlayer(), "graveyard", "deck",
													getLastDestroyedCardIndexInGraveyard(actionCard2.getPlayer()));
											aftermath.setCard2State("MTD");
											break;
										}
										default : {
											break;
										}
									}
								}
								else {
									aftermath.addAbility(actionCard2.getPlayer() + "#"
											+ abilityWhenDestroyed);
								}
							}
						}
					}
				}
				break;
				
			}
			default : {
				break;
			}

		}
		
		return aftermath;
	}
	
	private int getLastDestroyedCardIndexInGraveyard(String player) {
		return getZone("GV0", player).size() - 1;
	}
	
	private int getAddedPowerForCard(GameCardDTO card, String player, boolean noAttackingAddedPower) {
		int finalAddedPower = 0;
		for(String addedPowerProp : card.getAddedPower()) {
			//ATCG ability will not be checked, as this card is not attacking
			if(addedPowerProp.startsWith("TCG") || addedPowerProp.startsWith("ATCG")) {
				if(addedPowerProp.startsWith("ATCG") && noAttackingAddedPower == true) {
					break;
				}
				//decode TCG ability
				//the encoded ability has 6 parts:
				//0 - power level to be added
				//1 - type condition (NUL - for each card of type...; ONLY - if all cards have the same type...
				//2 - count condition (ALL - count all cards of type...; ONE - check if there is at least 1 card of type...)
				//3 - filter code (0 - no filter; 1 - realm filter; ...)
				//4 - filter property (if filter code is 1, then the filter property will indicate which realm will be used to filter
				//5 - zone (HD - hand, SD - shields, ...)
				String encodedAbility = Abilities.valueOf(addedPowerProp).getAbility();
				String[] encodedAblilityParts = encodedAbility.split("_");
				//get power level
				int addedPowerLevel = Integer.parseInt(encodedAblilityParts[0].substring(3));
				//count cards with filter in zone
				int nrOfCardsInZoneWithFilter = count(Integer.parseInt(encodedAblilityParts[3]),
						encodedAblilityParts[4], encodedAblilityParts[5], player);
				//check the combination of type and count conditions
				//compute the final added power
				if(encodedAblilityParts[1].equals("NUL") && encodedAblilityParts[2].equals("ALL")) {
					finalAddedPower = addedPowerLevel * nrOfCardsInZoneWithFilter;
				}
				else if(encodedAblilityParts[1].equals("NUL") && encodedAblilityParts[2].equals("ONE")) {
					finalAddedPower = addedPowerLevel;
				}
				else if(encodedAblilityParts[1].equals("ONLY") && encodedAblilityParts[2].equals("ALL")) {
					if(nrOfCardsInZoneWithFilter == count(0, null, encodedAblilityParts[5], player)) {
						finalAddedPower = addedPowerLevel;
					}
				}
			}
		}
		return finalAddedPower;
	}
	
	// utils

	public int count(int type, String filter, String zone, String player) {

		ArrayList<GameCardDTO> selectedZone = getZone(zone, player);

		switch (type) {
		// no filter
		case 0: {
			return selectedZone.size();
		}
		// realm
		case 1: {
			return selectedZone.stream().filter(card -> card.getRealm().toUpperCase().equals(filter)).collect(Collectors.toList())
					.size();
		}
		// class
		case 2: {
			return selectedZone.stream().filter(card -> card.getCardClass().toUpperCase().equals(filter)).collect(Collectors.toList())
					.size();
		}
		// less power
		case 3: {
			//TODO - get current power level, considering added power abilities
			return selectedZone.stream().filter(card -> Integer.parseInt(card.getPower()) > Integer.parseInt(filter))
					.collect(Collectors.toList()).size();
		}
		// more power
		case 4: {
			//TODO - get current power level, considering added power abilities
			return selectedZone.stream().filter(card -> Integer.parseInt(card.getPower()) < Integer.parseInt(filter))
					.collect(Collectors.toList()).size();
		}
		// power
		case 5: {
			//TODO - get current power level, considering added power abilities
			return selectedZone.stream().filter(card -> Integer.parseInt(card.getPower()) == Integer.parseInt(filter))
					.collect(Collectors.toList()).size();
		}
		// blocker TODO
		case 6: {
			return selectedZone.stream().filter(card -> card.getType().equals("")).collect(Collectors.toList()).size();
		}
		// evolution
		case 7: {
			return selectedZone.stream().filter(card -> card.getType().equals("Evolution")).collect(Collectors.toList())
					.size();
		}
		// tapped TODO
		case 8: {
			return -1;
		}
		// untapped TODO
		case 9: {
			return -1;
		}
		// random ???
		case 10: {
			return -1;
		}
		// spell
		case 11: {
			return selectedZone.stream().filter(card -> card.getType().equals("Spell")).collect(Collectors.toList())
					.size();
		}
		default: {
			return -1;
		}
		}
	}

	public ArrayList<Integer> get(int type, String filter, String zone, String player) {

		ArrayList<GameCardDTO> selectedZone = getZone(zone, player);
		ArrayList<Integer> selectedCards = new ArrayList<>();

		switch (type) {
		// no filter
		case 0: {
			for(int i = 0; i < selectedZone.size(); i++) {
				selectedCards.add(i);
			}
			break;
		}
		// realm
		case 1: {
			for(int i = 0; i < selectedZone.size(); i++) {
				if(selectedZone.get(i).getRealm().equals(filter)) {
					selectedCards.add(i);
				}
			}
			break;
		}
		// class
		case 2: {
			for(int i = 0; i < selectedZone.size(); i++) {
				if(selectedZone.get(i).getCardClass().equals(filter)) {
					selectedCards.add(i);
				}
			}
			break;
		}
		// less power
		case 3: {
			for(int i = 0; i < selectedZone.size(); i++) {
				if(Integer.parseInt(selectedZone.get(i).getPower()) > Integer.parseInt(filter)) {
					selectedCards.add(i);
				}
			}
			break;
		}
		// more power
		case 4: {
			for(int i = 0; i < selectedZone.size(); i++) {
				if(Integer.parseInt(selectedZone.get(i).getPower()) < Integer.parseInt(filter)) {
					selectedCards.add(i);
				}
			}
			break;
		}
		// power
		case 5: {
			for(int i = 0; i < selectedZone.size(); i++) {
				if(Integer.parseInt(selectedZone.get(i).getPower()) == Integer.parseInt(filter)) {
					selectedCards.add(i);
				}
			}
			break;
		}
		// blocker TODO
		case 6: {
			for(int i = 0; i < selectedZone.size(); i++) {
				if(selectedZone.get(i).getType().equals("Blocker")) {
					selectedCards.add(i);
				}
			}
			break;
		}
		// evolution
		case 7: {
			for(int i = 0; i < selectedZone.size(); i++) {
				if(selectedZone.get(i).getType().equals("Evolution")) {
					selectedCards.add(i);
				}
			}
			break;
		}
		// tapped TODO
		case 8: {
			break;
		}
		// untapped TODO
		case 9: {
			break;
		}
		// random ???
		case 10: {
			break;
		}
		// spell
		case 11: {
			for(int i = 0; i < selectedZone.size(); i++) {
				if(selectedZone.get(i).getType().equals("Spell")) {
					selectedCards.add(i);
				}
			}
			break;
		}
		default: {
			break;
		}
		}

		return selectedCards;

	}

	private ArrayList<GameCardDTO> getZone(String zone, String player) {

		switch (zone) {
		case "BZ0": {
			if(player.equals("player1")) {
				return player1.getBattleZone();
			}
			else {
				return player2.getBattleZone();
			}
		}
		case "BZ1": {
			if(player.equals("player1")) {
				return player2.getBattleZone();
			}
			else {
				return player1.getBattleZone();
			}
		}
		case "BZ2": {
			 ArrayList<GameCardDTO> cards = player1.getBattleZone();
			 cards.addAll(player2.getBattleZone());
			 return cards;
		}
		case "MN0": {
			if(player.equals("player1")) {
				return player1.getManaZone();
			}
			else {
				return player2.getManaZone();
			}
		}
		case "MN1": {
			if(player.equals("player1")) {
				return player2.getManaZone();
			}
			else {
				return player1.getManaZone();
			}
		}
		case "MN2": {
			 ArrayList<GameCardDTO> cards = player1.getManaZone();
			 cards.addAll(player2.getManaZone());
			 return cards;
		}
		case "GV0": {
			if(player.equals("player1")) {
				return player1.getGraveyard();
			}
			else {
				return player2.getGraveyard();
			}
		}
		case "GV1": {
			if(player.equals("player1")) {
				return player2.getGraveyard();
			}
			else {
				return player1.getGraveyard();
			}
		}
		case "GV2": {
			 ArrayList<GameCardDTO> cards = player1.getGraveyard();
			 cards.addAll(player2.getGraveyard());
			 return cards;
		}
		case "DK0": {
			if(player.equals("player1")) {
				return player1.getDeck();
			}
			else {
				return player2.getDeck();
			}
		}
		case "DK1": {
			if(player.equals("player1")) {
				return player2.getDeck();
			}
			else {
				return player1.getDeck();
			}
		}
		case "DK2": {
			 ArrayList<GameCardDTO> cards = player1.getDeck();
			 cards.addAll(player2.getDeck());
			 return cards;
		}
		case "HD0": {
			if(player.equals("player1")) {
				return player1.getHand();
			}
			else {
				return player2.getHand();
			}
		}
		case "HD1": {
			if(player.equals("player1")) {
				return player2.getHand();
			}
			else {
				return player1.getHand();
			}
		}
		case "HD2": {
			 ArrayList<GameCardDTO> cards = player1.getHand();
			 cards.addAll(player2.getHand());
			 return cards;
		}
		case "SD0": {
			if(player.equals("player1")) {
				return player1.getShields();
			}
			else {
				return player2.getShields();
			}
		}
		case "SD1": {
			if(player.equals("player1")) {
				return player2.getShields();
			}
			else {
				return player1.getShields();
			}
		}
		case "SD2": {
			 ArrayList<GameCardDTO> cards = player1.getShields();
			 cards.addAll(player2.getShields());
			 return cards;
		}
		default: {
			return null;
		}
		}
	}

	
	
	
	
	//AI logic
	
	//check draw card
	//check hand
	//check battle zone
}
