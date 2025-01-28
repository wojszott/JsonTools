package pl.put.poznan.transformer.model;

/**
 * Klasa reprezentująca żądanie przekształcenia tekstu.
 *
 * Obiekt tej klasy zawiera dane wejściowe potrzebne do wykonania transformacji tekstu:
 * <ul>
 * <li>Tablicę nazw transformacji, które mają zostać wykonane.</li>
 * <li>Tekst, który ma zostać poddany transformacjom.</li>
 * </ul>
 *
 *
 * @author Spitree, sathell, woijk
 * @version 1.1.4
 */
public class TextTransformationRequest {
    private String[] transforms; // Tablica nazw transformacji (np. "prettify", "upper")
    private String text;         // Tekst, który ma zostać przetworzony

    /**
     * Pobiera listę nazw transformacji, które mają zostać zastosowane.
     *
     * @return tablica nazw transformacji
     */
    public String[] getTransforms() {
        return transforms;
    }

    /**
     * Ustawia listę nazw transformacji, które mają zostać zastosowane.
     *
     * @param transforms tablica nazw transformacji
     */
    public void setTransforms(String[] transforms) {
        this.transforms = transforms;
    }

    /**
     * Pobiera tekst, który ma zostać przetworzony.
     *
     * @return tekst wejściowy
     */
    public String getText() {
        return text;
    }

    /**
     * Ustawia tekst, który ma zostać przetworzony.
     *
     * @param text tekst wejściowy
     */
    public void setText(String text) {
        this.text = text;
    }
}
