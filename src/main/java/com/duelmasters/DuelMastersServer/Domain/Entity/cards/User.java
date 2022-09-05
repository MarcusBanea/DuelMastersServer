package com.duelmasters.DuelMastersServer.Domain.Entity.cards;

import java.util.List;

import lombok.Data;

@Data
public class User {

	private String username;
	private String email;
	
	private byte[] image;
	
	private List<Card> collection;
	private List<Deck> deck;
}
