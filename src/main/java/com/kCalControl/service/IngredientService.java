package com.kCalControl.service;

import com.kCalControl.model.Ingredient;
import com.kCalControl.model.IngredientsOld;
import org.springframework.data.domain.Page;

public interface IngredientService {

    void addTypeToIngredient(String type, Ingredient ingredient);

    Ingredient convertIngredientOld2Ingredient(IngredientsOld ingredientsOld);

    public Page<Ingredient> getIngredient(int page, int pageSize);

    Page<Ingredient> getIngredientsFromSearch(int page, int pageSize, String query, String filter, String sort);
}
