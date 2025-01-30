package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Główna klasa aplikacji Spring Boot, która uruchamia aplikację TextTransformer.
 * Aplikacja odpowiada za transformację tekstu na podstawie określonych reguł.
 *
 * <p>Używa Spring Boot do skonfigurowania aplikacji i uruchomienia serwera, który obsługuje transformacje tekstu.</p>
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    /**
     * Metoda uruchamiająca aplikację Spring Boot.
     *
     * @param args Argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
