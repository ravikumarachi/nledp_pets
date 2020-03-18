package com.methods;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class read {


    public JSONArray readJsonFile(String jsonFile) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        String dir = System.getProperty("user.dir");
        String jsonDir = dir + "/src/test/java/com/testData/json/";
        System.out.println(jsonDir);

        Object obj = parser.parse(new FileReader(jsonDir + jsonFile + ".json"));
        JSONArray jsonArray = (JSONArray) obj;

        return jsonArray;


    }
}
