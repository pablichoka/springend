package com.kCalControl.repository;

import com.kCalControl.model.IdClases.UserRoleId;
import com.kCalControl.model.UserRole;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRoleRepository extends MongoRepository<UserRole, UserRoleId> {
    Optional<UserRole> findById_UserDB_Id(ObjectId userDBId);
}
