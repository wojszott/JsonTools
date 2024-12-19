package pl.put.poznan.transformer.logic;

/**
 * Interfejs definiujący metodę transformacji tekstu.
 *
 * <p>Każda klasa implementująca ten interfejs musi dostarczyć implementację metody {@code transform},
 * która będzie odpowiedzialna za przekształcenie tekstu wejściowego w zgodzie z określoną logiką.</p>
 *
 * @author Spitree, sathell, woijk
 * @version 1.1.4
 */
public interface Transform {

    /**
     * Przekształca tekst wejściowy zgodnie z określoną logiką transformacji.
     *
     * @param input tekst wejściowy do przekształcenia
     * @return przekształcony tekst
     */
    String transform(String input);
}
