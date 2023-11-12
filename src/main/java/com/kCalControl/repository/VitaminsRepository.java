package com.kCalControl.repository;

import com.kCalControl.model.Nutrients;
import com.kCalControl.model.Vitamins;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface VitaminsRepository extends CrudRepository<Vitamins, Integer> {

}
