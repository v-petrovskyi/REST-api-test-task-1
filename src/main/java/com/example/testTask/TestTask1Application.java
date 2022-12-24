package com.example.testTask;

import com.example.testTask.entity.PairPrice;
import com.example.testTask.entity.Price;
import com.example.testTask.repositories.PriceRepository;
import com.example.testTask.services.CexIoJson;
import com.example.testTask.services.PriceService;
import com.example.testTask.util.PairPriceToPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@SpringBootApplication
public class TestTask1Application implements CommandLineRunner {

    @Autowired
    private PriceService priceService;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(TestTask1Application.class, args);
    }


    @Override
    public void run(String... args) {
        Thread thread = new Thread(() -> {
            while (true) {
                PairPrice pairPriceBTC = new CexIoJson(restTemplate()).getLastPrice("BTC", "USD");
                priceService.add(PairPriceToPrice.convertToPrice(pairPriceBTC));
                PairPrice pairPriceETH = new CexIoJson(restTemplate()).getLastPrice("ETH", "USD");
                priceService.add(PairPriceToPrice.convertToPrice(pairPriceETH));
                PairPrice pairPriceXRP = new CexIoJson(restTemplate()).getLastPrice("XRP", "USD");
                priceService.add(PairPriceToPrice.convertToPrice(pairPriceXRP));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


}
