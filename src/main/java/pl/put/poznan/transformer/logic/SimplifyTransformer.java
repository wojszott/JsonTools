package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

/**
 * Klasa implementująca transformację polegającą na uproszczeniu struktury JSON poprzez
 * zachowanie tylko określonych właściwości.
 *
 * Transformacja ta pozwala na filtrowanie struktury JSON i zachowanie tylko tych pól,
 * które zostały określone w przekazanym zestawie nazw właściwości.
 *
 * Przykład użycia:
 * String[] properties = {"id", "name"};
 * SimplifyTransformer transformer = new SimplifyTransformer(properties);
 * String result = transformer.transform(jsonString);
 */
public class SimplifyTransformer implements Transform {
    private final Set<String> propertiesToKeep;


    /**
     * Tworzy nowy transformer z określonym zestawem właściwości do zachowania.
     *
     * @param properties tablica nazw właściwości, które mają zostać zachowane w strukturze JSON
     */
    public SimplifyTransformer(String[] properties) {
        this.propertiesToKeep = new HashSet<>(Arrays.asList(properties));
    }

    /**
     * Przekształca wejściowy JSON, zachowując tylko określone właściwości.
     *
     * @param input tekst JSON do przekształcenia
     * @return uproszczony JSON zawierający tylko wybrane właściwości
     * @throws RuntimeException gdy wejściowy JSON ma niepoprawny format
     */
    @Override
    public String transform(String input) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(input);
            JsonNode simplifiedNode = simplifyNode(rootNode);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(simplifiedNode);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON format: " + e.getMessage(), e);
        }
    }

    /**
     * Rekurencyjnie upraszcza strukturę węzła JSON.
     *
     * @param node węzeł JSON do uproszczenia
     * @return uproszczony węzeł JSON
     */
    private JsonNode simplifyNode(JsonNode node) {
        ObjectMapper mapper = new ObjectMapper();

        if (node.isObject()) {
            ObjectNode result = mapper.createObjectNode();
            Iterator<String> fieldNames = node.fieldNames();

            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                if (propertiesToKeep.contains(fieldName)) {
                    result.set(fieldName, node.get(fieldName));
                } else if (node.get(fieldName).isObject() || node.get(fieldName).isArray()) {
                    JsonNode simplified = simplifyNode(node.get(fieldName));
                    if (simplified.size() > 0) {
                        result.set(fieldName, simplified);
                    }
                }
            }
            return result;
        } else if (node.isArray()) {
            ArrayNode result = mapper.createArrayNode();
            for (JsonNode element : node) {
                result.add(simplifyNode(element));
            }
            return result;
        }

        return node;
    }
}