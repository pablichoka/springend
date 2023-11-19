package com.kCalControl.repository;

import com.kCalControl.model.BMData;
import com.kCalControl.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BMDataRepository extends CrudRepository<BMData, Integer> {
    BMData findByUserAssoc(User userAssoc);
    BMData findByUserAssoc_Id(Integer id);
}
