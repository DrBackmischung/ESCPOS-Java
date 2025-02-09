package de.mathisneunzig.escpos;

import javax.print.*;

public class POSPrinter implements POSPrinterInterface {
    private final PrintService printService;

    public POSPrinter(PrintService printService) {
        this.printService = printService;
    }

    @Override
    public void print(POSDocument document) {
        if (POSConfig.isPrintingDisabled()) {
            System.out.println("[ESC/POS] Printing is disabled for testing.");
            return;
        }

        if (printService == null) {
            throw new IllegalStateException("No print service available");
        }

        try {
            DocPrintJob job = printService.createPrintJob();
            Doc doc = new SimpleDoc(document.toBytes(), DocFlavor.BYTE_ARRAY.AUTOSENSE, null);
            job.print(doc, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
