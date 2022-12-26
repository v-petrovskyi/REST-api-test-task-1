package com.example.testTask.services;


import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;


@Service
public class CSVReport {

    public String createCSVFile(List<String> list) {
        try (PrintWriter printWriter = new PrintWriter("report.csv")) {
            for (String s : list) {
                printWriter.write(s + "\n");
            }
            return "report created";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "report NOT created";
        }
    }
}
