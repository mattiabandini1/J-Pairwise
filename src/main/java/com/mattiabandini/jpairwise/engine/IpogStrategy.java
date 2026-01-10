package com.mattiabandini.jpairwise.engine;

import com.mattiabandini.jpairwise.model.Parameter;
import com.mattiabandini.jpairwise.model.TestCase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implements the IPOG (In-Parameter-Order-General) strategy for Pairwise Testing.
 * <p>
 * This algorithm constructs a test suite that covers all possible pairs of parameter values
 * by building the suite incrementally. It starts with the first two parameters and generates
 * all combinations (Cartesian product). Then, it iterates through the remaining parameters,
 * extending the test suite horizontally (adding values to existing tests) and vertically
 * (adding new tests) to satisfy all pairwise requirements.
 * </p>
 */
public class IpogStrategy {

    /**
     * Generates a list of test cases covering all pairwise combinations of the given parameters.
     *
     * @param parameters The list of parameters (factors) and their possible values.
     * @return A list of {@link TestCase} objects representing the generated test suite.
     * @throws IllegalArgumentException if fewer than two parameters are provided.
     */
    public List<TestCase> generateTestCase(List<Parameter> parameters) {
        if (parameters == null || parameters.size() < 2) {
            throw new IllegalArgumentException("Need at least two parameters");
        }

        List<TestCase> tests = new ArrayList<>();

        // 1. Initialization: Generate the Cartesian product of the first two parameters.
        // This provides the base set of test cases to grow from.
        Parameter p0 = parameters.get(0);
        Parameter p1 = parameters.get(1);

        for (String val0 : p0.values()) {
            for (String val1 : p1.values()) {
                TestCase testCase = new TestCase();
                testCase.put(p0.name(), val0);
                testCase.put(p1.name(), val1);
                tests.add(testCase);
            }
        }

        // 2. Iterative Extension: Add remaining parameters one by one.
        for (int i = 2; i < parameters.size(); i++) {
            Parameter newParam = parameters.get(i);
            List<Parameter> previousParams = parameters.subList(0, i);

            // Phase A: Horizontal Growth
            // Try to extend existing test cases to cover pairs involving the new parameter.
            Set<Pair> leftovers = extendedHorizontal(tests, newParam, i, previousParams);

            // Phase B: Vertical Growth
            // Create new test cases for any pairs that couldn't be covered by extending existing ones.
            extendedVertical(tests, leftovers, newParam, i, previousParams);

            System.out.println("Parameter " + newParam.name() + " added. Total tests: " + tests.size());
        }

        return tests;
    }

    /**
     * Phase A - Horizontal Growth: Extends existing test cases with a value for the new parameter.
     * <p>
     * For each existing test case, it selects a value for the {@code newParam} that covers
     * the maximum number of previously uncovered pairs (greedy approach).
     * </p>
     *
     * @param tests        The current list of test cases (modified in place).
     * @param newParam     The new parameter being added.
     * @param newParamIndex Index of the new parameter.
     * @param prevParams   List of parameters already processed.
     * @return A set of {@link Pair}s that are still missing (leftovers) after horizontal extension.
     */
    private Set<Pair> extendedHorizontal(List<TestCase> tests, Parameter newParam, int newParamIndex, List<Parameter> prevParams) {
        Set<Pair> missingPairs = generateAllPairs(newParam, newParamIndex, prevParams);

        for (TestCase test : tests) {
            String bestValue = null;
            int maxCovered = -1;
            List<Pair> pairCoveredByBest = new ArrayList<>();

            for (String candidateVal : newParam.values()) {
                int currentScore = 0;
                List<Pair> currentPairs = new ArrayList<>();

                for (int i = 0; i < prevParams.size(); i++) {
                    String existingVal = test.get(prevParams.get(i).name());
                    Pair p = new Pair(i, existingVal, newParamIndex, candidateVal);

                    if (missingPairs.contains(p)) {
                        currentScore++;
                        currentPairs.add(p);
                    }
                }
                if (currentScore > maxCovered) {
                    maxCovered = currentScore;
                    pairCoveredByBest = currentPairs;
                    bestValue = candidateVal;
                }
            }
            if (bestValue == null) {
                bestValue = newParam.values().get(0);
            }

            test.put(newParam.name(), bestValue);

            for (Pair p : pairCoveredByBest) {
                missingPairs.remove(p);
            }
        }
        return missingPairs;
    }

