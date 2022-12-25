package com.example.testTask.services;

import com.example.testTask.entity.PairPrice;
import com.example.testTask.util.PairPriceToPrice;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WriteToDB {

    private final PriceService priceService;

    public WriteToDB(PriceService priceService) {
        this.priceService = priceService;
    }

    public void write(String curr1, String curr2){
        PairPrice pairPriceBTC = new CexIoJson(new RestTemplate()).getLastPrice(curr1, curr2);
        try{
            priceService.add(PairPriceToPrice.convertToPrice(pairPriceBTC));
        } catch (NullPointerException e){
            e.printStackTrace();
        }

    }

}
