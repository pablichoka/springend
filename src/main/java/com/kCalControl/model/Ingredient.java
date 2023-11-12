package com.kCalControl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String type;

    private String category;

    private String description;

    @OneToOne(mappedBy = "ingredient")
    private Assets assets;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nutrients_id", referencedColumnName = "id")
    private Nutrients nutrients;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vitamins_id", referencedColumnName = "id")
    private Vitamins vitamins;


    public String toJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode object2JSON = objectMapper.createObjectNode();
        object2JSON.put("type", this.type);
        object2JSON.put("category", this.category);
        object2JSON.put("description", this.description);
        return object2JSON.toString();
    }

}