    /**
     * Helper: Generates all required pairs between the {@code newParam} and all {@code prevParams}.
     * This set represents the total coverage goals for the current iteration.
     */
    private Set<Pair> generateAllPairs(Parameter newParam, int newParamIndex, List<Parameter> prevParams) {
        Set<Pair> allPairs = new HashSet<>();

        for (int i = 0; i < prevParams.size(); i++) {
            Parameter oldParam = prevParams.get(i);
            int oldParamIndex = i;

            for (String oldParamValue : oldParam.values()) {
                for (String newParamValue : newParam.values()) {
                    Pair p = new Pair(oldParamIndex, oldParamValue, newParamIndex, newParamValue);
                    allPairs.add(p);
                }
            }
        }
        return allPairs;
    }

    /**
     * Phase B - Vertical Growth: Adds new test cases for pairs missed during horizontal growth.
     * <p>
     * If any required pairs remain uncovered ("leftovers"), new test cases are created specifically
     * to cover them. Existing values in these new rows that don't affect the target pairs are
     * filled with defaults or "don't care" values.
     * </p>
     *
     * @param tests       The list of test cases to append to.
     * @param leftovers   The set of missing pairs that need coverage.
     * @param newParam    The new parameter being added.
     * @param newParamIndex Index of the new parameter.
     * @param prevParams  List of previously processed parameters.
     */
    private void extendedVertical(List<TestCase> tests, Set<Pair> leftovers, Parameter newParam, int newParamIndex, List<Parameter> prevParams) {
        if (leftovers.isEmpty()) {
            return;
        }

        List<TestCase> newRows = new ArrayList<>();

        for (Pair pair : leftovers) {
            boolean placed = false;

            for (TestCase newRow : newRows) {
                int p1Index = pair.paramIndex1();
                int p2Index = pair.paramIndex2();
                String p1Value = pair.value1();
                String p2Value = pair.value2();

                String name1 = (p1Index == newParamIndex) ? newParam.name() : prevParams.get(p1Index).name();
                String name2 = (p2Index == newParamIndex) ? newParam.name() : prevParams.get(p2Index).name();

                String valInRow1 = newRow.get(name1);
                String valInRow2 = newRow.get(name2);

                boolean compatible1 = (valInRow1 == null || valInRow1.equals(p1Value));
                boolean compatible2 = (valInRow2 == null || valInRow2.equals(p2Value));

                if (compatible1 && compatible2) {
                    newRow.put(name1, p1Value);
                    newRow.put(name2, p2Value);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                TestCase freshRow = new TestCase();

                int p1Index = pair.paramIndex1();
                int p2Index = pair.paramIndex2();
                String name1 = (p1Index == newParamIndex) ? newParam.name() : prevParams.get(p1Index).name();
                String name2 = (p2Index == newParamIndex) ? newParam.name() : prevParams.get(p2Index).name();

                freshRow.put(name1, pair.value1());
                freshRow.put(name2, pair.value2());
                freshRow.put(newParam.name(), freshRow.get(newParam.name()));

                newRows.add(freshRow);
            }
        }
        for (TestCase row : newRows) {
            for (Parameter p : prevParams) {
                if (row.get(p.name()) == null) {
                    row.put(p.name(), p.values().get(0));
                }
            }
            if (row.get(newParam.name()) == null) {
                row.put(newParam.name(), newParam.values().get(0));
            }
            tests.add(row);
        }
    }
}
