package pl.put.poznan.transformer.model;

public class TextTransformationRequest {
    private String[] transforms; // Tablica nazw transformacji (np. "prettify", "upper")
    private String text;         // Tekst, który ma zostać przetworzony

    // Gettery i settery
    public String[] getTransforms() {
        return transforms;
    }

    public void setTransforms(String[] transforms) {
        this.transforms = transforms;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
