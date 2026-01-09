package com.mattiabandini.jpairwise;

import com.mattiabandini.jpairwise.engine.IpogStrategy;
import com.mattiabandini.jpairwise.io.JavaTestGenerator;
import com.mattiabandini.jpairwise.io.JsonParser;
import com.mattiabandini.jpairwise.model.InputData;
import com.mattiabandini.jpairwise.model.TestCase;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("---J-PAIRWISE STARTED---");

            JsonParser parser = new JsonParser();
            InputData input = parser.readInput("input.json");

            System.out.println("Input loaded successfully");
            System.out.println("Target class: " + input.getClassName());
            System.out.println("Parameters found: " + input.getParameters().size());

            IpogStrategy strategy = new IpogStrategy();
            List<TestCase> tests = strategy.generateTestCase(input.getParameters());

            System.out.println("Test case generated: " + tests.size());

            JavaTestGenerator generator = new JavaTestGenerator();
            generator.generateFile(input, tests);

            System.out.println("--- DONE ---");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}