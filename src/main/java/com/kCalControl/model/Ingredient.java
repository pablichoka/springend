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
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "assets_id", referencedColumnName = "id")
    private Assets assets;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nutrients_id", referencedColumnName = "id")
    private Nutrients nutrients;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vitamins_id", referencedColumnName = "id")
    private Vitamins vitamins;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "minerals_id", referencedColumnName = "id")
    private Minerals minerals;

    public Integer getNutrientsId() {
        return getNutrients().getId();
    }

    public Integer getVitaminsId() {
        return getVitamins().getId();
    }

    public Integer getMineralsId() {
        return getMinerals().getId();
    }

    public ObjectNode toJson() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("id", this.getId());
        node.put("type", this.getType());
        node.put("category", this.getCategory());
        node.put("description", this.getDescription());
        node.put("assets", this.getAssets().toJson());
        node.put("nutrients", this.getNutrients().toJson());
        node.put("vitamins", this.getVitamins().toJson());
        return node;
    }

}
