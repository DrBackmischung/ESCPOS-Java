package de.mathisneunzig.escpos;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.print.*;
import javax.print.attribute.PrintRequestAttributeSet;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class POSPrinterTest {

    @Test
    void testPrint_Receipt() {
        try {
            PrintService mockPrintService = Mockito.mock(PrintService.class);
            DocPrintJob mockPrintJob = Mockito.mock(DocPrintJob.class);
            when(mockPrintService.createPrintJob()).thenReturn(mockPrintJob);
			doNothing().when(mockPrintJob).print(any(Doc.class), any(PrintRequestAttributeSet.class));
			
	        POSPrinter printer = new POSPrinter(mockPrintService);

	        POSReceipt receipt = new POSReceipt.Builder()
	            .setTitle("Test Print")
	            .addComponent(new POSText.Builder("Hello, ESC/POS!").build())
	            .build();

	        assertDoesNotThrow(() -> printer.print(receipt));
		} catch (PrintException e) {
			e.printStackTrace();
		}

    }

    @Test
    void testDisableEnablePrinting() {
        // Step 1: Disable printing
        POSConfig.setDisablePrinting(true);
        assertTrue(POSConfig.isPrintingDisabled(), "Printing should be disabled");

        // Step 2: Print while disabled (should not actually print)
        POSPrinter printer = new POSPrinter(null); // No real print service
        POSReceipt receipt = new POSReceipt.Builder()
                .setTitle("Test Receipt")
                .addComponent(new POSText.Builder("Test Line").build())
                .build();

        assertDoesNotThrow(() -> printer.print(receipt), "Printing while disabled should not throw errors");

        // Step 3: Enable printing again
        POSConfig.setDisablePrinting(false);
        assertFalse(POSConfig.isPrintingDisabled(), "Printing should be enabled again");
    }
}
