package com.kCalControl.controller.impl;

import com.kCalControl.config.Checker;
import com.kCalControl.controller.BMDataController;
import com.kCalControl.dto.bmdata.RetrieveBMDataDTO;
import com.kCalControl.dto.bmdata.UpdateBMDataDTO;
import com.kCalControl.dto.bmdata.UpdatePersonalDataDTO;
import com.kCalControl.exceptions.NetworkException;
import com.kCalControl.model.BMData;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.service.BMDataService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BMDataControllerImpl implements BMDataController {
    @Autowired
    BMDataService BMDataService;
    @Autowired
    BMDataRepository BMDataRepository;
    @Autowired
    Checker checker;

    @Override
    public ResponseEntity<RetrieveBMDataDTO> bmCalculator(Integer id){
        if(checker.checkValidUser(id)){
            throw new NetworkException("Valid user check failed", HttpStatus.FORBIDDEN);
        }
        BMData bmData = BMDataService.returnBMDataByUserDBId(id);
        BMDataService.calculateBaseBM(bmData);
        BMDataService.calculateFinalBM(bmData, bmData.getDietType(), bmData.getNumDaysEx());
        return ResponseEntity.ok(new RetrieveBMDataDTO(bmData));
    }

    @Override
    public ResponseEntity<String> updateBMCalc(Integer id, UpdateBMDataDTO dto) {
        if(checker.checkValidUser(id)){
            throw new NetworkException("Valid user check failed", HttpStatus.FORBIDDEN);
        }
        BMData bmData = BMDataService.saveCalc(id, dto);
        BMDataService.calculateBaseBM(bmData);
        BMDataService.calculateFinalBM(bmData, dto.getDietType(), dto.getNumDaysEx());
        BMDataRepository.save(bmData);
        return ResponseEntity.ok("BM calculation updated successfully");
    }

    @Override
    public ResponseEntity<String> updateBMData(Integer id, UpdatePersonalDataDTO dto) {
        if(checker.checkValidUser(id)){
            throw new NetworkException("Valid user check failed", HttpStatus.FORBIDDEN);
        }
        BMData bmData = BMDataService.saveData(id, dto);
        BMDataRepository.save(bmData);
        return ResponseEntity.ok("BM data updated successfully");
    }
}
