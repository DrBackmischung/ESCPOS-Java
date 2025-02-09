package de.mathisneunzig.escpos;

public enum POSPrintStyle {
    NONE(0), FONT_B(1), BOLD(8), DOUBLE_HEIGHT(16), DOUBLE_WIDTH(32), UNDERLINE(128);

    private final int value;
    POSPrintStyle(int value) { this.value = value; }
    public int getValue() { return value; }
}
