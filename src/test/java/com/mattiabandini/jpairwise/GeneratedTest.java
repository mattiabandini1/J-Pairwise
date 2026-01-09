package com.mattiabandini.jpairwise;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GeneratedTest {

    @ParameterizedTest
    @CsvSource({
        "Chrome,Windows,English",
		"Chrome,Linux,Italian",
		"Chrome,Mac,Spanish",
		"Firefox,Windows,Italian",
		"Firefox,Linux,English",
		"Firefox,Mac,English",
		"Chrome,Mac,Italian",
		"Firefox,Windows,Spanish",
		"Chrome,Linux,Spanish"
    })
    void testGeneratedCombinations(String browser, String os, String language) {
        System.out.println("Test running: " + browser + ", " + os + ", " + language);
    }
}
