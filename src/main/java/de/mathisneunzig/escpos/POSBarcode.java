package de.mathisneunzig.escpos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class POSBarcode extends POSComponent {
    private final String data;
    private final POSBarcodeType type;
    private final POSBarcodeWidth width;

    private POSBarcode(Builder builder) {
        this.data = builder.data;
        this.type = builder.type;
        this.width = builder.width;
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            output.write(POSCommand.GS);
            output.write(POSCommand.BARCODE_PRINT);
            output.write(type.getValue());
            output.write(data.length());
            output.write(data.getBytes());
            output.write(POSCommand.GS);
            output.write(POSCommand.SET_BAR_WIDTH);
            output.write(width.getValue());
            output.write(POSCommand.LINE_FEED);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toByteArray();
    }

    public static class Builder {
        private final String data;
        private POSBarcodeType type = POSBarcodeType.CODE128;
        private POSBarcodeWidth width = POSBarcodeWidth.DEFAULT;

        public Builder(String data) {
            this.data = data;
        }

        public Builder setType(POSBarcodeType type) {
            this.type = type;
            return this;
        }

        public Builder setWidth(POSBarcodeWidth width) {
            this.width = width;
            return this;
        }

        public POSBarcode build() {
            return new POSBarcode(this);
        }
    }
}
