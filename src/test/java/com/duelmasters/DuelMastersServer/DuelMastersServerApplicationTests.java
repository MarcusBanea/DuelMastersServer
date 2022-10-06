package com.duelmasters.DuelMastersServer;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.duelmasters.DuelMastersServer.Domain.DTO.MapperDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.UserCollectionDTO;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.User;
import com.duelmasters.DuelMastersServer.Repository.CardRepository;
import com.duelmasters.DuelMastersServer.Service.DAO.CardService;
import com.duelmasters.DuelMastersServer.Service.DAO.PackService;
import com.duelmasters.DuelMastersServer.Service.DAO.UserService;

@SpringBootTest
class DuelMastersServerApplicationTests {

	@Autowired
	CardService cardService;
	
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	PackService packService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MapperDTO mapper;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testRarityQuery() {
		/*
		System.out.println("LEGENDARY CARDS\n" + cardService.getAllLegendaryCards());
		System.out.println();
		System.out.println("SUPER RARE CARDS\n" + cardService.getAllSuperRareCards());
		System.out.println();
		System.out.println("VERY RARE CARDS\n" + cardService.getAllVeryRareCards());
		System.out.println();
		System.out.println("RARE CARDS\n" + cardService.getAllRareCards());
		System.out.println();
		System.out.println("UNCOMMON CARDS\n" + cardService.getAllUncommonCards());
		System.out.println();
		System.out.println("COMMON CARDS\n" + cardService.getAllCommonCards());
		
		
		
		System.out.println("Random LEGENDARY card:\n" + cardService.getRandomLegendaryCard());
		System.out.println();
		System.out.println("Random SUPER RARE card:\n" + cardService.getRandomSuperRareCard());
		System.out.println();
		System.out.println("Random VERY RARE card:\n" + cardService.getRandomVeryRareCard());
		System.out.println();
		System.out.println("Random RARE card:\n" + cardService.getRandomRareCard());
		System.out.println();
		System.out.println("Random UNCOMMON card:\n" + cardService.getRandomUncommonCard());
		System.out.println();
		System.out.println("Random COMMON card:\n" + cardService.getRandomCommonCard());
		System.out.println();
		*/
		
	}
	
	@Test
	void testPackOpening() {
		/*
		Pack bronzePack = new Pack("Bronze Pack", 6, 5, 1, 0, 0, 0, 0, null);
		List<Card> cards = packService.openPack(bronzePack);
		System.out.println("Bronze Pack:\n" + cards + "\n");
		
		Pack bronzePremiumPack = new Pack("Bronze Premium Pack", 7, 5, 2, 0, 0, 0, 0, null);
		cards = packService.openPack(bronzePremiumPack);
		System.out.println("Bronze Premium Pack:\n" + cards + "\n");
		
		Pack silverPack = new Pack("Silver Pack", 6, 4, 2, 0, 0, 0, 0, null);
		cards = packService.openPack(silverPack);
		System.out.println("Silver Pack:\n" + cards + "\n");
		
		Pack silverPremiumPack = new Pack("Silver Premium Pack", 7, 4, 2, 1, 0, 0, 0, null);
		cards = packService.openPack(silverPremiumPack);
		System.out.println("Silver Premium Pack:\n" + cards + "\n");
		
		Pack goldPack = new Pack("Gold Pack", 6, 3, 1, 2, 0, 0, 0, null);
		cards = packService.openPack(goldPack);
		System.out.println("Gold Pack:\n" + cards + "\n");
		
		Pack goldPremiumPack = new Pack("Gold Premium Pack", 7, 3, 1, 2, 1, 0, 0, null);
		cards = packService.openPack(goldPremiumPack);
		System.out.println("Gold Premium Pack:\n" + cards + "\n");
		
		Pack rubyPack = new Pack("Ruby pack", 6, 2, 1, 2, 1, 1, 0, null);
		cards = packService.openPack(rubyPack);
		System.out.println("Ruby Pack:\n" + cards + "\n");
		
		Pack diamondPack = new Pack("Diamond pack", 6, 1, 1, 1, 2, 2, 0, null);
		cards = packService.openPack(diamondPack);
		System.out.println("Diamond Pack:\n" + cards + "\n");
		
		Pack shoguPack = new Pack("Shogu pack", 6, 1, 1, 1, 1, 2, 1, null);
		cards = packService.openPack(shoguPack);
		System.out.println("Shogu Pack:\n" + cards + "\n");
		*/
		
	}
	
