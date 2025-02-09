package de.mathisneunzig.escpos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class POSTextTest {

    @Test
    void testToBytes_SimpleText() {
        POSText text = new POSText.Builder("Hello ESC/POS").build();
        byte[] bytes = text.toBytes();
        assertNotNull(bytes);
        assertTrue(new String(bytes).contains("Hello ESC/POS"));
    }

    @Test
    void testToBytes_WithSpecialCharactersEUR() {
        POSText text = new POSText.Builder("Total: {EUR} 99.99").build();
        byte[] bytes = text.toBytes();
        assertNotNull(bytes);
        assertTrue(new String(bytes).contains("Total: EUR 99.99")); 
    }

    @Test
    void testToBytes_WithSpecialCharactersCAD() {
        POSText text = new POSText.Builder("Total: {CAD} 99.99").build();
        byte[] bytes = text.toBytes();
        assertNotNull(bytes);
        assertTrue(new String(bytes).contains("Total: C$ 99.99")); 
    }

    @Test
    void testToBytes_WithStyles() {
        POSText text = new POSText.Builder("Bold Text")
            .setStyle(POSPrintStyle.BOLD)
            .build();
        byte[] bytes = text.toBytes();
        assertNotNull(bytes);
        assertTrue(bytes.length > 0);
    }
}
