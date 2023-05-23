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

import com.duelmasters.DuelMastersServer.Domain.ActionCard;
import com.duelmasters.DuelMastersServer.Domain.Aftermath;
import com.duelmasters.DuelMastersServer.Domain.Player;
import com.duelmasters.DuelMastersServer.Domain.PlayerDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.GameCard;
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
		
		Collections.sort(player1DeckCards, (c1, c2) -> Integer.parseInt(c1.getMana()) - Integer.parseInt(c2.getMana()));
		Collections.sort(player2DeckCards, (c1, c2) -> Integer.parseInt(c1.getMana()) - Integer.parseInt(c2.getMana()));
		
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
		player1HandCards.add(player1DeckCards.get(3));
		player1HandCards.add(player1DeckCards.get(4));
		player1HandCards.add(player1DeckCards.get(5));
		player1ManaCards.add(player1DeckCards.get(6));
		player1ManaCards.add(player1DeckCards.get(7));
		player1ManaCards.add(player1DeckCards.get(8));
		player1ManaCards.add(player1DeckCards.get(9));
		player1ManaCards.add(player1DeckCards.get(10));
		
		player2HandCards.add(player2DeckCards.get(0));
		player2HandCards.add(player2DeckCards.get(1));
		player2HandCards.add(player2DeckCards.get(2));
		player2HandCards.add(player2DeckCards.get(3));
		player2HandCards.add(player2DeckCards.get(4));
		player2BattleZoneCards.add(player2DeckCards.get(5));
		player2ManaCards.add(player2DeckCards.get(6));
		player2ManaCards.add(player2DeckCards.get(7));
		player2ManaCards.add(player2DeckCards.get(8));
		player2ManaCards.add(player2DeckCards.get(9));
		player2ManaCards.add(player2DeckCards.get(10));
		
		for(int i = 11; i <= 15; i++) {
			player1Shields.add(player1DeckCards.get(player1DeckCards.size() - i));
			player2Shields.add(player2DeckCards.get(player2DeckCards.size() - i));
			
			
			GameCard temp = player1Shields.get(i - 11);
			player1Shields.set(i - 11, player1HandCards.get(i - 11));
			player1HandCards.set(i - 11, temp);
		}
		
		
		for(int i = 0 ; i < 13; i++) {
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
	
	@GetMapping(value = "/2cardAction/{indexCard1}/{zoneCard1}/{playerCard1}/{indexCard2}/{zoneCard2}/{playerCard2}/{action}")
	public ResponseEntity<Object> execute2CardAction(@PathVariable Integer indexCard1,
			@PathVariable String zoneCard1, @PathVariable String playerCard1, @PathVariable Integer indexCard2,
			@PathVariable String zoneCard2, @PathVariable String playerCard2, @PathVariable String action) {
		
		ActionCard actionCard1 = new ActionCard(indexCard1, zoneCard1, playerCard1);
		ActionCard actionCard2 = new ActionCard(indexCard2, zoneCard2, playerCard2);
		
		Aftermath aftermath = gameEngine.execute2CardTypeAction(actionCard1, actionCard2, action);
		
		return new ResponseEntity<>(aftermath, HttpStatus.OK);
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
			case "Attack": {
				//TODO
				gameEngine.execute2CardTypeAction(new ActionCard(null, actionBegin, player), null, "attack");
								
				break;
			}
			case "MoveToMana" : {
				action = action.substring(10);
				action = action.replaceFirst("\\s", "");
				int cardIndex = Integer.parseInt(action);
				gameEngine.moveCard(player, "hand", "manaZone", cardIndex);
				break;
			}
			case "MoveToBattleZone" : {
				action = action.substring(16);
				action = action.replaceFirst("\\s", "");
				int cardIndex = Integer.parseInt(action);
				gameEngine.moveCard(player, "hand", "battleZone", cardIndex);
				break;
			}
			
			default : {
				break;
			}
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/act")
	public ResponseEntity<Object> executeAction(@PathVariable String player, @PathVariable String actionType,
			@PathVariable String[] zones, @PathVariable String[] indexes) {

		ArrayList<String> response = new ArrayList<>();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAttackOptions/{player}/{zone}/{index}")
	public ResponseEntity<Object> selectCardGetAttackOptions(@PathVariable String player, @PathVariable String zone,
			@PathVariable int index) {
		return new ResponseEntity<>(gameEngine.getAttackOptionsForCard(player, zone, index), HttpStatus.OK);
	}
	
	@GetMapping(value = "/moveCard/{player}/{zoneFrom}/{zoneTo}/{index}")
	public ResponseEntity<Object> moveCard(@PathVariable String player, @PathVariable String zoneFrom, @PathVariable String zoneTo,
			@PathVariable int index){
		
		return new ResponseEntity<>(gameEngine.moveCard(player, zoneFrom, zoneTo, index), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/test")
	public ResponseEntity<Object> test() {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
