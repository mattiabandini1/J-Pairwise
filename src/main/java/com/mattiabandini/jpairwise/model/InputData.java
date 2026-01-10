package com.mattiabandini.jpairwise.model;

import java.util.List;

/**
 * Represents the configuration data loaded from the JSON input file.
 * <p>
 * This class acts as a Data Transfer Object (DTO) holding all necessary information
 * to generate the test suite, including the target class/method to test, the desired
 * output package/class name for the test file, and the list of parameters (factors)
 * with their possible values.
 * </p>
 */
public class InputData {

    /** Name of the generated test class (e.g., "GeneratedLoginTest"). */
    private String className;

    /** Package where the generated test class will be placed (e.g., "com.example.tests"). */
    private String packageName;

    /** Name of the class under test (e.g., "LoginManager"). */
    private String targetClassName;

    /** Name of the method under test (e.g., "attemptLogin"). */
    private String targetMethodName;

    /** List of parameters (factors) and their levels (values) to be used for pairwise generation. */
    private List<Parameter> parameters;

    public String getClassName() { return this.className; }
    public String getPackageName() { return this.packageName; }
    public String getTargetClassName() { return this.targetClassName; }
    public String getTargetMethodName() { return this.targetMethodName; }
    public List<Parameter> getParameters() { return this.parameters; }

    public void setClassName(String className) { this.className = className; }
    public void setPackageName(String packageName) { this.packageName = packageName; }
    public void setTargetClassName(String targetClassName) { this.targetClassName = targetClassName; }
    public void setTargetMethodName(String targetMethodName) { this.targetMethodName = targetMethodName; }
    public void setParameters(List<Parameter> parameters) { this.parameters = parameters; }

}