package de.mathisneunzig.escpos;

public class POSLineFeed extends POSComponent {
    private final int count;

    public POSLineFeed() {
        this(1);
    }

    public POSLineFeed(int count) {
        this.count = count;
    }

    @Override
    public byte[] toBytes() {
        byte[] lineFeeds = new byte[count];
        for (int i = 0; i < count; i++) {
            lineFeeds[i] = POSCommand.LINE_FEED;
        }
        return lineFeeds;
    }
}
