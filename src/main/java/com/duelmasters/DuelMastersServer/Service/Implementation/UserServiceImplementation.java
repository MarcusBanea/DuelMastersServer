package com.duelmasters.DuelMastersServer.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.User;
import com.duelmasters.DuelMastersServer.Repository.UserRepository;
import com.duelmasters.DuelMastersServer.Service.DAO.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImplementation implements UserService {

	private UserRepository userRepository;

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
	public User addCardsToCollection(User user, List<Card> cards) {
		List<Card> currentCollection = user.getCollection();
		if (currentCollection != null) {
			currentCollection.addAll(cards);
			user.setCollection(currentCollection);
		}
		else {
			user.setCollection(cards);
		}
		return user;
	}

}
