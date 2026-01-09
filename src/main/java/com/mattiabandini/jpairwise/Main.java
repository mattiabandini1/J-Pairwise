package com.mattiabandini.jpairwise;

import com.mattiabandini.jpairwise.engine.IpogStrategy;
import com.mattiabandini.jpairwise.engine.Pair;
import com.mattiabandini.jpairwise.model.Parameter;
import com.mattiabandini.jpairwise.model.TestCase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Parameter browser = new Parameter("Browser", List.of("Chrome", "Firefox"));
        Parameter os = new Parameter("OS", List.of("Windows", "Linux", "Mac"));

        IpogStrategy strategy = new IpogStrategy();
        List<TestCase> tests = strategy.generateTestCase(List.of(browser, os));

        System.out.println("Generated tests: " + tests.size());
        for (TestCase test : tests) {
            System.out.println(test);
        }
    }
}