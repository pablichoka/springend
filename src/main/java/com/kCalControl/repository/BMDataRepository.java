package com.kCalControl.repository;

import com.kCalControl.model.BMData;
import com.kCalControl.model.UserDB;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BMDataRepository extends CrudRepository<BMData, Integer> {
    BMData findByUserAssoc(UserDB userAssoc);
    BMData findByUserAssoc_Id(Integer id);

}
