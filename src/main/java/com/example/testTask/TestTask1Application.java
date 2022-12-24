package com.example.testTask;

import com.example.testTask.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class TestTask1Application implements CommandLineRunner {

    @Autowired
    private PriceRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(TestTask1Application.class, args);
    }

    public void run(String... args){
        repository.save("BTC", "USD", 1535.8545, System.currentTimeMillis());

    }

}
