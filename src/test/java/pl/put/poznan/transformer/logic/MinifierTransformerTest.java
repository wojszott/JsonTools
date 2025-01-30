package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Klasa testowa dla MinifierTransformer.
 * Testuje różne przypadki minifikacji JSON.
 */

public class MinifierTransformerTest {

    private MinifierTransformer minifier;

    @BeforeEach
    public void setUp() {
        minifier = new MinifierTransformer();
    }

    @Test
    public void testMinifyJsonWithSpacesAndNewlines() {
        String input = "{\n  \"name\": \"John\",\n  \"age\": 30\n}";
        String expected = "{\"name\":\"John\",\"age\":30}";
        String result = minifier.transform(input);
        assertEquals(expected, result);
    }

    @Test
    public void testMinifyAlreadyMinifiedJson() {
        String input = "{\"name\":\"John\",\"age\":30}";
        String result = minifier.transform(input);
        assertEquals(input, result);
    }

    @Test
    public void testMinifyNestedJson() {
        String input = "{\n  \"a\": [1, 2, 3],\n  \"b\": { \"c\": 4 }\n}";
        String expected = "{\"a\":[1,2,3],\"b\":{\"c\":4}}";
        String result = minifier.transform(input);
        assertEquals(expected, result);
    }

    @Test
    public void testInvalidJsonReturnsErrorMessage() {
        String input = "{ \"a\": 1 ";
        String result = minifier.transform(input);
        assertTrue(result.startsWith("Błąd podczas transformacji JSON: "));
    }

    @Test
    public void testEmptyStringReturnsErrorMessage() {
        String input = "";
        String result = minifier.transform(input);
        assertTrue(result.startsWith("Błąd podczas transformacji JSON: "));
    }
}