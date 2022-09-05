package com.duelmasters.DuelMastersServer.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;

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
	List<Card> findCardsWithExactRarity(@Param("rarity") Long rarity);
	
	@Aggregation(pipeline = { "{'$match' : {'rarity' : {$lt : :#{#rarity}}}}" })
	List<Card> findCardsWithLowerRarity(@Param("rarity") Long maxRarity);
	
	@Aggregation(pipeline = { "{'$match' : {'rarity' : {$gt : :#{#rarity}}}}" })
	List<Card> findCardsWithHigherRarity(@Param("rarity") Long minRarity);
	
	@Aggregation(pipeline = { "{'$match' : {'rarity' : {$lt : :#{#rarity}}}}", "{'$sample' : {size: 1}}" })
	Card getRandomCardWithMaxRarity(@Param("rarity") Long maxRarity);
	
	@Aggregation(pipeline = { "{'$match' : {'rarity' : {$gt : :#{#rarity}}}}", "{'$sample' : {size: 1}}" })
	Card getRandomCardWithMinRarity(@Param("rarity") Long minRarity);
	
	@Aggregation(pipeline = { "{'$match' : {'rarity' : :#{#rarity}}}", "{'$sample' : {size: 1}}" })
	Card getRandomCardWithExactRarity(@Param("rarity") Long rarity);
}
