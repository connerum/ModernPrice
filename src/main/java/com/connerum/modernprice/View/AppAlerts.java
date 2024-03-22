package com.connerum.modernprice.View;

import javafx.scene.control.Alert;

public class AppAlerts {
    public void activated() {
        Alert alert = new Alert((Alert.AlertType.INFORMATION));
        alert.setTitle("Successfully Activated ModernPrice");
        alert.setContentText("You have successfully activated your ModernPrice license!");
        alert.showAndWait();
    }

    public void printerAlert(String printerName) {
        Alert alert = new Alert((Alert.AlertType.INFORMATION));
        alert.setTitle("Successfully set default printer");
        alert.setContentText("Successfully set " + printerName + " as your default printer");
        alert.showAndWait();
    }

    public void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void invalidLicenseAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Validating License");
        alert.setContentText(content);
        alert.showAndWait();
    }
}
