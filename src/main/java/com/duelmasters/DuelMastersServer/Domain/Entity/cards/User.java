package com.duelmasters.DuelMastersServer.Domain.Entity.cards;

import java.util.HashMap;
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

	//key - cardId, value - total number of that specific card in user collection
	private HashMap<String, Integer> collection;
	
	//list of cardIds
	private List<String> deck;

	public User(String username, String email, String nickname, byte[] image, Integer money, Integer level, Integer xp,
			HashMap<String, Integer> collection, List<String> deck) {
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
	
	public User() {
		
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", nickname=" + nickname + ", money=" + money + ", level=" + level
				+ ", xp=" + xp + "]\n";
	}

}
