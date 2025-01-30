package pl.put.poznan.transformer.model;

/**
 * Klasa reprezentująca żądanie transformacji tekstu.
 * Przechowuje tekst oraz listę transformacji do zastosowania.
 */
public class TextTransformationRequest {
    private String text;          // Tekst do transformacji
    private String[] transforms;  // Lista transformacji

    /**
     * Domyślny konstruktor.
     * Tworzy pusty obiekt TextTransformationRequest.
     */
    public TextTransformationRequest() {}

    /**
     * Konstruktor inicjujący z tekstem i listą transformacji.
     *
     * @param text Tekst do transformacji
     * @param transforms Lista transformacji do zastosowania
     */
    public TextTransformationRequest(String text, String[] transforms) {
        this.text = text;
        this.transforms = transforms;
    }

    /**
     * Pobiera tekst do transformacji.
     *
     * @return Tekst do transformacji
     */
    public String getText() {
        return text;
    }

    /**
     * Ustawia tekst do transformacji.
     *
     * @param text Tekst do transformacji
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Pobiera listę transformacji.
     *
     * @return Lista transformacji
     */
    public String[] getTransforms() {
        return transforms;
    }

    /**
     * Ustawia listę transformacji.
     *
     * @param transforms Lista transformacji do zastosowania
     */
    public void setTransforms(String[] transforms) {
        this.transforms = transforms;
    }
}
