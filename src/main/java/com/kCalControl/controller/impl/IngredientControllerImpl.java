package com.kCalControl.controller.impl;

import com.kCalControl.controller.IngredientController;
import com.kCalControl.dto.ingredient.CategorizeIngredientsDTO;
import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.ingredient.RetrieveIngredientDTO;
import com.kCalControl.dto.ingredient.RetrieveIngredientsDTO;
import com.kCalControl.dto.nutrients.RetrieveBasicNutrientsDTO;
import com.kCalControl.dto.nutrients.RetrieveMineralsDTO;
import com.kCalControl.dto.nutrients.RetrieveVitaminsDTO;
import com.kCalControl.exceptions.NetworkException;
import com.kCalControl.model.*;
import com.kCalControl.repository.IngredientRepository;
import com.kCalControl.repository.IngredientsOldRepository;
import com.kCalControl.repository.NutrientsRepository;
import com.kCalControl.service.IngredientService;
import com.kCalControl.service.NutrientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientControllerImpl implements IngredientController {

    private final static Logger logger = LoggerFactory.getLogger(IngredientControllerImpl.class);
    @Autowired
    IngredientsOldRepository ingredientsOldRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    NutrientsRepository nutrientsRepository;
    @Autowired
    IngredientService ingredientService;
    @Autowired
    NutrientService nutrientService;

    //TODO finish with this as soon as possible so that it would be possible to delete the method
    @Override
    public ResponseEntity<String> categorizeIngredients(CategorizeIngredientsDTO dto) {
        boolean ingredientExistingList = ingredientRepository.existsByCategoryLike(dto.getCategory());
        if (ingredientExistingList) {
            throw new NetworkException("Requested ingredient category does not exists.", HttpStatus.NOT_FOUND);
        } else {
            List<IngredientsOld> ingredientsOldList = ingredientsOldRepository.findByCategoryLike(dto.getCategory());
            List<Ingredient> ingredientList = ingredientsOldList.stream()
                    .map(i -> ingredientService.convertIngredientOld2Ingredient(i))
                    .collect(Collectors.toList());
            ingredientList.forEach(i -> i.setType(dto.getType()));
            ingredientList.forEach(i -> nutrientsRepository.save(i.getNutrients()));
            ingredientRepository.saveAll(ingredientList);
            ingredientsOldRepository.deleteAll(ingredientsOldList);
            return ResponseEntity.ok(Long.toString(ingredientsOldList.size()) + " elements have been migrated");
        }
    }

    @Override
    public ResponseEntity<RetrieveIngredientsDTO> listIngredients(SearchParamsDTO dto) {
        Page<Ingredient> ingredientsList = ingredientService.getIngredient(dto.getPage(), dto.getPageSize());
        RetrieveIngredientsDTO response = new RetrieveIngredientsDTO(ingredientsList.getNumberOfElements(),
                ingredientsList.getContent().stream().map(RetrieveIngredientDTO::new).toList());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RetrieveIngredientsDTO> searchIngredients(SearchParamsDTO dto) {
        Page<Ingredient> ingredientsSearchList = ingredientService.getIngredientsFromSearch(dto);
        RetrieveIngredientsDTO response = new RetrieveIngredientsDTO(ingredientsSearchList.getNumberOfElements(),
                ingredientsSearchList.getContent().stream().map(RetrieveIngredientDTO::new).toList());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RetrieveBasicNutrientsDTO> getNutrients(Integer id) {
        Nutrients nutrients = nutrientService.getNutrientsFromIngredient(id);
        return ResponseEntity.ok(new RetrieveBasicNutrientsDTO(nutrients));
    }

    @Override
    public ResponseEntity<RetrieveVitaminsDTO> getVitamins(Integer id) {
        Vitamins vitamins = nutrientService.getNutrientsFromIngredient(id);
        return ResponseEntity.ok(new RetrieveVitaminsDTO(vitamins));
    }

    @Override
    public ResponseEntity<RetrieveMineralsDTO> getMinerals(Integer id) {
        Minerals minerals = nutrientService.getNutrientsFromIngredient(id);
        return ResponseEntity.ok(new RetrieveMineralsDTO(minerals));    }

}
