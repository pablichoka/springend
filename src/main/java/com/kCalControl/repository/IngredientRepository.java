package com.kCalControl.repository;

import com.kCalControl.model.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    boolean existsByCategoryLike(String category);
    Page<Ingredient> findByCategoryLike(String category, Pageable pageable);
    Page<Ingredient> findByDescriptionLike(String description, Pageable pageable);
    Page<Ingredient> findByTypeIgnoreCaseOrCategoryIgnoreCaseOrDescriptionLikeIgnoreCase(String type, String category, String description,Pageable pageable);
    Page<Ingredient> findAll(Pageable pageable);

}
