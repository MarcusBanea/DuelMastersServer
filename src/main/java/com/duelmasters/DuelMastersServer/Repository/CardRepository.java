package com.duelmasters.DuelMastersServer.Repository;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CardRepository extends MongoRepository<Card, String> {

    Optional<Card> findByName(String name);

}
