package com.connerum.modernprice.Model;

import javax.print.*;
import org.slf4j.Logger;

public class Zebra {
    public void printLabel(Label label) {
        PrintFormatter printFormatter = new PrintFormatter();

        try {
            for (int i = 0; i < label.quantity; i++) {
                String defaultPrinter = PrintServiceLookup.lookupDefaultPrintService().getName();
                System.out.println("Default printer: " + defaultPrinter);

                PrintService service = PrintServiceLookup.lookupDefaultPrintService();

                String zplData = printFormatter.formatLabel(label);

                byte[] bytes = zplData.getBytes();

                DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
                Doc doc = new SimpleDoc(bytes, flavor, null);

                DocPrintJob job = service.createPrintJob();

                job.print(doc, null);
            }

        } catch (Exception e) {
            Logger logger = org.slf4j.LoggerFactory.getLogger(Zebra.class);
            logger.error("An error occurred: ", e);
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
                Logger logger = org.slf4j.LoggerFactory.getLogger(Zebra.class);
                logger.error("An error occurred: ", e);
            }
        } else {
            System.out.println("No available print services.");
        }
    }
}
