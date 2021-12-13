/*
 Copyright 2021 Joseph Curtis Licensed under the Educational
 Community License, Version 2.0 (the "License"); you may not use this file
 except in compliance with the License. You may obtain a copy of the License at

 http://opensource.org/licenses/ECL-2.0

  Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 License for the specific language governing permissions and limitations under
 the License.

 ******************************************************************************/

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
import util.BlankInputException;
import util.GuiUtil;
import util.InvObjNotFoundException;
import util.InvalidInputException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The Controller class for the Add and Modify Product forms.
 * @author Joseph Curtis
 * @version 2021.12.08
 */

public class ProductController implements Initializable {

    /**
     * The product in Inventory to modify
     */
    protected Product existingProduct;
    /**
     * The list of parts associated with this product
     */
    protected final ObservableList<Part> assocPartsList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class, formatting the TableView columns
     * @param location The location used to resolve relative paths for the root object,
     *            or null if the location is not known.
     * @param resources The resources used to localize the root object,
     *                       or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
     * This sets all properties for edited item to populate to corresponding text fields.
     * <p>The existing product in Inventory is passed when changing the scene</p>
     * <br>
     * <h1>LOGICAL ERROR</h1>
     * <p>When adding or deleting associated parts in the "Modify Product"
     * screen, the existing Product's associatedParts list was instantly
     * updated, even after the user clicked the Cancel button!  I found
     * this was because I was passing the associatedParts by <em>reference</em>
     * instead of passing by <em>value</em>.</p>
     * <p>My previous method used: <br>
     * <code>assocPartsList = oldProduct.getAllAssociatedParts();</code><br>
     * So the local variable <code>assocPartsList</code> was a pointer to the product's private
     * <code>associatedParts</code> List!  This is because the <code>private</code> modifier
     * prevents the variable being overwritten, but the nature of Lists (and ObservableLists)
     * allows items to be added or removed to the data structure.</p>
     * <p>I fixed the issue by calling the <code>addAll</code> method from the List class:
     * <br>assocPartsList.addAll(oldProduct.getAllAssociatedParts());<br>
     * This creates a <em>copy</em> of the list instead (passed by <strong>value</strong>)
     * so when the user clicks the "Cancel" button the original data integrity is intact.</p>
     * <p>After this fix, the associated Parts TableView would not update after
     * adding or removing associated parts. This was because the TableView was
     * connected to a now outdated object.  I fixed by making a call to refresh the table:
     * <br><code>assocPartsTableView.setItems(assocPartsList);</code><br>
     * This ensured up-to-date data was populated in the TableView for accuracy.
     * <br>See {@link #onActionAddPart(ActionEvent)} and {@link #onActionRemovePart(ActionEvent)}
     * source code for details.</p>
     * @see GuiUtil#changeScenePassProduct(ActionEvent, Product, String, String)
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
     * @see Inventory#getNewProductId()
     * @return a Product ID unique to Inventory
     */
    protected int acquireId() {
        if (existingProduct == null) {
            return Inventory.getNewProductId();  // get new ID for new Product
        } else {
            return existingProduct.getId();          // get ID of existing Product to edit
        }
    }

    /**
     * Add all associated parts to new or modified product.
     * @param product the product to be added or modified
     */
    protected void saveAssociatedParts(Product product) {
        for (Part part : assocPartsList) {
            product.addAssociatedPart(part);
        }
    }

    /*------------------------------------------*/
    //          Control declarations
    /*__________________________________________*/

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

    /*------------------------------------------*/
    //          Event declarations
    /*__________________________________________*/

    /**
     * Discard all changes, and go back to the "Main" screen.
     * @param event the user generated event (a button being clicked) that caused this to execute
     * @throws IOException if .fxml filename cannot be found
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        GuiUtil.changeScene(event, "/view/MainForm.fxml", "Acme IMS - Main");
    }

    /**
     * Adds the selected part to list of parts that is associated with this product.
     * <p>Refreshes the associated parts TableView to reflect changes.</p>
     * <p><b>See Also:</b><br>
     * {@link #setExistingProduct(Product)} for the <strong>LOGICAL ERROR</strong> description and fix.</p>
     * @param event the user generated event (a button being clicked) that caused this to execute
     */
    @FXML
    void onActionAddPart(ActionEvent event) {
        Part assocPart = (Part)invPartsTableView.getSelectionModel().getSelectedItem();

        if (assocPart != null && !assocPartsList.contains(assocPart))
            assocPartsList.add(assocPart);

        // refresh associated parts TableView:
        assocPartsTableView.setItems(assocPartsList);
    }

    /**
     * Deletes the selected part from associated parts list.
     * <p>Refreshes the associated parts TableView to reflect changes.</p>
     * <p><b>See Also:</b><br>
     * {@link #setExistingProduct(Product)} for the <strong>LOGICAL ERROR</strong> description and fix.</p>
     * @param event the user generated event (a button being clicked) that caused this to execute
     */
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

        // refresh associated parts TableView:
        assocPartsTableView.setItems(assocPartsList);
    }

