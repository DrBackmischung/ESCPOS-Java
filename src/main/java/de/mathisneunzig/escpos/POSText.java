package de.mathisneunzig.escpos;

import java.util.ArrayList;
import java.util.List;

public class POSText extends POSComponent {
    private final String text;
    private final List<POSPrintStyle> styles;
    private final POSTextAlignment alignment;

    private POSText(Builder builder) {
        this.text = builder.text;
        this.styles = builder.styles;
        this.alignment = builder.alignment;
    }

    @Override
    public byte[] toBytes() {
        List<Byte> byteList = new ArrayList<>();

        byteList.add(POSCommand.ESC);
        byteList.add(POSCommand.ALIGNMENT);
        byteList.add((byte) alignment.getValue());

        for (POSPrintStyle style : styles) {
            byteList.add(POSCommand.ESC);
            byteList.add(POSCommand.STYLE_MODE);
            byteList.add((byte) style.getValue());
        }

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '{' && text.indexOf('}', i) > i) {
                int endIdx = text.indexOf('}', i);
                String placeholder = text.substring(i + 1, endIdx);
                POSSpecialCharacter specialChar = getSpecialCharacter(placeholder);
                if (specialChar != null) {
                    for (byte b : specialChar.getBytes()) {
                        byteList.add(b);
                    }
                    i = endIdx;
                    continue;
                }
            }
            byteList.add((byte) text.charAt(i));
        }

        byteList.add((byte) '\n');

        byteList.add(POSCommand.ESC);
        byteList.add(POSCommand.STYLE_MODE);
        byteList.add((byte) POSPrintStyle.NONE.getValue());

        byte[] byteArray = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            byteArray[i] = byteList.get(i);
        }

        return byteArray;
    }

    private POSSpecialCharacter getSpecialCharacter(String placeholder) {
        for (POSSpecialCharacter specialChar : POSSpecialCharacter.values()) {
            if (specialChar.name().equalsIgnoreCase(placeholder)) {
                return specialChar;
            }
        }
        return null;
    }

    public static class Builder {
        private final String text;
        private List<POSPrintStyle> styles = new ArrayList<>();
        private POSTextAlignment alignment = POSTextAlignment.LEFT;

        public Builder(String text) {
            this.text = text;
        }

        public Builder setStyle(POSPrintStyle... styles) {
            for (POSPrintStyle style : styles) {
                this.styles.add(style);
            }
            return this;
        }

        public Builder setAlignment(POSTextAlignment alignment) {
            this.alignment = alignment;
            return this;
        }

        public POSText build() {
            return new POSText(this);
        }
    }
}
