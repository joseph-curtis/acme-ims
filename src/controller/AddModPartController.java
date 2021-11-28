package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import util.GuiUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The Controller class for the Add and Modify Part forms.
 * @author Joseph Curtis
 * @version 2021.11.28
 */

public class AddModPartController {
    private Part existingPart;

    public void editPartPass(Part part) {
        existingPart = part;
        currentFunctionLabel.setText("Modify Part");

        idTxt.setText(String.valueOf(part.getId()));
        nameTxt.setText(part.getName());
        stockTxt.setText(String.valueOf(part.getStock()));
        priceTxt.setText(String.valueOf(part.getPrice()));
        minTxt.setText(String.valueOf(part.getMin()));
        maxTxt.setText(String.valueOf(part.getMax()));

        if (part instanceof InHouse) {
            sourceTxt.setText(String.valueOf(((InHouse) part).getMachineId()));
        } else if (part instanceof Outsourced) {
            changeOutsourced(true);
            sourceTxt.setText(String.valueOf(((Outsourced) part).getCompanyName()));
        }
    }

    private void changeOutsourced(boolean outsourced) {
        if (outsourced) {
            outsourcedRadioBtn.setSelected(true);
            sourceLabel.setText("Company Name");
        } else {
            inHouseRadioBtn.setSelected(true);
            sourceLabel.setText("Machine ID");
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
        GuiUtil.changeSceneNew(event, "/view/MainForm.fxml", "Acme IMS - Main");
    }

    @FXML
    void onActionInHouse(ActionEvent event) {
        changeOutsourced(false);
    }

    @FXML
    void onActionOutsourced(ActionEvent event) {
        changeOutsourced(true);
    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        int id;
        if (existingPart == null) {
            id = Inventory.getNewPartId();  // get new ID for new part
        } else {
            id = existingPart.getId();          // get ID of existing part to edit
            Inventory.deletePart(existingPart); // replace existing part with edited one
        }
        String name = nameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(stockTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());

        if (inHouseRadioBtn.isSelected()) {
            int machineId = Integer.parseInt(sourceTxt.getText());
            Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
        } else if (outsourcedRadioBtn.isSelected()) {
            String companyName = sourceTxt.getText();
            Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
        } else {
            throw new IOException("No Radio Button selected!");
        }
        GuiUtil.changeSceneNew(event, "/view/MainForm.fxml", "Acme IMS - Main");
    }

}
