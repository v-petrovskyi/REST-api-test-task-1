package com.example.testTask.controllers;

import com.example.testTask.entity.Price;
import com.example.testTask.services.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cryptocurrencies")
public class Controller {

    private final PriceService priceService;

    public Controller(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/minprice")
    public Price returnMinPrice(@RequestParam("name") String currencyName) {
        return priceService.getMin(currencyName);
    }

    @GetMapping("/maxprice")
    public Price returnManPrice(@RequestParam("name") String currencyName) {
        return priceService.getMax(currencyName);
    }

    @GetMapping("/all")
    public List<Price> returnAll() {
        return priceService.getAll();
    }

}
