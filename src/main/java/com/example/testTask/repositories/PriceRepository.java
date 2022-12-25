package com.example.testTask.repositories;

import com.example.testTask.entity.Price;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PriceRepository extends MongoRepository<Price, String> {
     Price getFirstByCurr1AndCurr2OrderByLpriceAsc(String curr1, String curr2);
     Price getFirstByCurr1AndCurr2OrderByLpriceDesc(String curr1, String curr2);
     List<Price> getPriceByCurr1AndCurr2OrderByLpriceAsc(String curr1, String curr2);

}
