package org.realisticdreams.utility;

import java.util.List;
import java.util.Random;

public class RandomText {
    private final Random random = new Random();

    public enum DREAM {
        BAD, GOOD, DEFAULT
    }

    public RandomText() { }

    public String getRandomText(List<String> randomText, DREAM type) {
        if (randomText == null || randomText.isEmpty()) {
            throw new IllegalArgumentException("List of random text cannot be null or empty");
        }

        String selectedText = randomText.get(random.nextInt(randomText.size()));
        return formatText(selectedText, type);
    }

    private String formatText(String text, DREAM type) {
        switch (type) {
            case BAD:
                return TF.format(text, TF.RED, false, true);
            case GOOD:
                return TF.format(text, TF.GREEN, false, true);
            default:
                return text;
        }
    }
}
