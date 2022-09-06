package com.duelmasters.DuelMastersServer.Service.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Pack;
import com.duelmasters.DuelMastersServer.Repository.PackRepository;
import com.duelmasters.DuelMastersServer.Service.DAO.CardService;
import com.duelmasters.DuelMastersServer.Service.DAO.PackService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PackServiceImplementation implements PackService {

	private PackRepository packRepository;
	CardService cardService;

	@Override
	public Pack createPack(Pack pack) {
		packRepository.save(pack);
		return pack;
	}

	@Override
	public List<Pack> getAllPacks() {
		return packRepository.findAll();
	}

	@Override
	public List<Card> openPack(Pack pack) {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < pack.getNumberOfCommonCards(); i++) {
			cards.add(cardService.getRandomCommonCard());
		}
		for (int i = 0; i < pack.getNumberOfUncommonCards(); i++) {
			cards.add(cardService.getRandomUncommonCard());
		}
		for (int i = 0; i < pack.getNumberOfRareCards(); i++) {
			cards.add(cardService.getRandomRareCard());
		}
		for (int i = 0; i < pack.getNumberOfVeryRareCards(); i++) {
			cards.add(cardService.getRandomVeryRareCard());
		}
		for (int i = 0; i < pack.getNumberOfSuperRareCards(); i++) {
			cards.add(cardService.getRandomSuperRareCard());
		}
		for (int i = 0; i < pack.getNumberOfLegendaryCards(); i++) {
			cards.add(cardService.getRandomLegendaryCard());
		}
		return cards;
	}

	@Override
	public List<Card> openPackType(String packType) {
		List<Card> cards = new ArrayList<>();
		switch (packType) {
		case "BronzePack": {
			Pack bronzePack = new Pack("Bronze Pack", 6, 5, 1, 0, 0, 0, 0, null);
			cards = openPack(bronzePack);
			break;
		}
		case "BronzePremiumPack": {
			Pack bronzePremiumPack = new Pack("Bronze Premium Pack", 7, 5, 2, 0, 0, 0, 0, null);
			cards = openPack(bronzePremiumPack);
			break;
		}
		case "SilverPack": {
			Pack silverPack = new Pack("Silver Pack", 6, 4, 2, 0, 0, 0, 0, null);
			cards = openPack(silverPack);
			break;
		}
		case "SilverPremiumPack": {
			Pack silverPremiumPack = new Pack("Silver Premium Pack", 7, 4, 2, 1, 0, 0, 0, null);
			cards = openPack(silverPremiumPack);
			break;
		}
		case "GoldPack": {
			Pack goldPack = new Pack("Gold Pack", 6, 3, 1, 2, 0, 0, 0, null);
			cards = openPack(goldPack);
			break;
		}
		case "GoldPremiumPack": {
			Pack goldPremiumPack = new Pack("Gold Premium Pack", 7, 3, 1, 2, 1, 0, 0, null);
			cards = openPack(goldPremiumPack);
			break;
		}
		case "PlatinumPack": {
			Pack goldPremiumPack = new Pack("Gold Premium Pack", 7, 3, 1, 2, 1, 0, 0, null);
			cards = openPack(goldPremiumPack);
			break;
		}
		case "RubyPack": {
			Pack rubyPack = new Pack("Ruby pack", 6, 2, 1, 2, 1, 1, 0, null);
			cards = openPack(rubyPack);
			break;
		}
		case "DiamondPack": {
			Pack diamondPack = new Pack("Diamond pack", 6, 1, 1, 1, 2, 2, 0, null);
			cards = openPack(diamondPack);
			break;
		}
		case "ShoguPack": {
			Pack shoguPack = new Pack("Shogu pack", 6, 1, 1, 1, 1, 2, 1, null);
			cards = openPack(shoguPack);
			break;
		}
		
		}
		return cards;
	}

}
