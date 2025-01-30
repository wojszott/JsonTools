package pl.put.poznan.transformer.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class CompareTransformerTest {

    private CompareTransformer transformer;

    @BeforeEach
    void setUp() throws IOException {
        // Zakładając, że mamy plik o nazwie samplefile.txt z przykładowym tekstem
        transformer = new CompareTransformer("src/main/resources/sample.txt");
    }

    @Test
    void testTransformIdenticalTexts() {
        String input = "Line 1\nLine 2\nLine 3";
        String expected = "{\"message\": \"No differences found: Both texts are identical.\"}";
        String result = transformer.transform(input);
        assertEquals(expected, result);
    }


    @Test
    void testTransformWithNullText() {
        String input = null;
        String expected = "{\"message\": \"Entire text is different: One text is empty.\"}";
        String result = transformer.transform(input);
        assertEquals(expected, result);
    }
}
