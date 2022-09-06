package com.duelmasters.DuelMastersServer.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Pack;

public interface PackRepository extends MongoRepository<Pack, String>{
	
}
