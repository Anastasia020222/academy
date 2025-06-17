package selenium.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonFileToString {

    public static String getRequestBody() {
        StringBuilder sb = new StringBuilder();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/RequestBody.json"))) {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
