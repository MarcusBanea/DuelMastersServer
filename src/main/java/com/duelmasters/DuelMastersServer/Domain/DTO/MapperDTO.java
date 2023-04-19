package com.duelmasters.DuelMastersServer.Domain.DTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.duelmasters.DuelMastersServer.Domain.Player;
import com.duelmasters.DuelMastersServer.Domain.PlayerDTO;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.User;
import com.duelmasters.DuelMastersServer.Service.DAO.FileService;

@Component
public class MapperDTO {
	
	@Autowired
	private FileService fileService;
	
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
	
	public GameCard cardToGameCard(Card card) throws IOException {
		ModelMapper modelMapper = new ModelMapper();
		GameCard cardDTO = modelMapper.map(card, GameCard.class);
		
		cardDTO.setImage(fileService.downloadFile(card.getImageId()).getContent());
		
		return cardDTO;
	}
	
	public GameCardDTO gameCardToGameCardDTO(GameCard gameCard) throws IOException {
		ModelMapper modelMapper = new ModelMapper();
		GameCardDTO cardDTO = modelMapper.map(gameCard, GameCardDTO.class);
		
		return cardDTO;
	}
	
	public List<GameCardDTO> listOfGameCardToListOfGameCardDTO(List<GameCard> gameCards) throws IOException {
		List<GameCardDTO> cardDTO = new ArrayList<>();
		for(int i = 0; i < gameCards.size(); i++) {
			GameCard card = gameCards.get(i);
			GameCardDTO newCard = gameCardToGameCardDTO(card);
			cardDTO.add(newCard);
		}
		return cardDTO;
	}
	
	public PlayerDTO playerToPlayerDTO(Player player) {
		ModelMapper modelMapper = new ModelMapper();
		PlayerDTO playerDTO = modelMapper.map(player, PlayerDTO.class);
		return playerDTO;
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
