package com.kCalControl.repository;

import com.kCalControl.model.Nutrients;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NutrientsRepository extends MongoRepository<Nutrients, ObjectId> {

}
