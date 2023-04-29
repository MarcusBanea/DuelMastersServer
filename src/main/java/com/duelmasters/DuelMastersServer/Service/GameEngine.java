package com.duelmasters.DuelMastersServer.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.duelmasters.DuelMastersServer.Domain.Abilities;
import com.duelmasters.DuelMastersServer.Domain.Ability;
import com.duelmasters.DuelMastersServer.Domain.ActionCard;
import com.duelmasters.DuelMastersServer.Domain.Aftermath;
import com.duelmasters.DuelMastersServer.Domain.PlayerDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.GameCardDTO;

import lombok.Data;

@Service
@Data
//@Scope("singleton")
public class GameEngine {

	private PlayerDTO player1;

	private PlayerDTO player2;
	
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
	
	public void moveCard(String playerIndicator, String zoneFrom, String zoneTo, int index) {		
		GameCardDTO card = getCardInZone(playerIndicator, zoneFrom, index);
		addCardInZone(playerIndicator, zoneTo, card);
		removeCardInZone(playerIndicator, zoneFrom, index);
		
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
		
	public Aftermath execute2CardTypeAction(ActionCard actionCard1, ActionCard actionCard2, String actionType) {
		Aftermath aftermath = new Aftermath();
		
		GameCardDTO card1 = getCardInZone(actionCard1.getPlayer(), actionCard1.getZone(), actionCard1.getIndex());
		GameCardDTO card2 = getCardInZone(actionCard2.getPlayer(), actionCard2.getZone(), actionCard2.getIndex());

		switch(actionType) {
			case "block" : 
			case "attack" : {
				//check for trigger on attacking card
				//TODO
				for(Ability ability : card1.getAbility()) {
					if(ability.getTrigger().equals("attacking")) {
						//check for "Whenever this creature attacks" ability
						String abilityEffectEncoded = ability.getEffect();
						String abilityEffectDecoded = "";
						if(abilityEffectEncoded.indexOf("WTCA") != -1) {
							abilityEffectDecoded = abilityEffectEncoded.substring(abilityEffectEncoded.indexOf("WTCA"), 6);
							abilityEffectDecoded = Abilities.valueOf(abilityEffectDecoded).getAbility();
						}
						aftermath.addAbility(abilityEffectDecoded);
					}
				}
				
				//execute action attack
				//move cards to appropriate zones and modify their states (power level, speed attacker, ...)
				//check for triggered abilities after the execution
				if(card1.getPower() > card2.getPower()) {
					
					//card2 will be destroyed
					moveCard(actionCard2.getPlayer(), "battleZone", "graveyard", actionCard2.getIndex());
					
					aftermath.setCard1State("");
					aftermath.setCard2State("destroyed");
					
					//check for trigger on destroyed card
					//TODO
				}
				else if(card1.getPower() < card2.getPower()) {
					
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
				
				break;
			}
			default : {
				break;
			}

		}
		
		return aftermath;
	}
	
	
	// utils

	public int count(int type, String filter, String zone) {

		ArrayList<GameCardDTO> selectedZone = getZone(zone);

		switch (type) {
		// no filter
		case 0: {
			return selectedZone.size();
		}
		// realm
		case 1: {
			return selectedZone.stream().filter(card -> card.getCardRealm().equals(filter)).collect(Collectors.toList())
					.size();
		}
		// class
		case 2: {
			return selectedZone.stream().filter(card -> card.getCardClass().equals(filter)).collect(Collectors.toList())
					.size();
		}
		// less power
		case 3: {
			return selectedZone.stream().filter(card -> card.getPower() > Integer.parseInt(filter))
					.collect(Collectors.toList()).size();
		}
		// more power
		case 4: {
			return selectedZone.stream().filter(card -> card.getPower() < Integer.parseInt(filter))
					.collect(Collectors.toList()).size();
		}
		// power
		case 5: {
			return selectedZone.stream().filter(card -> card.getPower() == Integer.parseInt(filter))
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

	public ArrayList<Integer> get(int type, String filter, String zone) {

		ArrayList<GameCardDTO> selectedZone = getZone(zone);
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
				if(selectedZone.get(i).getCardRealm().equals(filter)) {
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
				if(selectedZone.get(i).getPower() > Integer.parseInt(filter)) {
					selectedCards.add(i);
				}
			}
			break;
		}
		// more power
		case 4: {
			for(int i = 0; i < selectedZone.size(); i++) {
				if(selectedZone.get(i).getPower() < Integer.parseInt(filter)) {
					selectedCards.add(i);
				}
			}
			break;
		}
		// power
		case 5: {
			for(int i = 0; i < selectedZone.size(); i++) {
				if(selectedZone.get(i).getPower() == Integer.parseInt(filter)) {
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

	private ArrayList<GameCardDTO> getZone(String zone) {

		switch (zone) {
		case "BZ0": {
			return player1.getBattleZone();
		}
		case "MN0": {
			return player1.getManaZone();
		}
		case "GV0": {
			return player1.getGraveyard();
		}
		case "DK0": {
			return player1.getDeck();
		}
		case "HD0": {
			return player1.getHand();
		}
		default: {
			return null;
		}
		}
	}

}
