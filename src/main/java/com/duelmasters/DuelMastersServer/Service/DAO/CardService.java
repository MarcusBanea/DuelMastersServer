package com.duelmasters.DuelMastersServer.Service.DAO;

import java.util.List;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;

public interface CardService {

    public List<Card> getAllCards();
    
    public Card createCard(Card card);
}
