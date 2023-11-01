package com.connerum.modernprice.Model;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.DocPrintJob;
import javax.print.Doc;
import javax.print.DocFlavor;

public class Zebra {
    public void printLabel(Labels labels) {
        PrintFormatter printFormatter = new PrintFormatter();

        try {
            for (int i = 0; i < labels.quantity; i++) {
            String defaultPrinter = PrintServiceLookup.lookupDefaultPrintService().getName();
            System.out.println("Default printer: " + defaultPrinter);

            PrintService service = PrintServiceLookup.lookupDefaultPrintService();

            String zplData = printFormatter.formatLabel(labels);

            byte[] bytes = zplData.getBytes();

            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(bytes, flavor, null);

            DocPrintJob job = service.createPrintJob();

            job.print(doc, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
