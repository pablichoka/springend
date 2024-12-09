package com.kCalControl.repository;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import com.kCalControl.model.Role;

public interface RoleRepository extends CrudRepository<Role, String> {
    Optional<Role> findByRoleName(@NotNull String roleName);

}
