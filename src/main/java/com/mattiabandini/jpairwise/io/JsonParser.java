package com.mattiabandini.jpairwise.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mattiabandini.jpairwise.model.InputData;

import java.io.File;
import java.io.IOException;

public class JsonParser {

    public InputData readInput(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), InputData.class);
    }

}
