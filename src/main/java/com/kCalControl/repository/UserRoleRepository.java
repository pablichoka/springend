package com.kCalControl.repository;

import com.kCalControl.model.IdClases.UserRoleId;
import com.kCalControl.model.Role;
import com.kCalControl.model.UserDB;
import com.kCalControl.model.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleId> {
    Optional<UserRole> findById_UserDB_Id(Integer userDBId);
}
