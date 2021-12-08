package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
 * @version 2021.12.07
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

    // Control declarations

    @FXML
    private BorderPane rootBorderPane;

    @FXML
    private Button exitButton;

    @FXML
    private Label searchErrorLabel;

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

    // Event declarations

    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        GuiUtil.changeScene(event, "/view/PartForm.fxml", "Acme IMS - Add Part");
    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        GuiUtil.changeScene(event, "/view/ProductForm.fxml", "Acme IMS - Add Product");
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        Part deletedPart = partsTable.getSelectionModel().getSelectedItem();
        if (deletedPart == null)
            return;     // no selection means nothing to delete or confirm

        GuiUtil.confirmDeletion(
                "Delete Part Confirmation"
                , "Delete Selected \"" + deletedPart.getName() + "\" ?"
                , "Part will be deleted.  This CANNOT be undone!"
                , ()-> Inventory.deletePart((Part)deletedPart));
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        Product deletedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (deletedProduct == null)
            return;         // no selection means nothing to delete or confirm

        if (deletedProduct.getAllAssociatedParts().isEmpty()) {
            GuiUtil.confirmDeletion(
                    "Delete Product Confirmation"
                    , "Delete Selected \"" + deletedProduct.getName() + "\" ?"
                    , "Product will be deleted.  This CANNOT be undone!"
                    , ()-> Inventory.deleteProduct((Product)deletedProduct));
        }
        else {
            // products with associated parts cannot be deleted
            Alert warningDelete = new Alert(Alert.AlertType.WARNING);
            warningDelete.setHeaderText("Unable to Delete \"" + deletedProduct.getName() + "\"");
            warningDelete.setContentText("This Product has " + deletedProduct.getAllAssociatedParts().size()
                    + " associated Parts.\nPlease remove all associated parts first.");
            warningDelete.showAndWait();
        }
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

        GuiUtil.changeScenePassPart(event, selectedPart,
                "/view/PartForm.fxml", "Acme IMS - Modify Part");
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        Product selectedProduct = (Product)productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null)
            return;     // if nothing is selected, do nothing

        GuiUtil.changeScenePassProduct(event, selectedProduct,
                "/view/ProductForm.fxml", "Acme IMS - Modify Product");
    }

    @FXML
    void partSearchKeyTyped(KeyEvent event) {
        String query = partSearchTxt.getText().trim();

        ObservableList<Part> partsFound = Inventory.lookupPart(query);

        try {
            int id = Integer.parseInt(query);
            Part partIdMatch = Inventory.lookupPart(id);
            if (partIdMatch != null)
                partsFound.add(partIdMatch);
        } catch (NumberFormatException exception) {
            // ignore exception, do not add anything to list
        }

        partsTable.setItems(partsFound);

        if (partsFound.size() == 0) {
            searchErrorLabel.setText("No part found with Name or ID entered");
        } else {
            searchErrorLabel.setText("");
        }
    }

    @FXML
    void productSearchKeyTyped(KeyEvent event) {
        String query = productSearchTxt.getText().trim();

        ObservableList<Product> productsFound = Inventory.lookupProduct(query);

        try {
            int id = Integer.parseInt(query);
            Product productIdMatch = Inventory.lookupProduct(id);
            if (productIdMatch != null)
                productsFound.add(productIdMatch);
        } catch (NumberFormatException exception) {
            // ignore exception, do not add anything to list
        }

        productsTable.setItems(productsFound);

        if (productsFound.size() == 0) {
            searchErrorLabel.setText("No product found with Name or ID entered");
        } else {
            searchErrorLabel.setText("");
        }
    }

}
