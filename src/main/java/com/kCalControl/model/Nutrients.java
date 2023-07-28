package com.kCalControl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "nutrients")
public class Nutrients {

    @Id
    ObjectId id;
    @Field("alpha_carotene")
    Double alpha_carotene;
    @Field("beta_carotene")
    Double beta_carotene;
    @Field("beta_cryptoxanthin")
    Double beta_cryptoxanthin;
    @Field("carbohydrate")
    Double carbohydrate;
    @Field("cholesterol")
    Double cholesterol;
    @Field("choline")
    Double choline;
    @Field("fiber")
    Double fiber;
    @Field("lutein_and_zeaxanthin")
    Double lutein_and_zeaxanthin;
    @Field("lycopene")
    Double lycopene;
    @Field("niacin")
    Double niacin;
    @Field("protein")
    Double protein;
    @Field("retinol")
    Double retinol;
    @Field("riboflavin")
    Double riboflavin;
    @Field("selenium")
    Double selenium;
    @Field("sugar_total")
    Double sugar_total;
    @Field("thiamin")
    Double thiamin;
    @Field("water")
    Double water;
    @Field("fat.monosaturated_fat")
    Double mono_fat;
    @Field("fat.polysaturated_fat")
    Double poly_fat;
    @Field("fat.saturated_fat")
    Double saturated_fat;
    @Field("fat.total_lipid")
    Double total_lipid;
    @Field("major_minerals.calcium")
    Double calcium;
    @Field("major_minerals.copper")
    Double copper;
    @Field("major_minerals.iron")
    Double iron;
    @Field("major_minerals.magnesium")
    Double magnesium;
    @Field("major_minerals.phosphorus")
    Double phosphorus;
    @Field("major_minerals.potassium")
    Double potassium;
    @Field("major_minerals.sodium")
    Double sodium;
    @Field("major_minerals.zinc")
    Double zinc;
    @Field("vitamins.vitamin_a")
    Double vitamin_a;
    @Field("vitamins.vitamin_b12")
    Double vitamin_b12;
    @Field("vitamins.vitamin_b6")
    Double vitamin_b6;
    @Field("vitamins.vitamin_c")
    Double vitamin_c;
    @Field("vitamins.vitamin_e")
    Double vitamin_e;
    @Field("vitamins.vitamin_k")
    Double vitamin_k;

}
