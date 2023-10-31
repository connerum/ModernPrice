package com.connerum.modernprice.Model;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.DocPrintJob;
import javax.print.Doc;
import javax.print.DocFlavor;

public class Zebra {
    public void printLabel(Labels labels) {

        try {
            String defaultPrinter = PrintServiceLookup.lookupDefaultPrintService().getName();
            System.out.println("Default printer: " + defaultPrinter);

            PrintService service = PrintServiceLookup.lookupDefaultPrintService();

            String zplData = "^XA" +
                    // "Card Price" text in the middle of the left half
                    "^FO20,50" +
                    "^A0N,40,40" +
                    "^FB190,1,0,C,0" +
                    "^FD" + "Card Price" + "^FS" +
                    // Card price below "Card Price" text
                    "^FO20,100" +
                    "^A0N,40,40" +
                    "^FB190,1,0,C,0" +
                    "^FD" + "$" + labels.credit + "^FS" +

                    // "Cash Price" text in the middle of the right half
                    "^FO220,50" +
                    "^A0N,40,40" +
                    "^FB190,1,0,C,0" +
                    "^FD" + "Cash Price" + "^FS" +
                    // Cash price below "Cash Price" text
                    "^FO220,100" +
                    "^A0N,40,40" +
                    "^FB190,1,0,C,0" +
                    "^FD" + "$" + labels.cash + "^FS" +
                    "^XZ";

            byte[] bytes = zplData.getBytes();

            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(bytes, flavor, null);

            DocPrintJob job = service.createPrintJob();
            for (int i = 0; i < labels.quantity; i++) {
                job.print(doc, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
