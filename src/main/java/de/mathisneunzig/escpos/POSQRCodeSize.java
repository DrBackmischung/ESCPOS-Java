package de.mathisneunzig.escpos;

public enum POSQRCodeSize {
    SMALL(2), MEDIUM(3), LARGE(4), EXTRA_LARGE(5);

    private final int value;
    POSQRCodeSize(int value) { this.value = value; }
    public int getValue() { return value; }
}
