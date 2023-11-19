package com.kCalControl.repository;

import com.kCalControl.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Page<User> findByNameLikeIgnoreCaseOrMobileLike(String username, String email, String firstName, Pageable pageable);
    Page<User> findAll(Pageable pageable);

    @Query(value = "SELECT MAX(id) FROM users", nativeQuery = true)
    Integer getLastId();
}
