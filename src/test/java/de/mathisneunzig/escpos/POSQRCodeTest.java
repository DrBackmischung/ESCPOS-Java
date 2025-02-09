package de.mathisneunzig.escpos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class POSQRCodeTest {

    @Test
    void testToBytes_QRCodeGeneration() {
        POSQRCode qrCode = new POSQRCode.Builder("https://example.com").build();
        byte[] bytes = qrCode.toBytes();
        assertNotNull(bytes);
        assertTrue(bytes.length > 0);
        assertTrue(new String(bytes).contains("https://example.com"));
    }
}
