package com.kCalControl.dto.nutrients;

import com.kCalControl.model.Vitamins;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetrieveVitaminsDTO {

    Double vitaminA;
    Double vitaminB12;
    Double vitaminB6;
    Double vitaminC;
    Double vitaminE;
    Double vitaminK;
    Double thiamin;
    Double niacin;
    Double riboflavin;

    public RetrieveVitaminsDTO(Vitamins vitamins){
        vitaminA = vitamins.getVitaminA();
        vitaminB12 = vitamins.getVitaminB12();
        vitaminB6 = vitamins.getVitaminB6();
        vitaminC = vitamins.getVitaminC();
        vitaminE = vitamins.getVitaminE();
        vitaminK = vitamins.getVitaminK();
        thiamin = vitamins.getThiamin();
        niacin = vitamins.getNiacin();
        riboflavin = vitamins.getRiboflavin();
    }

}
