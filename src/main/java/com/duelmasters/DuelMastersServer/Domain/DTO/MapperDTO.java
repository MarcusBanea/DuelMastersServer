package com.duelmasters.DuelMastersServer.Domain.DTO;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;

@Component
public class MapperDTO {
	
	public CardDTO cardToCardDTO(Card card) {
		ModelMapper modelMapper = new ModelMapper();
		CardDTO cardDTO = modelMapper.map(card, CardDTO.class);
		return cardDTO;
	}
	
}
