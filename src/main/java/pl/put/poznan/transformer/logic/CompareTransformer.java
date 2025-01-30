package pl.put.poznan.transformer.logic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa CompareTransformer implementuje interfejs Transform i porównuje tekst wejściowy
 * z zawartością pliku podanego w konstruktorze. Wynikiem działania jest JSON zawierający
 * różnice między tekstami.
 */
public class CompareTransformer implements Transform {

    private final String textToCompare;

    /**
     * Tworzy instancję CompareTransformer, wczytując zawartość pliku do porównania.
     *
     * @param textToCompare Ścieżka do pliku, który będzie porównywany.
     * @throws IOException Jeśli plik nie istnieje lub wystąpił błąd odczytu.
     */
    public CompareTransformer(String textToCompare) throws IOException {
        File file = new File(textToCompare);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + textToCompare);
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new IOException("Error reading file: " + textToCompare, e);
        }

        this.textToCompare = content.toString();
    }

    /**
     * Porównuje podany tekst z wcześniej wczytanym tekstem i zwraca różnice w formacie JSON.
     *
     * @param text Tekst do porównania z wczytanym plikiem.
     * @return JSON zawierający różnice lub komunikat o braku różnic.
     */
    @Override
    public String transform(String text) {
        if (text == null && textToCompare == null) {
            return "{\"message\": \"No differences found: Both texts are empty.\"}";
        } else if (text == null || textToCompare == null) {
            return "{\"message\": \"Entire text is different: One text is empty.\"}";
        }

        String[] lines1 = text.split("\\n");
        String[] lines2 = textToCompare.split("\\n");
        int maxLines = Math.max(lines1.length, lines2.length);
        List<String> differences = new ArrayList<>();

        for (int i = 0; i < maxLines; i++) {
            String line1 = i < lines1.length ? lines1[i].trim() : "";
            String line2 = i < lines2.length ? lines2[i].trim() : "";

            if (!line1.equals(line2)) {
                differences.add("{\n" +
                        "\"line\": " + (i + 1) + ",\n" +
                        "\"text1\": \"" + escapeJsonString(line1) + "\",\n" +
                        "\"text2\": \"" + escapeJsonString(line2) + "\"\n" +
                        "}");
            }
        }

        if (!differences.isEmpty()) {
            return "{\n\"differences\": [\n" + String.join(",\n", differences) + "\n]\n}";
        }

        return "{\"message\": \"No differences found: Both texts are identical.\"}";
    }

    /**
     * Metoda pomocnicza do konwersji znaków specjalnych na format JSON-escaped.
     *
     * @param input Tekst wejściowy do przekształcenia.
     * @return Tekst z odpowiednimi escape'ami.
     */
    private String escapeJsonString(String input) {
        if (input == null) return "";
        return input.replace("\"", "\\\"")  // Escape quotes
                .replace("\n", "\\n")   // Escape newlines
                .replace("\r", "\\r")   // Escape carriage return
                .replace("\t", "\\t");  // Escape tabs
    }
}