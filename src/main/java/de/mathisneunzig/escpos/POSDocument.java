package de.mathisneunzig.escpos;

import java.util.ArrayList;
import java.util.List;

public class POSDocument extends POSComponent {
    private final List<POSComponent> components = new ArrayList<>();

    public void addComponent(POSComponent component) {
        components.add(component);
    }
    
    public void addLineFeed(int count) {
        components.add(new POSLineFeed(count));
    }
    
    public void addLineFeed() {
        components.add(new POSLineFeed());
    }

    @Override
    public byte[] toBytes() {
        List<Byte> byteList = new ArrayList<>();
        
        //For some reason (and I have no clue why), some text is cut off from the beginning. Therefore I am adding some empty stuff so that everything is printed.
        
        byte[] buffer = new POSText.Builder("")
	        .setStyle(POSPrintStyle.BOLD, POSPrintStyle.DOUBLE_WIDTH)
	        .setAlignment(POSTextAlignment.CENTER)
	        .build().toBytes();
        
        for(byte b : buffer) {
        	byteList.add(b);
        }
        
        for(byte b : buffer) {
        	byteList.add(b);
        }
        
        for(byte b : buffer) {
        	byteList.add(b);
        }
        
        for(byte b : buffer) {
        	byteList.add(b);
        }

        byteList.add(POSCommand.ESC);
        byteList.add(POSCommand.PAGE_MODE);

        byteList.add(POSCommand.ESC);
        byteList.add(POSCommand.PRINTER_RESET);

        byteList.add(POSCommand.GS);
        byteList.add(POSCommand.STATUS_REQUEST);
        byteList.add((byte) 1);

        byteList.add(POSCommand.ESC);
        byteList.add(POSCommand.UNIDIRECTIONAL_MODE);
        byteList.add((byte) 1);

        byteList.add(POSCommand.ESC);
        byteList.add(POSCommand.PRINT_PAGE_MODE);

        for (POSComponent component : components) {
            for (byte b : component.toBytes()) {
                byteList.add(b);
            }
        }

        byte[] byteArray = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            byteArray[i] = byteList.get(i);
        }

        byteList.add(POSCommand.ESC);
        byteList.add(POSCommand.PRINTER_RESET);

        return byteArray;
    }
}
