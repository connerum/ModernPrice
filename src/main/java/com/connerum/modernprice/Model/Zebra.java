package com.connerum.modernprice.Model;

import javax.print.*;

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

    public void calibrate() {
        String calibrateCommand = "~JC\n";
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();

        if (service != null) {
            DocPrintJob job = service.createPrintJob();
            Doc doc = new SimpleDoc(calibrateCommand.getBytes(), flavor, null);

            try {
                job.print(doc, null);
            } catch (PrintException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No available print services.");
        }
    }
}
