package de.mathisneunzig.escpos;

public class POSReceipt extends POSDocument {
    private POSReceipt() {}

    public static class Builder {
        private final POSReceipt receipt = new POSReceipt();

        public Builder setTitle(String title) {
            receipt.addComponent(new POSText.Builder(title)
                .setStyle(POSPrintStyle.BOLD, POSPrintStyle.DOUBLE_HEIGHT)
                .setAlignment(POSTextAlignment.CENTER)
                .build());
            return this;
        }

        public Builder addItem(String itemName, double price) {
            receipt.addComponent(new POSText.Builder(String.format("%-20s %10.2f", itemName, price))
                .build());
            return this;
        }

        public Builder addItemStyled(String itemName, double price, POSPrintStyle... styles) {
            receipt.addComponent(new POSText.Builder(String.format("%-20s %10.2f", itemName, price))
                .setStyle(styles)
                .build());
            return this;
        }

        public Builder setFooter(String footer) {
            receipt.addComponent(new POSText.Builder(footer)
                .setAlignment(POSTextAlignment.CENTER)
                .setStyle(POSPrintStyle.UNDERLINE)
                .build());
            receipt.addLineFeed(2);
            return this;
        }

        public Builder addComponent(POSComponent component) {
            receipt.addComponent(component);
            return this;
        }

        public Builder addFeed() {
            receipt.addLineFeed();
            return this;
        }

        public Builder addFeed(int count) {
            receipt.addLineFeed(count);
            return this;
        }

        public POSReceipt build() {
            receipt.addLineFeed(3);
            return receipt;
        }
    }
}
