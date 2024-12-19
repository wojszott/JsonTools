package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Główna klasa aplikacji TextTransformer.
 *
 * <p>Odpowiada za uruchomienie aplikacji Spring Boot oraz
 * konfigurację skanowania komponentów w pakiecie {@code pl.put.poznan.transformer}.
 * </p>
 *
 * @author wzorzec projektowy
 * @version 1.1.4
 */
@SpringBootApplication
@ComponentScan("pl.put.poznan.transformer")
public class TextTransformerApplication {

    /**
     * Główna metoda uruchamiająca aplikację.
     *
     * <p>Metoda wykorzystuje {@link SpringApplication#run(Class, String[])} do inicjalizacji
     * środowiska Spring Boot i uruchomienia aplikacji.</p>
     *
     * @param args tablica argumentów wejściowych przekazywanych przy uruchomieniu aplikacji
     */
    public static void main(String[] args) {
        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
