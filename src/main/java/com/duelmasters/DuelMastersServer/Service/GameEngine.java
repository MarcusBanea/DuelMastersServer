package com.duelmasters.DuelMastersServer.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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

	// TODO
	public void executeAbility(String ability) {

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
