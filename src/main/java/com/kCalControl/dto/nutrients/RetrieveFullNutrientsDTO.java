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
        this.alphaCarotene = nutrients.getAlphaCarotene();
        this.betaCarotene = nutrients.getBetaCarotene();
        this.betaCryptoxanthin = nutrients.getBetaCryptoxanthin();
        this.carbohydrate = nutrients.getCarbohydrate();
        this.cholesterol = nutrients.getCholesterol();
        this.choline = nutrients.getCholine();
        this.fiber = nutrients.getFiber();
        this.luteinAndZeaxanthin = nutrients.getLuteinAndZeaxanthin();
        this.lycopene = nutrients.getLycopene();
        this.niacin = nutrients.getNiacin();
        this.protein = nutrients.getProtein();
        this.retinol = nutrients.getRetinol();
        this.riboflavin = nutrients.getRiboflavin();
        this.selenium = nutrients.getSelenium();
        this.sugarTotal = nutrients.getSugarTotal();
        this.thiamin = nutrients.getThiamin();
        this.water = nutrients.getWater();
        this.monoFat = nutrients.getMonoFat();
        this.polyFat = nutrients.getPolyFat();
        this.saturatedFat = nutrients.getSaturatedFat();
        this.totalLipid = nutrients.getTotalLipid();
        this.calcium = nutrients.getCalcium();
        this.copper = nutrients.getCopper();
        this.iron = nutrients.getIron();
        this.magnesium = nutrients.getMagnesium();
        this.phosphorus = nutrients.getPhosphorus();
        this.potassium = nutrients.getPotassium();
        this.sodium = nutrients.getSodium();
        this.zinc = nutrients.getZinc();
        this.vitaminA = nutrients.getVitaminA();
        this.vitaminB12 = nutrients.getVitaminB12();
        this.vitaminB6 = nutrients.getVitaminB6();
        this.vitaminC = nutrients.getVitaminC();
        this.vitaminE = nutrients.getVitaminE();
        this.vitaminK = nutrients.getVitaminK();
    }
}
