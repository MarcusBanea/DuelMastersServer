package com.duelmasters.DuelMastersServer.Service.Implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.duelmasters.DuelMastersServer.Domain.DTO.CardInCollectionDTO;
import com.duelmasters.DuelMastersServer.Domain.DTO.GameCard;
import com.duelmasters.DuelMastersServer.Domain.DTO.MapperDTO;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Pack;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.User;
import com.duelmasters.DuelMastersServer.Repository.CardRepository;
import com.duelmasters.DuelMastersServer.Repository.UserRepository;
import com.duelmasters.DuelMastersServer.Service.Interfaces.PackService;
import com.duelmasters.DuelMastersServer.Service.Interfaces.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImplementation implements UserService {

	private UserRepository userRepository;
	private PackService packService;
	private CardRepository cardRepository;
	private MapperDTO mapper;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(String id) {
		Optional<User> possibleUser = userRepository.findById(id);
		if (possibleUser.isPresent()) {
			return possibleUser.get();
		} else {
			return null;
		}
	}

	@Override
	public List<String> openPack(String userId, String packType) {
		User user = userRepository.findById(userId).get();
		Pack pack = packService.getPackByName(packType);

		if (user.getMoney() >= pack.getPrice()) {
			user.setMoney(user.getMoney() - pack.getPrice());

			List<String> cards = new ArrayList<>();
			cards = packService.openPack(pack).stream().map(Card::getId).collect(Collectors.toList());

			user = addCardsToCollection(user, cards);
			userRepository.save(user);
			return cards;

		}
		// exception
		return null;
	}

	//@Override
	private User addCardsToCollection(User user, List<String> cardsIds) {
		HashMap<String, Integer> currentCollection = user.getCollection();
		if (currentCollection != null) {
			for (String cardId : cardsIds) {
				currentCollection.merge(cardId, 1, Integer::sum);
			}
			user.setCollection(currentCollection);
		} else {
			currentCollection = new HashMap<>();
			for (String cardId : cardsIds) {
				if (currentCollection.containsKey(cardId)) {
					currentCollection.replace(cardId, currentCollection.get(cardId) + 1);
				} else {
					currentCollection.put(cardId, 1);
				}
			}
			user.setCollection(currentCollection);
		}
		return user;
	}
	
	@Override
	public void resetUserCollection(String id) {
		User user = userRepository.findById(id).get();
		user.setCollection(null);
		userRepository.save(user);
	}
	
	@Override
	public HashMap<String, Integer> getUserCollection(String id) {
		return userRepository.findById(id).get().getCollection();
	}
	
	@Override
	public HashMap<String, Integer> getUserCollectionWithNames(String id) {
		HashMap<String, Integer> collectionWithNames = new HashMap<>();
		for(Map.Entry<String, Integer> card : userRepository.findById(id).get().getCollection().entrySet()) {
			collectionWithNames.put(cardRepository.findById(card.getKey()).get().getName(), card.getValue());
		}
		return collectionWithNames;
	}
	
	@Override
	public List<CardInCollectionDTO> getUserCollectionCards(String id) {
		List<CardInCollectionDTO> collectionWithNames = new ArrayList<>();
		User user = userRepository.findById(id).get();
		for(Map.Entry<String, Integer> card : user.getCollection().entrySet()) {
			Card cardInCollection = cardRepository.findById(card.getKey()).get();
			collectionWithNames.add(new CardInCollectionDTO(cardInCollection.getName(), cardInCollection.getImageId(), card.getValue()));
		}
		return collectionWithNames;
	}
	
	@Override
	public List<String> generateRandomDeckFromCollection(String userId, int numberOfCardsInDeck) {
		
		List<String> deck = new ArrayList<>();
		HashMap<String, Integer> userCollection = userRepository.findById(userId).get().getCollection();
		
		Random random = new Random();
		
		List<String> cardIds = new ArrayList<String>(userCollection.keySet());
		while(deck.size() < numberOfCardsInDeck) {
			String randomCardId = cardIds.get(random.nextInt(cardIds.size()));
			deck.add(randomCardId);
			int randomCardDuplicates = userCollection.get(randomCardId);
			if(randomCardDuplicates - 1 != 0) {
				userCollection.replace(randomCardId, randomCardDuplicates - 1);
			}
			else {
				userCollection.remove(randomCardId);
			}
		}
		
		return deck;
	}
	
	@Override
	public List<GameCard> generateRandomDeckFromCollectionWithGameCards(String userId, int numberOfCardsInDeck) throws IOException {
		
		List<GameCard> deck = new ArrayList<>();
		HashMap<String, Integer> userCollection = userRepository.findById(userId).get().getCollection();
		
		Random random = new Random();
		
		//DEMO
		
				List<String> cardIds = new ArrayList<>(Arrays.asList(
						"6456969b52f47c0df28cb54a",
						"6456969b52f47c0df28cb565",
						"6456969b52f47c0df28cb56f",
						"6456969b52f47c0df28cb58b",
						"6456969b52f47c0df28cb598",
						//"6456969b52f47c0df28cb5b8",
						"6456969b52f47c0df28cb5cf",
						"6456969b52f47c0df28cb5e0",
						"6456969b52f47c0df28cb602",
						"6456969b52f47c0df28cb60b",
						"6456969b52f47c0df28cb60e",
						"6456969b52f47c0df28cb615",
						"6456969b52f47c0df28cb61e",
						"6456969b52f47c0df28cb623",
						"6456969b52f47c0df28cb633",
						"6456969b52f47c0df28cb640",
						"6456969b52f47c0df28cb641",
						"6456969b52f47c0df28cb646",
						"6456969b52f47c0df28cb657",
						"6456969b52f47c0df28cb665",
						"6456969b52f47c0df28cb661",
						"6456969b52f47c0df28cb669",
						"6456969b52f47c0df28cb68d",
						"6456969b52f47c0df28cb6b7",
						"6456969b52f47c0df28cb6b9",
						"6456969b52f47c0df28cb6cc",
						"6456969b52f47c0df28cb6cf",
						"6456969b52f47c0df28cb6e7",
						"6456969b52f47c0df28cb6ef"
						));
				
				
				//
		
		//List<String> cardIds = new ArrayList<String>(userCollection.keySet());
		List<String> usedCardIds = new ArrayList<>();
		
		while(deck.size() < numberOfCardsInDeck) {
			String randomCardId = cardIds.get(random.nextInt(cardIds.size()));
			
			//if a card with this id was already brought from db
			//don't bring it again, use the object already saved in deck
			if(usedCardIds.contains(randomCardId)) {
				deck.add(deck.get(usedCardIds.indexOf(randomCardId)));
			}
			else {
				Card gameCard = cardRepository.findById(randomCardId).get();
				deck.add(mapper.cardToGameCard(gameCard));
				usedCardIds.add(randomCardId);
			}
			
			int randomCardDuplicates = userCollection.get(randomCardId);
			if(randomCardDuplicates - 1 != 0) {
				userCollection.replace(randomCardId, randomCardDuplicates - 1);
			}
			else {
				userCollection.remove(randomCardId);
				cardIds.remove(randomCardId);
			}
		}
		
		return deck;
		
	}

}
