package com.mattiabandini.jpairwise.model;

import java.util.List;

public class InputData {

    private String className;
    private String packageName;
    private String targetClassName;
    private String targetMethodName;
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


