package com.kCalControl.service;

import com.kCalControl.model.Nutrients;
import org.bson.types.ObjectId;

public interface NutrientService {

    public Nutrients getNutrientsFromIngredient(Integer id);

}
