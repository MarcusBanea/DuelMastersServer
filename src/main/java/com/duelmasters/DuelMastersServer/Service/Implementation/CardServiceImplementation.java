package com.duelmasters.DuelMastersServer.Service.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Repository.CardRepository;
import com.duelmasters.DuelMastersServer.Service.DAO.CardService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CardServiceImplementation implements CardService {

	private CardRepository cardRepository;

	@Override
	public List<Card> getAllCards() {
		return cardRepository.findAll();
	}

	@Override
	public Card createCard(Card card) {
		cardRepository.save(card);
		return card;
	}

	public List<Card> getAllLegendaryCards() {
		return cardRepository.findCardsWithExactRarity(100L);
	}
	
	public List<Card> getAllSuperRareCards() {
		return cardRepository.findCardsWithExactRarity(90L);
	}

	public List<Card> getAllVeryRareCards() {
		return cardRepository.findCardsWithExactRarity(70L);
	}
	
	public List<Card> getAllRareCards() {
		return cardRepository.findCardsWithExactRarity(50L);
	}
	
	public List<Card> getAllUncommonCards() {
		return cardRepository.findCardsWithExactRarity(30L);
	}
	
	public List<Card> getAllCommonCards() {
		return cardRepository.findCardsWithExactRarity(0L);
	}

	@Override
	public Card getRandomCardWithMaxRarity(Long rarity) {
		return cardRepository.getRandomCardWithMaxRarity(rarity);
	}

	
	@Override
	public Card getRandomCommonCard() {
		return cardRepository.getRandomCardWithExactRarity(0L);
	}

	
	@Override
	public Card getRandomUncommonCard() {
		return cardRepository.getRandomCardWithExactRarity(30L);
	}

	
	@Override
	public Card getRandomRareCard() {
		return cardRepository.getRandomCardWithExactRarity(50L);
	}

	
	@Override
	public Card getRandomVeryRareCard() {
		return cardRepository.getRandomCardWithExactRarity(70L);
	}

	
	@Override
	public Card getRandomSuperRareCard() {
		return cardRepository.getRandomCardWithExactRarity(90L);
	}

	@Override
	public Card getRandomLegendaryCard() {
		return cardRepository.getRandomCardWithExactRarity(100L);
	}

}
