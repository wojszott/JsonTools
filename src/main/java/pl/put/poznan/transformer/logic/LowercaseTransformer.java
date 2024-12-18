package pl.put.poznan.transformer.logic;

public class LowercaseTransformer implements Transform {

    @Override
    public String transform(String input) {
        return input.toLowerCase();
    }
}
