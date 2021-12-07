package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import util.GuiUtil;

import java.io.IOException;

/**
 * The Controller class for the Add and Modify Part forms.
 * @author Joseph Curtis
 * @version 2021.11.28
 */

public class PartController {

    private Part existingPart;

    /**
     * This sets all properties for edited item to populate to corresponding text fields
     * @param oldPart existing part in inventory to be edited
     */
    public void setExistingPart(Part oldPart) {
        existingPart = oldPart;
        currentFunctionLabel.setText("Modify Part");

        idTxt.setText(String.valueOf(oldPart.getId()));
        nameTxt.setText(oldPart.getName());
        stockTxt.setText(String.valueOf(oldPart.getStock()));
        priceTxt.setText(String.valueOf(oldPart.getPrice()));
        minTxt.setText(String.valueOf(oldPart.getMin()));
        maxTxt.setText(String.valueOf(oldPart.getMax()));

        if (oldPart instanceof InHouse) {
            inHouseRadioBtn.fireEvent(new ActionEvent());
            sourceTxt.setText(String.valueOf(((InHouse) oldPart).getMachineId()));
        } else if (oldPart instanceof Outsourced) {
            outsourcedRadioBtn.fireEvent(new ActionEvent());
            sourceTxt.setText(String.valueOf(((Outsourced) oldPart).getCompanyName()));
        }
    }

    /**
     * gets existing ID or new unique ID if part is new
     * @return an ID unique to Inventory
     */
    private int acquireId() {
        if (existingPart == null) {
            return Inventory.getNewPartId();  // get new ID for new part
        } else {
            return existingPart.getId();          // get ID of existing part to edit
        }
    }

    @FXML
    private BorderPane rootBorderPane;

    @FXML
    private Button cancelButton;

    @FXML
    private Label currentFunctionLabel;

    @FXML
    private TextField idTxt;

    @FXML
    private RadioButton inHouseRadioBtn;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private RadioButton outsourcedRadioBtn;

    @FXML
    private TextField priceTxt;

    @FXML
    private Button saveButton;

    @FXML
    private ToggleGroup source;

    @FXML
    private Label sourceLabel;

    @FXML
    private TextField sourceTxt;

    @FXML
    private TextField stockTxt;

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        GuiUtil.changeScene(event, "/view/MainForm.fxml", "Acme IMS - Main");
    }

    @FXML
    void onActionInHouse(ActionEvent event) {
        inHouseRadioBtn.setSelected(true);
        sourceLabel.setText("Machine ID");
        sourceTxt.setPromptText("In-House ID");
    }

    @FXML
    void onActionOutsourced(ActionEvent event) {
        outsourcedRadioBtn.setSelected(true);
        sourceLabel.setText("Company Name");
        sourceTxt.setPromptText("Outsourced Company");
    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        Part savedPart;

        int id = acquireId();
        String name = nameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(stockTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());

        if (inHouseRadioBtn.isSelected()) {
            int machineId = Integer.parseInt(sourceTxt.getText());
            savedPart = new InHouse(id, name, price, stock, min, max, machineId);
        } else if (outsourcedRadioBtn.isSelected()) {
            String companyName = sourceTxt.getText();
            savedPart = new Outsourced(id, name, price, stock, min, max, companyName);
        } else {
            throw new IOException("No Radio Button selected!");
        }

        if (existingPart == null) {
            Inventory.addPart(savedPart);
        } else {
            Inventory.updatePart(id, savedPart);
        }

        GuiUtil.changeScene(event, "/view/MainForm.fxml", "Acme IMS - Main");
    }

}
