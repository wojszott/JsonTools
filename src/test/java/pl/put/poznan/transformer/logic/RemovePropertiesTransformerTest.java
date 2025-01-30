package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemovePropertiesTransformerTest {

    private RemovePropertiesTransformer transformer;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        transformer = new RemovePropertiesTransformer(new String[]{"password", "secretKey"});
        mapper = new ObjectMapper();
    }

    private void assertJsonEquals(String expectedJson, String actualJson) throws Exception {
        JsonNode expected = mapper.readTree(expectedJson);
        JsonNode actual = mapper.readTree(actualJson);
        assertTrue(expected.equals(actual), "JSONs are not equal.");
    }

    @Test
    void testTransformWithNestedObjects() throws Exception {
        String input = "{ \"user\": { \"name\": \"John\", \"password\": \"1234\" } }";
        String expected = "{ \"user\": { \"name\": \"John\" } }";
        String result = transformer.transform(input);
        assertJsonEquals(expected, result);
    }

    @Test
    void testTransformWithNoMatchingProperties() throws Exception {
        String input = "{ \"user\": { \"name\": \"John\", \"age\": 30 } }";
        String expected = "{ \"user\": { \"name\": \"John\", \"age\": 30 } }";
        String result = transformer.transform(input);
        assertJsonEquals(expected, result);
    }

    @Test
    void testTransformEmptyJson() throws Exception {
        String input = "{}";
        String expected = "{}";
        String result = transformer.transform(input);
        assertJsonEquals(expected, result);
    }

    @Test
    void testTransformWithPropertiesRemoved() throws Exception {
        String input = "{ \"user\": { \"name\": \"John\", \"password\": \"1234\" } }";
        String expected = "{ \"user\": { \"name\": \"John\" } }";
        String result = transformer.transform(input);
        assertJsonEquals(expected, result);
    }

    @Test
    void testTransformWithNoPropertiesToRemove() throws Exception {
        String input = "{\"name\": \"John\", \"age\": 30, \"city\": \"New York\"}";
        String[] propertiesToRemove = {"password", "secretKey"};
        RemovePropertiesTransformer transformer = new RemovePropertiesTransformer(propertiesToRemove);
        String expected = "{\"name\": \"John\", \"age\": 30, \"city\": \"New York\"}"; // Oczekiwany wynik bez zmian
        String result = transformer.transform(input);
        assertJsonEquals(expected, result);
    }

}
