package com.duelmasters.DuelMastersServer.Domain.Entity.cards;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User {

	@Id
	private String id;

	private String username;
	private String email;
	private String nickname;
	private byte[] image;

	private Integer money;

	private Integer level;
	private Integer xp;

	private List<Card> collection;
	private List<Deck> deck;

	public User(String username, String email, String nickname, byte[] image, Integer money, Integer level, Integer xp,
			List<Card> collection, List<Deck> deck) {
		super();
		this.username = username;
		this.email = email;
		this.nickname = nickname;
		this.image = image;
		this.money = money;
		this.level = level;
		this.xp = xp;
		this.collection = collection;
		this.deck = deck;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", nickname=" + nickname + ", money=" + money + ", level=" + level
				+ ", xp=" + xp + "]\n";
	}

}
