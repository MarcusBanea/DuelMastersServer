package com.duelmasters.DuelMastersServer.Service.Interfaces;

import java.io.IOException;
import java.util.List;

import com.duelmasters.DuelMastersServer.Domain.DTO.CardWithImageDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.GameCard;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;

public interface CardService {
	
	public Card getCard(String id);
	
	public Card getCardByName(String name);
	
	public CardWithImageDTO getCardWithImage(String id) throws IOException;
	
	public GameCard getGameCard(String id) throws IOException;

    public List<Card> getAllCards();
    
    public Card createCard(Card card);
        
    public List<Card> getAllLegendaryCards();
    
    public List<Card> getAllSuperRareCards();
    
    public List<Card> getAllVeryRareCards();
    
    public List<Card> getAllRareCards();
    
    public List<Card> getAllUncommonCards();
    
    public List<Card> getAllCommonCards();
    
    public Card getRandomCommonCard();
    
    public Card getRandomUncommonCard();
    
    public Card getRandomRareCard();
    
    public Card getRandomVeryRareCard();
    
    public Card getRandomSuperRareCard();
    
    public Card getRandomLegendaryCard();
    
    public Card getRandomCard();
    
    public Card updateCard(String cardId, Card newCard);
    
    public void deleteCard(String id);
}
