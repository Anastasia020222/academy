package rest.assured.utils;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Token {

    public static String extractToken(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new File(json));
            return root.get("token").asText();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось получить токен из JSON", e);
        }
    }
}
