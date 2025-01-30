package pl.put.poznan.transformer.rest;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.model.TextTransformationRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextTransformerControllerTest {

    @Test
    public void testTransformWithFields() throws Exception {
        // Tworzymy instancję kontrolera
        TextTransformerController controller = new TextTransformerController();

        // Dane wejściowe
        TextTransformationRequest request = new TextTransformationRequest("Hello World", new String[]{"upper"});

        // Wywołanie metody z parametrem fields
        String fields = "originalText";
        String jsonResponse = null;
        //controller.transformText(fields,null,null,null);

        // Asercje
        assertTrue(jsonResponse.contains("\"originalText\":\"Hello World\""));
        assertTrue(!jsonResponse.contains("\"transformedText\""));
    }

    @Test
    public void testTransformWithAllFields() throws Exception {
        // Tworzymy instancję kontrolera
        TextTransformerController controller = new TextTransformerController();

        // Dane wejściowe
        TextTransformationRequest request = new TextTransformationRequest("Hello World", new String[]{"upper"});

        // Wywołanie metody bez parametru fields
        String jsonResponse = null;
                //controller.transformText(request, null);

        // Asercje
        //assertTrue(jsonResponse.contains("\"originalText\":\"Hello World\""));
        //assertTrue(jsonResponse.contains("\"transformedText\":\"HELLO WORLD\""));
    }
}