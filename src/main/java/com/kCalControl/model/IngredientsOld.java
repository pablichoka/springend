package com.kCalControl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ingredients_old")
public class IngredientsOld {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "category")
    String category;

    @Column(name = "description")
    String description;

    @Column(name = "alpha_carotene")
    Double alpha_carotene;

    @Column(name = "beta_carotene")
    Double beta_carotene;

    @Column(name = "beta_cryptoxanthin")
    Double beta_cryptoxanthin;

    @Column(name = "carbohydrate")
    Double carbohydrate;

    @Column(name = "cholesterol")
    Double cholesterol;

    @Column(name = "choline")
    Double choline;

    @Column(name = "fiber")
    Double fiber;

    @Column(name = "lutein_and_zeaxanthin")
    Double lutein_and_zeaxanthin;

    @Column(name = "lycopene")
    Double lycopene;

    @Column(name = "niacin")
    Double niacin;

    @Column(name = "protein")
    Double protein;

    @Column(name = "retinol")
    Double retinol;

    @Column(name = "riboflavin")
    Double riboflavin;

    @Column(name = "selenium")
    Double selenium;

    @Column(name = "sugar_total")
    Double sugar_total;

    @Column(name = "thiamin")
    Double thiamin;

    @Column(name = "water")
    Double water;

    @Column(name = "monosaturated_fat")
    Double mono_fat;

    @Column(name = "polysaturated_fat")
    Double poly_fat;

    @Column(name = "saturated_fat")
    Double saturated_fat;

    @Column(name = "total_lipid")
    Double total_lipid;

    @Column(name = "calcium")
    Double calcium;

    @Column(name = "copper")
    Double copper;

    @Column(name = "iron")
    Double iron;

    @Column(name = "magnesium")
    Double magnesium;

    @Column(name = "phosphorus")
    Double phosphorus;

    @Column(name = "potassium")
    Double potassium;

    @Column(name = "sodium")
    Double sodium;

    @Column(name = "zinc")
    Double zinc;

    @Column(name = "vitamin_a")
    Double vitamin_a;

    @Column(name = "vitamin_b12")
    Double vitamin_b12;

    @Column(name = "vitamin_b6")
    Double vitamin_b6;

    @Column(name = "vitamin_c")
    Double vitamin_c;

    @Column(name = "vitamin_e")
    Double vitamin_e;

    @Column(name = "vitamin_k")
    Double vitamin_k;
}