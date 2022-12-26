package com.example.testTask.services.impl;

import com.example.testTask.entity.PairPrice;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CexIoJsonTest {
    private final RestTemplate restTemplate = new RestTemplate();


    @Test
    void getLastPriceFromURL_Should_ReturnObjectOfClassPairPrice() {
        CexIoJson cexIoJson = new CexIoJson(restTemplate);
        String expected1 = "BTC";
        String expected2 = "USD";

        PairPrice lastPriceFromURL = cexIoJson.getLastPriceFromURL(expected1, expected2);

        String actual1 = lastPriceFromURL.getCurr1();
        String actual2 = lastPriceFromURL.getCurr2();
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }
}