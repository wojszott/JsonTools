package pl.put.poznan.transformer.logic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CompareTransformer implements Transform {

    private final String textToCompare;

    public CompareTransformer(String textToCompare) throws IOException {
        // Odczytanie zawartości pliku
        File file = new File(textToCompare);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + textToCompare);
        }

        // Odczytanie pliku
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new IOException("Error reading file: " + textToCompare, e);
        }

        // Zapisanie zawartości pliku do textToCompare
        this.textToCompare = content.toString();
    }

    @Override
    public String transform(String text) {
        if (text == null && textToCompare == null) {
            return "{\"message\": \"No differences found: Both texts are empty.\"}";
        } else if (text == null || textToCompare == null) {
            return "{\"message\": \"Entire text is different: One text is empty.\"}";
        }

        // Podzielenie tekstów na linie
        String[] lines1 = text.split("\\n");
        String[] lines2 = textToCompare.split("\\n");

        // Obliczenie maksymalnej liczby linii
        int maxLines = Math.max(lines1.length, lines2.length);
        List<String> differences = new ArrayList<>();

        // Porównanie każdej linii
        for (int i = 0; i < maxLines; i++) {
            String line1 = i < lines1.length ? lines1[i].trim() : ""; // Usunięcie zbędnych białych znaków
            String line2 = i < lines2.length ? lines2[i].trim() : ""; // Usunięcie zbędnych białych znaków

            if (!line1.equals(line2)) {
                // Dodanie linii, które różnią się w formacie JSON, z odpowiednim escape'owaniem znaków
                differences.add("{\n" +
                        "\"line\": " + (i + 1) + ",\n" +
                        "\"text1\": \"" + escapeJsonString(line1) + "\",\n" +
                        "\"text2\": \"" + escapeJsonString(line2) + "\"\n" +
                        "}");
            }
        }

        // Jeśli różnice zostały znalezione
        if (!differences.isEmpty()) {
            return "{\n\"differences\": [\n" + String.join(",\n", differences) + "\n]\n}";
        }

        // Jeśli brak różnic
        return "{\"message\": \"No differences found: Both texts are identical.\"}";
    }

    // Funkcja do escape'owania specjalnych znaków w stringu (np. " " i \n)
    private String escapeJsonString(String input) {
        if (input == null) return "";
        // Zamiana specjalnych znaków na formaty escape'owane
        return input.replace("\"", "\\\"")  // Escape quotes
                .replace("\n", "\\n")   // Escape newlines
                .replace("\r", "\\r")   // Escape carriage return
                .replace("\t", "\\t");  // Escape tabs
    }
}
