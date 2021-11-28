package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.BorderPane;
import model.Inventory;
import model.Part;
import model.Product;
import util.GuiUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The Controller class for the Main form.
 * @author Joseph Curtis
 * @version 2021.11.28
 */

public class MainController implements Initializable {

    /**
     * Initializes the controller class, formatting the TableView columns
     * @param url (not used)
     * @param resourceBundle (not used)
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
    private BorderPane rootBorderPane;

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
        GuiUtil.changeSceneNew(event, "/view/AddModPartForm.fxml", "Acme IMS - Add Part");
    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        GuiUtil.changeSceneNew(event, "/view/AddModProductForm.fxml", "Acme IMS - Add Product");
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        Inventory.deletePart((Part)partsTable.getSelectionModel().getSelectedItem());
        // TODO implement confirm dialog
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        Inventory.deleteProduct((Product)productsTable.getSelectionModel().getSelectedItem());
        // TODO implement confirm dialog
    }

    @FXML
    void onActionExitApplication(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        Part selectedPart = (Part)partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null)
            return;     // if nothing is selected, do nothing

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AddModPartForm.fxml"));
        loader.load();

        AddModPartController editPartController = loader.getController();
        editPartController.editPartPass(selectedPart);

        GuiUtil.changeSceneModify(event, loader, "Acme IMS - Modify Part");
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        Product selectedProduct = (Product)productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null)
            return;     // if nothing is selected, do nothing

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AddModProductForm.fxml"));
        loader.load();

        AddModProductController editProductController = loader.getController();
        editProductController.editProductPass(selectedProduct);

        GuiUtil.changeSceneModify(event, loader, "Acme IMS - Modify Product");
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
