package com.kCalControl.repository;

import com.kCalControl.model.Role;
import com.kCalControl.model.UserDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByRoleName(String roleName);

    Optional<Role> findByRoleNameLike(String roleName);

}
