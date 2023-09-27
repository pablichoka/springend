package com.kCalControl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    Double alphaCarotene;
    @Field("beta_carotene")
    Double betaCarotene;
    @Field("beta_cryptoxanthin")
    Double betaCryptoxanthin;
    @Field("carbohydrate")
    Double carbohydrate;
    @Field("cholesterol")
    Double cholesterol;
    @Field("choline")
    Double choline;
    @Field("fiber")
    Double fiber;
    @Field("lutein_and_zeaxanthin")
    Double luteinAndZeaxanthin;
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
    Double sugarTotal;
    @Field("thiamin")
    Double thiamin;
    @Field("water")
    Double water;
    @Field("fat.monosaturated_fat")
    Double monoFat;
    @Field("fat.polysaturated_fat")
    Double polyFat;
    @Field("fat.saturated_fat")
    Double saturatedFat;
    @Field("fat.total_lipid")
    Double totalLipid;
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
    Double vitaminA;
    @Field("vitamins.vitamin_b12")
    Double vitaminB12;
    @Field("vitamins.vitamin_b6")
    Double vitaminB6;
    @Field("vitamins.vitamin_c")
    Double vitaminC;
    @Field("vitamins.vitamin_e")
    Double vitaminE;
    @Field("vitamins.vitamin_k")
    Double vitaminK;

    public String toJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode object2JSON = objectMapper.createObjectNode();
        object2JSON.put("alphaCarotene", this.alphaCarotene);
        object2JSON.put("betaCarotene", this.betaCarotene);
        object2JSON.put("betaCryptoxanthin", this.betaCryptoxanthin);
        object2JSON.put("carbohydrate", this.carbohydrate);
        object2JSON.put("cholesterol", this.cholesterol);
        object2JSON.put("choline", this.choline);
        object2JSON.put("fiber", this.fiber);
        object2JSON.put("luteinAndZeaxanthin", this.luteinAndZeaxanthin);
        object2JSON.put("lycopene", this.lycopene);
        object2JSON.put("niacin", this.niacin);
        object2JSON.put("protein", this.protein);
        object2JSON.put("retinol", this.retinol);
        object2JSON.put("riboflavin", this.riboflavin);
        object2JSON.put("selenium", this.selenium);
        object2JSON.put("sugarTotal", this.sugarTotal);
        object2JSON.put("thiamin", this.thiamin);
        object2JSON.put("water", this.water);
        object2JSON.put("monoFat", this.monoFat);
        object2JSON.put("polyFat", this.polyFat);
        object2JSON.put("saturatedFat", this.saturatedFat);
        object2JSON.put("totalLipid", this.totalLipid);
        object2JSON.put("calcium", this.calcium);
        object2JSON.put("copper", this.copper);
        object2JSON.put("iron", this.iron);
        object2JSON.put("magnesium", this.magnesium);
        object2JSON.put("phosphorus", this.phosphorus);
        object2JSON.put("potassium", this.potassium);
        object2JSON.put("sodium", this.sodium);
        object2JSON.put("zinc", this.zinc);
        object2JSON.put("vitaminA", this.vitaminA);
        object2JSON.put("vitaminB12", this.vitaminB12);
        object2JSON.put("vitaminB6", this.vitaminB6);
        object2JSON.put("vitaminC", this.vitaminC);
        object2JSON.put("vitaminE", this.vitaminE);
        object2JSON.put("vitaminK", this.vitaminK);

        return object2JSON.toString();
    }

}
