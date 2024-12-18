package pl.put.poznan.transformer.logic;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMinifier implements Transform {

    @Override
    public String transform(String input) {
        try {
            // Tworzenie ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            // Parsowanie JSON do obiektu Java
            Object jsonObject = objectMapper.readValue(input, Object.class);
            // Serializacja obiektu do zminifikowanego JSON
            return objectMapper.writeValueAsString(jsonObject);
        } catch (Exception e) {
            // Obsługa błędu - np. zwrócenie pustego stringa lub wiadomości o błędzie
            return "Błąd podczas transformacji JSON: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        // Test implementacji
        Transform jsonMinifier = new JsonMinifier();

        String prettyJson = """
        {
            "name": "John Doe",
            "age": 30,
            "address": {
                "street": "123 Main St",
                "city": "New York"
            },
            "phones": ["123-456-7890", "987-654-3210"]
        }
        """;

        String minifiedJson = jsonMinifier.transform(prettyJson);
        System.out.println("Zminifikowany JSON:");
        System.out.println(minifiedJson);
    }
}
