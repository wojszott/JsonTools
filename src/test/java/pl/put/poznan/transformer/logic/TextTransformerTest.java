package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextTransformerTest {
    @Test
    public void testUpperTransformation() {
        TextTransformer transformer = new TextTransformer(new String[]{"upper"});
        assertEquals("HELLO", transformer.transform("hello"));
    }

    @Test
    public void testMultipleTransformations() {
        TextTransformer transformer = new TextTransformer(new String[]{"upper", "reverse"});
        assertEquals("OLLEH", transformer.transform("hello"));
    }
}
