package com.duelmasters.DuelMastersServer.Service.Implementation;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Repository.CardRepository;
import com.duelmasters.DuelMastersServer.Service.DAO.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
