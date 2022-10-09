package com.duelmasters.DuelMastersServer.Service.Implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Pack;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.User;
import com.duelmasters.DuelMastersServer.Repository.UserRepository;
import com.duelmasters.DuelMastersServer.Service.DAO.PackService;
import com.duelmasters.DuelMastersServer.Service.DAO.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImplementation implements UserService {

	private UserRepository userRepository;
	private PackService packService;

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
	
	public HashMap<String, Integer> getUserCollection(String id) {
		return userRepository.findById(id).get().getCollection();
	}

}
