package com.duelmasters.DuelMastersServer.Service.DAO;


import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Repository.CardRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface CardService {

    public List<Card> getAllCards();
    
    public Card createCard(Card card);
}
