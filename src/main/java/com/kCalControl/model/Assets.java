package com.kCalControl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Assets")
public class Assets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer creationPerson;

    private LocalDateTime creationDate;

    private Integer modificationPerson;

    private LocalDateTime modificationDate;

    public ObjectNode toJson() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("id", this.getId());
        node.put("creationPerson", this.getCreationPerson());
        node.put("creationDate", this.getCreationDate().toString());
        node.put("modificationPerson", this.getModificationPerson());
        node.put("modificationDate", this.getModificationDate().toString());
        return node;
    }
}