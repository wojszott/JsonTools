package pl.put.poznan.transformer.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;

/**
 * Kontroler REST do transformacji tekstu.
 * Udostępnia endpoint POST /api/transform do wykonywania operacji na tekście.
 *
 * @author Spitree, sathell, woijk
 * @version 1.1.5
 */
@RestController
@RequestMapping("/api/transform")
public class TextTransformerController {

    /**
     * Obsługuje żądanie transformacji tekstu
     * @param request obiekt żądania zawierający:
     * <ul>
     *   <li>text - tekst wejściowy</li>
     *   <li>transforms - lista transformacji do zastosowania</li>
     *   <li>goodValues - opcjonalne wartości poprawne</li>
     *   <li>badValues - opcjonalne wartości błędne</li>
     *   <li>compareFile - opcjonalna nazwa pliku porównawczego</li>
     * </ul>
     * @return ResponseEntity z przetworzonym tekstem lub komunikatem błędu:
     * <ul>
     *   <li>200 OK z wynikiem jeśli sukces</li>
     *   <li>400 Bad Request jeśli brak transformacji</li>
     * </ul>
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> transformText(@RequestBody TransformRequest request) {
        if (request.getTransforms() == null || request.getTransforms().length == 0) {
            return ResponseEntity.badRequest().body("No transforms specified");
        }

        TextTransformer transformer = new TextTransformer(
                request.getTransforms(),
                request.getGoodValues(),
                request.getBadValues(),
                request.getCompareFile()
        );

        return ResponseEntity.ok(transformer.transform(request.getText()));
    }
}