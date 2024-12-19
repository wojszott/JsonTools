package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Klasa `JsonMinifier` odpowiada za zminifikowanie danych JSON.
 * Implementuje interfejs Transform.
 *
 * @author Paweł Muryn
 * @version 1.0
 */
public class JsonMinifier implements Transform {

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
            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonObject = objectMapper.readValue(input, Object.class);
            return objectMapper.writeValueAsString(jsonObject);
        } catch (Exception e) {
            return "Błąd podczas transformacji JSON: " + e.getMessage();
        }
    }

    /**
     * Domyślny konstruktor dla klasy JsonMinifier.
     * Inicjalizuje instancję klasy, nie wykonując dodatkowych operacji.
     */
    public JsonMinifier() {
        // Domyślny konstruktor
    }
}
