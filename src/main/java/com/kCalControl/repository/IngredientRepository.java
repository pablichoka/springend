package com.kCalControl.repository;

import com.kCalControl.model.Ingredient;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends MongoRepository<Ingredient, ObjectId> {
    boolean existsByCategoryLike(String category);
    Page<Ingredient> findByCategoryLike(String category, Pageable pageable);
    Page<Ingredient> findByDescriptionLike(String description, Pageable pageable);
    Page<Ingredient> findByTypeIgnoreCaseOrCategoryIgnoreCaseOrDescriptionLikeIgnoreCase(String type, String category, String description,Pageable pageable);

}
