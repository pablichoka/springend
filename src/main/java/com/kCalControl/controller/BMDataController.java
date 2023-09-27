package com.kCalControl.controller;

import com.kCalControl.dto.update.UpdateBMDataDTO;
import com.kCalControl.dto.update.UpdatePersonalDataDTO;
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
    void updateBMCalc(@RequestBody UpdateBMDataDTO dto, HttpServletResponse httpServletResponse);
    @PostMapping("bmdata/updateBMData")
    void updateBMData(@RequestBody UpdatePersonalDataDTO dto, HttpServletResponse httpServletResponse);

}
