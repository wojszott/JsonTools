package pl.put.poznan.transformer.logic;

/**
 * Klasa implementująca transformację polegającą na konwersji tekstu na małe litery.
 *
 * <p>Wszystkie znaki w podanym ciągu znaków są zamieniane na ich odpowiedniki w formacie małych liter,
 * zgodnie z ustawieniami lokalnymi JVM.</p>
 *
 * @author Spitree
 * @version 1.1.4
 */
public class LowercaseTransformer implements Transform {

    /**
     * Przekształca tekst wejściowy, zamieniając wszystkie znaki na małe litery.
     *
     * <p>Jeśli tekst wejściowy zawiera znaki specjalne lub cyfry, pozostają one niezmienione.</p>
     *
     * @param input tekst wejściowy do przekształcenia
     * @return tekst w formacie małych liter
     */
    @Override
    public String transform(String input) {
        return input.toLowerCase();
    }
}
