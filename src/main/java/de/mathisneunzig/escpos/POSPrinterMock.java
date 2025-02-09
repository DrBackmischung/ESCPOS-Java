package de.mathisneunzig.escpos;

import java.util.ArrayList;
import java.util.List;

public class POSPrinterMock implements POSPrinterInterface {
    private final List<byte[]> printedData = new ArrayList<>();

    @Override
    public void print(POSDocument document) {
        byte[] data = document.toBytes();
        printedData.add(data);
        System.out.println("[POSPrinterMock] Simulated printing " + data.length + " bytes.");
    }

    public List<byte[]> getPrintedData() {
        return printedData;
    }

    public void clearPrintedData() {
        printedData.clear();
    }
}
