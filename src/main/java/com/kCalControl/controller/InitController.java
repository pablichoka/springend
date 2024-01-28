package com.kCalControl.controller;

import com.kCalControl.dto.home.Index;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface InitController {
    @GetMapping("/index")
    @ResponseBody
    ResponseEntity<Index> webIndex();

}
