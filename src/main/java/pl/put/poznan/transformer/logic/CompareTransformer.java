package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

//cos
public class CompareTransformer implements Transform {

    //cos
    private final String textToCompare;

    public CompareTransformer(String textToCompare) {
        this.textToCompare = textToCompare;
    }

    @Override
    public String transform(String text) {
        if (text == null && textToCompare == null) {
            return "No differences found: Both texts are empty.";
        } else if (text == null || textToCompare == null) {
            return "Entire text is different: One text is empty.";
        }

        String[] lines1 = text.split("\n");
        String[] lines2 = textToCompare.split("\n");

        int maxLines = Math.max(lines1.length, lines2.length);
        List<String> differences = new ArrayList<>();

        for (int i = 0; i < maxLines; i++) {
            String line1 = i < lines1.length ? lines1[i] : "";
            String line2 = i < lines2.length ? lines2[i] : "";

            if (!line1.equals(line2)) {
                differences.add("Line " + (i + 1) + ":\n" +
                        "Text 1: " + (line1.isEmpty() ? "(empty)" : line1) + "\n" +
                        "Text 2: " + (line2.isEmpty() ? "(empty)" : line2));
            }
        }

        return differences.isEmpty() ? "No differences found: Both texts are identical." :
                String.join("\n\n", differences);
    }
}
