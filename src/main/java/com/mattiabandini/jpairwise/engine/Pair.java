package com.mattiabandini.jpairwise.engine;

public record Pair(int paramIndex1, String value1, int paramIndex2, String value2) {
    public Pair {
        if (paramIndex1 >= paramIndex2) {
            throw new IllegalArgumentException("Parameter index 1 must be smaller than parameter index 2");
        }

        if (value1 == null || value2 == null) {
            throw new IllegalArgumentException("Parameter values cannot be null");
        }
    }
}