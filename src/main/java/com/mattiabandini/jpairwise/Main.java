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

            String jsonPath = "demo_config.json";

            if (args.length > 0) {
                if (args[0].equals("--help") || args[0].equals("-h")) {
                    System.out.println("Usage: java -jar jpairwise.jar [path/to/config.json]");
                    System.out.println("If no file is specified, 'demo_config.json' will be used.");
                    return;
                }

                jsonPath = args[0];
                System.out.println("Custom configuration selected: " + jsonPath);
            } else {
                System.out.println("No file specified. Using default: " + jsonPath);
            }

            JsonParser parser = new JsonParser();
            InputData input = parser.readInput(jsonPath);

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