package com.connerum.modernprice.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrinterDriver {
    public void installPrinter() {
        try {
            String printerURI = findPrinterURI();
            if (printerURI == null) {
                System.out.println("Printer not found.");
                return;
            }

            String command = "lpadmin -p Zebra_Label -E -v '" + printerURI + "'";

            // Execute the command
            Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", command});
            process.waitFor();

            if (process.exitValue() == 0) {
                System.out.println("Printer added successfully.");
            } else {
                System.out.println("Error adding printer.");
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private String findPrinterURI() throws IOException {
        Process process = Runtime.getRuntime().exec("lpinfo -v");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("Zebra")) {
                // Extracting the URI part
                int index = line.indexOf("usb://");
                if (index != -1) {
                    return line.substring(index);
                }
            }
        }
        return null;
    }
}
