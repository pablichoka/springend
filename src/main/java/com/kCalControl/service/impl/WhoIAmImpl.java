package com.kCalControl.service.impl;

import com.kCalControl.service.WhoIAm;
import org.bson.types.ObjectId;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class WhoIAmImpl implements WhoIAm {

    @Override
    public ObjectId whoIAm() {
        return new ObjectId(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
