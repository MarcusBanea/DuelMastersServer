package com.duelmasters.DuelMastersServer.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duelmasters.DuelMastersServer.Domain.Player;
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
		
		List<GameCard> player1HandCards = new ArrayList<>();
		List<GameCard> player1Shields = new ArrayList<>();
		
		List<GameCard> player2HandCards = new ArrayList<>();
		List<GameCard> player2Shields = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) {
			player1HandCards.add(player1DeckCards.get(i));
			player1Shields.add(player1DeckCards.get(i + 5));
			player2HandCards.add(player2DeckCards.get(i));
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
		player1.setBattleZone(new ArrayList<>());
		player1.setManaZone(new ArrayList<>());
		player1.setGraveyard(new ArrayList<>());
		
		player2.setDeck((ArrayList) player1DeckCards);
		player2.setHand((ArrayList) player1HandCards);
		player2.setShields((ArrayList) player1Shields);
		player2.setBattleZone(new ArrayList<>());
		player2.setManaZone(new ArrayList<>());
		player2.setGraveyard(new ArrayList<>());
		
		gameEngine.setPlayer1(mapper.playerToPlayerDTO(player1));
		gameEngine.setPlayer2(mapper.playerToPlayerDTO(player2));
		
		return new ResponseEntity<>(Arrays.asList(player1, player2), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/action/{action}/{player}")
	public void executeAction(@PathVariable String action, @PathVariable String player) {
		switch(action) {
			case "Draw card" : {
				gameEngine.drawCard(player);
				break;
			}
			default : {
				break;
			}
		}
	}
	
	@GetMapping(value = "/test")
	public ResponseEntity<Object> test() {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
