package com.kCalControl.controller.impl;

import com.kCalControl.config.UserSession;
import com.kCalControl.controller.IngredientController;
import com.kCalControl.dto.CategorizeIngredientsDTO;
import com.kCalControl.dto.SearchUserParamsDTO;
import com.kCalControl.model.Ingredient;
import com.kCalControl.model.IngredientsOld;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.IngredientRepository;
import com.kCalControl.repository.IngredientsOldRepository;
import com.kCalControl.service.IngredientService;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
    IngredientService ingredientService;

    @Override
    public String showCategorizeIngredients(Model model) {
        model.addAttribute("dto", new CategorizeIngredientsDTO());
        return "/auth/api/categorizeIngredient";
    }

    @Override
    public void categorizeIngredients(CategorizeIngredientsDTO dto, HttpServletResponse httpServletResponse) {
        List<IngredientsOld> ingredientsOldList = ingredientsOldRepository.findByCategoryLike(dto.getCategory());
        List<Ingredient> ingredientList = ingredientsOldList.stream()
                .map(i -> ingredientService.convertIngredientOld2Ingredient(i))
                .collect(Collectors.toList());
        ingredientList.stream().forEach(i -> i.setType(dto.getType()));
        logger.info("Ingredienteeees: " + ingredientList.get(0).toString());
        ingredientRepository.saveAll(ingredientList); //!
        httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    public String listIngredients(Model model, int page, int pageSize) {
        Page<Ingredient> usersList = ingredientService.getIngredients(page, pageSize);
        model.addAttribute("users", usersList.getContent());
        model.addAttribute("last", usersList.isLast());
        model.addAttribute("params",new SearchUserParamsDTO());
        return "auth/admin/listIngredient";
    }
}
