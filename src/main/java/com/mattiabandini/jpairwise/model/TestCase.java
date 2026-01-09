package com.mattiabandini.jpairwise.model;

import java.util.HashMap;
import java.util.Map;

public class TestCase {

    private Map<String, String> data;

    public TestCase() {
        this.data = new HashMap<>();
    }

    public void put(String paramName, String value) {
        this.data.put(paramName, value);
    }

    public String get(String paramName) {
        return this.data.get(paramName);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}