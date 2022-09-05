package com.duelmasters.DuelMastersServer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.duelmasters.DuelMastersServer.Repository.CardRepository;
import com.duelmasters.DuelMastersServer.Service.DAO.CardService;

@SpringBootTest
class DuelMastersServerApplicationTests {

	@Autowired
	CardService cardService;
	
	@Autowired
	CardRepository cardRepository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testRarityQuery() {
		
		System.out.println("LEGENDARY CARDS\n" + cardService.getAllLegendaryCards());
		System.out.println();
		System.out.println("VERY RARE CARDS\n" + cardService.getAllVeryRareCards());
		System.out.println();
		System.out.println("RARE CARDS\n" + cardService.getAllRareCards());
		System.out.println();
		System.out.println("UNCOMMON CARDS\n" + cardService.getAllUncommonCards());
		System.out.println();
		System.out.println("COMMON CARDS\n" + cardService.getAllCommonCards());
	}

}
