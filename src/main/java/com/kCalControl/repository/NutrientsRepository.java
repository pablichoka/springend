package com.kCalControl.repository;

import com.kCalControl.model.Nutrients;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NutrientsRepository extends MongoRepository<Nutrients, ObjectId> {
}
