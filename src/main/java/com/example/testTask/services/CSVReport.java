package com.example.testTask.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CSVReport {

    public void createCSVFile(String[] headers, Map<String, String> map) throws IOException {

        FileWriter out = new FileWriter("report.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
            map.forEach((currency, values)->{
                try {
                    printer.printRecord(currency);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
