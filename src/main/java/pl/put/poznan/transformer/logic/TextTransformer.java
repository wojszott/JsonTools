package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

public class TextTransformer {
    private final List<Transform> transforms;

    // Konstruktor przyjmuje listę nazw transformacji
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
                    transforms.add(new JsonMinifier());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown transform: " + transformName + " ");
            }
        }
    }

    // Metoda wykonująca wszystkie transformacje
    public String transform(String text) {
        for (Transform transform : transforms) {
            text = transform.transform(text);
        }
        return text;
    }
}
