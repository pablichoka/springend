package com.kCalControl.controller.impl;

import com.kCalControl.controller.NutrientController;
import com.kCalControl.dto.nutrients.RetrieveBasicNutrientsDTO;
import com.kCalControl.dto.nutrients.RetrieveFullNutrientsDTO;
import com.kCalControl.dto.nutrients.RetrieveMineralsDTO;
import com.kCalControl.dto.nutrients.RetrieveVitaminsDTO;
import com.kCalControl.model.Nutrients;
import com.kCalControl.service.NutrientService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class NutrientControllerImpl implements NutrientController {

    @Autowired
    NutrientService nutrientService;

    @Override
    public ResponseEntity<RetrieveBasicNutrientsDTO> getBasicNutrients(ObjectId id) {
        Nutrients nutrients = nutrientService.getNutrientsFromIngredient(id);
        return ResponseEntity.ok(new RetrieveBasicNutrientsDTO(nutrients));
    }

    @Override
    public ResponseEntity<RetrieveFullNutrientsDTO> getFullNutrients(ObjectId id) {
        Nutrients nutrients = nutrientService.getNutrientsFromIngredient(id);
        return ResponseEntity.ok(new RetrieveFullNutrientsDTO(nutrients));
    }

    @Override
    public ResponseEntity<RetrieveVitaminsDTO> getVitamins(ObjectId id) {
        Nutrients nutrients = nutrientService.getNutrientsFromIngredient(id);
        return ResponseEntity.ok(new RetrieveVitaminsDTO(nutrients));
    }

    @Override
    public ResponseEntity<RetrieveMineralsDTO> getMinerals(ObjectId id) {
        Nutrients nutrients = nutrientService.getNutrientsFromIngredient(id);
        return ResponseEntity.ok(new RetrieveMineralsDTO(nutrients));    }
}
