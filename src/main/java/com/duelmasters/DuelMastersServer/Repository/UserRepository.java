package com.duelmasters.DuelMastersServer.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.User;

public interface UserRepository extends MongoRepository<User, String>{

}
