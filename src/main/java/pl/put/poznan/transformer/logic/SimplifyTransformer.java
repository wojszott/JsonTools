package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Klasa implementująca transformację polegającą na uproszczeniu struktury JSON poprzez
 * zachowanie tylko określonych właściwości.
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

            if (!rootNode.isObject()) {
                throw new RuntimeException("Root JSON element must be an object.");
            }

            JsonNode simplifiedNode = simplifyNode((ObjectNode) rootNode, mapper);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(simplifiedNode);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON format: " + e.getMessage(), e);
        }
    }

    /**
     * Uproszcza strukturę JSON, pozostawiając tylko określone właściwości.
     *
     * @param node  obiekt JSON do uproszczenia
     * @param mapper obiekt Jackson do operacji na JSON
     * @return uproszczony JSON jako ObjectNode
     */
    private ObjectNode simplifyNode(ObjectNode node, ObjectMapper mapper) {
        ObjectNode result = mapper.createObjectNode();
        Iterator<String> fieldNames = node.fieldNames();

        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            if (propertiesToKeep.contains(fieldName)) {
                result.set(fieldName, node.get(fieldName));
            }
        }
        return result;
    }
}
