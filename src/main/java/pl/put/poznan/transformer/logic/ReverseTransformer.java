package pl.put.poznan.transformer.logic;

public class ReverseTransformer implements Transform {

    @Override
    public String transform(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
