package com.mattiabandini.jpairwise.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mattiabandini.jpairwise.model.InputData;

import java.io.File;
import java.io.IOException;

/**
 * Utility class for parsing the configuration JSON file.
 * <p>
 * Uses the Jackson library to deserialize the JSON content into the {@link InputData} model.
 * </p>
 */
public class JsonParser {

    /**
     * Reads a JSON file from the specified path and converts it into an {@link InputData} object.
     *
     * @param filePath The absolute or relative path to the JSON configuration file.
     * @return The populated {@link InputData} object.
     * @throws IOException If the file cannot be read or the JSON structure is invalid.
     */
    public InputData readInput(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), InputData.class);
    }

}