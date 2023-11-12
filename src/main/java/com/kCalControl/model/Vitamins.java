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
@Table(name = "Vitamins")
public class Vitamins {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "vitamin_a")
    private Double vitaminA;
    @Column(name = "vitamin_b12")
    private Double vitaminB12;
    @Column(name = "vitamin_b6")
    private Double vitaminB6;
    @Column(name = "vitamin_c")
    private Double vitaminC;
    @Column(name = "vitamin_e")
    private Double vitaminE;
    @Column(name = "vitamin_k")
    private Double vitaminK;
    @Column(name = "niacin")
    private Double niacin;
    @Column(name = "thiamin")
    private Double thiamin;
    @Column(name = "riboflavin")
    private Double riboflavin;

    @OneToOne(mappedBy = "vitaminsAssoc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Ingredient ingredientAssoc;


}
