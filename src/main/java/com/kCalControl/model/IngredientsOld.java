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
@Document(collection = "ingredients_old")
public class IngredientsOld {

    //TODO insert old model to recognize inserted ingredients
    @Id
    ObjectId id;
    @Field("Category")
    String category;
    @Field("Description")
    String description;
    @DBRef()
    @Field("Data")
    Nutrients nutrients;


}
