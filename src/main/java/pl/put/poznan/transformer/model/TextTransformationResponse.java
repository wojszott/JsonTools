package pl.put.poznan.transformer.model;

/**
 * Klasa reprezentująca odpowiedź po przetworzeniu tekstu.
 *
 * <p>Obiekt tej klasy zawiera przekształcony tekst, który jest wynikiem zastosowania określonych transformacji.</p>
 *
 * @author Spitree, sathell, woijk
 * @version 1.1.4
 */
public class TextTransformationResponse {
    private String transformedText; // Przekształcony tekst

    /**
     * Tworzy obiekt {@code TextTransformationResponse} z przekształconym tekstem.
     *
     * @param transformedText przekształcony tekst
     */
    public TextTransformationResponse(String transformedText) {
        this.transformedText = transformedText;
    }

    /**
     * Pobiera przekształcony tekst.
     *
     * @return przekształcony tekst
     */
    public String getTransformedText() {
        return transformedText;
    }

    /**
     * Ustawia przekształcony tekst.
     *
     * @param transformedText przekształcony tekst
     */
    public void setTransformedText(String transformedText) {
        this.transformedText = transformedText;
    }
}
