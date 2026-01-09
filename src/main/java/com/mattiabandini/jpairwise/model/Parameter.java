package com.mattiabandini.jpairwise.model;

import java.util.List;

public record Parameter(String name, List<String> values) {

    public Parameter {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Parameter name cannot be null or blank");
        }
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Parameter values cannot be null or empty");
        }
        values = List.copyOf(values);
    }
}