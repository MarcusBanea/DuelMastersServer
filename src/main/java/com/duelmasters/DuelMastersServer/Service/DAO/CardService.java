package com.duelmasters.DuelMastersServer.Service.DAO;

import java.util.List;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;

public interface CardService {

    public List<Card> getAllCards();
    
    public Card createCard(Card card);
    
    public Card getRandomCard(Long maxRarity);
    
    public List<Card> getAllLegendaryCards();
    
    public List<Card> getAllSuperRareCards();
    
    public List<Card> getAllVeryRareCards();
    
    public List<Card> getAllRareCards();
    
    public List<Card> getAllUncommonCards();
    
    public List<Card> getAllCommonCards();
}
