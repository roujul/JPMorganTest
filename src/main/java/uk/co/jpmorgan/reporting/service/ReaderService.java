package uk.co.jpmorgan.reporting.service;

import com.google.gson.Gson;
import uk.co.jpmorgan.reporting.domain.Instruction;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReaderService {

    public List<Instruction> readInstructions(String url) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String json = reader.lines().collect(Collectors.joining("\n"));
        Instruction[] data = new Gson().fromJson(json, Instruction[].class);
        return Arrays.asList(data);
    }

}
