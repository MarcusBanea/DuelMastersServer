package com.duelmasters.DuelMastersServer.Service.Implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
	public List<Card> openPack(User user, String packType) {
		List<Card> cards = new ArrayList<>();

		Pack pack = packService.getPackByName(packType);
		if (user.getMoney() >= pack.getPrice()) {
			cards = packService.openPack(pack);
			//addCardsToCollection(user, cards);
			user.setMoney(user.getMoney() - pack.getPrice());
			userRepository.save(user);
			return cards;
		}
		//exception
		return cards;
	}
	
	@Override
	public User storeCardsFromPack(User user, List<Card> cards) {
		user = addCardsToCollection(user, cards);
		return user;
	}

	@Override
	public User addCardsToCollection(User user, List<Card> cards) {
		List<Card> currentCollection = user.getCollection();
		if (currentCollection != null) {
			currentCollection.addAll(cards);
			user.setCollection(currentCollection);
		} else {
			user.setCollection(cards);
		}

		Collections.sort(user.getCollection(), new Comparator<Card>() {
			@Override
			public int compare(Card c1, Card c2) {
				if(c1.getRarity().compareTo(c2.getRarity()) != 0) {
					return c1.getRarity().compareTo(c2.getRarity());
				}
				return c1.getName().compareTo(c2.getName());
			}
		});
		user.setCollection(user.getCollection());
		// userRepository.save(user);
		return user;
	}

}
