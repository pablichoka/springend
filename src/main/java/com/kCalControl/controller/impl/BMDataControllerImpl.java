package com.kCalControl.controller.impl;

import com.kCalControl.controller.BMDataController;
import com.kCalControl.dto.update.UpdateBMDataDTO;
import com.kCalControl.dto.update.UpdatePersonalDataDTO;
import com.kCalControl.model.BMData;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.service.BMDataService;
import com.kCalControl.service.UserDBService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BMDataControllerImpl implements BMDataController {
    @Autowired
    UserDBService userDBService;
    @Autowired
    BMDataService BMDataService;
    @Autowired
    BMDataRepository BMDataRepository;
//    @Override
//    public String bmCalculator(Model model) {
//        model.addAttribute("user", userDBService.returnLoggedUser());
//        BMData bmData = BMDataService.returnBMDataLoggedUser();
//        BMDataService.calculateBaseBM(bmData);
//        BMDataService.calculateFinalBM(bmData, bmData.getDietType(), bmData.getNumDaysEx());
//        model.addAttribute("BMData", bmData);
//        model.addAttribute("BMDataDTO", new BMDataDTO());
//        model.addAttribute("personalDataDTO", new UpdatePersonalDataDTO());
//        return "/auth/views/calcBM";
//    }

    @Override
    public ResponseEntity<String> bmCalculator(){
        BMData bmData = BMDataService.returnBMDataLoggedUser();
        BMDataService.calculateBaseBM(bmData);
        BMDataService.calculateFinalBM(bmData, bmData.getDietType(), bmData.getNumDaysEx());
        return ResponseEntity.ok(bmData.toJSON());
    }

    @Override
    public void updateBMCalc(UpdateBMDataDTO dto, HttpServletResponse httpServletResponse) {
        BMData bmData = BMDataService.saveCalc(dto);
        BMDataService.calculateBaseBM(bmData);
        BMDataService.calculateFinalBM(bmData, dto.getDietType(), dto.getNumDaysEx());
        BMDataRepository.save(bmData);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void updateBMData(UpdatePersonalDataDTO dto, HttpServletResponse httpServletResponse) {
        BMData bmData = BMDataService.saveData(dto);
        BMDataRepository.save(bmData);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }
}
