package pl.put.poznan.transformer.logic;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MinifierTransformer implements Transform {

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
}
