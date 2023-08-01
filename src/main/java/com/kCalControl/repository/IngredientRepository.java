package com.kCalControl.repository;

import com.kCalControl.model.Ingredient;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends MongoRepository<Ingredient, ObjectId> {
    boolean existsByCategoryLike(String category);

}
