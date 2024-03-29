package com.duelmasters.DuelMastersServer.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;

@Repository
public interface CardRepository extends MongoRepository<Card, String> {

	Optional<Card> findByName(String name);

	Page<Card> findAll(Pageable page);

	@Query("select count(c) from Card c where c.rarity <= :maxRarity")
	Long countByRarity(@Param("maxRarity") Long maxRarity);

	@Aggregation(pipeline = { "{'$match' : {'type' : 'Creature'}}" })
	List<Card> findCreatures();

	@Aggregation(pipeline = { "{'$match' : {'type' : 'Evolution'}}" })
	List<Card> findEvolutions();

	@Aggregation(pipeline = { "{'$match' : {'type' : 'Spell'}}" })
	List<Card> findSpells();

	@Aggregation(pipeline = { "{'$match' : {'rarity' : :#{#rarity}}}" })
	List<Card> findCardsWithExactRarity(@Param("rarity") String rarity);
	
	/*
	
	@Aggregation(pipeline = { "{'$match' : {'rarity' : {$lt : :#{#rarity}}}}" })
	List<Card> findCardsWithLowerRarity(@Param("rarity") String maxRarity);
	
	@Aggregation(pipeline = { "{'$match' : {'rarity' : {$gt : :#{#rarity}}}}" })
	List<Card> findCardsWithHigherRarity(@Param("rarity") String minRarity);
	
	@Aggregation(pipeline = { "{'$match' : {'rarity' : {$lt : :#{#rarity}}}}", "{'$sample' : {size: 1}}" })
	Card getRandomCardWithMaxRarity(@Param("rarity") String maxRarity);
	
	@Aggregation(pipeline = { "{'$match' : {'rarity' : {$gt : :#{#rarity}}}}", "{'$sample' : {size: 1}}" })
	Card getRandomCardWithMinRarity(@Param("rarity") String minRarity);
	
	*/
	
	@Aggregation(pipeline = { "{'$match' : {'rarity' : :#{#rarity}}}", "{'$sample' : {size: 1}}" })
	Card getRandomCardWithExactRarity(@Param("rarity") String rarity);
	
	@Aggregation(pipeline = { "{'$sample' : {size: 1}}" })
	Card getRandomCard();
	
	void deleteById(String id);
}
