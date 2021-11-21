package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import model.Inventory;
import model.Part;
import model.Product;
import util.GuiUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    /**
     * Initializes the controller class
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        productsTable.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    // Controls declarations

    @FXML
    private Button exitButton;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> partStockCol;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TextField partSearchTxt;

    @FXML
    private Button partsAddButton;

    @FXML
    private Button partsDeleteButton;

    @FXML
    private Button partsModifyButton;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, Integer> productStockCol;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TextField productSearchTxt;

    @FXML
    private Button productsAddButton;

    @FXML
    private Button productsDeleteButton;

    @FXML
    private Button productsModifyButton;

    // event declarations

    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        GuiUtil.changeSceneOnEvent(event, "/view/AddModPartForm.fxml", "Acme IMS - Add Part");
    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        GuiUtil.changeSceneOnEvent(event, "/view/AddModProductForm.fxml", "Acme IMS - Add Product");
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        // TODO implement button action
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        // TODO implement button action
    }

    @FXML
    void onActionExitApplication(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        GuiUtil.changeSceneOnEvent(event, "/view/AddModPartForm.fxml", "Acme IMS - Modify Part");
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        GuiUtil.changeSceneOnEvent(event, "/view/AddModProductForm.fxml", "Acme IMS - Modify Product");
    }

    @FXML
    void partSearchTxtChanged(InputMethodEvent event) {
        // TODO implement button action
    }

    @FXML
    void productSearchTxtChanged(InputMethodEvent event) {
        // TODO implement button action
    }
}
