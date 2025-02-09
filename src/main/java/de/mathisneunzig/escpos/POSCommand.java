package de.mathisneunzig.escpos;

public class POSCommand {
    public static final byte ESC = 0x1B;  // Escape character
    public static final byte GS = 0x1D;   // Group separator

    // Text Formatting Commands
    public static final byte INIT = 0x40;       // ESC @ = Initialize Printer
    public static final byte STYLE_MODE = 0x21; // ESC ! = Select Print Mode
    public static final byte ALIGNMENT = 0x61;  // ESC a = Align Text
    public static final byte LINE_FEED = 0x0A;  // LF = Line Feed
    public static final byte FEED_LINES = 0x64; // ESC d = Feed n Lines

    // Barcode & QR Code Commands
    public static final byte BARCODE_PRINT = 0x6B;           // GS k = Print Barcode
    public static final byte SET_BARCODE_HEIGHT = 0x68;      // GS h = Set Barcode Height
    public static final byte SET_BAR_WIDTH = 0x77;          // GS w = Set Barcode Width
    public static final byte SET_BAR_LABEL_POSITION = 0x48; // GS H = Set Barcode Label Position
    public static final byte SET_BAR_LABEL_FONT = 0x66;     // GS f = Set Barcode Font

    // Cut & Feed Commands
    public static final byte PAPER_CUT = 0x56;  // ESC V = Cut Paper
    public static final byte[] ESC_CUT = new byte[]{ESC, 0x69}; // Full Cut

    // Buffer Preloading Commands
    public static final byte PAGE_MODE = 0x4C;    // ESC L = Enter Page Mode (Buffering)
    public static final byte PRINT_PAGE_MODE = 0x46;  // ESC F = Print & Exit Page Mode
    public static final byte PRINTER_RESET = 0x40; // ESC @ = Reset Printer (Soft Reset)
    public static final byte STATUS_REQUEST = 0x72; // GS r = Request Printer Status
    public static final byte UNIDIRECTIONAL_MODE = 0x55; // ESC U = Unidirectional Mode Toggle

    // Method for Full Cut Command
    public static byte[] getFullCutCommand() {
        return new byte[]{ESC, PAPER_CUT, 0x00};
    }

    // Method for Partial Cut Command
    public static byte[] getPartialCutCommand() {
        return new byte[]{ESC, PAPER_CUT, 0x01};
    }
}
