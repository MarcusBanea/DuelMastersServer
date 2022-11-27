package com.duelmasters.DuelMastersServer.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardDTO {
	
	public CardDTO() {
		super();
	}
	
	private String name;
	private String imageId;
	
}
