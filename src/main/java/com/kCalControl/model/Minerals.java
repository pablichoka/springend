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
@Table(name = "Minerals")
public class Minerals {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Double calcium;
    private Double copper;
    private Double iron;
    private Double magnesium;
    private Double phosphorus;
    private Double potassium;
    private Double sodium;
    private Double zinc;

    @OneToOne(mappedBy = "MineralsAssoc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Ingredient ingredientAssoc;

}
