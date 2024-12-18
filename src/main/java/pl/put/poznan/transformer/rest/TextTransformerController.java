package pl.put.poznan.transformer.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.model.TextTransformationRequest;
import pl.put.poznan.transformer.model.TextTransformationResponse;


@RestController
@RequestMapping("/api/transform")
public class TextTransformerController {

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TextTransformationResponse> transformText(
            @RequestBody String text,  // Tylko tekst w żądaniu
            @RequestParam String[] transforms) {  // Operacje do wykonania przekazywane jako parametry

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



