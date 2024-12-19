package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.model.TextTransformationRequest;
import pl.put.poznan.transformer.model.TextTransformationResponse;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/transform")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    // Istniejąca metoda
    @PostMapping(produces = "application/json", consumes = "application/json")
    public TextTransformationResponse transform(@RequestBody TextTransformationRequest request) {
        logger.debug("Received text: {}", request.getText());
        logger.debug("Requested transforms: {}", (Object) request.getTransforms());

        TextTransformer transformer = new TextTransformer(request.getTransforms());
        String transformedText = transformer.transform(request.getText());

        return new TextTransformationResponse(request.getText(), transformedText);
    }

    // Nowa metoda obsługująca `fields`
    @PostMapping(value = "/select", produces = "application/json", consumes = "application/json")
    public String transformWithFields(
            @RequestBody TextTransformationRequest request,
            @RequestParam(value = "fields", required = false) String fields) {
        logger.debug("Received text: {}", request.getText());
        logger.debug("Requested transforms: {}", (Object) request.getTransforms());
        logger.debug("Requested fields: {}", fields);

        TextTransformer transformer = new TextTransformer(request.getTransforms());
        String transformedText = transformer.transform(request.getText());

        TextTransformationResponse response = new TextTransformationResponse(request.getText(), transformedText);

        try {
            if (fields != null && !fields.isEmpty()) {
                List<String> selectedFields = Arrays.asList(fields.split(","));
                return response.toJsonWithSelectedFields(selectedFields);
            }
            return response.toJsonWithSelectedFields(List.of("originalText", "transformedText"));
        } catch (Exception e) {
            logger.error("Error during response serialization", e);
            throw new RuntimeException("Failed to generate response");
        }
    }
}
