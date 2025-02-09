package de.mathisneunzig.escpos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class POSBarcodeTest {

    @Test
    void testToBytes_BarcodeEAN13() {
        POSBarcode barcode = new POSBarcode.Builder("123456789012")
            .setType(POSBarcodeType.JAN13_EAN13)
            .setWidth(POSBarcodeWidth.DEFAULT)
            .build();

        byte[] bytes = barcode.toBytes();
        assertNotNull(bytes);
        assertTrue(bytes.length > 0);
    }

    @Test
    void testToBytes_BarcodeCODE128() {
        POSBarcode barcode = new POSBarcode.Builder("CODE128TEST")
            .setType(POSBarcodeType.CODE128)
            .setWidth(POSBarcodeWidth.THICK)
            .build();

        byte[] bytes = barcode.toBytes();
        assertNotNull(bytes);
        assertTrue(bytes.length > 0);
    }
}
