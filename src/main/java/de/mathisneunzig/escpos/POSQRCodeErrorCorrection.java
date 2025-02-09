package de.mathisneunzig.escpos;

public enum POSQRCodeErrorCorrection {
    LOW(48), MEDIUM(49), QUARTILE(50), HIGH(51);

    private final int value;
    POSQRCodeErrorCorrection(int value) { this.value = value; }
    public int getValue() { return value; }
}
