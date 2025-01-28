package pl.put.poznan.transformer.logic;
import com.fasterxml.jackson.databind.ObjectMapper;


//cos
public class MinifierTransformer implements Transform {

    /**
     * Przekształca wejściowy JSON na jego zminifikowaną wersję.
     *
     * @param input JSON jako ciąg znaków
     * @return Zminifikowany JSON lub komunikat o błędzie w przypadku niepowodzenia
     * @throws IllegalArgumentException jeśli wejście nie jest poprawnym JSON-em
     */
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
