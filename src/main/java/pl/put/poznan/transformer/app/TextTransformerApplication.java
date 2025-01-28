package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
//cos
public class TextTransformerApplication {
    //cos
    public static void main(String[] args) {
        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
