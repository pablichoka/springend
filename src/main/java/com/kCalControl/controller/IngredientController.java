package com.kCalControl.controller;

import com.kCalControl.dto.CategorizeIngredientsDTO;
import com.kCalControl.dto.SearchParamsDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public interface IngredientController {

    @GetMapping("/auth/api/categorizeIngredient")
    String showCategorizeIngredients(Model model);

    @PostMapping("/auth/api/categorizeIngredient")
    void categorizeIngredients(CategorizeIngredientsDTO dto, HttpServletResponse httpServletResponse);

    @GetMapping("/auth/admin/listIngredient")
    String listIngredients(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize);

    @PostMapping("/auth/admin/listIngredient")
    String searchIngredients(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize,
                             SearchParamsDTO dto, Model model, HttpServletResponse response);
}
