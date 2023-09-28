package com.kCalControl.controller.impl;

import com.kCalControl.controller.BMDataController;
import com.kCalControl.dto.bmdata.UpdateBMDataDTO;
import com.kCalControl.dto.bmdata.UpdatePersonalDataDTO;
import com.kCalControl.model.BMData;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.service.BMDataService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BMDataControllerImpl implements BMDataController {
    @Autowired
    BMDataService BMDataService;
    @Autowired
    BMDataRepository BMDataRepository;

    @Override
    public ResponseEntity<String> bmCalculator(){
        BMData bmData = BMDataService.returnBMDataLoggedUser();
        BMDataService.calculateBaseBM(bmData);
        BMDataService.calculateFinalBM(bmData, bmData.getDietType(), bmData.getNumDaysEx());
        return ResponseEntity.ok(bmData.toJSON());
    }

    @Override
    public ResponseEntity<Void> updateBMCalc(UpdateBMDataDTO dto, HttpServletResponse httpServletResponse) {
        BMData bmData = BMDataService.saveCalc(dto);
        BMDataService.calculateBaseBM(bmData);
        BMDataService.calculateFinalBM(bmData, dto.getDietType(), dto.getNumDaysEx());
        BMDataRepository.save(bmData);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateBMData(UpdatePersonalDataDTO dto, HttpServletResponse httpServletResponse) {
        BMData bmData = BMDataService.saveData(dto);
        BMDataRepository.save(bmData);
        return ResponseEntity.ok().build();
    }
}
