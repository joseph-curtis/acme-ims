package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Inventory;
import model.Part;
import util.GuiUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddModProductController implements Initializable {

    /**
     * Initializes the controller class
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        invPartsTableView.setItems(Inventory.getAllParts());
        invPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        invPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        invPartStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    @FXML
    private Button addPartButton;

    @FXML
    private TableView<Part> assocPartsTableView;

    @FXML
    private TableColumn<Part, Integer> assocPartIdCol;

    @FXML
    private TableColumn<Part, String> assocPartNameCol;

    @FXML
    private TableColumn<Part, Double> assocPartPriceCol;

    @FXML
    private TableColumn<Part, Integer> assocPartStockCol;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField idTxt;

    @FXML
    private TableView<Part> invPartsTableView;

    @FXML
    private TableColumn<Part, Integer> invPartIdCol;

    @FXML
    private TableColumn<Part, String> invPartNameCol;

    @FXML
    private TableColumn<Part, Double> invPartPriceCol;

    @FXML
    private TableColumn<Part, Integer> invPartStockCol;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private Button removePartButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField searchTxt;

    @FXML
    private TextField stockTxt;

    @FXML
    void onActionAddPart(ActionEvent event) {

    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        GuiUtil.changeSceneOnEvent(event, "/view/MainForm.fxml", "Acme IMS - Main");
    }

    @FXML
    void onActionRemovePart(ActionEvent event) {

    }

    @FXML
    void onActionSaveProduct(ActionEvent event) {

    }

}
