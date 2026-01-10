package com.mattiabandini.jpairwise.engine;

/**
 * Represents a specific interaction between two parameter values.
 * <p>
 * This is the fundamental unit of coverage for pairwise testing. The goal of the
 * IPOG algorithm is to ensure that every possible valid {@code Pair} appears
 * in at least one {@link com.mattiabandini.jpairwise.model.TestCase}.
 * </p>
 *
 * @param paramIndex1 Index of the first parameter in the list.
 * @param value1      Value of the first parameter.
 * @param paramIndex2 Index of the second parameter in the list.
 * @param value2      Value of the second parameter.
 */
public record Pair(int paramIndex1, String value1, int paramIndex2, String value2) {

    /**
     * Compact constructor for validation.
     * Enforces an ordering constraint (index1 < index2) to ensure uniqueness
     * and avoid treating (A, B) and (B, A) as different pairs.
     *
     * @param paramIndex1 Index of the first parameter.
     * @param value1      Value of the first parameter.
     * @param paramIndex2 Index of the second parameter.
     * @param value2      Value of the second parameter.
     */
    public Pair {
        if (paramIndex1 >= paramIndex2) {
            throw new IllegalArgumentException("Parameter index 1 must be smaller than parameter index 2");
        }

        if (value1 == null || value2 == null) {
            throw new IllegalArgumentException("Parameter values cannot be null");
        }
    }
}
