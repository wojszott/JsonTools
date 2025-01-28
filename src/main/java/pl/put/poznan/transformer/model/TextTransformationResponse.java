package pl.put.poznan.transformer.model;
//cos
public class TextTransformationResponse {
    private String originalText;   // Oryginalny tekst
    private String transformedText; // Przekształcony tekst
    //cos
    public TextTransformationResponse(String originalText, String transformedText) {
        this.originalText = originalText;
        this.transformedText = transformedText;
    }
    //cos
    public String getOriginalText() {
        return originalText;
    }
    //cos
    public String getTransformedText() {
        return transformedText;
    }
}
