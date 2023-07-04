package com.duelmasters.DuelMastersServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.duelmasters.DuelMastersServer.Domain.DTO.MapperDTO;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Repository.CardRepository;
import com.duelmasters.DuelMastersServer.Service.Interfaces.CardService;
import com.duelmasters.DuelMastersServer.Service.Interfaces.FileService;
import com.duelmasters.DuelMastersServer.Service.Interfaces.PackService;
import com.duelmasters.DuelMastersServer.Service.Interfaces.UserService;

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
	
	@Autowired
	FileService fileService;
	
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
		
//		User user = userService.getUser("633f18459af2fa78268b91d4");
//		System.out.println(user);
		
		//User user = new User("Markus", "markus@email.com", "TheLegend27", null, 999999999, 1, 0, null, null);
		//userService.createUser(user);
		
//		String userId = "633f18459af2fa78268b91d4";
//		
//		userService.openPack(userId, "Bronze Pack");
//		//user = userService.addCardsToCollection(user, cardsOfPack);
//		//userService.storeCardsFromPack(user, cardsOfPack);
//		
//		userService.openPack(userId, "Gold Premium Pack");
//		//user = userService.addCardsToCollection(user, cardsOfPack);
//		//userService.storeCardsFromPack(user, cardsOfPack);
//		
//		userService.openPack(userId, "Ruby Pack");
//		//user = userService.addCardsToCollection(user, cardsOfPack);
//		//userService.storeCardsFromPack(user, cardsOfPack);
//		
//		UserCollectionDTO user = mapper.userToUserCollectionDTO(userService.getUser(userId));
//		System.out.println(user);
//			
//		System.out.println("Collection:\n");
//		for(String cardId : user.getCollection()) {
//			Card card = cardService.getCard(cardId);
//			System.out.println(card.getName() + " --- " + card.getRarity());
//		}
		
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
	
	@Test
	public void testRemoveUserCardCollection() {
		/*
		String userId = "633f18459af2fa78268b91d4";
		userService.resetUserCollection(userId);
		assertEquals(null, userService.getUserCollection(userId));
		*/
	}
	
	@Test
	public void testGetAbilityDescriptionOfCard() throws IOException {
//		GameCardDTO card = mapper.gameCardToGameCardDTO(mapper.cardToGameCard(cardRepository.findByName("Sniper Mosquito").get()));
//		assertEquals(2000, card.getPower());
//		
//		assertEquals("WTCA33", card.getAbility());
//		assertEquals("Choose0(1, MN0) + SEL:MTH", Abilities.valueOf(card.getAbility()).getAbility());
	}

	
	@Test
	public void testAddImageToCard() throws IOException {
		//Creature card = new Creature();
		
		//card = creatureRepository.findByName("Sniper Mosquito").get();
		
//		ArrayList<String> images = new ArrayList<>();
//		
//		final String imagesDirectoryPath = "M:\\PC2\\Facultate\\Licenta\\DuelMasters\\Cards\\Creature\\Resized";
//		final File directory = new File(imagesDirectoryPath);
//		
//		int ct = 0;
//		
//		for(File file : directory.listFiles()) {
//			String fileName = file.getName().substring(0, file.getName().indexOf("."));
//			images.add(fileName);
//		}
//		
//		for(String imageName : images) {
//			if(!creatureRepository.findByName(imageName).isPresent()) {
//				System.out.println(imageName + " not found!");
//			}
//			ct++;
//		}
		
		
		

//		Card card = new Card();
//		
//		card = cardRepository.findByName("Sniper Mosquito").get();
//		
//		final String imagesDirectoryPath = "M:\\PC2\\Facultate\\Licenta\\DuelMasters\\Cards\\Creature\\Resized";
//		//final File directory = new File(imagesDirectoryPath);
//		File file = new File(imagesDirectoryPath + "\\" + card.getName() + ".jpeg");
//		
//		byte[] imageBytes = Files.readAllBytes(Paths.get(file.getPath()));
//		
//		card.setImageId(fileService.uploadFileFromServer(imageBytes, file.getName(), file.length()));
//		//card.setImageId("test");
//		
//		cardRepository.save(card);
		
		ArrayList<String> images = new ArrayList<>();
		
		final String imagesDirectoryPath = "M:\\PC2\\Facultate\\Licenta\\DuelMasters\\Cards\\Creature\\Resized";
		final File directory = new File(imagesDirectoryPath);
		
		for(File file : directory.listFiles()) {
			String fileName = file.getName().substring(0, file.getName().indexOf("."));
			images.add(fileName);
		}
		
		int ct = 0;
		
		for(String imageName : images) {
			Optional<Card> optionalCard = cardRepository.findByName(imageName);
			if(optionalCard.isPresent()) {
				Card card = optionalCard.get();
				
				card.setImageId(fileService.getFileIdByName(imageName + ".jpeg"));
				
				//File file = new File(imagesDirectoryPath + "\\" + imageName + ".jpeg");
				//byte[] imageBytes = Files.readAllBytes(Paths.get(file.getPath()));
				
				//card.setImageId(fileService.uploadFileFromServer(imageBytes, file.getName(), file.length()));
				
				cardRepository.save(card);
			}
		}
		
		System.out.println(ct);
//		
//		System.out.println(file.getName());
//		System.out.println("found!");
	}
	
	
	@Test
	public void test2() throws IOException {
//		fileService.deleteFile("6336c005d6d6916dce591cd0");
//		fileService.deleteFile("6336cfaed6d6916dce591cd4");
//		fileService.deleteFile("633bedf1d24f7b3fd2e4038a");
//		fileService.deleteFile("633bee44d24f7b3fd2e4038e");
//		fileService.deleteFile("633bee6bd24f7b3fd2e40392");
//		fileService.deleteFile("633bee96d24f7b3fd2e40395");
//		fileService.deleteFile("633beec1d24f7b3fd2e40399");
//		fileService.deleteFile("633bef0cd24f7b3fd2e4039d");
//		fileService.deleteFile("633bef34d24f7b3fd2e403a1");
//		fileService.deleteFile("633bef5fd24f7b3fd2e403a5");
//		fileService.deleteFile("633bef91d24f7b3fd2e403a9");
//		fileService.deleteFile("633befb5d24f7b3fd2e403ad");
//		fileService.deleteFile("633befe0d24f7b3fd2e403b1");
//		fileService.deleteFile("633bf006d24f7b3fd2e403b5");
//		fileService.deleteFile("633bf045d24f7b3fd2e403b9");
//		fileService.deleteFile("633bf065d24f7b3fd2e403bd");
//		fileService.deleteFile("633bf086d24f7b3fd2e403c2");
//		fileService.deleteFile("633bf0a4d24f7b3fd2e403c6");
//		fileService.deleteFile("633bf0c4d24f7b3fd2e403ca");
//		fileService.deleteFile("633bf0e4d24f7b3fd2e403cf");
//		fileService.deleteFile("63ee8ec9e5aba62c84b0eaa9");
//		fileService.deleteFile("63ee907ee5aba62c84b0eaad");
//		fileService.deleteFile("63ee90d3e5aba62c84b0eab1");
//		fileService.deleteFile("63ee911ae5aba62c84b0eab5");
//		fileService.deleteFile("63ee91e2e5aba62c84b0eab9");
//		fileService.deleteFile("63ee922be5aba62c84b0eabd");
//		fileService.deleteFile("63ee929de5aba62c84b0eac0");
//		fileService.deleteFile("63ee92dee5aba62c84b0eac4");
//		fileService.deleteFile("63ee9312e5aba62c84b0eac8");
//		fileService.deleteFile("63ee9344e5aba62c84b0eacc");
//		fileService.deleteFile("63ee937de5aba62c84b0ead0");
//		fileService.deleteFile("63ee93a3e5aba62c84b0ead3");
//		fileService.deleteFile("63ee93c5e5aba62c84b0ead6");
//		fileService.deleteFile("63ee93ede5aba62c84b0eada");
//		fileService.deleteFile("63ee941ee5aba62c84b0eade");
//		fileService.deleteFile("63ee9466e5aba62c84b0eae1");
//		fileService.deleteFile("63ee9483e5aba62c84b0eae5");
//		fileService.deleteFile("63ee94b2e5aba62c84b0eae9");
//		fileService.deleteFile("63ee94fae5aba62c84b0eaed");
//		fileService.deleteFile("63ee951de5aba62c84b0eaf1");
//		fileService.deleteFile("63ee9550e5aba62c84b0eaf4");
//		fileService.deleteFile("63ee95cfe5aba62c84b0eaf8");
//		fileService.deleteFile("63ee95ffe5aba62c84b0eafc");
//		fileService.deleteFile("63ee9620e5aba62c84b0eb00");
//		fileService.deleteFile("63ee967ee5aba62c84b0eb04");
//		fileService.deleteFile("63ee969de5aba62c84b0eb07");
//		fileService.deleteFile("63ee96efe5aba62c84b0eb0b");
//		fileService.deleteFile("63ee9712e5aba62c84b0eb0f");
//		fileService.deleteFile("63ee97b0e5aba62c84b0eb13");
//		fileService.deleteFile("63ee97b1e5aba62c84b0eb17");
//		fileService.deleteFile("63ee97d8e5aba62c84b0eb1b");
//		fileService.deleteFile("63ee9801e5aba62c84b0eb1f");
//		fileService.deleteFile("63ee9827e5aba62c84b0eb23");
//		fileService.deleteFile("63ee9864e5aba62c84b0eb26");
//		fileService.deleteFile("63ee9886e5aba62c84b0eb29");
//		fileService.deleteFile("63ee98a7e5aba62c84b0eb2d");
//		fileService.deleteFile("63ee98cbe5aba62c84b0eb31");
//		fileService.deleteFile("63ee9904e5aba62c84b0eb35");
//		fileService.deleteFile("63ee992ce5aba62c84b0eb39");
//		fileService.deleteFile("63ee9957e5aba62c84b0eb3d");
//		fileService.deleteFile("63ee9977e5aba62c84b0eb41");
//		fileService.deleteFile("63ee999ee5aba62c84b0eb44");
//		fileService.deleteFile("63ee99b7e5aba62c84b0eb48");
//		fileService.deleteFile("63ee99d9e5aba62c84b0eb4c");
//		fileService.deleteFile("63ee9a04e5aba62c84b0eb50");
//		fileService.deleteFile("63ee9a2be5aba62c84b0eb54");
//		fileService.deleteFile("63ee9a4be5aba62c84b0eb58");
//		fileService.deleteFile("64568d993becd735dcc6eb1a");
	}
}
