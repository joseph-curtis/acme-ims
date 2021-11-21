package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import util.GuiUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddModPartController implements Initializable {

    /**
     * Initializes the controller class
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized!");
    }

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
        GuiUtil.changeSceneOnEvent(event, "/view/MainForm.fxml", "Acme IMS - Main");
    }

    @FXML
    void onActionInHouse(ActionEvent event) {

    }

    @FXML
    void onActionOutsourced(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) {

    }

}
