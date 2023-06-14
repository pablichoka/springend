package com.kCalControl.repository;

import com.kCalControl.model.UserDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserDB, Integer> {
    Optional<UserDB> findByUsername(String name);
    Optional<UserDB> findByEmail(String email);
}
