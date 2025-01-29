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
 * usunięcie tylko określonych właściwości.
 *
 * Transformacja ta pozwala na filtrowanie struktury JSON i usunięcie tylko tych pól,
 * które zostały określone w przekazanym zestawie nazw właściwości.
 *
 * Przykład użycia:
 * String[] properties = {"password", "secretKey"};
 * RemovePropertiesTransformer transformer = new RemovePropertiesTransformer(properties);
 * String result = transformer.transform(jsonString);
 */
public class RemovePropertiesTransformer implements Transform {
    private final Set<String> propertiesToRemove;

    /**
     * Tworzy nowy transformer z określonym zestawem właściwości do usunięcia.
     *
     * @param properties tablica nazw właściwości, które mają zostać usunięte ze struktury JSON
     */
    public RemovePropertiesTransformer(String[] properties) {
        this.propertiesToRemove = new HashSet<>(Arrays.asList(properties));
    }

    /**
     * Przekształca wejściowy JSON, usuwając określone właściwości.
     *
     * @param input tekst JSON do przekształcenia
     * @return uproszczony JSON bez określonych właściwości
     * @throws RuntimeException gdy wejściowy JSON ma niepoprawny format
     */
    @Override
    public String transform(String input) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(input);
            JsonNode modifiedNode = removeProperties(rootNode);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(modifiedNode);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON format: " + e.getMessage(), e);
        }
    }

    /**
     * Rekurencyjnie usuwa określone właściwości z węzła JSON.
     *
     * @param node węzeł JSON do modyfikacji
     * @return zmodyfikowany węzeł JSON bez określonych właściwości
     */
    private JsonNode removeProperties(JsonNode node) {
        ObjectMapper mapper = new ObjectMapper();

        if (node.isObject()) {
            ObjectNode result = mapper.createObjectNode();
            Iterator<String> fieldNames = node.fieldNames();

            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                if (!propertiesToRemove.contains(fieldName)) {
                    JsonNode childNode = removeProperties(node.get(fieldName));
                    result.set(fieldName, childNode);
                }
            }
            return result;
        } else if (node.isArray()) {
            ArrayNode result = mapper.createArrayNode();
            for (JsonNode element : node) {
                result.add(removeProperties(element));
            }
            return result;
        }

        return node;
    }
}
