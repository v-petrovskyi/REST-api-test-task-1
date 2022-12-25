package com.example.testTask.services;

import com.example.testTask.entity.PairPrice;
import com.example.testTask.util.PairPriceToPrice;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WriteToDB {
    private final Map<String, String> lastPricesMap = new HashMap<>();

    private final PriceService priceService;

    public WriteToDB(PriceService priceService) {
        this.priceService = priceService;
    }

    public void write(String curr1, String curr2) {
        PairPrice pairPrice = new CexIoJson(new RestTemplate()).getLastPrice(curr1, curr2);
        System.out.println(pairPrice);
        String key = curr1 + "/" + curr2;
        if (!lastPricesMap.containsKey(key)) {
            lastPricesMap.put(key, pairPrice.getLprice());
            try {
                priceService.add(PairPriceToPrice.convertToPrice(pairPrice));
            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println(pairPrice);
            }
        }
        if (lastPricesMap.get(key).equals(pairPrice.getLprice())){
            return;
        }
        lastPricesMap.put(key, pairPrice.getLprice());
        try {
            priceService.add(PairPriceToPrice.convertToPrice(pairPrice));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
