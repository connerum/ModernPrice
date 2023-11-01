package com.connerum.modernprice.Controller;

import com.connerum.modernprice.Model.Label;
import com.connerum.modernprice.Model.Zebra;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
        String cashString = cashInput.getText();
        String quantityString = quantityInput.getText();  // Changed this to String to validate first
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();

        // Check if all values are filled out
        if (cashString.isEmpty() || quantityString.isEmpty() || selectedRadioButton == null) {
            // Alert or log that some values are not filled out
            showAlert("All fields must be filled out.");
            return;
        }

        int quantityInt;
        try {
            quantityInt = Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            // Handle invalid number format
            showAlert("Invalid quantity format.");
            return;
        }

        String labelSizeString = selectedRadioButton.getText();  // This gets the text value of the selected RadioButton

        if (!Objects.equals(labelSizeString, this.labelSizeString)) {
            calibrateClick();
        }

        Label userLabel = new Label(labelSizeString, quantityInt, cashString);

        this.labelSizeString = labelSizeString;

        Zebra zebra = new Zebra();
        zebra.printLabel(userLabel);

        clearAllInputs();
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(content);
        alert.showAndWait();
    }

    protected void calibrateClick() {
        Zebra zebra = new Zebra();
        zebra.calibrate();
    }



    protected void clearAllInputs() {
        cashInput.clear();
        quantityInput.clear();
    }

    @FXML
    protected void initialize() {
        group = new ToggleGroup();

        sizeRatio1.setToggleGroup(group);
        sizeRatio2.setToggleGroup(group);
        sizeRatio3.setToggleGroup(group);
    }
}