package com.connerum.modernprice.Controller;

import com.connerum.modernprice.Model.Labels;
import com.connerum.modernprice.Model.Zebra;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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
    private ToggleGroup group;

    @FXML
    protected void printClick() {
        String cashString = cashInput.getText();
        int quantityString = Integer.parseInt(quantityInput.getText());
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String labelSizeString = selectedRadioButton.getText();  // This gets the text value of the selected RadioButton

        Labels userLabel = new Labels(labelSizeString, quantityString, cashString);

        Zebra zebra = new Zebra();
        zebra.printLabel(userLabel);

        clearAllInputs();
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