	@Test
	void testUser() {
//		
//		// user = new User("Markus", "markus@email.com", "TheLegend27", null, 10000, 1, 0, null, null);
//		//userService.createUser(user);
//		
//		
//		User user = userService.getUser("631b10bc5f56771e8167ee17");
//		System.out.println(user);
//		
////		user = userService.openPack(user, "Bronze Pack");
////		user = userService.openPack(user, "Gold Pack");
////		System.out.println(user);
//		
//		
//		
//		List<Card> cardsOfPack = packService.openPackType("Bronze Pack");
//		user = userService.addCardsToCollection(user, cardsOfPack);
//		
////		cardsOfPack = packService.openPackType("Gold Premium Pack");
////		user = userService.addCardsToCollection(user, cardsOfPack);
////		
////		cardsOfPack = packService.openPackType("Ruby Pack");
////		user = userService.addCardsToCollection(user, cardsOfPack);
//		
//		UserCollectionDTO userDTO = mapper.userToUserCollectionDTO(user);
//	
//		System.out.println("Collection:\n");
//		for(String cardId : userDTO.getCollection()) {
//			Card card = cardService.getCard(cardId);
//			System.out.println(card.getName() + " --- " + card.getRarity());
//		}
//		
//		System.out.println(userService.getUser("6319e0650ba2f85a47f317a1").getCollection());
//		

	}
	
	@Test
	void testUpdateCard() {
		/*
		Card cardToUpdate = cardService.getCard("6314fa39ded3210745c2e850");
		System.out.println(cardToUpdate);
		Card newCardData = new Card(null, null, null, null, null, null, null, null, "Common", null);
		cardService.updateCard("6314fa39ded3210745c2e850", newCardData);
		cardToUpdate = cardService.getCard("6314fa39ded3210745c2e850");
		System.out.println(cardToUpdate);
		*/
		
	}
	
	@Test
	void testUpdateAllCardsRarity() {
		/*
		List<Card> cards = cardService.getAllCards();
		for(Card card : cards) {
			switch(card.getRarity()) {
				case "0": {
					//System.out.println("Common: ->  " + card + "\n");
					Card newCardData = new Card(null, null, null, null, null, null, null, null, "Common", null);
					cardService.updateCard(card.getId(), newCardData);
					break;
				}
				case "30": {
					//System.out.println("Uncommon: ->  " + card + "\n");
					Card newCardData = new Card(null, null, null, null, null, null, null, null, "Uncommon", null);
					cardService.updateCard(card.getId(), newCardData);
					break;
				}
				case "50": {
					//System.out.println("Rare: ->  " + card + "\n");
					Card newCardData = new Card(null, null, null, null, null, null, null, null, "Rare", null);
					cardService.updateCard(card.getId(), newCardData);
					break;
				}
				case "70": {
					//System.out.println("Very Rare: ->  " + card + "\n");
					Card newCardData = new Card(null, null, null, null, null, null, null, null, "VeryRare", null);
					cardService.updateCard(card.getId(), newCardData);
					break;
				}
				case "90": {
					//System.out.println("Super Rare: ->  " + card + "\n");
					Card newCardData = new Card(null, null, null, null, null, null, null, null, "SuperRare", null);
					cardService.updateCard(card.getId(), newCardData);
					break;
				}
				case "100": {
					//System.out.println("Legendary: ->  " + card + "\n");
					Card newCardData = new Card(null, null, null, null, null, null, null, null, "Legendary", null);
					cardService.updateCard(card.getId(), newCardData);
					break;
				}
			}
		}
		cards = cardService.getAllCards();
		for(Card card : cards) {
			System.out.println(card);
		}
		*/
	}

}
