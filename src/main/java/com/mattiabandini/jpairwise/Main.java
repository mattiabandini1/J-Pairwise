package com.mattiabandini.jpairwise;

import com.mattiabandini.jpairwise.engine.IpogStrategy;
import com.mattiabandini.jpairwise.io.JavaTestGenerator;
import com.mattiabandini.jpairwise.io.JsonParser;
import com.mattiabandini.jpairwise.model.InputData;
import com.mattiabandini.jpairwise.model.TestCase;

import java.util.List;

/**
 * Entry point for the J-Pairwise application.
 * <p>
 * This CLI tool reads a JSON configuration file, generates a pairwise test suite
 * using the IPOG algorithm, and writes a Java JUnit 5 test file.
 * </p>
 */
public class Main {

    /**
     * Main method.
     *
     * @param args Command line arguments. Expects the path to the JSON config file.
     *             If omitted, defaults to "demo_config.json".
     */
    public static void main(String[] args) {
        try {
            System.out.println("---J-PAIRWISE STARTED---");

            String jsonPath = "demo_config.json";

            // CLI Argument Handling
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

            // 1. Parsing: Load configuration
            JsonParser parser = new JsonParser();
            InputData input = parser.readInput(jsonPath);

            // 2. Generation: Run the IPOG algorithm
            IpogStrategy strategy = new IpogStrategy();
            List<TestCase> tests = strategy.generateTestCase(input.getParameters());

            System.out.println("Test case generated: " + tests.size());

            // 3. Output: Write the Java test file
            JavaTestGenerator generator = new JavaTestGenerator();
            generator.generateFile(input, tests);

            System.out.println("--- DONE ---");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
