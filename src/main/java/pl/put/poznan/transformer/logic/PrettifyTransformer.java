package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Klasa implementująca transformację polegającą na "upiększeniu" (prettify) JSON-a.
 *
 * <p>Transformacja ta przekształca surowy ciąg JSON-a w sformatowaną, czytelną strukturę,
 * z wcięciami i przejrzystym układem.</p>
 *
 * <p>W przypadku, gdy wejściowy tekst nie jest poprawnym JSON-em, rzucany jest wyjątek {@link RuntimeException}.</p>
 *
 * @author Spitree
 * @version 1.1.4
 */
public class PrettifyTransformer implements Transform {

    /**
     * Przekształca wejściowy tekst JSON na sformatowany, czytelny tekst.
     *
     * <p>Metoda korzysta z biblioteki Jackson do parsowania i ponownego generowania JSON-a
     * z zastosowaniem domyślnego formatera.</p>
     *
     * @param input surowy ciąg znaków zawierający JSON do przekształcenia
     * @return sformatowany JSON w postaci ciągu znaków
     * @throws RuntimeException gdy wejściowy JSON ma niepoprawny format
     */
    @Override
    public String transform(String input) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Object json = objectMapper.readValue(input, Object.class);

            ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
            return writer.writeValueAsString(json);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON format: " + e.getMessage(), e);
        }
    }
}
