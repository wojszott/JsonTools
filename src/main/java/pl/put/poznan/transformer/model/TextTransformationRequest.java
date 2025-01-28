package pl.put.poznan.transformer.model;


//cos
public class TextTransformationRequest {
    private String text;          // Tekst do transformacji
    private String[] transforms;  // Lista transformacji

    //cos
    public TextTransformationRequest() {}
    //cos
    public TextTransformationRequest(String text, String[] transforms) {
        this.text = text;
        this.transforms = transforms;
    }
    //cos
    public String getText() {
        return text;
    }
    //cos
    public void setText(String text) {
        this.text = text;
    }
    //cos
    public String[] getTransforms() {
        return transforms;
    }
    //cos
    public void setTransforms(String[] transforms) {
        this.transforms = transforms;
    }
}
