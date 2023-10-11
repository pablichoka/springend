package com.kCalControl.service.impl;

import com.kCalControl.model.Ingredient;
import com.kCalControl.model.Nutrients;
import com.kCalControl.repository.IngredientRepository;
import com.kCalControl.repository.NutrientsRepository;
import com.kCalControl.service.IngredientService;
import com.kCalControl.service.NutrientService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NutrientServiceImpl implements NutrientService {

    @Autowired
    NutrientsRepository nutrientsRepository;

    @Autowired
    IngredientRepository ingredientRepository;
    @Override
    public Nutrients getNutrientsFromIngredient(ObjectId id) {
        Nutrients nutrients;
        Optional<Ingredient> ingredientOptional= ingredientRepository.findById(id);
        if(ingredientOptional.isEmpty()){
            throw new RuntimeException("Could not find that element");
        }
        Optional<Nutrients> nutrientsOptional = nutrientsRepository.findById(ingredientOptional.get().getId());
        if(nutrientsOptional.isEmpty()){
            throw new RuntimeException("Could not find that element");
        }else{
            nutrients = nutrientsOptional.get();
        }
        return nutrients;
    }
}
