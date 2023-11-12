package com.kCalControl.dto.nutrients;

import com.kCalControl.model.Nutrients;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetrieveBasicNutrientsDTO {

    Double carbohydrate;
    Double cholesterol;
    Double fiber;
    Double protein;
    Double sugarTotal;
    Double water;
    Double monoFat;
    Double polyFat;
    Double saturatedFat;
    Double totalLipid;
    Double sodium;

    public RetrieveBasicNutrientsDTO(Nutrients nutrients){
        carbohydrate = nutrients.getCarbohydrate();
        cholesterol = nutrients.getCholesterol();
        fiber = nutrients.getFiber();
        protein = nutrients.getProtein();
        sugarTotal = nutrients.getSugarTotal();
        water = nutrients.getWater();
        monoFat = nutrients.getMonoFat();
        polyFat = nutrients.getPolyFat();
        saturatedFat = nutrients.getSaturatedFat();
        totalLipid = nutrients.getTotalLipid();
    }

}