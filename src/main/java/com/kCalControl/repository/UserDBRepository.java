package com.kCalControl.repository;

import com.kCalControl.model.UserDB;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDBRepository extends CrudRepository<UserDB, Integer> {
    Optional<UserDB> findByUsername(String name);
    Page<UserDB> findByUsernameLikeIgnoreCaseOrEmailIgnoreCaseOrNameLikeIgnoreCase(String username, String email, String firstName, Pageable pageable);
    Page<UserDB> findAll(PageRequest pageRequest);
}
