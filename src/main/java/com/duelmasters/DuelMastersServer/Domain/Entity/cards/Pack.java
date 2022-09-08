package com.duelmasters.DuelMastersServer.Domain.Entity.cards;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Pack {

	@Id
	private String id;

	private String name;
	private Integer totalNumberOfCards;

	private Integer price;

//	private Double commonCardProbability;
//	private Double uncommonCardProbability;
//	private Double rareCardProbability;
//	private Double veryRareCardProbability;
//	private Double superRareCardProbability;
//	private Double legendaryCardProbability;

	private Integer numberOfCommonCards;
	private Integer numberOfUncommonCards;
	private Integer numberOfRareCards;
	private Integer numberOfVeryRareCards;
	private Integer numberOfSuperRareCards;
	private Integer numberOfLegendaryCards;

	private byte[] image;

	public Pack(String name, Integer totalNumberOfCards, Integer price, Integer numberOfCommonCards,
			Integer numberOfUncommonCards, Integer numberOfRareCards, Integer numberOfVeryRareCards,
			Integer numberOfSuperRareCards, Integer numberOfLegendaryCards, byte[] image) {
		super();
		this.name = name;
		this.totalNumberOfCards = totalNumberOfCards;
		this.price = price;
		this.numberOfCommonCards = numberOfCommonCards;
		this.numberOfUncommonCards = numberOfUncommonCards;
		this.numberOfRareCards = numberOfRareCards;
		this.numberOfVeryRareCards = numberOfVeryRareCards;
		this.numberOfSuperRareCards = numberOfSuperRareCards;
		this.numberOfLegendaryCards = numberOfLegendaryCards;
		this.image = image;
	}

	@Override
	public String toString() {
		return "Pack [name=" + name + ", totalNumberOfCards=" + totalNumberOfCards + ", price=" + price + "]\n";
	}

}
