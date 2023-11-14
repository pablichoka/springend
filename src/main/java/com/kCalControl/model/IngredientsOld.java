package com.kCalControl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "ingredients_old")
public class IngredientsOld {

    @Id
    Integer id;
    @Field("category")
    String category;
    @Field("description")
    String description;
    @Field("nutrients.alpha_carotene")
    Double alpha_carotene;
    @Field("nutrients.beta_carotene")
    Double beta_carotene;
    @Field("nutrients.beta_cryptoxanthin")
    Double beta_cryptoxanthin;
    @Field("nutrients.carbohydrate")
    Double carbohydrate;
    @Field("nutrients.cholesterol")
    Double cholesterol;
    @Field("nutrients.choline")
    Double choline;
    @Field("nutrients.fiber")
    Double fiber;
    @Field("nutrients.lutein_and_zeaxanthin")
    Double lutein_and_zeaxanthin;
    @Field("nutrients.lycopene")
    Double lycopene;
    @Field("nutrients.niacin")
    Double niacin;
    @Field("nutrients.protein")
    Double protein;
    @Field("nutrients.retinol")
    Double retinol;
    @Field("nutrients.riboflavin")
    Double riboflavin;
    @Field("nutrients.selenium")
    Double selenium;
    @Field("nutrients.sugar_total")
    Double sugar_total;
    @Field("nutrients.thiamin")
    Double thiamin;
    @Field("nutrients.water")
    Double water;
    @Field("nutrients.fat.monosaturated_fat")
    Double mono_fat;
    @Field("nutrients.fat.polysaturated_fat")
    Double poly_fat;
    @Field("nutrients.fat.saturated_fat")
    Double saturated_fat;
    @Field("nutrients.fat.total_lipid")
    Double total_lipid;
    @Field("nutrients.major_minerals.calcium")
    Double calcium;
    @Field("nutrients.major_minerals.copper")
    Double copper;
    @Field("nutrients.major_minerals.iron")
    Double iron;
    @Field("nutrients.major_minerals.magnesium")
    Double magnesium;
    @Field("nutrients.major_minerals.phosphorus")
    Double phosphorus;
    @Field("nutrients.major_minerals.potassium")
    Double potassium;
    @Field("nutrients.major_minerals.sodium")
    Double sodium;
    @Field("nutrients.major_minerals.zinc")
    Double zinc;
    @Field("nutrients.vitamins.vitamin_a")
    Double vitamin_a;
    @Field("nutrients.vitamins.vitamin_b12")
    Double vitamin_b12;
    @Field("nutrients.vitamins.vitamin_b6")
    Double vitamin_b6;
    @Field("nutrients.vitamins.vitamin_c")
    Double vitamin_c;
    @Field("nutrients.vitamins.vitamin_e")
    Double vitamin_e;
    @Field("nutrients.vitamins.vitamin_k")
    Double vitamin_k;

}
