package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TextTransformer {
    private final String[] transforms;

    private static final Map<String, Function<String, String>> FUNCTIONS = new HashMap<>();

    static {
        FUNCTIONS.put("upper", String::toUpperCase);
        FUNCTIONS.put("lower", String::toLowerCase);
        FUNCTIONS.put("reverse", text -> new StringBuilder(text).reverse().toString());
    }

    public TextTransformer(String[] transforms) {
        this.transforms = transforms;
    }

    public String transform(String text) {
        String result = text;
        for (String transform : transforms) {
            Function<String, String> function = FUNCTIONS.get(transform.toLowerCase());
            if (function != null) {
                result = function.apply(result);
            }
        }
        return result;
    }
}
