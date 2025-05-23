package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * Klasa odpowiedzialna za zarządzanie i wykonywanie transformacji tekstu.
 * Tworzy listę transformacji na podstawie przekazanych nazw oraz wykonuje je w ustalonej kolejności
 * na dostarczonym tekście. Obsługuje różne rodzaje transformacji, takie jak:
 *
 *   Prettify - formatowanie JSON-a na wersję nie minimalizowaną
 *   Upper - konwersja tekstu na wielkie litery
 *   Lower - konwersja tekstu na małe litery
 *   Reverse - odwracanie kolejności znaków
 *   Minify - minimalizacja JSON-a
 *   Simplify - uproszczenie JSON-a
 *
 *
 * @author Spitree, sathell, woijk
 * @version 1.1.4
 */
public class TextTransformer {
    private final List<Transform> transforms;

    /**
     * Tworzy instancję {@code TextTransformer} na podstawie przekazanych nazw transformacji.
     * <p>W konstruktorze mapowane są nazwy transformacji na ich odpowiednie implementacje.
     * W przypadku nieznanej transformacji lub pustej tablicy nazw, rzucany jest wyjątek.</p>
     *
     * @param transformNames tablica nazw transformacji do wykonania
     * @param goodValues tablica dobrych wartości używanych w transformacjach
     * @param badValues tablica złych wartości używanych w transformacjach
     * @param TextToCompare tekst, który ma zostać porównany w transformacji "compare"
     * @throws IllegalArgumentException jeśli tablica nazw transformacji jest pusta,
     *                                  null lub zawiera nieznaną nazwę
     */
    public TextTransformer(String[] transformNames, String[] goodValues, String[] badValues, String TextToCompare) {
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
                    transforms.add(new SimplifyTransformer(goodValues));
                    break;
                case "compare":
                    try {
                        transforms.add(new CompareTransformer(TextToCompare));
                    } catch (IOException e) {
                        // Obsługuje wyjątek, np. loguje błąd lub informuje użytkownika
                        System.err.println("Error occurred while reading the file: " + e.getMessage());
                        // Możesz również ponownie rzucić wyjątek, jeśli chcesz przerwać działanie
                        // throw new RuntimeException("Error while creating CompareTransformer", e);
                    }
                    break;
                case "delete":
                    transforms.add(new RemovePropertiesTransformer(badValues));
                    break;
                case "validate":
                    transforms.add(new JsonValidationTransformer("example-schema.json"));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown transform: " + transformName);
            }
        }
    }

    /**
     * Wykonuje wszystkie transformacje na dostarczonym tekście w ustalonej kolejności.
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
