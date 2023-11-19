package com.kCalControl.repository;

import com.kCalControl.model.IngredientsOld;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientsOldRepository extends CrudRepository<IngredientsOld, Integer> {

    List<IngredientsOld> findByCategoryLike(String category);

}
