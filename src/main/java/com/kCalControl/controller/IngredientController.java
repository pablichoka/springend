package com.kCalControl.controller;

import com.kCalControl.dto.ingredient.CategorizeIngredientsDTO;
import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.ingredient.RetrieveIngredientsDTO;
import com.kCalControl.dto.nutrients.RetrieveBasicNutrientsDTO;
import com.kCalControl.dto.nutrients.RetrieveMineralsDTO;
import com.kCalControl.dto.nutrients.RetrieveVitaminsDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/", "/mobile"})
public interface IngredientController {  //TODO missing add ingredient, update ingredient, delete ingredient

    @PostMapping("categorize-ingredients")
    ResponseEntity<String> categorizeIngredients(@RequestBody CategorizeIngredientsDTO dto);

    @GetMapping("list-ingredients")
    @ResponseBody
    ResponseEntity<RetrieveIngredientsDTO> listIngredients(@RequestParam Integer page, @RequestParam Integer size);

    @PostMapping("search-ingredients")
    @ResponseBody
    ResponseEntity<RetrieveIngredientsDTO> searchIngredients(@RequestBody SearchParamsDTO dto);

    @GetMapping("get-nutrients/{id}")
    @ResponseBody
    ResponseEntity<RetrieveBasicNutrientsDTO> getNutrients(@PathVariable Integer id);

    @GetMapping("get-vitamins/{id}")
    @ResponseBody
    ResponseEntity<RetrieveVitaminsDTO> getVitamins(@PathVariable Integer id);

    @GetMapping("get-minerals/{id}")
    @ResponseBody
    ResponseEntity<RetrieveMineralsDTO> getMinerals(@PathVariable Integer id);
}
