package com.kCalControl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
@Table(name = "Nutrients")
public class Nutrients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Double carbohydrate;
    private Double cholesterol;
    private Double fiber;
    private Double protein;
    @Column(name = "sugar_total")
    private Double sugarTotal;
    private Double water;
    @Column(name = "total_lipid")
    private Double totalLipid;
    @Column(name = "monosaturated_fat")
    private Double monoFat;
    @Column(name = "polysaturated_fat")
    private Double polyFat;
    @Column(name = "saturated_fat")
    private Double saturatedFat;

    @OneToOne(mappedBy = "mineralsAssoc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Ingredient ingredientAssoc;

    public ObjectNode toJson(){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("carbohydrate", this.getCarbohydrate());
        node.put("cholesterol", this.getCholesterol());
        node.put("fiber", this.getFiber());
        node.put("protein", this.getProtein());
        node.put("sugarTotal", this.getSugarTotal());
        node.put("water", this.getWater());
        node.put("totalLipid", this.getTotalLipid());
        node.put("monoFat", this.getMonoFat());
        node.put("polyFat", this.getPolyFat());
        node.put("saturatedFat", this.getSaturatedFat());
        return node;
    }

}
