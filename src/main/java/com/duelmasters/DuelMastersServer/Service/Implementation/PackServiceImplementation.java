package com.duelmasters.DuelMastersServer.Service.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Pack;
import com.duelmasters.DuelMastersServer.Service.DAO.CardService;
import com.duelmasters.DuelMastersServer.Service.DAO.PackService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PackServiceImplementation implements PackService {

	CardService cardService;

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

}
