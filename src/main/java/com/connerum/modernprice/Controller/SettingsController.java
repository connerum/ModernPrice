package com.connerum.modernprice.Controller;

import com.connerum.modernprice.MainApplication;
import com.connerum.modernprice.Model.Convert;
import com.connerum.modernprice.Model.Management;
import com.connerum.modernprice.View.AppAlerts;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SettingsController {
    private static String rateChoice;
    private static String rate;

    @FXML
    private ToggleGroup group;
    @FXML
    private RadioButton defaultRadio;
    @FXML
    private RadioButton customRadio;
    @FXML
    private TextField customRate;
    @FXML
    private Label updateStatusLabel;

    @FXML
    protected void updateClick() throws IOException {
        updateStatusLabel.setVisible(true);

        Management management = new Management();
        boolean status = management.isAppVersionUpToDate(MainApplication.APP_VERSION);

        if (status) {
            updateStatusLabel.setText("You are up to date");
            updateStatusLabel.setLayoutX(125);
            updateStatusLabel.setLayoutY(145);
        } else {
            updateStatusLabel.setText("An update is available");
            updateStatusLabel.setLayoutX(112);
            updateStatusLabel.setLayoutY(145);
        }
    }

    @FXML
    protected void cancelClick() {
        closeStage();
    }

    @FXML
    protected void saveClick() {
        if (defaultRadio.isSelected()) {
            // Save default settings
            Convert.rate = "1.04";
            rateChoice = "Default";
        } else if (customRadio.isSelected() && !customRate.getText().isEmpty()) {
            // Save custom settings
            rate = customRate.getText();
            Convert.rate = rate;
            rateChoice = "Custom";
        } else {
            // Alert or log that some values are not filled out
            AppAlerts appAlerts = new AppAlerts();
            appAlerts.showAlert("All fields must be filled out.");
            return;
        }
        closeStage();
    }

    @FXML
    protected void closeStage() {
        Stage stage = (Stage) defaultRadio.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void initialize() {
        group = new ToggleGroup();
        defaultRadio.setToggleGroup(group);
        customRadio.setToggleGroup(group);

        if (Objects.equals(rateChoice, "Custom")) {
            customRadio.setSelected(true);
            customRate.setText(rate);
        } else {
            defaultRadio.setSelected(true);
        }

        defaultRadio.setOnMousePressed(event -> customRate.setText(""));
    }
}
