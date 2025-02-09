package de.mathisneunzig.escpos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class POSReceiptTest {

    @Test
    void testToBytes_ReceiptWithText() {
        POSReceipt receipt = new POSReceipt.Builder()
            .setTitle("Test Receipt")
            .addComponent(new POSText.Builder("Item: ABC").build())
            .setFooter("Thank You!")
            .build();

        byte[] bytes = receipt.toBytes();
        assertNotNull(bytes);
        assertTrue(new String(bytes).contains("Test Receipt"));
        assertTrue(new String(bytes).contains("Item: ABC"));
        assertTrue(new String(bytes).contains("Thank You!"));
    }
}
