package com.example.testTask.services;

import com.example.testTask.entity.PairPrice;

public interface UrlApi {
    PairPrice getLastPriceFromURL(String curr1, String curr2);
}
