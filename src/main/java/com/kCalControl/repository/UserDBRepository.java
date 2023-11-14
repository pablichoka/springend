package com.kCalControl.repository;

import com.kCalControl.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDBRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String name);
    Page<User> findByUsernameLikeIgnoreCaseOrEmailIgnoreCaseOrNameLikeIgnoreCase(String username, String email, String firstName, Pageable pageable);
    Page<User> findAll(PageRequest pageRequest);
}
