package com.connerum.modernprice.Model;

import java.io.FileOutputStream;
import java.io.PrintStream;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.DocPrintJob;
import javax.print.Doc;
import javax.print.DocFlavor;

public class Zebra {
    public static void main(String[] args) {
        try {
            String defaultPrinter = PrintServiceLookup.lookupDefaultPrintService().getName();
            System.out.println("Default printer: " + defaultPrinter);

            PrintService service = PrintServiceLookup.lookupDefaultPrintService();

            String zplData = "^XA" +
                    "^FO50,50" +
                    "^A0N,50,50" + // Font, height, and width
                    "^FB400,1,0,C,0" + // Field block for centering
                    "^FDShoes^FS" + // The text "Shoes"
                    "^FO50,150" + // Position for the second text field
                    "^A0N,50,50" + // Font, height, and width
                    "^FB400,1,0,C,0" + // Field block for centering
                    "^FD$129.99^FS" + // The text "$129.99"
                    "^XZ";

            byte[] bytes = zplData.getBytes();

            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(bytes, flavor, null);

            DocPrintJob job = service.createPrintJob();
            job.print(doc, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
