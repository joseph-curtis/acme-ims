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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import util.BlankInputException;
import util.GuiUtil;
import util.InvObjNotFoundException;
import util.InvalidInputException;

import java.io.IOException;

/**
 * The Controller class for the Add and Modify Part forms.
 * @author Joseph Curtis
 * @version 2021.12.09
 */

public class PartController {

    /**
     * The part in Inventory to modify
     */
    protected Part existingPart;

    /**
     * This sets all properties for edited item to populate to corresponding text fields.
     * <p>The existing part in Inventory is passed when changing the scene</p>
     * @see GuiUtil#changeScenePassPart(ActionEvent, Part, String, String)
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
     * gets existing ID or new unique ID if Part is new
     * @see Inventory#getNewPartId()
     * @return a Part ID unique to Inventory
     */
    protected int acquireId() {
        if (existingPart == null) {
            return Inventory.getNewPartId();  // get new ID for new part
        } else {
            return existingPart.getId();          // get ID of existing part to edit
        }
    }

    /*------------------------------------------*/
    //          Control declarations
    /*__________________________________________*/

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
     * Change the part type to "In-House".
     * <p>Source field becomes "Machine ID"</p>
     * @param event the user generated event (a radio button being clicked) that caused this to execute
     */
    @FXML
    void onActionInHouse(ActionEvent event) {
        inHouseRadioBtn.setSelected(true);
        sourceLabel.setText("Machine ID");
        sourceTxt.setPromptText("In-House ID");
    }

    /**
     * Change the part type to "Outsourced".
     * <p>Source field becomes "Company Name"</p>
     * @param event the user generated event (a radio button being clicked) that caused this to execute
     */
    @FXML
    void onActionOutsourced(ActionEvent event) {
        outsourcedRadioBtn.setSelected(true);
        sourceLabel.setText("Company Name");
        sourceTxt.setPromptText("Outsourced Company");
    }

    /**
     * Save this new or modified part.
     * <p>Updates existing part, or adds new part to Inventory.</p>
     * @param event the user generated event (a button being clicked) that caused this to execute
     * @throws IOException if .fxml filename cannot be found
     */
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
        Part savedPart;

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
            double price = GuiUtil.parseDoubleAndHandleException(priceTxt, "Price/Cost");

            // validate input:
            if (min < 0 || max < 0 || stock < 0)
                GuiUtil.handleLogicalError("Min, Max, and Inv cannot be less than zero!");
            if (price < 0)
                GuiUtil.handleLogicalError("Price/Cost cannot be less than 0.00 !");
            if (max < min)
                GuiUtil.handleLogicalError("Min should be less than Max");
            if (stock < min || stock > max)
                GuiUtil.handleLogicalError("Inv should be between Min and Max");

            // create Part to save:
            if (inHouseRadioBtn.isSelected()) {
                int machineId = GuiUtil.parseIntAndHandleException(sourceTxt, "Machine ID");
                savedPart = new InHouse(id, name, price, stock, min, max, machineId);
            } else if (outsourcedRadioBtn.isSelected()) {
                String companyName = sourceTxt.getText();
                savedPart = new Outsourced(id, name, price, stock, min, max, companyName);
            } else {
                throw new BlankInputException("No Radio Button selected!");
            }

            // update Inventory with Part (add or modify):
            if (existingPart == null) {
                // Save new added part:
                Inventory.addPart(savedPart);
            } else {
                int index = Inventory.getAllParts().indexOf(existingPart);

                if (index < 0)
                    throw new InvObjNotFoundException("Existing Part to modify no longer exists in Inventory!");
                else
                    Inventory.updatePart(index, savedPart);
                    // (saves modified part)
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
            // Do nothing and return to Add/Modify Parts screen
        }
    }

}
