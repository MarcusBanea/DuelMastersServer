package com.duelmasters.DuelMastersServer.Domain.Entity.cards;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Deck {

	@Id
	private String id;

	private String name;

	private List<Card> shields;
	private List<Card> cards;

	public Deck(String name, List<Card> shields, List<Card> cards) {
		super();
		this.name = name;
		this.shields = shields;
		this.cards = cards;
	}

}
