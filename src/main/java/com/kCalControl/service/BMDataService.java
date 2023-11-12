package com.kCalControl.service;

import com.kCalControl.dto.bmdata.UpdateBMDataDTO;
import com.kCalControl.dto.bmdata.UpdatePersonalDataDTO;
import com.kCalControl.model.BMData;
import org.bson.types.ObjectId;

public interface BMDataService {
    BMData returnBMDataByUserDBId(Integer id);

    BMData saveData(Integer id, UpdatePersonalDataDTO personalDataDTO);

    BMData saveCalc(Integer id, UpdateBMDataDTO dto);

    void calculateBaseBM(BMData bmData);

    void calculateFinalBM(BMData bmData, String dietType, Integer numDaysEx);
}
