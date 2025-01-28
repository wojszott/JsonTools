package pl.put.poznan.transformer.logic;

/**
 * Klasa implementująca transformację polegającą na odwróceniu kolejności znaków w tekście.
 *
 * Wszystkie znaki w podanym ciągu znaków zostają odwrócone,
 * tak że ostatni znak staje się pierwszym, a pierwszy ostatnim.
 *
 * Przykład:
 * <ul>
 * <li>Wejście: "example"</li>
 * <li>Wyjście: "elpmaxe"</li>
 * </ul>
 *
 *
 * @author Spitree
 * @version 1.1.4
 */
public class ReverseTransformer implements Transform {

    /**
     * Konstruktor domyślny. Inicjalizuje instancję klasy ReverseTransformer.
     */
    public ReverseTransformer() {
        // Domyślny konstruktor
    }
    /**
     * Odwraca kolejność znaków w podanym tekście wejściowym.
     *
     * @param input tekst wejściowy, którego kolejność znaków ma zostać odwrócona
     * @return tekst z odwróconą kolejnością znaków
     */
    @Override
    public String transform(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
