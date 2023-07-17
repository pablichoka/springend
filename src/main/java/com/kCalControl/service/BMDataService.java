package com.kCalControl.service;

import com.kCalControl.dto.BMDataDTO;
import com.kCalControl.model.BMData;
import org.bson.types.ObjectId;

public interface BMDataService {
    BMData returnBMLoggedUser();

    BMData returnUserBMByUserDBId(ObjectId id);

    void saveData(BMDataDTO dto);

    Double calculateBaseBM();

    Double calculateFinalBM(String dietType, Integer numDaysEx);
}
