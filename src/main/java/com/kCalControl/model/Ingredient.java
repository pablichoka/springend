package com.kCalControl.model;

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

}
