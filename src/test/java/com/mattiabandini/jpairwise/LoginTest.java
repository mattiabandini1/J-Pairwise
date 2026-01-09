package com.mattiabandini.jpairwise;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LoginTest {

    @ParameterizedTest
    @CsvSource({
        "Chrome,Windows,Admin",
		"Chrome,Linux,Editor",
		"Chrome,Mac,Viewer",
		"Firefox,Windows,Editor",
		"Firefox,Linux,Admin",
		"Firefox,Mac,Admin",
		"Edge,Windows,Viewer",
		"Edge,Linux,Admin",
		"Edge,Mac,Editor",
		"Firefox,Linux,Viewer"
    })
    void testGeneratedCombinations(String browser, String os, String role) {
        System.out.println("Test running: " + browser + ", " + os + ", " + role);
    }
}
