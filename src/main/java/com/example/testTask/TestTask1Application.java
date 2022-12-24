package com.example.testTask;

import com.example.testTask.entity.Price;
import com.example.testTask.repositories.PriceRepository;
import com.example.testTask.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class TestTask1Application implements CommandLineRunner {

    @Autowired
    private PriceService priceService;

    public static void main(String[] args) {
        SpringApplication.run(TestTask1Application.class, args);
    }

    @Override
    public void run(String... args) throws InterruptedException {
        priceService.add(new Price("BTC", "USD", 1535.8545, LocalDateTime.now()));
        Thread.sleep(1000);
        priceService.add(new Price("BTC", "USD", 15632.11, LocalDateTime.now()));
        Thread.sleep(1000);
        priceService.add(new Price("BTC", "USD", 1534.25, LocalDateTime.now()));

        for (Price price : priceService.getAll()) {
            System.out.println(price);
        }


    }

}
