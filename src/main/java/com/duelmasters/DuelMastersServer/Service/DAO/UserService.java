package com.duelmasters.DuelMastersServer.Service.DAO;

import java.util.List;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.User;

public interface UserService {

	public User createUser(User user);
	
	public User getUser(String id);
	
	public User addCardsToCollection(User user, List<Card> cards);
}
