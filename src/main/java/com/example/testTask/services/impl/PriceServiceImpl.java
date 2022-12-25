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
    public Price getMin(String currency) {
        String[] split = currency.split("/");
        return repository.getFirstByCurr1AndCurr2OrderByLpriceAsc(split[0], split[1]);
    }

    @Override
    public Price getMax(String currency) {
        String[] split = currency.split("/");
        return repository.getFirstByCurr1AndCurr2OrderByLpriceDesc(split[0], split[1]);
    }

    @Override
    public List<Price> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Price> getSelectedPageOfCurrencies(String currency, int page, int size) {
        String[] split = currency.split("/");
        repository.getPriceByCurr1AndCurr2OrderByLpriceAsc(split[0], split[1]);
        return null;
    }
}
