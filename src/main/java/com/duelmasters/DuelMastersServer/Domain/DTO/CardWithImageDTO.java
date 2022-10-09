package com.duelmasters.DuelMastersServer.Domain.DTO;

import lombok.Data;

@Data
public class CardWithImageDTO {
	
	private String id;
	private String rarity;
	private byte[] imageBytes;

}
