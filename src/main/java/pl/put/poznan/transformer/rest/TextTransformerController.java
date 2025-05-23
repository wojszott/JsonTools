package pl.put.poznan.transformer.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;

/**
 * Kontroler REST API obsługujący przekształcanie tekstu.
 *
 * Klasa ta definiuje punkt końcowy API dostępny pod adresem {@code /api/transform},
 * który umożliwia wykonywanie transformacji tekstu na podstawie określonych operacji.
 *
 * Klasa {@link TextTransformerController} jest klasą dekorowaną, która zawiera metodę
 * {@link #transformText(String, String[], String[], String[], String)} umożliwiającą
 * wykonywanie transformacji tekstu. Metoda ta jest dekorowana przez adnotację
 * {@link PostMapping}, która wskazuje, że jest to punkt końcowy API przyjmujący żądania typu POST.
 *
 * @author Spitree, sathell, woijk
 * @version 1.1.4
 */
@RestController
@RequestMapping("/api/transform")
public class TextTransformerController {

    /**
     * Konstruktor domyślny. Inicjalizuje instancję klasy TextTransformerController.
     */
    public TextTransformerController() {
        // Domyślny konstruktor
    }

    /**
     * Obsługuje żądanie przekształcenia tekstu.
     *
     * Przyjmuje tekst wejściowy oraz listę transformacji do wykonania jako parametry.
     * Wynik transformacji jest zwracany w formacie JSON w odpowiedzi.
     *
     * Klasa {@link TextTransformerController} dekoruje metodę transformText, która przyjmuje
     * parametry w postaci tekstu wejściowego i parametrów transformacji. Dekoracja przez
     * {@link PostMapping} wskazuje, że jest to punkt końcowy API.
     *
     * @param text tekst wejściowy do przekształcenia
     * @param transforms tablica nazw transformacji do zastosowania na tekście
     * @param goodValues tablica wartości, które pozostaną w wyjściowym pliku Json
     * @param badValues tablica wartości, które zostaną usunięte z wyściowego pliku Json
     * @param compareFile opcjonalny parametr wskazujący plik do porównania
     * @return obiekt {@link ResponseEntity} zawierający wynik transformacji w postaci String
     *         lub komunikat o błędzie w przypadku nieprawidłowych danych wejściowych
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> transformText(
            @RequestBody String text,  // Input text in the request body
            @RequestParam String[] transforms,
            @RequestParam(required = false) String[] goodValues,
            @RequestParam(required = false) String[] badValues,
            @RequestParam(required = false) String compareFile) {  // Transformations passed as query parameters

        // Validate input
        if (transforms == null || transforms.length == 0) {
            return ResponseEntity.badRequest().body("No transforms specified");
        }

        // Perform the transformation
        TextTransformer transformer = new TextTransformer(transforms, goodValues, badValues, compareFile);  // Initialize the transformer
        String transformedText = transformer.transform(text);  // Transform the text

        // Return the response
        return ResponseEntity.ok(transformedText);
    }
}
