package com.duelmasters.DuelMastersServer.Domain.DTO;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.User;

@Component
public class MapperDTO {
	
	public CardDTO cardToCardDTO(Card card) {
		ModelMapper modelMapper = new ModelMapper();
		CardDTO cardDTO = modelMapper.map(card, CardDTO.class);
		return cardDTO;
	}
	
	public CardWithImageDTO cardToCardWithImageDTO(Card card) {
		ModelMapper modelMapper = new ModelMapper();
		CardWithImageDTO cardDTO = modelMapper.map(card, CardWithImageDTO.class);
		return cardDTO;
	}

	public UserDTO userToUserDTO(User user) {
		ModelMapper modelMapper = new ModelMapper();
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}
	
	public UserCollectionDTO userToUserCollectionDTO(User user) {
		ModelMapper modelMapper = new ModelMapper();
		UserCollectionDTO userDTO = modelMapper.map(user, UserCollectionDTO.class);
		return userDTO;
	}
	
	
	public User userDTOtoUser(UserDTO userDTO) {
		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(userDTO, User.class);
		return user;
	}
	
	public User userCollectionDTOtoUser(UserCollectionDTO userDTO) {
		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(userDTO, User.class);
		return user;
	}

}
