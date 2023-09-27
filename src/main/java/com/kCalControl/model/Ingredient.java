package com.kCalControl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "ingredients")
public class Ingredient {

    @Id
    ObjectId id;
    @Field("type")
    String type;
    @Field("category")
    String category;
    @Field("description")
    String description;
    @DBRef
    @Field("nutrients")
    Nutrients nutrients;

    public String toJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode object2JSON = objectMapper.createObjectNode();
        object2JSON.put("type", this.type);
        object2JSON.put("category", this.category);
        object2JSON.put("description", this.description);
        return object2JSON.toString();
    }

}
