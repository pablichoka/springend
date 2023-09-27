package com.kCalControl.controller;

import com.kCalControl.dto.CategorizeIngredientsDTO;
import com.kCalControl.dto.search.SearchParamsDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public interface IngredientController {

    @PostMapping("/ingredients/categorizeIngredient")
    void categorizeIngredients(@RequestBody CategorizeIngredientsDTO dto, HttpServletResponse httpServletResponse);

    //TODO re-evaluate pagination method
    @GetMapping("/ingredients/listIngredient")
    String listIngredients(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize);

    //TODO make reliable to click navigation pages buttons depending on get or post request
    @PostMapping("/ingredients/listIngredient")
    String searchIngredients(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize,
                             SearchParamsDTO dto, Model model, HttpServletResponse response);
}
