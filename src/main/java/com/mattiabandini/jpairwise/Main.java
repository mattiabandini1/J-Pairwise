package com.mattiabandini.jpairwise;

import com.mattiabandini.jpairwise.engine.IpogStrategy;
import com.mattiabandini.jpairwise.io.JavaTestGenerator;
import com.mattiabandini.jpairwise.model.Parameter;
import com.mattiabandini.jpairwise.model.TestCase;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Parameter browser = new Parameter("Browser", List.of("Chrome", "Firefox"));
        Parameter os = new Parameter("OS", List.of("Windows", "Linux", "Mac"));
        Parameter language = new Parameter("Language", List.of("English", "Italian", "Spanish"));

        IpogStrategy strategy = new IpogStrategy();
        List<TestCase> tests = strategy.generateTestCase(List.of(browser, os, language));

        System.out.println("Generated tests: " + tests.size());
        for (TestCase test : tests) {
            System.out.println(test);
        }

        JavaTestGenerator generator = new JavaTestGenerator();
        try {
            String targetPackage = "com.mattiabandini.jpairwise";
            generator.generateFile(List.of(browser, os, language), tests, "GeneratedTest", targetPackage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}