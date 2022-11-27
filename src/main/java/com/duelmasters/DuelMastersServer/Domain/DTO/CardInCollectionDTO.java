package com.duelmasters.DuelMastersServer.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CardInCollectionDTO extends CardDTO {
	
	private int numberOfCards;

	public CardInCollectionDTO(String name, String imageId, Integer numberOfCards) {
		super(name, imageId);
		this.numberOfCards = numberOfCards;
	}
}
