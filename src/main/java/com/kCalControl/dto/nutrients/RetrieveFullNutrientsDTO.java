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
public class RetrieveFullNutrientsDTO {

    Double alphaCarotene;
    Double betaCarotene;
    Double betaCryptoxanthin;
    Double carbohydrate;
    Double cholesterol;
    Double choline;
    Double fiber;
    Double luteinAndZeaxanthin;
    Double lycopene;
    Double niacin;
    Double protein;
    Double retinol;
    Double riboflavin;
    Double selenium;
    Double sugarTotal;
    Double thiamin;
    Double water;
    Double monoFat;
    Double polyFat;
    Double saturatedFat;
    Double totalLipid;
    Double calcium;
    Double copper;
    Double iron;
    Double magnesium;
    Double phosphorus;
    Double potassium;
    Double sodium;
    Double zinc;
    Double vitaminA;
    Double vitaminB12;
    Double vitaminB6;
    Double vitaminC;
    Double vitaminE;
    Double vitaminK;

    public RetrieveFullNutrientsDTO(Nutrients nutrients) {
        this.carbohydrate = nutrients.getCarbohydrate();
        this.cholesterol = nutrients.getCholesterol();
        this.fiber = nutrients.getFiber();
        this.protein = nutrients.getProtein();
        this.sugarTotal = nutrients.getSugarTotal();
        this.water = nutrients.getWater();
        this.monoFat = nutrients.getMonoFat();
        this.polyFat = nutrients.getPolyFat();
        this.saturatedFat = nutrients.getSaturatedFat();
        this.totalLipid = nutrients.getTotalLipid();
    }
}
