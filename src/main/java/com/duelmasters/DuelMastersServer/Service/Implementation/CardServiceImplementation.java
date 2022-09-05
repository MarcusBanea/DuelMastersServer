package com.duelmasters.DuelMastersServer.Service.Implementation;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	public Card getRandomCard(Long maxRarity) {
		Long numberOfCards = cardRepository.countByRarity(maxRarity);
		int randomCardIndex = (int) (Math.random() * numberOfCards);
		Pageable card = PageRequest.of(randomCardIndex, 1);
		Card randomCard = cardRepository.findAll(card).getContent().get(0);
		return randomCard;
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
}
