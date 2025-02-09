package de.mathisneunzig.escpos;

public class POSConfig {
    private static boolean disablePrinting = false;

    public static boolean isPrintingDisabled() {
        return disablePrinting;
    }

    public static void setDisablePrinting(boolean disable) {
        disablePrinting = disable;
    }
}
