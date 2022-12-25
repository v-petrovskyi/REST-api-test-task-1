package com.example.testTask.services;

import com.example.testTask.entity.PairPrice;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CexIoJson {
    private final RestTemplate restTemplate;

    public CexIoJson(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PairPrice getLastPriceFromURL(String curr1, String curr2) {
        String URL_API_LAST_PRICE = "https://cex.io/api/last_price/";
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
//        PairPrice lastPrice = restTemplate.getForObject("https://cex.io/api/last_price/BTC/USD", PairPrice.class);
        PairPrice lastPrice = restTemplate.getForObject(URL_API_LAST_PRICE + curr1 + "/" + curr2, PairPrice.class);
        return lastPrice;
    }
}