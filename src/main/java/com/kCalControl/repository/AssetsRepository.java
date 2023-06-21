//package com.kCalControl.repository;
//
//import com.kCalControl.model.Assets;
//import org.bson.types.ObjectId;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Repository
//public interface AssetsRepository extends MongoRepository<Assets, ObjectId> {
//    Optional<Assets> findByCreationDate(LocalDateTime localDateTime);
//    Optional<Assets> findByCreationPerson(ObjectId objectId);
//    void deleteById(ObjectId objectId);
//}
