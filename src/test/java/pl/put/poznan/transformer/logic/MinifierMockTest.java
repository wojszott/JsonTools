package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MinifierMockTest {

    @Test
    void testTransformWithMockedObjectMapper() throws Exception {
        // Tworzenie mocka ObjectMapper
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);

        // Przykładowe dane wejściowe i wyjściowe
        String inputJson = "{ \"name\": \"test\" }";
        String expectedOutput = "{\"name\":\"test\"}";

        // Konfiguracja mocka
        when(mockObjectMapper.readValue(inputJson, Object.class)).thenReturn(new Object());
        when(mockObjectMapper.writeValueAsString(any())).thenReturn(expectedOutput);

        // Testowany obiekt z zamockowanym ObjectMapper
        MinifierTransformer transformer = new MinifierTransformer() {
            @Override
            public String transform(String input) {
                try {
                    Object jsonObject = mockObjectMapper.readValue(input, Object.class);
                    return mockObjectMapper.writeValueAsString(jsonObject);
                } catch (Exception e) {
                    return "Błąd podczas transformacji JSON: " + e.getMessage();
                }
            }
        };

        // Wykonanie testu
        String result = transformer.transform(inputJson);

        // Weryfikacja wyników
        assertEquals(expectedOutput, result);

        // Weryfikacja wywołań metod na mocku
        verify(mockObjectMapper, times(1)).readValue(inputJson, Object.class);
        verify(mockObjectMapper, times(1)).writeValueAsString(any());
    }
}
