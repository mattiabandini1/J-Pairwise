package com.mattiabandini.jpairwise.model;

import java.util.List;

/**
 * Represents a single factor (parameter) in the pairwise testing configuration.
 * <p>
 * A parameter consists of a unique name (e.g., "Browser") and a list of possible
 * values or levels (e.g., ["Chrome", "Firefox", "Edge"]).
 * </p>
 *
 * @param name   The name of the parameter.
 * @param values The list of possible values for this parameter.
 */
public record Parameter(String name, List<String> values) {

    /**
     * Compact constructor for validation.
     * Ensures that the parameter has a valid name and at least one value.
     *
     * @param name   The name of the parameter.
     * @param values The list of possible values.
     * @throws IllegalArgumentException if the name is blank or values are empty.
     */
    public Parameter {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Parameter name cannot be null or blank");
        }
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Parameter values cannot be null or empty");
        }
        // Create an immutable copy to prevent external modification
        values = List.copyOf(values);
    }
}
