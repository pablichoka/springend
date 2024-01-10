package com.kCalControl.controller;

import com.kCalControl.dto.bmdata.RetrieveBMDataDTO;
import com.kCalControl.dto.bmdata.UpdateBMDataDTO;
import com.kCalControl.dto.bmdata.UpdatePersonalDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/", "/mobile"})
public interface BMDataController {
    @GetMapping("calc-bm-data/{id}")
    @ResponseBody
    ResponseEntity<RetrieveBMDataDTO> bmCalculator(@PathVariable Integer id);

    @PostMapping("save-bm-calc/{id}")
    ResponseEntity<String> updateBMCalc(@PathVariable Integer id, @RequestBody UpdateBMDataDTO dto);

    @PostMapping("save-bm-data/{id}")
    ResponseEntity<String> updateBMData(@PathVariable Integer id, @RequestBody UpdatePersonalDataDTO dto);

}
