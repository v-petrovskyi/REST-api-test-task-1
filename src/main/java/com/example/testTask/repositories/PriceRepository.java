package com.example.testTask.repositories;

import com.example.testTask.entity.Price;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceRepository extends MongoRepository<Price, String> {

//    public Price getPriceBySymbol1AndSymbol2OrderByLpriceAsc(String symbol1, String symbol2);
//    public Price getPriceBySymbol1AndSymbol2OrderByLpriceDesc(String symbol1, String symbol2);

}
