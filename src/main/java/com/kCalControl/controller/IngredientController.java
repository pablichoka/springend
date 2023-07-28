package com.kCalControl.controller;

import com.kCalControl.dto.CategorizeIngredientsDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public interface IngredientController {

    @GetMapping("/auth/api/categorizeIngredients")
    void categorizeIngredients(Model model, CategorizeIngredientsDTO dto, HttpServletResponse httpServletResponse);
    @GetMapping("/auth/admin/listIngredient")
    String listIngredients(Model model, Integer page, Integer pageSize);


}
