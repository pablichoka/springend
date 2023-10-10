package com.kCalControl.controller;

import com.kCalControl.dto.bmdata.UpdateBMDataDTO;
import com.kCalControl.dto.bmdata.UpdatePersonalDataDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public interface BMDataController {

    @GetMapping("bmdata/calcBM")
    @ResponseBody
    ResponseEntity<String> bmCalculator();
    @PostMapping("bmdata/updateBMCalc")
    ResponseEntity<Void> updateBMCalc(@RequestBody UpdateBMDataDTO dto);
    @PostMapping("bmdata/updateBMData")
    ResponseEntity<Void> updateBMData(@RequestBody UpdatePersonalDataDTO dto);

}
