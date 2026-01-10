package com.mattiabandini.jpairwise;

/**
 * A dummy class used for demonstration purposes.
 * <p>
 * This class acts as the "System Under Test" (SUT) for the demo configuration.
 * The generated test cases will attempt to call the {@code attemptLogin} method
 * with various combinations of parameters.
 * </p>
 */
public class DemoLoginManager {

    /**
     * Simulates a login attempt.
     *
     * @param browser The browser used (e.g., Chrome, Firefox).
     * @param os      The operating system (e.g., Windows, Linux).
     * @param role    The user role (e.g., Admin, User).
     * @return Always returns "Success" for this demo.
     */
    public String attemptLogin(String browser, String os, String role) {
        return "Success";
    }
}
