package com.example.testTask.services.impl;

import com.example.testTask.entity.Price;
import com.example.testTask.repositories.PriceRepository;
import com.example.testTask.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository repository;

    @Autowired
    public PriceServiceImpl(PriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Price add(Price price) {
        return repository.save(price);
    }

    @Override
    public Price getMin(String symbol1, String symbol2) {
//        return repository.getPriceBySymbol1AndSymbol2OrderByLpriceAsc(symbol1, symbol2);
        return null;
    }

    @Override
    public Price getMax(String symbol1, String symbol2) {
//        return repository.getPriceBySymbol1AndSymbol2OrderByLpriceDesc(symbol1, symbol2);
        return null;
    }

    @Override
    public List<Price> getAll() {
        return repository.findAll();
    }
}
