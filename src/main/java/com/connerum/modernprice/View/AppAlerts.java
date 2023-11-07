package com.connerum.modernprice.View;

import javafx.scene.control.Alert;

public class AppAlerts {
    public void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setContentText(content);
        alert.showAndWait();
    }
}
