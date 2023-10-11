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
//TODO test these endpoints
    @GetMapping("calcBM")
    @ResponseBody
    ResponseEntity<RetrieveBMDataDTO> bmCalculator();
    @PutMapping("updateBMCalc")
    ResponseEntity<String> updateBMCalc(@RequestBody UpdateBMDataDTO dto);
    @PutMapping("updateBMData")
    ResponseEntity<String> updateBMData(@RequestBody UpdatePersonalDataDTO dto);

}
