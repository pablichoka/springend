package com.kCalControl.repository;

import com.kCalControl.model.Ingredient;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IngredientRepository extends MongoRepository<Ingredient, ObjectId> {
}
