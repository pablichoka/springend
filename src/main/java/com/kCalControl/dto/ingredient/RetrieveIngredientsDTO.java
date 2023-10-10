package com.kCalControl.dto.ingredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetrieveIngredientsDTO {

    int numOfElements;
    List<RetrieveIngredientDTO> ingredients;

}
