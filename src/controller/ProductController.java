package controller;

import javafx.collections.FXCollections;
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
import java.io.InvalidObjectException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The Controller class for the Add and Modify Product forms.
 * @author Joseph Curtis
 * @version 2021.12.08
 */

public class ProductController implements Initializable {

    private Product existingProduct;
    private ObservableList<Part> assocPartsList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class -- sets up table views
     * @param url (not used)
     * @param resourceBundle (not used)
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

    /**
     * This sets all properties for edited item to populate to corresponding text fields
     * @param oldProduct existing product in inventory to be edited
     */
    public void setExistingProduct(Product oldProduct) {
        existingProduct = oldProduct;
        assocPartsList.addAll(oldProduct.getAllAssociatedParts());

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

    /**
     * gets existing ID or new unique ID if Product is new
     * @return a Product ID unique to Inventory
     */
    private int acquireId() {
        if (existingProduct == null) {
            return Inventory.getNewProductId();  // get new ID for new Product
        } else {
            return existingProduct.getId();          // get ID of existing Product to edit
        }
    }

    /**
     * add all associated parts to new or modified product
     * @param product the product to be added or modified
     */
    private void saveAssociatedParts(Product product) {
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
    private Label searchErrorLabel;

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        GuiUtil.changeScene(event, "/view/MainForm.fxml", "Acme IMS - Main");
    }

    @FXML
    void onActionAddPart(ActionEvent event) {
        Part assocPart = (Part)invPartsTableView.getSelectionModel().getSelectedItem();

        if (assocPart != null && !assocPartsList.contains(assocPart))
            assocPartsList.add(assocPart);

        // refresh all TableViews:
        initialize(null, null);
    }

    @FXML
    void onActionRemovePart(ActionEvent event) {
        Part removedPart = assocPartsTableView.getSelectionModel().getSelectedItem();
        if (removedPart == null)
            return;     // no selection means nothing to delete or confirm

        GuiUtil.confirmDeletion(
                "Remove Part Association" ,
                "Please Confirm Removal" ,
                "Remove associated Part \"" + removedPart.getName() + "\" ?" ,
                ()-> assocPartsList.remove((Part)removedPart));

        // refresh all TableViews:
        initialize(null, null);
    }

    @FXML
    void onActionSaveProduct(ActionEvent event) {
        try {
            if (nameTxt.getText().isBlank()
                    || priceTxt.getText().isBlank()
                    || stockTxt.getText().isBlank()
                    || minTxt.getText().isBlank()
                    || maxTxt.getText().isBlank() )
                throw new IOException("Fields Cannot be Blank");

            int id = acquireId();
            String name = nameTxt.getText();

            int stock = GuiUtil.parseIntAndHandleException(stockTxt, "Inv");
            int min = GuiUtil.parseIntAndHandleException(minTxt, "Min");
            int max = GuiUtil.parseIntAndHandleException(maxTxt, "Max");
            double price = GuiUtil.parseDoubleAndHandleException(priceTxt, "Price");

            Product savedProduct = new Product(id, name, price, stock, min, max);
            saveAssociatedParts(savedProduct);        // save list of associated Parts

            if (existingProduct == null) {
                // Save new added product:
                Inventory.addProduct(savedProduct);
            }
            else {
                int index = Inventory.getAllProducts().indexOf(existingProduct);

                if (index < 0)
                    throw new InvalidObjectException("Existing Product to modify no longer exists in Inventory!");
                else
                    Inventory.updateProduct(index, savedProduct);
                    // (saves modified product)
            }

            GuiUtil.changeScene(event, "/view/MainForm.fxml", "Acme IMS - Main");
        }
        catch(InvalidObjectException exception) {
            Alert inventoryError = new Alert(Alert.AlertType.ERROR);
            inventoryError.setHeaderText("Error in Inventory");
            inventoryError.setContentText(exception.getMessage());
            inventoryError.showAndWait();
        }
        catch(IOException exception) {
            Alert blankTextWarning = new Alert(Alert.AlertType.WARNING);
            blankTextWarning.setHeaderText(exception.getMessage());
            blankTextWarning.setContentText("Please enter data in each field.");
            blankTextWarning.showAndWait();
        }
        catch(IllegalArgumentException exception) {
            // Do nothing and return to Add/Modify Products screen
        }
    }

    @FXML
    void searchTxtKeyTyped(KeyEvent event) {
        String query = searchTxt.getText().trim();

        ObservableList<Part> partsFound = Inventory.lookupPart(query);

        try {
            int id = Integer.parseInt(query);
            Part partIdMatch = Inventory.lookupPart(id);
            if (partIdMatch != null)
                partsFound.add(partIdMatch);
        } catch (NumberFormatException exception) {
            // ignore exception, do not add anything to list
        }

        invPartsTableView.setItems(partsFound);

        if (partsFound.size() == 0) {
            searchErrorLabel.setText("No part found with Name or ID entered");
        } else {
            searchErrorLabel.setText("");
        }
    }

}
