package com.connerum.modernprice.Model;

import java.util.Objects;

public class PrintFormatter {
    public String formatLabel(Label label) {
        String zplData = null;
        if (Objects.equals(label.size, "2\"  x  1\"")) {
            zplData = LargeSize(label);
        }
        else if (Objects.equals(label.size, "1 1/4\"  x  1\"")) {
            zplData = mediumSize(label);
        }
        else if (Objects.equals(label.size, "2\"  x  1/2\"")) {
            zplData = smallSize(label);
        }

        return zplData;
    }

    public String smallSize(Label label) {
        return "^XA" +
                "^LL101" +  // Explicitly set label length
                // "Card Price" text in the left half
                "^FO60,10" +
                "^A0N,18,18" +
                "^FB100,1,0,C,0" +
                "^FD" + "Card Price" + "^FS" +
                // Card price below "Card Price" text
                "^FO60,40" +
                "^A0N,24,24" +
                "^FB100,1,0,C,0" +
                "^FD" + "$" + label.credit + "^FS" +

                // "Cash Price" text in the right half
                "^FO210,10" +
                "^A0N,18,18" +
                "^FB100,1,0,C,0" +
                "^FD" + "Cash Price" + "^FS" +
                // Cash price below "Cash Price" text
                "^FO210,40" +
                "^A0N,24,24" +
                "^FB100,1,0,C,0" +
                "^FD" + "$" + label.cash + "^FS" +
                "^XZ";
    }

    public String mediumSize(Label label) {
        return "^XA" +
                "^LL203" +  // Explicitly set label length
                // "Card Price" text in the left half
                "^FO110,60" +
                "^A0N,24,24" +
                "^FB125,1,0,C,0" +
                "^FD" + "Card Price" + "^FS" +
                // Card price below "Card Price" text
                "^FO110,105" +
                "^A0N,28,28" +
                "^FB125,1,0,C,0" +
                "^FD" + "$" + label.credit + "^FS" +

                // "Cash Price" text in the right half
                "^FO232,60" +
                "^A0N,24,24" +
                "^FB125,1,0,C,0" +
                "^FD" + "Cash Price" + "^FS" +
                // Cash price below "Cash Price" text
                "^FO230,105" +
                "^A0N,28,28" +
                "^FB125,1,0,C,0" +
                "^FD" + "$" + label.cash + "^FS" +
                "^XZ";
    }

    public String LargeSize(Label label) {
        return "^XA" +
                // "Card Price" text in the middle of the left half
                "^FO35,50" +
                "^A0N,40,40" +
                "^FB190,1,0,C,0" +
                "^FD" + "Card Price" + "^FS" +
                // Card price below "Card Price" text
                "^FO35,125" +
                "^A0N,40,40" +
                "^FB190,1,0,C,0" +
                "^FD" + "$" + label.credit + "^FS" +

                // "Cash Price" text in the middle of the right half
                "^FO235,50" +
                "^A0N,40,40" +
                "^FB190,1,0,C,0" +
                "^FD" + "Cash Price" + "^FS" +
                // Cash price below "Cash Price" text
                "^FO235,125" +
                "^A0N,40,40" +
                "^FB190,1,0,C,0" +
                "^FD" + "$" + label.cash + "^FS" +
                "^XZ";
    }
}
