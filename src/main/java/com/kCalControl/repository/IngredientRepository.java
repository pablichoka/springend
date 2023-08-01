package com.kCalControl.repository;

import com.kCalControl.model.Ingredient;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IngredientRepository extends MongoRepository<Ingredient, ObjectId> {
    Optional<Ingredient> findByCategoryLike(String category);

}