    /**
     * Save this new or modified product.
     * <p>Updates existing product, or adds new product to Inventory.</p>
     * @param event the user generated event (a button being clicked) that caused this to execute
     * @throws IOException if .fxml filename cannot be found
     */
    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {
        try {
            if (nameTxt.getText().isBlank()
                    || priceTxt.getText().isBlank()
                    || stockTxt.getText().isBlank()
                    || minTxt.getText().isBlank()
                    || maxTxt.getText().isBlank() )
                throw new BlankInputException("Fields Cannot be Blank");

            // Get input from fields:
            int id = acquireId();
            String name = nameTxt.getText();
            int stock = GuiUtil.parseIntAndHandleException(stockTxt, "Inv");
            int min = GuiUtil.parseIntAndHandleException(minTxt, "Min");
            int max = GuiUtil.parseIntAndHandleException(maxTxt, "Max");
            double price = GuiUtil.parseDoubleAndHandleException(priceTxt, "Price");

            // validate input:
            if (min < 0 || max < 0 || stock < 0)
                GuiUtil.handleLogicalError("Min, Max, and Inv cannot be less than zero!");
            if (price < 0)
                GuiUtil.handleLogicalError("Price cannot be less than 0.00 !");
            if (max < min)
                GuiUtil.handleLogicalError("Min should be less than Max");
            if (stock < min || stock > max)
                GuiUtil.handleLogicalError("Inv should be between Min and Max");

            // create Product to save:
            Product savedProduct = new Product(id, name, price, stock, min, max);
            saveAssociatedParts(savedProduct);        // save list of associated Parts

            // update Inventory with Product (add or modify):
            if (existingProduct == null) {
                // Save new added product:
                Inventory.addProduct(savedProduct);
            } else {
                int index = Inventory.getAllProducts().indexOf(existingProduct);

                if (index < 0)
                    throw new InvObjNotFoundException("Existing Product to modify no longer exists in Inventory!");
                else
                    Inventory.updateProduct(index, savedProduct);
                    // (saves modified product)
            }

            // go back to the Main screen:
            GuiUtil.changeScene(event, "/view/MainForm.fxml", "Acme IMS - Main");
        }
        catch(InvObjNotFoundException exception) {
            GuiUtil.handleInvObjNotFoundException(exception);
        }
        catch(BlankInputException exception) {
            GuiUtil.handleBlankInputException(exception);
        }
        catch(InvalidInputException exception) {
            // Do nothing and return to Add/Modify Products screen
        }
    }

    /**
     * Searches the Inventory for any part name or ID that at least partially matches the search string.
     * <p>Each time the user presses a key this updates the listing of parts found in partsTable.</p>
     * <p>If search string is null, all parts are displayed.</p>
     * @param event user generated event (a key pressed) that caused this to execute
     */
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
