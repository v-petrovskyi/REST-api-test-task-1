package com.example.testTask.services;


import com.example.testTask.entity.Price;

import java.util.List;

public interface PriceService {
    Price add(Price price);
    Price getMin(String currency);
    Price getMax(String currency);
    List<Price> getAll();
}

