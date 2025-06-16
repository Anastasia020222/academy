package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonFileToString {

    public static String getRequestBody(String path) {
        StringBuilder sb = new StringBuilder();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
