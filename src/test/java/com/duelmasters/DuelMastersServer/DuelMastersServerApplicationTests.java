package com.duelmasters.DuelMastersServer;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Pack;
import com.duelmasters.DuelMastersServer.Repository.CardRepository;
import com.duelmasters.DuelMastersServer.Service.DAO.CardService;
import com.duelmasters.DuelMastersServer.Service.DAO.PackService;

@SpringBootTest
class DuelMastersServerApplicationTests {

	@Autowired
	CardService cardService;
	
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	PackService packService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testRarityQuery() {
		/*
		System.out.println("LEGENDARY CARDS\n" + cardService.getAllLegendaryCards());
		System.out.println();
		System.out.println("VERY RARE CARDS\n" + cardService.getAllVeryRareCards());
		System.out.println();
		System.out.println("RARE CARDS\n" + cardService.getAllRareCards());
		System.out.println();
		System.out.println("UNCOMMON CARDS\n" + cardService.getAllUncommonCards());
		System.out.println();
		System.out.println("COMMON CARDS\n" + cardService.getAllCommonCards());
		*/
		
		/*
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
		Pack bronzePack = new Pack("Bronze Pack", 6, 5, 1, 0, 0, 0, 0);
		List<Card> cards = packService.openPack(bronzePack);
		System.out.println("Bronze Pack:\n" + cards + "\n");
		
		Pack bronzePremiumPack = new Pack("Bronze Premium Pack", 7, 5, 2, 0, 0, 0, 0);
		cards = packService.openPack(bronzePremiumPack);
		System.out.println("Bronze Premium Pack:\n" + cards + "\n");
		
		Pack silverPack = new Pack("Silver Pack", 6, 4, 2, 0, 0, 0, 0);
		cards = packService.openPack(silverPack);
		System.out.println("Silver Pack:\n" + cards + "\n");
		
		Pack silverPremiumPack = new Pack("Silver Premium Pack", 7, 4, 2, 1, 0, 0, 0);
		cards = packService.openPack(silverPremiumPack);
		System.out.println("Silver Premium Pack:\n" + cards + "\n");
		
		Pack goldPack = new Pack("Gold Pack", 6, 3, 1, 2, 0, 0, 0);
		cards = packService.openPack(goldPack);
		System.out.println("Gold Pack:\n" + cards + "\n");
		
		Pack goldPremiumPack = new Pack("Gold Premium Pack", 7, 3, 1, 2, 1, 0, 0);
		cards = packService.openPack(goldPremiumPack);
		System.out.println("Gold Premium Pack:\n" + cards + "\n");
		
		Pack rubyPack = new Pack("Ruby pack", 6, 2, 1, 2, 1, 1, 0);
		cards = packService.openPack(rubyPack);
		System.out.println("Ruby Pack:\n" + cards + "\n");
		
		Pack diamondPack = new Pack("Diamond pack", 6, 1, 1, 1, 2, 2, 0);
		cards = packService.openPack(diamondPack);
		System.out.println("Diamond Pack:\n" + cards + "\n");
		
		Pack shoguPack = new Pack("Shogu pack", 6, 1, 1, 1, 1, 2, 1);
		cards = packService.openPack(shoguPack);
		System.out.println("Shogu Pack:\n" + cards + "\n");
		
		
	}

}
