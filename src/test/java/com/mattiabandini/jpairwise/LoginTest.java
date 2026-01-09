package com.mattiabandini.jpairwise;

import org.junit.jupiter.api.Assertions;
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
        LoginManager app = new LoginManager();

        var result = app.attemptLogin(browser, os, role);

        var expected = getExpectedResult(browser, os, role);

        Assertions.assertEquals("???", result);
    }

    private String getExpectedResult(String browser, String os, String role) {
        // TODO: Implement your verification logic here!
        // Example:
        // if (role.equals("Admin")) return "Success";
        // return "Failure";

        throw new UnsupportedOperationException("getExpectedResult() not implemented yet");
    }
}
