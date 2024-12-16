package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.model.TextTransformationRequest;
import pl.put.poznan.transformer.model.TextTransformationResponse;

@RestController
@RequestMapping("/api/transform")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @PostMapping(produces = "application/json", consumes = "application/json")
    public TextTransformationResponse transform(@RequestBody TextTransformationRequest request) {
        // Logowanie danych wejściowych
        logger.debug("Received text: {}", request.getText());
        logger.debug("Requested transforms: {}", (Object) request.getTransforms());

        // Obsługa transformacji
        TextTransformer transformer = new TextTransformer(request.getTransforms());
        String transformedText = transformer.transform(request.getText());

        // Zwrócenie wyniku
        return new TextTransformationResponse(request.getText(), transformedText);
    }
}
