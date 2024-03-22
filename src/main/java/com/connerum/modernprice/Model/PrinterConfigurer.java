package com.connerum.modernprice.Model;

import com.connerum.modernprice.View.AppAlerts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PrinterConfigurer {
    public void printerConfigurer() {
        try {
            if (isZebraPrinterDefault()) {
                System.out.println("A Zebra printer is already set as the default printer.");
                return; // Skip setting default printer and showing alert
            }

            List<String> zebraPrinters = findZebraPrinters();
            if (!zebraPrinters.isEmpty()) {
                // Assuming you want to set the first found Zebra printer as default
                String zebraPrinter = zebraPrinters.get(0);
                setDefaultPrinter(zebraPrinter);

                AppAlerts appAlerts = new AppAlerts();
                appAlerts.printerAlert(zebraPrinter);
            } else {
                System.out.println("No Zebra printers found.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isZebraPrinterDefault() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("lpstat -d");
        process.waitFor();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = reader.readLine();
            if (line != null && line.toLowerCase().contains("zebra")) {
                return true; // The default printer is a Zebra printer
            }
        }
        return false; // No Zebra printer is set as default
    }

    private static List<String> findZebraPrinters() throws IOException, InterruptedException {
        List<String> zebraPrinters = new ArrayList<>();
        Process process = Runtime.getRuntime().exec("lpstat -p");
        process.waitFor();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains("zebra")) {
                    // Extract printer name
                    String printerName = line.split(" ")[1];
                    zebraPrinters.add(printerName);
                }
            }
        }
        return zebraPrinters;
    }

    private static void setDefaultPrinter(String printerName) throws IOException, InterruptedException {
        String command = String.format("lpoptions -d %s", printerName);
        Process process = Runtime.getRuntime().exec(command);
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println(printerName + " has been set as the default printer.");
        } else {
            System.out.println("Failed to set " + printerName + " as the default printer.");
        }
    }
}
