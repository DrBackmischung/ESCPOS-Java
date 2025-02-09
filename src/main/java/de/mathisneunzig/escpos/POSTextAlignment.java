package de.mathisneunzig.escpos;

public enum POSTextAlignment {
    LEFT(0), CENTER(1), RIGHT(2);

    private final int value;
    POSTextAlignment(int value) { this.value = value; }
    public int getValue() { return value; }
}
