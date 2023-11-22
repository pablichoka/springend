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
    @Column(name = "creation_person_id", nullable = false)
    private Integer creationPerson;                //TODO consider to change from User to integer -> More steps but simpler
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;
    @Column(name = "modification_person_id", nullable = false)
    private Integer modificationPerson;            //TODO consider to change from User to integer -> More steps but simpler
    @Column(name = "modification_date", nullable = false)
    private Date modificationDate;

    public Assets(Integer userID, Date from, Integer userID1, Date from1) {
        this.creationPerson = userID;
        this.creationDate = from;
        this.modificationPerson = userID1;
        this.modificationDate = from1;
    }


    public ObjectNode toJson(String creationPerson, String modificationPerson) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("creationPerson", creationPerson);
        node.put("creationDate", this.getCreationDate().toString());
        node.put("modificationPerson", modificationPerson);
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
        return Objects.hash(id, creationPerson, creationDate, modificationPerson, modificationDate);
    }

    @Override
    public String toString() {
        return "Assets{" +
                "id=" + id +
                ", creationPerson=" + creationPerson + //TODO check why this.creationPerson is null || BLOCKS ANY ACTION
                ", creationDate=" + creationDate +
                ", modificationPerson=" + modificationPerson + //TODO check why this.modificationPerson is null || BLOCKS ANY ACTION
                ", modificationDate=" + modificationDate +
                '}';
    }
}