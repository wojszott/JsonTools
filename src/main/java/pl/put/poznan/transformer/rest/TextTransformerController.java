package pl.put.poznan.transformer.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.model.TextTransformationRequest;
import pl.put.poznan.transformer.model.TextTransformationResponse;

/**
 * Kontroler REST API obsługujący przekształcanie tekstu.
 *
 * Klasa ta definiuje punkt końcowy API dostępny pod adresem {@code /api/transform},
 * który umożliwia wykonywanie transformacji tekstu na podstawie określonych operacji
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
     * @param text tekst wejściowy do przekształcenia
     * @param transforms tablica nazw transformacji do zastosowania na tekście
     * @return obiekt {@link ResponseEntity} zawierający wynik transformacji w postaci {@link TextTransformationResponse}
     *         lub komunikat o błędzie w przypadku nieprawidłowych danych wejściowych
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TextTransformationResponse> transformText(
            @RequestBody String text,  // Input text in the request body
            @RequestParam String[] transforms,
            @RequestParam(required = false) String[] good_values,
            @RequestParam(required = false) String[] bad_values,
            @RequestParam(required = false) String compareFile) {  // Transformations passed as query parameters

        // Validate input
        if (transforms == null || transforms.length == 0) {
            return ResponseEntity.badRequest().body(new TextTransformationResponse(text, "No transforms specified"));
        }

        // Perform the transformation
        TextTransformer transformer = new TextTransformer(transforms,good_values, bad_values, compareFile);  // Initialize the transformer
        String transformedText = transformer.transform(text);  // Transform the text

        // Create and return the response
        TextTransformationResponse response = new TextTransformationResponse(text, transformedText);
        return ResponseEntity.ok(response);
    }

}
