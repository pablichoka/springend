package com.kCalControl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assets")
public class Assets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "creation_person_id")
    private User creationPerson;

    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "modification_person_id")
    private User modificationPerson;

    private Date modificationDate;

    public Assets(User user, Date from, User user1, Date from1) {
        this.creationPerson = user;
        this.creationDate = from;
        this.modificationPerson = user1;
        this.modificationDate = from1;
    }


    public ObjectNode toJson() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("id", this.getId());
        node.put("creationPerson", this.getCreationPerson().toJson());
        node.put("creationDate", this.getCreationDate().toString());
        node.put("modificationPerson", this.getModificationPerson().toJson());
        node.put("modificationDate", this.getModificationDate().toString());
        return node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assets assets)) return false;
        return Objects.equals(id, assets.id) && Objects.equals(creationPerson, assets.creationPerson) && Objects.equals(creationDate, assets.creationDate) && Objects.equals(modificationPerson, assets.modificationPerson) && Objects.equals(modificationDate, assets.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationDate, modificationDate);
    }
}