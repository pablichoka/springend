package com.kCalControl.controller.impl;

import com.kCalControl.controller.InitController;
import com.kCalControl.dto.home.Index;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InitControllerImpl implements InitController {

    @Override
    public ResponseEntity<Index> webIndex() {
            return ResponseEntity.ok(new Index("Aye"));
    }
}
