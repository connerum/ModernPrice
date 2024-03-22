package com.connerum.modernprice.Controller;

import com.connerum.modernprice.Model.LicenseStorage;
import com.connerum.modernprice.Model.LicenseValidator;
import com.connerum.modernprice.View.AppAlerts;
import com.connerum.modernprice.View.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

import java.io.IOException;

public class LicenseController {
    @FXML
    private ToolBar toolBar;
    @FXML
    private TextField keyInput;

    @FXML
    private Button activateButton;

    @FXML
    protected void closeWindow() {
        System.exit(0);
    }

    @FXML
    protected void activateClick() throws IOException {
        String license_key = keyInput.getText();

        if (LicenseValidator.validateLicenseKey(license_key)) {
            LicenseStorage.saveLicenseKey(license_key);
            successful();
        }

        else {
            AppAlerts appAlerts = new AppAlerts();
            appAlerts.invalidLicenseAlert("Failed to activate license");
        }

    }

    @FXML
    protected void successful() throws IOException {
        AppAlerts appAlerts = new AppAlerts();
        appAlerts.activated();
        Stage currentStage = (Stage) activateButton.getScene().getWindow();
        currentStage.close();

        Stage stage = new Stage();
        SceneLoader.loadMainScene(stage);
    }

    public ToolBar getToolbar() {
        return toolBar;
    }
}
