package com.connerum.modernprice.Controller;

import com.connerum.modernprice.MainApplication;
import com.connerum.modernprice.Model.Convert;
import com.connerum.modernprice.Model.Labels;
import com.connerum.modernprice.Model.Zebra;
import com.connerum.modernprice.View.AppAlerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    @FXML
    private TextField cashInput;
    @FXML
    private TextField quantityInput;
    @FXML
    private RadioButton sizeRatio1;
    @FXML
    private RadioButton sizeRatio2;
    @FXML
    private RadioButton sizeRatio3;
    @FXML
    private ToolBar toolBar;
    @FXML
    private ToggleGroup group;
    private String labelSizeString;


    public ToolBar getToolbar() {
        return toolBar;
    }


    @FXML
    protected void closeWindow() {
        System.exit(0);
    }

    @FXML
    protected void printClick() {
        Zebra zebra = new Zebra();
        AppAlerts appAlerts = new AppAlerts();

        String cashString = cashInput.getText();
        String quantityString = quantityInput.getText();  // Changed this to String to validate first
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();

        // Check if all values are filled out
        if (cashString.isEmpty() || quantityString.isEmpty() || selectedRadioButton == null) {
            // Alert or log that some values are not filled out
            appAlerts.showAlert("All fields must be filled out.");
            return;
        }

        int quantityInt;
        try {
            quantityInt = Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            // Handle invalid number format
            appAlerts.showAlert("Invalid quantity format.");
            return;
        }

        String labelSizeString = selectedRadioButton.getText();  // This gets the text value of the selected RadioButton

        if (!Objects.equals(labelSizeString, this.labelSizeString)) {
            zebra.calibrate();
        }

        Labels userLabels = new Labels(labelSizeString, quantityInt, cashString);
        System.out.println(Convert.rate);
        System.out.println(userLabels.credit);
        this.labelSizeString = labelSizeString;

        zebra.printLabel(userLabels);

        clearAllInputs();
    }

    @FXML
    protected void settingsClick() {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("settings-view.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle("ModernPrice - Settings"); // Set the title of the new window

            // Create a scene and set it as the content of the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setAlwaysOnTop(true);
            stage.initModality(Modality.APPLICATION_MODAL);

            // Show the new stage
            stage.showAndWait();
        } catch (IOException e) {
            // Handle any exceptions, such as FXML file not found
            Logger logger = org.slf4j.LoggerFactory.getLogger(Zebra.class);
            logger.error("An error occurred: ", e);
        }
    }

    protected void clearAllInputs() {
        cashInput.clear();
        quantityInput.clear();
    }

    @FXML
    protected void initialize() {
        Convert.rate = "1.04";
        group = new ToggleGroup();

        sizeRatio1.setToggleGroup(group);
        sizeRatio2.setToggleGroup(group);
        sizeRatio3.setToggleGroup(group);
    }
}