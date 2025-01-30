package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Klasa testowa dla TextTransformer.
 */

public class TextTransformerTest {
    @Test
    public void testUpperTransformation() {
        TextTransformer transformer = new TextTransformer(new String[]{"upper"},null,null,"null");
        assertEquals("HELLO", transformer.transform("hello"));
    }

    @Test
    public void testMultipleTransformations() {
        TextTransformer transformer = new TextTransformer(new String[]{"upper", "reverse"},null,null, "null");
        assertEquals("OLLEH", transformer.transform("hello"));
    }
}
