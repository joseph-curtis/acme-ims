package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.*;
import util.GuiUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The Controller class for the Add and Modify Product forms.
 * @author Joseph Curtis
 * @version 2021.11.28
 */

public class AddModProductController implements Initializable {
    private Product existingProduct;
    private ObservableList<Part> assocPartsList = FXCollections.observableArrayList();

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

        assocPartsTableView.setItems(assocPartsList);
        assocPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        assocPartStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    public void editProductPass(Product oldProduct) {
        existingProduct = oldProduct;
        assocPartsList = oldProduct.getAllAssociatedParts();
        currentFunctionLabel.setText("Modify Product");

        idTxt.setText(String.valueOf(oldProduct.getId()));
        nameTxt.setText(oldProduct.getName());
        stockTxt.setText(String.valueOf(oldProduct.getStock()));
        priceTxt.setText(String.valueOf(oldProduct.getPrice()));
        minTxt.setText(String.valueOf(oldProduct.getMin()));
        maxTxt.setText(String.valueOf(oldProduct.getMax()));

        assocPartsTableView.setItems(oldProduct.getAllAssociatedParts());
        assocPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        assocPartStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    private void saveAssociatedParts(Product product) {
        // add all associated parts to new or modified product
        for (Part part : assocPartsList) {
            product.addAssociatedPart(part);
        }
    }

    @FXML
    private BorderPane rootBorderPane;

    @FXML
    private Button addPartButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button removePartButton;

    @FXML
    private Button saveButton;

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
    private TextField idTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField stockTxt;

    @FXML
    private TextField searchTxt;

    @FXML
    private Label currentFunctionLabel;

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        GuiUtil.changeSceneNew(event, "/view/MainForm.fxml", "Acme IMS - Main");
    }

    @FXML
    void onActionAddPart(ActionEvent event) {
        Part assocPart = (Part)invPartsTableView.getSelectionModel().getSelectedItem();
        if (!assocPartsList.contains(assocPart) && assocPart != null)
            assocPartsList.add(assocPart);
    }

    @FXML
    void onActionRemovePart(ActionEvent event) {
        assocPartsList.remove((Part)assocPartsTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {
        int id;
        String name = nameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(stockTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());

        if (existingProduct == null) {
            id = Inventory.getNewProductId();       // get new ID for new product
            Product newProduct = new Product(id, name, price, stock, min, max);
            saveAssociatedParts(newProduct);        // save list of associated Parts

            Inventory.addProduct(newProduct);
        } else {
            id = existingProduct.getId();           // get ID of existing product to edit
            Product modifiedProduct = new Product(id, name, price, stock, min, max);
            saveAssociatedParts(modifiedProduct);   // save list of associated Parts

            Inventory.updateProduct(id, modifiedProduct);
        }

        GuiUtil.changeSceneNew(event, "/view/MainForm.fxml", "Acme IMS - Main");
    }

}
