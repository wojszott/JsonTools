package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Klasa testowa dla SimplifyTransformer.
 * Testuje różne przypadki transformacji JSON.
 */
class SimplifyTransformerTest {

    private SimplifyTransformer transformerBasic;
    private SimplifyTransformer transformerNested;

    @BeforeEach
    void setUp() {
        transformerBasic = new SimplifyTransformer(new String[]{"id", "name"});
        transformerNested = new SimplifyTransformer(new String[]{"id", "name", "address"});
    }

    /**
     * Test transformacji prostego obiektu JSON.
     */
    @Test
    void testSimpleJsonTransformation() {
        String input = """
            {
                "id": 1,
                "name": "Test",
                "age": 25,
                "email": "test@test.com"
            }""";

        String expected = """
            {
              "id" : 1,
              "name" : "Test"
            }""";

        String result = transformerBasic.transform(input);
        //assertEquals(expected, result);
    }

    /**
     * Test transformacji zagnieżdżonego obiektu JSON.
     */
    @Test
    void testNestedJsonTransformation() {
        String input = """
            {
                "id": 1,
                "name": "Test",
                "age": 25,
                "address": {
                    "street": "Main St",
                    "city": "Test City"
                }
            }""";

        String result = transformerNested.transform(input);
        assertTrue(result.contains("\"address\""));
        assertTrue(result.contains("\"street\""));
        assertTrue(result.contains("\"city\""));
        assertFalse(result.contains("\"age\""));
    }

    /**
     * Test transformacji pustego obiektu JSON.
     */
    @Test
    void testEmptyJson() {
        String input = "{}";
        String result = transformerBasic.transform(input);
        assertEquals("{ }", result);
    }

    /**
     * Test obsługi niepoprawnego JSON.
     */
    @Test
    void testInvalidJson() {
        String input = "{invalid json}";
        assertThrows(RuntimeException.class, () -> transformerBasic.transform(input));
    }

    /**
     * Test transformacji JSON bez pasujących właściwości.
     */
    @Test
    void testNoMatchingProperties() {
        String input = """
            {
                "age": 25,
                "email": "test@test.com"
            }""";

        String result = transformerBasic.transform(input);
        assertEquals("{ }", result);
    }
}