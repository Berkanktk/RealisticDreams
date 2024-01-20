package org.realisticdreams.utility;

public class TF {
    private static final boolean DEFAULT_BOLD = false;
    private static final boolean DEFAULT_ITALIC = false;

    public static final String RED = "§c";
    public static final String GREEN = "§a";
    public static final String BOLD = "§l";
    public static final String ITALIC = "§o";

    public static String format(String message, String color) {
        return format(message, color, DEFAULT_BOLD, DEFAULT_ITALIC);
    }

    public static String format(String message, String color, boolean isBold) {
        return format(message, color, isBold, DEFAULT_ITALIC);
    }

    public static String format(String message, String color, boolean isBold, boolean isItalic) {
        return color + (isBold ? BOLD : "") + (isItalic ? ITALIC : "") + message;
    }
}
