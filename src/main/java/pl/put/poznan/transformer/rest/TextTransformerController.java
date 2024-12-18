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
            @RequestBody TextTransformationRequest request) {

        // Logika transformacji
        TextTransformer transformer = new TextTransformer(request.getTransforms());
        String transformedText = transformer.transform(request.getText());

        // Tworzenie odpowiedzi
        TextTransformationResponse response = new TextTransformationResponse(transformedText);
        return ResponseEntity.ok(response);
    }
}
