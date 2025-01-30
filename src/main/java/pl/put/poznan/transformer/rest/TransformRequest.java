// Lokalizacja: src/main/java/pl/put/poznan/transformer/rest/TransformRequest.java
package pl.put.poznan.transformer.rest;

/**
 * Klasa reprezentująca żądanie transformacji tekstu.
 * Przechowuje parametry przekazywane w ciele żądania HTTP.
 */
public class TransformRequest {
    private String text;
    private String[] transforms;
    private String[] goodValues;
    private String[] badValues;
    private String compareFile;

    /**
     * Zwraca tekst wejściowy do transformacji
     * @return tekst do przetworzenia
     */
    public String getText() {
        return text;
    }

    /**
     * Ustawia tekst wejściowy
     * @param text tekst do przetworzenia
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Zwraca listę transformacji do zastosowania
     * @return tablica nazw transformacji
     */
    public String[] getTransforms() {
        return transforms;
    }

    /**
     * Ustawia listę transformacji
     * @param transforms tablica nazw transformacji (np. ["upper", "reverse"])
     */
    public void setTransforms(String[] transforms) {
        this.transforms = transforms;
    }

    /**
     * Zwraca wartości poprawne używane w transformacjach porównawczych
     * @return tablica poprawnych wartości
     */
    public String[] getGoodValues() {
        return goodValues;
    }

    /**
     * Ustawia wartości poprawne
     * @param goodValues tablica poprawnych wartości
     */
    public void setGoodValues(String[] goodValues) {
        this.goodValues = goodValues;
    }

    /**
     * Zwraca wartości błędne używane w transformacjach porównawczych
     * @return tablica błędnych wartości
     */
    public String[] getBadValues() {
        return badValues;
    }

    /**
     * Ustawia wartości błędne
     * @param badValues tablica błędnych wartości
     */
    public void setBadValues(String[] badValues) {
        this.badValues = badValues;
    }

    /**
     * Zwraca nazwę pliku do porównania
     * @return nazwa pliku z wzorcowymi danymi
     */
    public String getCompareFile() {
        return compareFile;
    }

    /**
     * Ustawia nazwę pliku do porównania
     * @param compareFile nazwa pliku z wzorcowymi danymi
     */
    public void setCompareFile(String compareFile) {
        this.compareFile = compareFile;
    }
}