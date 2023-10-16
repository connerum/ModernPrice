package com.connerum.modernprice.Controller;

import com.connerum.modernprice.Model.Labels;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private Button printButton;
    @FXML
    private TextField cashInput;
    @FXML
    private TextField quantityInput;
    @FXML
    private ChoiceBox<String> labelSize;

    @FXML
    protected void printClick() {
        validateInputs();
        String cashString = cashInput.getText();
        int quantityString = Integer.parseInt(quantityInput.getText());
        String labelSizeString = labelSize.getValue();
        Labels userLabel = new Labels(labelSizeString, quantityString, cashString);

        System.out.println(userLabel.cash);
        System.out.println(userLabel.credit);
        System.out.println(userLabel.size);
        clearAllInputs();
    }



    @FXML protected void checkInputs() {
        validateInputs();
    }

    @FXML
    protected void validateInputs() {
        printButton.setDisable(cashInput.getText().isEmpty() || quantityInput.getText().isEmpty() || labelSize.getValue() == null);
    }

    protected void clearAllInputs() {
        cashInput.clear();
        quantityInput.clear();
        labelSize.getSelectionModel().clearSelection();
    }

    @FXML
    protected void initialize() {
        printButton.setDisable(true);
        labelSize.getItems().addAll("2\" x 1\"", "1.25\" x 1\"", "2\" x 1/2\"");
    }
}