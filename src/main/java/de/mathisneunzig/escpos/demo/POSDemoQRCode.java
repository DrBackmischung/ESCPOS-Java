package de.mathisneunzig.escpos.demo;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import de.mathisneunzig.escpos.POSPrintStyle;
import de.mathisneunzig.escpos.POSPrinter;
import de.mathisneunzig.escpos.POSQRCode;
import de.mathisneunzig.escpos.POSQRCodeErrorCorrection;
import de.mathisneunzig.escpos.POSQRCodeSize;
import de.mathisneunzig.escpos.POSReceipt;
import de.mathisneunzig.escpos.POSText;

public class POSDemoQRCode {
    public static void main(String[] args) {
        // Locate the printer
        PrintService printerService = findPrintService("Printer");

        if (printerService == null) {
            System.out.println("Printer not found");
            return;
        }

        // Create the receipt
        POSReceipt receipt = new POSReceipt.Builder()
                // Title
                .setTitle("QR-Code DEMO")

                // Add Line Feeds for Spacing
                .addFeed()

                // Add QR Code Examples
                .addComponent(new POSText.Builder("QR Code Examples:").setStyle(POSPrintStyle.BOLD).build())
                .addComponent(new POSQRCode.Builder("https://example.com")
                        .setSize(POSQRCodeSize.SMALL)
                        .setErrorCorrection(POSQRCodeErrorCorrection.LOW)
                        .build())
                .addComponent(new POSQRCode.Builder("https://example.com")
                        .setSize(POSQRCodeSize.LARGE)
                        .setErrorCorrection(POSQRCodeErrorCorrection.MEDIUM)
                        .build())
                .addComponent(new POSQRCode.Builder("https://example.com")
                        .setSize(POSQRCodeSize.LARGE)
                        .setErrorCorrection(POSQRCodeErrorCorrection.QUARTILE)
                        .build())
                .addComponent(new POSQRCode.Builder("https://example.com")
                        .setSize(POSQRCodeSize.LARGE)
                        .setErrorCorrection(POSQRCodeErrorCorrection.HIGH)
                        .build())

                .build();

        // Print the receipt
        POSPrinter printer = new POSPrinter(printerService);
        printer.print(receipt);
    }

    // Helper method to find a printer by name
    public static PrintService findPrintService(String printerName) {
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService service : services) {
            if (service.getName().equalsIgnoreCase(printerName)) {
                return service;
            }
        }
        return null;
    }
}
