package com.duelmasters.DuelMastersServer.Service.DAO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.duelmasters.DuelMastersServer.Domain.DTO.CardInCollectionDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.GameCard;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.User;

public interface UserService {

	public User createUser(User user);

	public User getUser(String id);
	
	public List<String> openPack(String userId, String packType);
	
	public HashMap<String, Integer> getUserCollection(String id);
	
	public HashMap<String, Integer> getUserCollectionWithNames(String id);
	
	public List<CardInCollectionDTO> getUserCollectionCards(String id);
	
	public List<String> generateRandomDeckFromCollection(String userId, int numberOfCardsInDeck);
	
	public List<GameCard> generateRandomDeckFromCollectionWithGameCards(String userId, int numberOfCardsInDeck) throws IOException;
}
