package com.kCalControl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "assets")
public class Assets {

    @Id
    private ObjectId id;

    private ObjectId creationPerson;
    private LocalDateTime creationDate;
    private ObjectId modificationPerson;
    private LocalDateTime modificationDate;

}
