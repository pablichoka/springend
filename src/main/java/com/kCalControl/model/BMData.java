package com.kCalControl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bm_data")
public class BMData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer age;
    private Double weight;
    private Integer height;
    private String gender;
    @Column(name = "base_bm")
    private Double baseBm;
    @Column(name = "num_days_ex")
    private Integer numDaysEx;
    @Column(name = "diet_type")
    private String dietType;
    @Column(name = "total_bm")
    private Double totalBm;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "assets_id", referencedColumnName = "id")
    private Assets assets;

    @OneToOne(mappedBy = "bmData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User userAssoc;

    public String getUsername() {
        return getUserAssoc().getUsername();
    }

    public BMData(Integer age, Double weight, Integer height, String gender, Double baseBM, Integer numDaysEx, String dietType, Double totalBM) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.baseBm = baseBM;
        this.numDaysEx = numDaysEx;
        this.dietType = dietType;
        this.totalBm = totalBM;
    }

    public Integer getCreationPerson() {
        return getAssets().getCreationPerson();
    }

    public Date getCreationDate() {
        return getAssets().getCreationDate();
    }

    public Integer getModificationPerson() {
        return getAssets().getModificationPerson();
    }

    public Date getModificationDate() {
        return getAssets().getModificationDate();
    }

    public void setCreationPerson(Integer creationPerson) {
        getAssets().setCreationPerson(creationPerson);
    }

    public void setCreationDate(Date creationDate) {
        getAssets().setCreationDate(creationDate);
    }

    public void setModificationPerson(Integer modificationPerson) {
        getAssets().setModificationPerson(modificationPerson);
    }

    public void setModificationDate(Date modificationDate) {
        getAssets().setModificationDate(modificationDate);
    }

    public String toJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode object2JSON = objectMapper.createObjectNode();
        object2JSON.put("age", this.age);
        object2JSON.put("weight", this.weight);
        object2JSON.put("height", this.height);
        object2JSON.put("gender", this.gender);
        object2JSON.put("baseBM", this.baseBm);
        object2JSON.put("numDaysEx", this.numDaysEx);
        object2JSON.put("dietType", this.dietType);
        object2JSON.put("totalBM", this.totalBm);
        return object2JSON.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BMData bmData)) return false;
        return Objects.equals(id, bmData.id) && Objects.equals(age, bmData.age) && Objects.equals(weight, bmData.weight) && Objects.equals(height, bmData.height) && Objects.equals(gender, bmData.gender) && Objects.equals(baseBm, bmData.baseBm) && Objects.equals(numDaysEx, bmData.numDaysEx) && Objects.equals(dietType, bmData.dietType) && Objects.equals(totalBm, bmData.totalBm) && Objects.equals(assets, bmData.assets) && Objects.equals(userAssoc, bmData.userAssoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, weight, height, gender, baseBm, numDaysEx, dietType, totalBm);
    }
}
