//package com.kCalControl.repository;
//
//import com.kCalControl.model.Role;
//import com.kCalControl.model.UserDB;
//import com.kCalControl.model.UserRole;
//import org.bson.types.ObjectId;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import java.util.Optional;
//
//public interface UserRoleRepository extends MongoRepository<UserRole, ObjectId> {
//    Optional<UserRole> findByUserDB(UserDB userDB);
//    Optional<UserRole> findByRole(Role role);
//}
