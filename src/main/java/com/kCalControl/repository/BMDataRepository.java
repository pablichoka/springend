package com.kCalControl.repository;

import com.kCalControl.model.BMData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BMDataRepository extends MongoRepository<BMData, ObjectId> {
    BMData findByUserAssoc_Id(ObjectId id);

}
