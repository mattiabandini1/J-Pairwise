package com.mattiabandini.jpairwise.model;

import java.util.List;

public record InputData(
        String className,
        String packageName,
        List<Parameter> parameters
) {
}
