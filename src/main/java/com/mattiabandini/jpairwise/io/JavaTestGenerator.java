package com.mattiabandini.jpairwise.io;

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

    public void generateFile(List<Parameter> parameters, List<TestCase> testCases, String className, String packageName) throws IOException {
        String methodArgs = generateMethodSignature(parameters);
        String csvBody = generateCsvBody(parameters, testCases);
        String printArgs = generatePrintArgs(parameters);

        String packagePath = packageName.replace(".", "/");
        Path directory = Path.of("src", "test", "java", packagePath);

        Files.createDirectories(directory);

        Path filePath = directory.resolve(className + ".java");

        String fileContent = """
                package %s;
                
                import org.junit.jupiter.params.ParameterizedTest;
                import org.junit.jupiter.params.provider.CsvSource;
                
                public class %s {
                
                    @ParameterizedTest
                    @CsvSource({
                        %s
                    })
                    void testGeneratedCombinations(%s) {
                        System.out.println("Test running: " + %s);
                    }
                }
                """.formatted(packageName, className, csvBody, methodArgs, printArgs);

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

    private String generatePrintArgs(List<Parameter> parameters) {
        return parameters.stream()
                .map(p -> p.name().toLowerCase())
                .collect(Collectors.joining(" + \", \" + "));
    }
}