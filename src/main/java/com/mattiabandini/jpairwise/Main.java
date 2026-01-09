package com.mattiabandini.jpairwise;

import com.mattiabandini.jpairwise.engine.Pair;
import com.mattiabandini.jpairwise.model.Parameter;
import com.mattiabandini.jpairwise.model.TestCase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Pair p1 = new Pair(0, "a", 1, "b");
        System.out.println("Pair 1: " + p1);

        Pair p2 = new Pair(0, "a", 1, "b");
        System.out.println("Pair 2: " + p2);

        System.out.println("Pair 1 is equals to pair 2? " + p1.equals(p2));

        Set<Pair> coveredPairs = new HashSet<>();
        coveredPairs.add(p1);
        coveredPairs.add(p2);

        System.out.println("Set dimension: " + coveredPairs.size());

        // Test order error
        // new Pair(1, "b", 0, "a");
    }
}