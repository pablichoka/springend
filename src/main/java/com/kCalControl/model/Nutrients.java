package com.kCalControl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "nutrients")
public class Nutrients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(mappedBy = "nutrients", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nutrients nutrients)) return false;
        return Objects.equals(id, nutrients.id) && Objects.equals(carbohydrate, nutrients.carbohydrate) && Objects.equals(cholesterol, nutrients.cholesterol) && Objects.equals(fiber, nutrients.fiber) && Objects.equals(protein, nutrients.protein) && Objects.equals(sugarTotal, nutrients.sugarTotal) && Objects.equals(water, nutrients.water) && Objects.equals(totalLipid, nutrients.totalLipid) && Objects.equals(monoFat, nutrients.monoFat) && Objects.equals(polyFat, nutrients.polyFat) && Objects.equals(saturatedFat, nutrients.saturatedFat) && Objects.equals(ingredientAssoc, nutrients.ingredientAssoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carbohydrate, cholesterol, fiber, protein, sugarTotal, water, totalLipid, monoFat, polyFat, saturatedFat);
    }

    @Override
    public String toString() {
        return "Nutrients{" +
                "id=" + id +
                ", carbohydrate=" + carbohydrate +
                ", cholesterol=" + cholesterol +
                ", fiber=" + fiber +
                ", protein=" + protein +
                ", sugarTotal=" + sugarTotal +
                ", water=" + water +
                ", totalLipid=" + totalLipid +
                ", monoFat=" + monoFat +
                ", polyFat=" + polyFat +
                ", saturatedFat=" + saturatedFat +
                ", ingredientAssoc=" + ingredientAssoc.getId() +
                '}';
    }
}
