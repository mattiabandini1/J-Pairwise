package com.mattiabandini.jpairwise;

import com.mattiabandini.jpairwise.model.Parameter;
import com.mattiabandini.jpairwise.model.TestCase;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Parameter p1 = new Parameter("Browser", List.of("Chrome", "Firefox"));
        System.out.println("Parameter created:" + p1);

        // Uncomment to test if this parameter throws an exception
        // Parameter pError = new Parameter("Error", List.of());

        TestCase testCase = new TestCase();
        testCase.put("Browser", "Chrome");
        testCase.put("OS", "Windows");
        System.out.println("Test case created:" + testCase);
        System.out.println("OS value: " + testCase.get("OS"));
    }
}