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
@Table(name = "minerals")
public class Minerals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "calcium")
    private Double calcium;
    @Column(name = "copper")
    private Double copper;
    @Column(name = "iron")
    private Double iron;
    @Column(name = "magnesium")
    private Double magnesium;
    @Column(name = "phosphorus")
    private Double phosphorus;
    @Column(name = "potassium")
    private Double potassium;
    @Column(name = "sodium")
    private Double sodium;
    @Column(name = "zinc")
    private Double zinc;

    @OneToOne(mappedBy = "minerals", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Ingredient ingredientAssoc;

}
