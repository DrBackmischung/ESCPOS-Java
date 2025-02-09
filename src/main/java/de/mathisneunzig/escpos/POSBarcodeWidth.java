package de.mathisneunzig.escpos;

public enum POSBarcodeWidth {
    THINNEST(2), THIN(3), DEFAULT(4), THICK(5), THICKEST(6);

    private final int value;
    POSBarcodeWidth(int value) { this.value = value; }
    public int getValue() { return value; }
}
