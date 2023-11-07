package com.connerum.modernprice.Model;

import java.util.Objects;

public class LabelFormatter {
    public String formatLabel(Labels labels) {
        String zplData = null;
        if (Objects.equals(labels.size, "2\"  x  1\"")) {
            zplData = LargeSize(labels);
        }
        else if (Objects.equals(labels.size, "1 1/4\"  x  1\"")) {
            zplData = mediumSize(labels);
        }
        else if (Objects.equals(labels.size, "2\"  x  1/2\"")) {
            zplData = smallSize(labels);
        }

        return zplData;
    }

    public String smallSize(Labels labels) {
        return "^XA" +
                "^PQ1" + // Print Quantity set to 1
                "^LL101" +  // Explicitly set labels length
                // "Card Price" text in the left half
                "^FO45,18" +
                "^A0N,22,22" +
                "^FB125,1,0,C,0" +
                "^FD" + "Card Price" + "^FS" +
                // Card price below "Card Price" text
                "^FO45,46" +
                "^A0N,24,24" +
                "^FB125,1,0,C,0" +
                "^FD" + "$" + labels.credit + "^FS" +

                // "Cash Price" text in the right half
                "^FO270,18" +
                "^A0N,22,22" +
                "^FB125,1,0,C,0" +
                "^FD" + "Cash Price" + "^FS" +
                // Cash price below "Cash Price" text
                "^FO270,46" +
                "^A0N,24,24" +
                "^FB125,1,0,C,0" +
                "^FD" + "$" + labels.cash + "^FS" +
                "^XZ";
    }

    public String mediumSize(Labels labels) {
        return "^XA" +
                "^PQ1" + // Print Quantity set to 1
                "^LL203" +  // Explicitly set labels length (you may need to change 203 based on your specific medium labels size in dots)
                // "Card Price" text in the left half
                "^FO110,60" +
                "^A0N,24,24" +
                "^FB125,1,0,C,0" +
                "^FD" + "Card Price" + "^FS" +
                // Card price below "Card Price" text
                "^FO110,105" +
                "^A0N,25,25" +
                "^FB125,1,0,C,0" +
                "^FD" + "$" + labels.credit + "^FS" +

                // "Cash Price" text in the right half
                "^FO232,60" +
                "^A0N,24,24" +
                "^FB125,1,0,C,0" +
                "^FD" + "Cash Price" + "^FS" +
                // Cash price below "Cash Price" text
                "^FO230,105" +
                "^A0N,25,25" +
                "^FB125,1,0,C,0" +
                "^FD" + "$" + labels.cash + "^FS" +
                "^XZ";
    }

    public String LargeSize(Labels labels) {
        return "^XA" +
                "^PQ1" + // Print Quantity set to 1
                // "Card Price" text in the middle of the left half
                "^LL203" +
                "^FO35,50" +
                "^A0N,40,40" +
                "^FB190,1,0,C,0" +
                "^FD" + "Card Price" + "^FS" +
                // Card price below "Card Price" text
                "^FO35,125" +
                "^A0N,40,40" +
                "^FB190,1,0,C,0" +
                "^FD" + "$" + labels.credit + "^FS" +

                // "Cash Price" text in the middle of the right half
                "^FO235,50" +
                "^A0N,40,40" +
                "^FB190,1,0,C,0" +
                "^FD" + "Cash Price" + "^FS" +
                // Cash price below "Cash Price" text
                "^FO235,125" +
                "^A0N,40,40" +
                "^FB190,1,0,C,0" +
                "^FD" + "$" + labels.cash + "^FS" +
                "^XZ";
    }
}
