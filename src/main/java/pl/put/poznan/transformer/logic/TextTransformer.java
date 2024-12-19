package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa odpowiedzialna za zarządzanie i wykonywanie transformacji tekstu.
 *
 * Tworzy listę transformacji na podstawie przekazanych nazw oraz wykonuje je w ustalonej kolejności
 * na dostarczonym tekście. Obsługuje różne rodzaje transformacji, takie jak:
 * <ul>
 * <li>Prettify - formatowanie JSON-a na wersję nie minimalizowaną</li>
 * <li>Upper - konwersja tekstu na wielkie litery</li>
 * <li>Lower - konwersja tekstu na małe litery</li>
 * <li>Reverse - odwracanie kolejności znaków</li>
 * <li>Minify - minimalizacja JSON-a</li>
 * <li>Simplify - uproszczenie JSON-a</li>
 * </ul>
 *
 *
 * @author Spitree, sathell, woijk
 * @version 1.1.4
 */
public class TextTransformer {
    private final List<Transform> transforms;

    /**
     * Tworzy instancję {@code TextTransformer} na podstawie przekazanych nazw transformacji.
     *
     * <p>W konstruktorze mapowane są nazwy transformacji na ich odpowiednie implementacje.
     * W przypadku nieznanej transformacji lub pustej tablicy nazw, rzucany jest wyjątek.</p>
     *
     * @param transformNames tablica nazw transformacji do wykonania
     * @throws IllegalArgumentException jeśli tablica nazw transformacji jest pusta,
     *                                  null lub zawiera nieznaną nazwę
     */
    public TextTransformer(String[] transformNames) {
        if (transformNames == null || transformNames.length == 0) {
            throw new IllegalArgumentException("Transform names cannot be null or empty");
        }
        this.transforms = new ArrayList<>();

        // Mapowanie nazw transformacji na ich implementacje
        for (String transformName : transformNames) {
            switch (transformName.toLowerCase()) {
                case "prettify":
                    transforms.add(new PrettifyTransformer());
                    break;
                case "upper":
                    transforms.add(new UppercaseTransformer());
                    break;
                case "lower":
                    transforms.add(new LowercaseTransformer());
                    break;
                case "reverse":
                    transforms.add(new ReverseTransformer());
                    break;
                case "minify":
                    transforms.add(new MinifierTransformer());
                    break;
                case "simplify":
                    //transforms.add(new SimplifierTransformer());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown transform: " + transformName);
            }
        }
    }

    /**
     * Wykonuje wszystkie transformacje na dostarczonym tekście w ustalonej kolejności.
     *
     * <p>Każda transformacja modyfikuje tekst, przekazując go do kolejnej transformacji.</p>
     *
     * @param text tekst wejściowy, na którym mają zostać wykonane transformacje
     * @return wynikowy tekst po wykonaniu wszystkich transformacji
     */
    public String transform(String text) {
        for (Transform transform : transforms) {
            text = transform.transform(text);
        }
        return text;
    }
}
