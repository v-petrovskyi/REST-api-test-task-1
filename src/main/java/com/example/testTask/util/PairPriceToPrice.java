package com.example.testTask.util;

import com.example.testTask.entity.PairPrice;
import com.example.testTask.entity.Price;

import java.time.LocalDateTime;

public class PairPriceToPrice {
    public static Price convertToPrice(PairPrice pairPrice){
        return new Price(pairPrice.getCurr1(), pairPrice.getCurr2(), Double.valueOf(pairPrice.getLprice()), LocalDateTime.now());

    }
}
