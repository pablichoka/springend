package com.kCalControl.repository;

import com.kCalControl.model.Minerals;
import com.kCalControl.model.Nutrients;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface MineralsRepository extends CrudRepository<Minerals, Integer> {

}
