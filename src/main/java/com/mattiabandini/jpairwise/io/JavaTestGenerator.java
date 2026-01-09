package com.mattiabandini.jpairwise.io;

import com.mattiabandini.jpairwise.model.InputData;
import com.mattiabandini.jpairwise.model.Parameter;
import com.mattiabandini.jpairwise.model.TestCase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaTestGenerator {

    public void generateFile(InputData inputData, List<TestCase> testCases) throws IOException {

        List<Parameter> parameters = inputData.getParameters();
        String className = inputData.getClassName();
        String packageName = inputData.getPackageName();
        String targetClassName = inputData.getTargetClassName();
        String targetMethodName = inputData.getTargetMethodName();

        String methodArgs = generateMethodSignature(parameters);
        String csvBody = generateCsvBody(parameters, testCases);
        String callArgs = generateMethodCallArgs(parameters);

        String packagePath = packageName.replace(".", "/");
        Path directory = Path.of("src", "test", "java", packagePath);

        Files.createDirectories(directory);
        Path filePath = directory.resolve(className + ".java");

        String fileContent = """
                package %s;
                
                import org.junit.jupiter.api.Assertions;
                import org.junit.jupiter.params.ParameterizedTest;
                import org.junit.jupiter.params.provider.CsvSource;
                
                public class %s {
                
                    @ParameterizedTest
                    @CsvSource({
                        %s
                    })
                    void testGeneratedCombinations(%s) {
                        %s app = new %s();
                        
                        var result = app.%s(%s);
                        
                        var expected = getExpectedResult(%s);
                        
                        Assertions.assertEquals(expected, result);
                    }
                    
                    private String getExpectedResult(%s) {
                        // TODO: Implement your verification logic here!
                        // Example:
                        // if (role.equals("Admin")) return "Success";
                        // return "Failure";
                        
                        throw new UnsupportedOperationException("getExpectedResult() not implemented yet");
                    }
                }
                """.formatted(
                        packageName,
                        className,
                        csvBody,
                        methodArgs,
                        targetClassName, targetClassName,
                        targetMethodName, callArgs,
                        callArgs,
                        methodArgs);

        Files.writeString(filePath, fileContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("File generated: " + filePath.toAbsolutePath());
    }

    private String generateMethodSignature(List<Parameter> parameters) {
        return parameters.stream()
                .map(p -> "String " + p.name().toLowerCase())
                .collect(Collectors.joining(", "));
    }

    private String generateCsvBody(List<Parameter> parameters, List<TestCase> testCases) {
        StringBuilder sb =  new StringBuilder();
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<String> orderedValues = new ArrayList<>();

            for (Parameter p : parameters) {
                String val = tc.get(p.name());
                orderedValues.add(val);
            }

            String rowContent = String.join(",", orderedValues);
            sb.append('"').append(rowContent).append('"');

            if (i < testCases.size() - 1) {
                sb.append(",\n\t\t");
            }
        }
        return sb.toString();
    }

    private String generateMethodCallArgs(List<Parameter> parameters) {
        return parameters.stream()
                .map(p -> p.name().toLowerCase())
                .collect(Collectors.joining(", "));
    }
}