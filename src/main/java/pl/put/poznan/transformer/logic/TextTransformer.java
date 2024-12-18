package pl.put.poznan.transformer.logic;

/**
 * This is the logic for transforming text.
 */
public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms) {
        this.transforms = transforms;
    }

    public String transform(String text) {
        String result = text;

        for (String transform : transforms) {
            switch (transform.toLowerCase()) {
                case "upper":
                    result = result.toUpperCase();
                    break;
                case "reverse":
                    result = new StringBuilder(result).reverse().toString();
                    break;
                default:
                    // Nieznana transformacja
                    break;
            }
        }

        return result;
    }
}
