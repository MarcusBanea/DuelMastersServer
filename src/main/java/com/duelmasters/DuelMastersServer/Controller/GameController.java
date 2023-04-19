package com.duelmasters.DuelMastersServer.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duelmasters.DuelMastersServer.Domain.Abilities;
import com.duelmasters.DuelMastersServer.Domain.Player;
import com.duelmasters.DuelMastersServer.Domain.PlayerDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.GameCard;
import com.duelmasters.DuelMastersServer.Domain.DTO.GameCardDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.MapperDTO;
import com.duelmasters.DuelMastersServer.Service.GameEngine;
import com.duelmasters.DuelMastersServer.Service.DAO.UserService;

@RestController
@RequestMapping("/game")
public class GameController {
	
	private UserService userService;
	private MapperDTO mapper;
	
	@Autowired
	GameEngine gameEngine;
	
	@Autowired
	public GameController(UserService userService, MapperDTO mapperDTO) {
		this.userService = userService;
		this.mapper = mapperDTO;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = "/initialize/{userId1}/{userId2}")
	public ResponseEntity<Object> initialize(@PathVariable String userId1, @PathVariable String userId2) throws IOException {
		List<GameCard> player1DeckCards = userService.generateRandomDeckFromCollectionWithGameCards(userId1, 25);
		List<GameCard> player2DeckCards = userService.generateRandomDeckFromCollectionWithGameCards(userId2, 25);
		
		Collections.sort(player1DeckCards, (c1, c2) -> c1.getMana() - c2.getMana());
		Collections.sort(player2DeckCards, (c1, c2) -> c1.getMana() - c2.getMana());
		
		List<GameCard> player1HandCards = new ArrayList<>();
		List<GameCard> player1Shields = new ArrayList<>();
		
		List<GameCard> player2HandCards = new ArrayList<>();
		List<GameCard> player2Shields = new ArrayList<>();
		
		
		/*
		for(int i = 0; i < 5; i++) {
			player1HandCards.add(player1DeckCards.get(i));
			player1Shields.add(player1DeckCards.get(i + 5));
			player2HandCards.add(player2DeckCards.get(i));
			player2Shields.add(player2DeckCards.get(i + 5));
		}
		*/
		
		//for testing
		List<GameCard> player1ManaCards = new ArrayList<>();
		List<GameCard> player1BattleZoneCards = new ArrayList<>();
		List<GameCard> player2ManaCards = new ArrayList<>();
		List<GameCard> player2BattleZoneCards = new ArrayList<>();
		
		player1BattleZoneCards.add(player1DeckCards.get(0));
		player1HandCards.add(player1DeckCards.get(1));
		player1HandCards.add(player1DeckCards.get(2));
		player1ManaCards.add(player1DeckCards.get(3));
		player1ManaCards.add(player1DeckCards.get(4));
		
		player2HandCards.add(player2DeckCards.get(0));
		player2HandCards.add(player2DeckCards.get(1));
		player2BattleZoneCards.add(player2DeckCards.get(2));
		player2ManaCards.add(player2DeckCards.get(3));
		player2ManaCards.add(player2DeckCards.get(4));
		
		for(int i = 5; i <= 9; i++) {
			player1Shields.add(player1DeckCards.get(i + 5));
			player2Shields.add(player2DeckCards.get(i + 5));
		}
		
		
		for(int i = 0 ; i < 10; i++) {
			player1DeckCards.remove(0);
			player2DeckCards.remove(0);
		}		
		
		Player player1 = new Player();
		Player player2 = new Player();
		
		player1.setDeck((ArrayList) player1DeckCards);
		player1.setHand((ArrayList) player1HandCards);
		player1.setShields((ArrayList) player1Shields);
		//player1.setBattleZone(new ArrayList<>());
		//player1.setManaZone(new ArrayList<>());
		player1.setBattleZone((ArrayList) player1BattleZoneCards);
		player1.setManaZone((ArrayList) player1ManaCards);
		player1.setGraveyard(new ArrayList<>());
		
		player2.setDeck((ArrayList) player2DeckCards);
		player2.setHand((ArrayList) player2HandCards);
		player2.setShields((ArrayList) player2Shields);
		//player2.setBattleZone(new ArrayList<>());
		//player2.setManaZone(new ArrayList<>());
		player2.setBattleZone((ArrayList) player2BattleZoneCards);
		player2.setManaZone((ArrayList) player2ManaCards);
		player2.setGraveyard(new ArrayList<>());
		
		gameEngine.setPlayer1(mapper.playerToPlayerDTO(player1));
		gameEngine.setPlayer2(mapper.playerToPlayerDTO(player2));
		
		return new ResponseEntity<>(Arrays.asList(player1, player2), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/action/{action}/{player}")
	public ResponseEntity<Object> executeAction(@PathVariable String action, @PathVariable String player) {
		ArrayList<String> response = new ArrayList<>();
		//response[0] - response to player1
		//response[1] - response to player2
		
		PlayerDTO currentPlayer = player.equals("player1") ? gameEngine.getPlayer1() : gameEngine.getPlayer2();
		
		String actionBegin = "";
		if(action.indexOf(' ') != -1) {
			actionBegin = action.substring(0, action.indexOf(' '));
		}
		else {
			actionBegin = action;
		}
		switch(actionBegin) {
			case "DrawCard" : {
				gameEngine.drawCard(player);
				break;
			}
			//action parameter will represent the indexes, in their specific battle zone, 
			//of the attacking card, and the attacked card
			case "Attack" : {
				action = action.substring(7);
				//indexes[0] - index of attacking card
				//indexes[1] - index of attacked card
				String[] indexes = action.split("\\s+");
				
				//compare cards power level
				switch(player) {
				
					//player1 is attacking
					case "player1" : {
						
//						//check for "Whenever this creature attacks" ability
//						String abilityOfAttackingCard = gameEngine.getPlayer1().getBattleZone().get(Integer.parseInt(indexes[0])).getAbility();
//						if(abilityOfAttackingCard.indexOf("WTCA") != -1) {
//							abilityOfAttackingCard = abilityOfAttackingCard.substring(abilityOfAttackingCard.indexOf("WTCA"), 6);
//							abilityOfAttackingCard = Abilities.valueOf(abilityOfAttackingCard).getAbility();
//						}
						
						
						
						int powerLvlAttackingCard = gameEngine.getPlayer1().getBattleZone().get(Integer.parseInt(indexes[0])).getPower();
						int powerLvlAttackedCard = gameEngine.getPlayer2().getBattleZone().get(Integer.parseInt(indexes[1])).getPower();
						if(powerLvlAttackingCard > powerLvlAttackedCard) {
							//response to player1 - nothing, his card won the battle
							response.add("NMV");
							//response to player2 - his card lost the battle, so it will be moved to graveyard
							response.add("MTG");
							
							//save move on the server
							gameEngine.getPlayer2().getGraveyard().add(gameEngine.getPlayer2().getBattleZone().get(Integer.parseInt(indexes[1])));
							gameEngine.getPlayer2().getBattleZone().remove(Integer.parseInt(indexes[1]));
						}
						//slayer ?
						else if (powerLvlAttackingCard < powerLvlAttackedCard) {
							//response to player1 - his card lost the battle, so it will be moved to graveyard
							response.add("MTG");
							//response to player2 - nothing, his card won the battle
							response.add("");
							
							//save move on the server
							gameEngine.getPlayer1().getGraveyard().add(gameEngine.getPlayer1().getBattleZone().get(Integer.parseInt(indexes[0])));
							gameEngine.getPlayer1().getBattleZone().remove(Integer.parseInt(indexes[0]));
						}
						else {
							//cards have equal power levels, so both will be destroyed
							response.add("MTG");
							response.add("MTG");
							
							//save move on the server
							gameEngine.getPlayer2().getGraveyard().add(gameEngine.getPlayer2().getBattleZone().get(Integer.parseInt(indexes[1])));
							gameEngine.getPlayer2().getBattleZone().remove(Integer.parseInt(indexes[1]));
							gameEngine.getPlayer1().getGraveyard().add(gameEngine.getPlayer1().getBattleZone().get(Integer.parseInt(indexes[0])));
							gameEngine.getPlayer1().getBattleZone().remove(Integer.parseInt(indexes[0]));
						}
						break;
					}
					//player2 is attacking
					case "player2" : {
						int powerLvlAttackingCard = gameEngine.getPlayer2().getBattleZone().get(Integer.parseInt(indexes[0])).getPower();
						int powerLvlAttackedCard = gameEngine.getPlayer1().getBattleZone().get(Integer.parseInt(indexes[1])).getPower();
						if(powerLvlAttackingCard > powerLvlAttackedCard) {
							//response to player1 - nothing, his card won the battle
							response.add("MTG");
							//response to player2 - his card lost the battle, so it will be moved to graveyard
							response.add("");
							
							//save move on the server
							gameEngine.getPlayer1().getGraveyard().add(gameEngine.getPlayer1().getBattleZone().get(Integer.parseInt(indexes[1])));
							gameEngine.getPlayer1().getBattleZone().remove(Integer.parseInt(indexes[1]));
						}
						//slayer ?
						else if (powerLvlAttackingCard < powerLvlAttackedCard) {
							//response to player1 - his card lost the battle, so it will be moved to graveyard
							response.add("");
							//response to player2 - nothing, his card won the battle
							response.add("MTG");
							
							//save move on the server
							gameEngine.getPlayer2().getGraveyard().add(gameEngine.getPlayer2().getBattleZone().get(Integer.parseInt(indexes[0])));
							gameEngine.getPlayer2().getBattleZone().remove(Integer.parseInt(indexes[0]));
						}
						else {
							//cards have equal power levels, so both will be destroyed
							response.add("MTG");
							response.add("MTG");
							
							//save move on the server
							gameEngine.getPlayer2().getGraveyard().add(gameEngine.getPlayer2().getBattleZone().get(Integer.parseInt(indexes[0])));
							gameEngine.getPlayer2().getBattleZone().remove(Integer.parseInt(indexes[0]));
							gameEngine.getPlayer1().getGraveyard().add(gameEngine.getPlayer1().getBattleZone().get(Integer.parseInt(indexes[1])));
							gameEngine.getPlayer1().getBattleZone().remove(Integer.parseInt(indexes[1]));
						}
						break;
					}
					default : {
						break;
					}
				}
				break;
			}
			case "MoveToMana" : {
				action = action.substring(10);
				action = action.replaceFirst("\\s", "");
				int cardIndex = Integer.parseInt(action);
				currentPlayer.getManaZone().add(currentPlayer.getBattleZone().get(cardIndex));
				currentPlayer.getBattleZone().remove(cardIndex);
				break;
			}
			case "MoveToBattleZone" : {
				action = action.substring(16);
				action = action.replaceFirst("\\s", "");
				int cardIndex = Integer.parseInt(action);
				currentPlayer.getBattleZone().add(currentPlayer.getHand().get(cardIndex));
				currentPlayer.getHand().remove(cardIndex);
				break;
			}
			
			default : {
				break;
			}
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAttackOptions/{player}/{zone}/{index}")
	public ResponseEntity<Object> selectCardGetAttackOptions(@PathVariable String player, @PathVariable String zone, @PathVariable int index){
		String abilityText = gameEngine.getPlayer1().getBattleZone().get(index).getAbility();
		String[] abilityCodes = abilityText.split("\\s+");
		
		ArrayList<String> attackPosibilities = new ArrayList<>();
		
		//search for attacking restrictions (CNAx code type)
		for(String code : abilityCodes) {
			if(code.indexOf("CNA") != -1) {
				//TODO - check type of CNA and fill attackPosibilites array with strings with pattern: zone_index, indicating the cards that can be attacked
			}
			else {
				//no restriction, return a simple message
				attackPosibilities.add("ALL");
			}
		}
		return new ResponseEntity<>(attackPosibilities, HttpStatus.OK);
	}
	
	@GetMapping(value = "/test")
	public ResponseEntity<Object> test() {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
