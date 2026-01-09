package com.mattiabandini.jpairwise.engine;

import com.mattiabandini.jpairwise.model.Parameter;
import com.mattiabandini.jpairwise.model.TestCase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IpogStrategy {

    public List<TestCase> generateTestCase(List<Parameter> parameters) {
        if (parameters == null || parameters.size() < 2) {
            throw new IllegalArgumentException("Need at least two parameters");
        }

        List<TestCase> tests = new ArrayList<>();

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

        for (int i = 2; i < parameters.size(); i++) {
            Parameter newParam = parameters.get(i);
            List<Parameter> previousParams = parameters.subList(0, i);

            Set<Pair> leftovers = extendedHorizontal(tests, newParam, i, previousParams);

            System.out.println("Leftovers: " + leftovers.size());
        }

        return tests;
    }

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
}