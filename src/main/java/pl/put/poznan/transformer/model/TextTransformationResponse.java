package pl.put.poznan.transformer.model;

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
}
