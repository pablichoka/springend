package com.kCalControl.dto.nutrients;

import com.kCalControl.model.Nutrients;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetrieveMineralsDTO {

    Double calcium;
    Double copper;
    Double iron;
    Double magnesium;
    Double phosphorus;
    Double potassium;
    Double sodium;
    Double zinc;

    public RetrieveMineralsDTO(Nutrients nutrients){
        calcium = nutrients.getCalcium();
        copper = nutrients.getCopper();
        iron = nutrients.getIron();
        magnesium = nutrients.getMagnesium();
        phosphorus = nutrients.getPhosphorus();
        potassium = nutrients.getPotassium();
        sodium = nutrients.getSodium();
        zinc = nutrients.getZinc();
    }

}
