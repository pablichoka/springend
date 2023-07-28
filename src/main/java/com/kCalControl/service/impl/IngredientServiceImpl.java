package com.kCalControl.service.impl;

import com.kCalControl.model.Ingredient;
import com.kCalControl.model.IngredientsOld;
import com.kCalControl.model.Nutrients;
import com.kCalControl.repository.IngredientRepository;
import com.kCalControl.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public void addTypeToIngredient(String type, Ingredient ingredient) {
        ingredient.setType(type);
    }

    @Override
    public Ingredient convertIngredientOld2Ingredient(IngredientsOld ingredientsOld) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientsOld.getId());
        ingredient.setCategory(ingredientsOld.getCategory());
        ingredient.setDescription(ingredientsOld.getDescription());

        Nutrients nutrients = new Nutrients();

        //GOLD
        try {
            Class<?> oldIngredientClass = ingredientsOld.getClass();
            Class<?> nutrientsClass = nutrients.getClass();
            Field[] fields = oldIngredientClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();

                if (fieldName.equals("id")) {
                    continue;
                }

                Object value = field.get(ingredientsOld);

                try {
                    Field nutrientsField = nutrientsClass.getDeclaredField(fieldName);
                    nutrientsField.setAccessible(true);
                    nutrientsField.set(nutrients, value);
                } catch (NoSuchFieldException e) {
                    // Field does not exist in Nutrients class
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        ingredient.setNutrients(nutrients);
        return ingredient;
    }

    @Override
    public Page<Ingredient> getIngredients(int page, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "category");
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);
        return ingredientRepository.findAll(pageRequest);
    }

}
