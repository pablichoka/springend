package com.kCalControl.repository;

import com.kCalControl.model.UserDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDB, Integer> {
    Optional<UserDB> findByName(String name);
}
