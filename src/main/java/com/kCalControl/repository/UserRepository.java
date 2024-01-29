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
    Page<User> findByCredentials_UsernameLikeIgnoreCase(String username, Pageable pageable);
    Page<User> findByCredentials_EmailLikeIgnoreCase(String email, Pageable pageable);
    Page<User> findByNameLikeIgnoreCase(String name, Pageable pageable);
    Page<User> findByMobileLike(String mobile, Pageable pageable);
    Page<User> findAll(Pageable pageable);

    Optional<User> findByCredentialsId(Integer id);

    @Query(value = "SELECT MAX(id) FROM users", nativeQuery = true)
    Integer getLastId();
}
