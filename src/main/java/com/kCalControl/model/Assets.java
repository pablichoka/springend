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

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection= "assets")
public class Assets {

    @Id
    private ObjectId id;
    //TODO add field tags and try to implement filter by date
    @DBRef
    private UserDB creationPerson;
    private LocalDateTime creationDate;
    @DBRef
    private UserDB modificationPerson;
    private LocalDateTime modificationDate;

}
