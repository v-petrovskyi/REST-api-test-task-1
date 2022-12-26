package com.example.testTask;

import com.example.testTask.services.CSVReport;
import com.example.testTask.services.WriteToDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TestTask1Application {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Scope("singleton")
    WriteToDB writeToDB(){
        return new WriteToDB();
    }

    @Bean
    @Scope("singleton")
    CSVReport csvReport(){
        return new CSVReport();
    }

    public static void main(String[] args) {
        SpringApplication.run(TestTask1Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getDataAndWriteToDB() {
        Thread thread = new Thread(() -> {
            while (true) {
                writeToDB().write("BTC", "USD");
                writeToDB().write("ETH", "USD");
                writeToDB().write("XRP", "USD");
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
