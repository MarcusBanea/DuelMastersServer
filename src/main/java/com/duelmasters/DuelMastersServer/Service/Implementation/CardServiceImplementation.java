package com.duelmasters.DuelMastersServer.Service.Implementation;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.duelmasters.DuelMastersServer.Domain.DTO.CardWithImageDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.MapperDTO;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Repository.CardRepository;
import com.duelmasters.DuelMastersServer.Service.DAO.CardService;
import com.duelmasters.DuelMastersServer.Service.DAO.FileService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CardServiceImplementation implements CardService {

	private CardRepository cardRepository;
	private MapperDTO mapper;
	private FileService fileService;
	
	@Override
	public Card getCard(String id) {
		return cardRepository.findById(id).get();
	}
	
	@Override
	public CardWithImageDTO getCardWithImage(String id) throws IOException {
		Card card = cardRepository.findById(id).get();
		CardWithImageDTO cardDTO = mapper.cardToCardWithImageDTO(card);
		cardDTO.setImageBytes(fileService.downloadFile(card.getImageId()).getContent());
		return cardDTO;
	}

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
		return cardRepository.findCardsWithExactRarity("Legendary");
	}
	
	public List<Card> getAllSuperRareCards() {
		return cardRepository.findCardsWithExactRarity("SuperRare");
	}

	public List<Card> getAllVeryRareCards() {
		return cardRepository.findCardsWithExactRarity("VeryRare");
	}
	
	public List<Card> getAllRareCards() {
		return cardRepository.findCardsWithExactRarity("Rare");
	}
	
	public List<Card> getAllUncommonCards() {
		return cardRepository.findCardsWithExactRarity("Uncommon");
	}
	
	public List<Card> getAllCommonCards() {
		return cardRepository.findCardsWithExactRarity("Common");
	}

	
	@Override
	public Card getRandomCommonCard() {
		return cardRepository.getRandomCardWithExactRarity("Common");
	}

	
	@Override
	public Card getRandomUncommonCard() {
		return cardRepository.getRandomCardWithExactRarity("Uncommon");
	}

	
	@Override
	public Card getRandomRareCard() {
		return cardRepository.getRandomCardWithExactRarity("Rare");
	}

	
	@Override
	public Card getRandomVeryRareCard() {
		return cardRepository.getRandomCardWithExactRarity("VeryRare");
	}

	
	@Override
	public Card getRandomSuperRareCard() {
		return cardRepository.getRandomCardWithExactRarity("SuperRare");
	}

	@Override
	public Card getRandomLegendaryCard() {
		return cardRepository.getRandomCardWithExactRarity("Legendary");
	}

	@Override
	public Card updateCard(String cardId, Card newCard) {
		Card card = cardRepository.findById(cardId).get();
		if(newCard.getName() != null) {
			card.setName(newCard.getName());
		}
		if(newCard.getCardRealm() != null) {
			card.setCardRealm(newCard.getCardRealm());
		}
		if(newCard.getCardClass() != null) {
			card.setCardClass(newCard.getCardClass());
		}
		if(newCard.getMana() != null) {
			card.setMana(newCard.getMana());
		}
		if(newCard.getPower() != null) {
			card.setPower(newCard.getPower());
		}
		if(newCard.getBreakerNumber() != null) {
			card.setBreakerNumber(newCard.getBreakerNumber());
		}
		if(newCard.getType() != null) {
			card.setType(newCard.getType());
		}
		if(newCard.getAbility() != null) {
			card.setAbility(newCard.getAbility());
		}
		if(newCard.getRarity() != null) {
			card.setRarity(newCard.getRarity());
		}
		if(newCard.getImageId() != null) {
			card.setImageId(newCard.getImageId());
		}
		cardRepository.save(card);
		return card;
	}
	
	public Card getRandomCard() {
		return cardRepository.getRandomCard();
	}
	
	public void deleteCard(String id) {
		cardRepository.deleteById(id);
	}

}
