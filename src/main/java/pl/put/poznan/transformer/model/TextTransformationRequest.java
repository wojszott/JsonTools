package pl.put.poznan.transformer.model;

public class TextTransformationRequest {
    private String text;          // Tekst do transformacji
    private String[] transforms;  // Lista transformacji

    public TextTransformationRequest() {}

    public TextTransformationRequest(String text, String[] transforms) {
        this.text = text;
        this.transforms = transforms;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getTransforms() {
        return transforms;
    }

    public void setTransforms(String[] transforms) {
        this.transforms = transforms;
    }
}
