package com.kCalControl.dto.nutrients;

import com.kCalControl.model.Nutrients;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetrieveVitaminsDTO {

    Double vitaminA;
    Double vitaminB12;
    Double vitaminB6;
    Double vitaminC;
    Double vitaminE;
    Double vitaminK;

    public RetrieveVitaminsDTO(Nutrients nutrients){
        vitaminA = nutrients.getVitaminA();
        vitaminB12 = nutrients.getVitaminB12();
        vitaminB6 = nutrients.getVitaminB6();
        vitaminC = nutrients.getVitaminC();
        vitaminE = nutrients.getVitaminE();
        vitaminK = nutrients.getVitaminK();
    }

}
