package pl.put.poznan.transformer.logic;

/**
 * Klasa implementująca transformację polegającą na odwróceniu kolejności znaków w tekście.
 *
 * <p>Wszystkie znaki w podanym ciągu znaków zostają odwrócone,
 * tak że ostatni znak staje się pierwszym, a pierwszy ostatnim.</p>
 *
 * <p>Przykład:
 * <ul>
 * <li>Wejście: "example"</li>
 * <li>Wyjście: "elpmaxe"</li>
 * </ul>
 * </p>
 *
 * @author Spitree
 * @version 1.1.4
 */
public class ReverseTransformer implements Transform {

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
