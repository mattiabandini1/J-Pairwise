package com.mattiabandini.jpairwise.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a single generated test case (a row in the pairwise table).
 * <p>
 * A TestCase maps each parameter name to a specific value selected by the
 * generation algorithm.
 * </p>
 */
public class TestCase {

    /** Map storing the parameter name -> value assignment for this test case. */
    private Map<String, String> data;

    public TestCase() {
        this.data = new HashMap<>();
    }

    /**
     * Assigns a value to a parameter in this test case.
     *
     * @param paramName The name of the parameter.
     * @param value     The value assigned to the parameter.
     */
    public void put(String paramName, String value) {
        this.data.put(paramName, value);
    }

    /**
     * Retrieves the value assigned to a specific parameter.
     *
     * @param paramName The name of the parameter.
     * @return The assigned value, or null if not yet assigned.
     */
    public String get(String paramName) {
        return this.data.get(paramName);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
