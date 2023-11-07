package com.connerum.modernprice.Model;

import org.slf4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;

public class Zebra {
    public void printLabel(Labels labels) {
        LabelFormatter labelFormatter = new LabelFormatter();

        try {
            for (int i = 0; i < labels.quantity; i++) {
                String zplData = labelFormatter.formatLabel(labels);

                // Write ZPL data to a temporary file
                Path tempFile = Files.createTempFile("labels", ".zpl");
                Files.write(tempFile, zplData.getBytes());

                // Use 'lpr' to send the ZPL file to the printer
                Process process = Runtime.getRuntime().exec("lpr -o raw " + tempFile);
                process.waitFor();

                // Delete the temporary file
                Files.delete(tempFile);
            }

        } catch (Exception e) {
            Logger logger = org.slf4j.LoggerFactory.getLogger(Zebra.class);
            logger.error("An error occurred: ", e);
        }
    }


    public void calibrate() {
        try {
            String calibrateCommand = "~JC\n";

            // Write ZPL data to a temporary file
            Path tempFile = Files.createTempFile("label", ".zpl");
            Files.write(tempFile, calibrateCommand.getBytes());

            // Use 'lpr' to send the ZPL file to the printer
            Process process = Runtime.getRuntime().exec("lpr -o raw " + tempFile);
            process.waitFor();

            // Delete the temporary file
            Files.delete(tempFile);
        }
        catch (Exception e) {
            Logger logger = org.slf4j.LoggerFactory.getLogger(Zebra.class);
            logger.error("An error occurred: ", e);
        }
    }
}
