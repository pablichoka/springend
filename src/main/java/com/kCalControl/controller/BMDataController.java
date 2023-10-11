package com.kCalControl.controller;

import com.kCalControl.dto.bmdata.RetrieveBMDataDTO;
import com.kCalControl.dto.bmdata.UpdateBMDataDTO;
import com.kCalControl.dto.bmdata.UpdatePersonalDataDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bm-data")
public interface BMDataController {
//TODO specify an ID, otherwise it only would work for one user
    @GetMapping("calcBM")
    @ResponseBody
    ResponseEntity<RetrieveBMDataDTO> bmCalculator();
    @PostMapping("save-bm-calc")
    ResponseEntity<String> updateBMCalc(@RequestBody UpdateBMDataDTO dto);
    @PostMapping("save-bm-data")
    ResponseEntity<String> updateBMData(@RequestBody UpdatePersonalDataDTO dto);

}
