package com.kCalControl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "BM_Data")
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

    @OneToOne(mappedBy = "bmData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserDB userAssoc;

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

}
