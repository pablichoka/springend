package com.kCalControl.service;

import com.kCalControl.model.Ingredient;
import com.kCalControl.model.IngredientsOld;
import com.kCalControl.model.Nutrients;
import org.springframework.data.domain.Page;

public interface IngredientService {

    void addTypeToIngredient(String type, Ingredient ingredient);

    Ingredient convertIngredientOld2Ingredient(IngredientsOld ingredientsOld);

    public Page<Ingredient> getIngredients(int page, int pageSize);
}
