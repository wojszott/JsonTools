package pl.put.poznan.transformer.model;

public class TextTransformationResponse {
    private String transformedText;

    public TextTransformationResponse(String transformedText) {
        this.transformedText = transformedText;
    }

    public String getTransformedText() {
        return transformedText;
    }

    public void setTransformedText(String transformedText) {
        this.transformedText = transformedText;
    }
}
