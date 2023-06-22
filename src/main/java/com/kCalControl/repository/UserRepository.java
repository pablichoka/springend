package com.kCalControl.repository;

import com.kCalControl.model.Role;
import com.kCalControl.model.UserDB;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserDB, ObjectId> {
    @Query(value = "{'userDB.username': ?0}")
    Optional<UserDB> findByUsername(String name);
    Optional<UserDB> findByEmail(String email);
    List<UserDB> findAllByRole(Role role);
}
