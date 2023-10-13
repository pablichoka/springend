package com.kCalControl.controller.impl;

import com.kCalControl.config.Checker;
import com.kCalControl.controller.BMDataController;
import com.kCalControl.dto.bmdata.RetrieveBMDataDTO;
import com.kCalControl.dto.bmdata.UpdateBMDataDTO;
import com.kCalControl.dto.bmdata.UpdatePersonalDataDTO;
import com.kCalControl.model.BMData;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.service.BMDataService;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class BMDataControllerImpl implements BMDataController {
    @Autowired
    BMDataService BMDataService;
    @Autowired
    BMDataRepository BMDataRepository;
    @Autowired
    Checker checker;

    @Override
    public ResponseEntity<RetrieveBMDataDTO> bmCalculator(ObjectId id){
        checker.checkValidUser(id);
        BMData bmData = BMDataService.returnBMDataByUserDBId(id);
        BMDataService.calculateBaseBM(bmData);
        BMDataService.calculateFinalBM(bmData, bmData.getDietType(), bmData.getNumDaysEx());
        return ResponseEntity.ok(new RetrieveBMDataDTO(bmData));
    }

    @Override
    public ResponseEntity<String> updateBMCalc(ObjectId id, UpdateBMDataDTO dto) {
        checker.checkValidUser(id);
        BMData bmData = BMDataService.saveCalc(id, dto);
        BMDataService.calculateBaseBM(bmData);
        BMDataService.calculateFinalBM(bmData, dto.getDietType(), dto.getNumDaysEx());
        BMDataRepository.save(bmData);
        return ResponseEntity.ok("BM calculation updated successfully");
    }

    @Override
    public ResponseEntity<String> updateBMData(ObjectId id, UpdatePersonalDataDTO dto) {
        checker.checkValidUser(id);
        BMData bmData = BMDataService.saveData(id, dto);
        BMDataRepository.save(bmData);
        return ResponseEntity.ok("BM data updated successfully");
    }
}
