package com.kCalControl.dto.ingredient;

import com.kCalControl.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveIngredientDTO {
    String type;
    String category;
    String description;

    public RetrieveIngredientDTO(Ingredient ingredient){
        type = ingredient.getType();
        category = ingredient.getCategory();
        description = ingredient.getDescription();
    }

}
