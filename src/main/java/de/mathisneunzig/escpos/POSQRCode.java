package de.mathisneunzig.escpos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class POSQRCode extends POSComponent {
    private final String data;
    private final POSQRCodeSize size;
    private final POSQRCodeErrorCorrection errorCorrection;

    private POSQRCode(Builder builder) {
        this.data = builder.data;
        this.size = builder.size;
        this.errorCorrection = builder.errorCorrection;
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            // Select QR Code Model (Model 2)
            output.write(POSCommand.GS);
            output.write(0x28);
            output.write(0x6B);
            output.write(4);
            output.write(0);
            output.write(49);
            output.write(65);
            output.write(50);
            output.write(0);

            // Set QR Code Size
            output.write(POSCommand.GS);
            output.write(0x28);
            output.write(0x6B);
            output.write(3);
            output.write(0);
            output.write(49);
            output.write(67);
            output.write(size.getValue());

            // Set QR Code Error Correction Level
            output.write(POSCommand.GS);
            output.write(0x28);
            output.write(0x6B);
            output.write(3);
            output.write(0);
            output.write(49);
            output.write(69);
            output.write(errorCorrection.getValue());

            // Store Data
            byte[] dataBytes = data.getBytes();
            int dataLength = dataBytes.length + 3;
            output.write(POSCommand.GS);
            output.write(0x28);
            output.write(0x6B);
            output.write(dataLength % 256);
            output.write(dataLength / 256);
            output.write(49);
            output.write(80);
            output.write(48);
            output.write(dataBytes);

            // Print QR Code
            output.write(POSCommand.GS);
            output.write(0x28);
            output.write(0x6B);
            output.write(3);
            output.write(0);
            output.write(49);
            output.write(81);
            output.write(48);

            output.write(POSCommand.LINE_FEED);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toByteArray();
    }

    public static class Builder {
        private final String data;
        private POSQRCodeSize size = POSQRCodeSize.MEDIUM;
        private POSQRCodeErrorCorrection errorCorrection = POSQRCodeErrorCorrection.MEDIUM;

        public Builder(String data) {
            this.data = data;
        }

        public Builder setSize(POSQRCodeSize size) {
            this.size = size;
            return this;
        }

        public Builder setErrorCorrection(POSQRCodeErrorCorrection errorCorrection) {
            this.errorCorrection = errorCorrection;
            return this;
        }

        public POSQRCode build() {
            return new POSQRCode(this);
        }
    }
}
