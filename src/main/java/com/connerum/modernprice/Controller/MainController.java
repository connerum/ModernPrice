package com.connerum.modernprice.Controller;

import com.connerum.modernprice.Model.Labels;
import com.connerum.modernprice.Model.Zebra;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TextField nameInput;
    @FXML
    private TextField cashInput;
    @FXML
    private TextField quantityInput;
    @FXML
    private ChoiceBox<String> labelSize;

    @FXML
    protected void printClick() {
        String cashString = cashInput.getText();
        String nameString = nameInput.getText();
        int quantityString = Integer.parseInt(quantityInput.getText());
        String labelSizeString = labelSize.getValue();

        Labels userLabel = new Labels(labelSizeString, nameString, quantityString, cashString);

        System.out.println(userLabel.cash);
        System.out.println(userLabel.name);
        System.out.println(userLabel.credit);
        System.out.println(userLabel.size);

        Zebra zebra = new Zebra();
        zebra.printLabel(userLabel);

        clearAllInputs();
    }


    protected void clearAllInputs() {
        cashInput.clear();
        nameInput.clear();
        quantityInput.clear();
        labelSize.getSelectionModel().clearSelection();
    }

    @FXML
    protected void initialize() {
        labelSize.getItems().addAll("2\" x 1\"", "1.25\" x 1\"", "2\" x 1/2\"");
    }
}