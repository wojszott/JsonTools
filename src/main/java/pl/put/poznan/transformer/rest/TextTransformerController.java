package pl.put.poznan.transformer.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.model.TextTransformationRequest;
import pl.put.poznan.transformer.model.TextTransformationResponse;

/**
 * Kontroler REST API obsługujący przekształcanie tekstu.
 *
 * <p>Klasa ta definiuje punkt końcowy API dostępny pod adresem {@code /api/transform},
 * który umożliwia wykonywanie transformacji tekstu na podstawie określonych operacji.</p>
 *
 * @author Spitree, sathell, woijk
 * @version 1.1.4
 */
@RestController
@RequestMapping("/api/transform")
public class TextTransformerController {

    /**
     * Obsługuje żądanie przekształcenia tekstu.
     *
     * <p>Przyjmuje tekst wejściowy oraz listę transformacji do wykonania jako parametry.
     * Wynik transformacji jest zwracany w formacie JSON w odpowiedzi.</p>
     *
     * @param text tekst wejściowy do przekształcenia
     * @param transforms tablica nazw transformacji do zastosowania na tekście
     * @return obiekt {@link ResponseEntity} zawierający wynik transformacji w postaci {@link TextTransformationResponse}
     *         lub komunikat o błędzie w przypadku nieprawidłowych danych wejściowych
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TextTransformationResponse> transformText(
            @RequestBody String text,  // Tylko tekst w żądaniu
            @RequestParam String[] transforms) {  // Operacje do wykonania przekazywane jako parametry

        // Walidacja danych wejściowych
        if (transforms == null || transforms.length == 0) {
            return ResponseEntity.badRequest().body(new TextTransformationResponse("No transforms specified"));
        }

        // Logika transformacji
        TextTransformer transformer = new TextTransformer(transforms);  // Zainicjowanie transformacji
        String transformedText = transformer.transform(text);  // Przekształcanie tekstu

        // Tworzenie odpowiedzi
        TextTransformationResponse response = new TextTransformationResponse(transformedText);
        return ResponseEntity.ok(response);
    }
}
