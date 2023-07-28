package com.kCalControl.repository;

import com.kCalControl.model.IngredientsOld;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IngredientsOldRepository extends MongoRepository<IngredientsOld, ObjectId> {

    List<IngredientsOld> findByCategoryLike(String category);

}
