package pl.put.poznan.transformer.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;

public class TextTransformationResponse {
    private String originalText;   // Oryginalny tekst
    private String transformedText; // Przekształcony tekst

    public TextTransformationResponse(String originalText, String transformedText) {
        this.originalText = originalText;
        this.transformedText = transformedText;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getTransformedText() {
        return transformedText;
    }

    public String toJsonWithSelectedFields(List<String> selectedFields) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        if (selectedFields.contains("originalText")) {
            node.put("originalText", this.getOriginalText());
        }
        if (selectedFields.contains("transformedText")) {
            node.put("transformedText", this.getTransformedText());
        }
        return mapper.writeValueAsString(node);
    }
}
