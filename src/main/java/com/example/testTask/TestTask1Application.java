package com.example.testTask;

import com.example.testTask.services.WriteToDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class TestTask1Application implements CommandLineRunner {

    @Autowired
    private WriteToDB write;

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
                write.write("BTC", "USD");
                write.write("ETH", "USD");
                write.write("XRP", "USD");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


}
