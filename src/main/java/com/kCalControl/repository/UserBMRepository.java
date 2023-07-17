package com.kCalControl.repository;

import com.kCalControl.model.UserBM;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBMRepository extends MongoRepository<UserBM, ObjectId> {

    UserBM findByUserAssoc_Id(ObjectId id);

}
