package com.mattiabandini.jpairwise.engine;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import com.mattiabandini.jpairwise.model.Parameter;
import com.mattiabandini.jpairwise.model.TestCase;

import java.util.ArrayList;
import java.util.List;

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
        return tests;
    }

}