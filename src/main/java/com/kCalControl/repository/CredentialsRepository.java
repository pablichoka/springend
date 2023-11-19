package com.kCalControl.repository;

import com.kCalControl.model.Credentials;
import com.kCalControl.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CredentialsRepository extends CrudRepository<Credentials, Integer> {

    Optional<Credentials> findByUsername(String name);

}
