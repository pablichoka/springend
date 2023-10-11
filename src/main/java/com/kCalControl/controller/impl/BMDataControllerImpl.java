package com.kCalControl.controller.impl;

import com.kCalControl.controller.BMDataController;
import com.kCalControl.dto.bmdata.RetrieveBMDataDTO;
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
    public ResponseEntity<RetrieveBMDataDTO> bmCalculator(){
        BMData bmData = BMDataService.returnBMDataLoggedUser();
        BMDataService.calculateBaseBM(bmData);
        BMDataService.calculateFinalBM(bmData, bmData.getDietType(), bmData.getNumDaysEx());
        return ResponseEntity.ok(new RetrieveBMDataDTO(bmData));
    }

    @Override
    public ResponseEntity<String> updateBMCalc(UpdateBMDataDTO dto) {
        BMData bmData = BMDataService.saveCalc(dto);
        BMDataService.calculateBaseBM(bmData);
        BMDataService.calculateFinalBM(bmData, dto.getDietType(), dto.getNumDaysEx());
        BMDataRepository.save(bmData);
        return ResponseEntity.ok("BM calculation updated successfully");
    }

    @Override
    public ResponseEntity<String> updateBMData(UpdatePersonalDataDTO dto) {
        BMData bmData = BMDataService.saveData(dto);
        BMDataRepository.save(bmData);
        return ResponseEntity.ok("BM data updated successfully");
    }
}
