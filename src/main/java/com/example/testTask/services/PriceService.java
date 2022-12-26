package com.example.testTask.services;


import com.example.testTask.entity.Price;
import com.example.testTask.exeptions.CurrencyException;

import java.util.List;
import java.util.Map;

public interface PriceService {
    Price add(Price price);
    Price getMin(String currency) throws CurrencyException;
    Price getMax(String currency) throws CurrencyException;
    List<Price> getAll();
    List<Price> getSelectedPageOfCurrencies(String currency, int page, int size) throws CurrencyException;
    Map<String, String> mapForCSVReport() throws CurrencyException;
}

