package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Klasa odpowiedzialna za zminifikowanie wejściowego JSON-a.
 * Zmienia format JSON na jego zminifikowaną wersję, usuwając zbędne spacje i wcięcia.
 */
public class MinifierTransformer implements Transform {

    /**
     * Przekształca wejściowy JSON na jego zminifikowaną wersję.
     *
     * @param input JSON jako ciąg znaków, który ma zostać zminifikowany
     * @return Zminifikowany JSON lub komunikat o błędzie w przypadku niepowodzenia.
     *         Jeśli wejście nie jest poprawnym JSON-em, zwrócony zostanie komunikat o błędzie.
     * @throws IllegalArgumentException jeśli wejście nie jest poprawnym JSON-em
     */
    @Override
    public String transform(String input) {
        try {
            // Tworzenie obiektu ObjectMapper do przetwarzania JSON
            ObjectMapper objectMapper = new ObjectMapper();
            // Parsowanie JSON do obiektu Java
            Object jsonObject = objectMapper.readValue(input, Object.class);
            // Serializacja obiektu Java do zminifikowanego JSON-a
            return objectMapper.writeValueAsString(jsonObject);
        } catch (Exception e) {
            // Obsługa błędu - zwrócenie komunikatu o błędzie
            return "Błąd podczas transformacji JSON: " + e.getMessage();
        }
    }
}
