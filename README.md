# ESC/POS Printing Library

A **Java library** for printing receipts, barcodes, and QR codes on ESC/POS-compatible printers.

---

## 🚀 Features

✅ Print **formatted text** (alignments, styles, special characters)  
✅ Print **barcodes** (EAN-13, CODE128, etc.)  
✅ Print **QR codes** (with error correction and size settings)  
✅ **Mock printing** for unit/integration tests  
✅ Supports **disabling printing in tests**  

---

## 📌 Installation

Add this library to your **Maven project**:

```xml
<dependency>
  <groupId>de.mathisneunzig</groupId>
  <artifactId>escpos</artifactId>
  <version>1.0.0</version>
</dependency>
```

Or manually download the **JAR file** and include it in your project.

---

## 🎮 Quick Demo

### 🖨️ Printing a Receipt with Text, Barcodes, and QR Codes

```java
package de.mathisneunzig.escpos.demo;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import de.mathisneunzig.escpos.*;

public class POSDemo {
    public static void main(String[] args) {
        PrintService printerService = findPrintService("Printer");

        if (printerService == null) {
            System.out.println("Printer not found");
            return;
        }

        POSReceipt receipt = new POSReceipt.Builder()
                .setTitle("ESC/POS PRINTER DEMO")
                .addFeed()
                .addComponent(new POSText.Builder("Left Aligned").setAlignment(POSTextAlignment.LEFT).build())
                .addComponent(new POSText.Builder("Centered").setAlignment(POSTextAlignment.CENTER).build())
                .addComponent(new POSText.Builder("Right Aligned").setAlignment(POSTextAlignment.RIGHT).build())
                .addComponent(new POSText.Builder("BOLD").setStyle(POSPrintStyle.BOLD).build())
                .addComponent(new POSText.Builder("Underlined").setStyle(POSPrintStyle.UNDERLINE).build())
                .addItem("Product 1", 10.0)
                .addItem("Product 2", 5.5)
                .addFeed()
                .addComponent(new POSBarcode.Builder("123456789012")
                        .setType(POSBarcodeType.JAN13_EAN13)
                        .setWidth(POSBarcodeWidth.THIN)
                        .build())
                .addComponent(new POSQRCode.Builder("https://example.com")
                        .setSize(POSQRCodeSize.LARGE)
                        .setErrorCorrection(POSQRCodeErrorCorrection.HIGH)
                        .build())
                .addComponent(new POSText.Builder("----------------------").build())
                .addComponent(new POSText.Builder("Total: {EUR} 15.50").setStyle(POSPrintStyle.BOLD).build())
                .setFooter("Thank you!")
                .build();

        POSPrinter printer = new POSPrinter(printerService);
        printer.print(receipt);
    }

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
```

---

## 🛠️ Testing Guide

### 🔹 Disabling Printing for Tests

If you want to **prevent actual printing** in your tests, disable it using `POSConfig`.

```java
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import de.mathisneunzig.escpos.POSConfig;

public class SomeControllerTest {

    @BeforeAll
    static void setup() {
        POSConfig.setDisablePrinting(true); // Disable real printing for all tests
    }

    @Test
    void testSomeBusinessLogic() {
        someController.methodThatPrintsReceipt(); // No actual printing happens
    }
}
```

To **enable printing again**, use:

```java
POSConfig.setDisablePrinting(false); // Printing works again
```

---

### 🔹 Using `POSPrinterMock` for Tests

For unit tests where you need to **verify printed output** without a real printer, use `POSPrinterMock`.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import de.mathisneunzig.escpos.*;

public class POSPrinterMockTest {

    @Test
    void testMockPrinting() {
        POSPrinterMock printerMock = new POSPrinterMock();

        POSReceipt receipt = new POSReceipt.Builder()
            .setTitle("Test Receipt")
            .addComponent(new POSText.Builder("Test Line").build())
            .build();

        printerMock.print(receipt);

        assertEquals(1, printerMock.getPrintedData().size());
        assertTrue(printerMock.getPrintedData().get(0).length > 0);
    }
}
```

---

## 📜 License

This project is licensed under the **MIT License**.

---

### 🚀 Now You Can Print & Test ESC/POS Receipts Like a Pro!  
Let me know if you'd like any refinements! 🚀🔥
