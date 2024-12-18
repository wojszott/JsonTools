package pl.put.poznan.transformer.logic;

public class UppercaseTransformer implements Transform {

    @Override
    public String transform(String input) {
        return input.toUpperCase();
    }
}
