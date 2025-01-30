package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.InputStream;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Klasa implementująca walidację JSON względem schematu.
 */
public class JsonValidationTransformer implements Transform {
    private final JsonSchema schema;

    /**
     * Konstruktor wczytujący schemat JSON do walidacji.
     *
     * @param schemaPath ścieżka do pliku schematu JSON w zasobach
     */
    public JsonValidationTransformer(String schemaPath) {
        try {
            InputStream schemaStream = getClass().getClassLoader().getResourceAsStream(schemaPath);
            if (schemaStream == null) {
                throw new IllegalArgumentException("Schema file not found: " + schemaPath);
            }

            JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
            this.schema = schemaFactory.getSchema(schemaStream);
        } catch (Exception e) {
            throw new RuntimeException("Error loading JSON schema: " + e.getMessage(), e);
        }
    }

    /**
     * Waliduje wejściowy JSON względem schematu.
     *
     * @param input tekst JSON do walidacji
     * @return komunikaty o błędach lub informacja o poprawnej strukturze
     */
    @Override
    public String transform(String input) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(input);
            Set<ValidationMessage> errors = schema.validate(jsonNode);

            if (errors.isEmpty()) {
                return "JSON is valid!";
            }

            return "Validation errors:\n" + errors.stream()
                    .map(ValidationMessage::getMessage)
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "Invalid JSON format: " + e.getMessage();
        }
    }
}
