package com.example.testTask.services;


import com.example.testTask.entity.Price;

import java.util.List;

public interface PriceService {
    Price add(Price price);
    Price getMin(String symbol1, String symbol2);
    Price getMax(String symbol1, String symbol2);
    List<Price> getAll();
}

