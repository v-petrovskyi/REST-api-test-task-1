package com.example.testTask.services;

import com.example.testTask.entity.PairPrice;
import com.example.testTask.entity.Price;
import com.example.testTask.services.impl.CexIoJson;
import com.example.testTask.util.PairPriceToPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class WriteToDB {
    private final Map<String, String> lastPricesMap = new HashMap<>();

    @Autowired
    private PriceService priceService;

    public void write(String curr1, String curr2) {
        PairPrice lastPriceFromURL = new CexIoJson(new RestTemplate()).getLastPriceFromURL(curr1, curr2);
        String key = curr1 + "/" + curr2;
        Price price = null;
        try {
            price = PairPriceToPrice.convertToPrice(lastPriceFromURL, LocalDateTime.now());
        } catch (Exception e) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            write(curr1, curr2);
        }
        if (!lastPricesMap.containsKey(key)) {
            try {
                priceService.add(price);
                lastPricesMap.put(key, lastPriceFromURL.getLprice());
                return;
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        if (lastPricesMap.get(key).equals(lastPriceFromURL.getLprice())) {
            return;
        }
        try {
            priceService.add(price);
            lastPricesMap.put(key, lastPriceFromURL.getLprice());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
