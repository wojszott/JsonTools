package pl.put.poznan.transformer.logic;

/**
 * Klasa implementująca transformację polegającą na konwersji tekstu na wielkie litery.
 *
 * <p>Wszystkie znaki w podanym ciągu znaków są zamieniane na ich odpowiedniki w formacie wielkich liter,
 * zgodnie z ustawieniami lokalnymi JVM.</p>
 *
 * @author Spitree
 * @version 1.1.4
 */
public class UppercaseTransformer implements Transform {

    /**
     * Przekształca tekst wejściowy, zamieniając wszystkie znaki na wielkie litery.
     *
     * <p>Znaki specjalne lub cyfry pozostają niezmienione.</p>
     *
     * @param input tekst wejściowy do przekształcenia
     * @return tekst w formacie wielkich liter
     */
    @Override
    public String transform(String input) {
        return input.toUpperCase();
    }
}
