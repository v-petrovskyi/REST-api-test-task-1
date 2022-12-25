package com.example.testTask.controllers;

import com.example.testTask.entity.Price;
import com.example.testTask.exeptions.CurrencyException;
import com.example.testTask.exeptions.ErrorResponse;
import com.example.testTask.services.PriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    private final PriceService priceService;

    public Controller(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/cryptocurrencies/minprice")
    public Price returnMinPrice(@RequestParam("name") String currencyName) throws CurrencyException {
            return priceService.getMin(currencyName);
    }

    @GetMapping("/cryptocurrencies/maxprice")
    public Price returnMaxPrice(@RequestParam("name") String currencyName) throws CurrencyException {
        return priceService.getMax(currencyName);
    }

    @GetMapping("/cryptocurrencies/all")
    public List<Price> returnAll() {
        return priceService.getAll();
    }

    @GetMapping("/cryptocurrencies")
    public List<Price> returnListOfPrices(@RequestParam("name") String currencyName, @RequestParam ("page") int page, @RequestParam ("size") int size){
        return priceService.getSelectedPageOfCurrencies(currencyName, page, size);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(CurrencyException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }




}
