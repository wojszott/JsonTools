package pl.put.poznan.transformer.logic;

/**
 * Interfejs `Transform` definiuje metodę przekształcania danych wejściowych.
 */
public interface Transform {

    /**
     * Przekształca dane wejściowe w wymaganym formacie.
     *
     * @param input Dane wejściowe w formacie JSON
     * @return Przekształcony ciąg znaków
     */
    String transform(String input);
}
