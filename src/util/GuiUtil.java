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

package util;

import controller.PartController;
import controller.ProductController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Part;
import model.Product;

import java.io.IOException;
import java.util.Optional;
import java.util.function.BooleanSupplier;

/**
 * Contains helper functions for changing scenes and displaying dialog boxes to user.
 * <p>Use for easier maintenance of code
 * instead of code duplication</p>
 * @author Joseph Curtis
 * @version 2021.12.07
 */

public final class GuiUtil {

    /**
     * Switches scene to Create New Part or Product form.
     * @param event the user generated event (a button being clicked) that caused this to execute
     * @param fxmlFileName the .fxml file holding the next scene
     * @param windowTitle the new window title to set
     * @return FXML Loader for use when getting the controller
     * @throws IOException if .fxml filename cannot be found
     */
    public static FXMLLoader changeScene(ActionEvent event,
                                         String fxmlFileName,
                                         String windowTitle) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GuiUtil.class.getResource(fxmlFileName));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(root));
        stage.show();

        return loader;
    }

    /**
     * Switches scene to Modify Part form.
     * <p>The fields are populated with data from the existing part that is to be edited.</p>
     * @see PartController#setExistingPart(Part)
     * @param event the user generated event (a button being clicked) that caused this to execute
     * @param passedPart the part to modify
     * @param fxmlFileName the .fxml file holding the next scene
     * @param windowTitle the new window title to set
     * @throws IOException if .fxml filename cannot be found
     */
    public static void changeScenePassPart(ActionEvent event,
                                           Part passedPart,
                                           String fxmlFileName,
                                           String windowTitle) throws IOException {

        FXMLLoader loader = changeScene(event, fxmlFileName, windowTitle);

        PartController modifyPartController = loader.getController();
        modifyPartController.setExistingPart(passedPart);
    }

    /**
     * Switches scene to Modify Product form.
     * <p>The fields are populated with data from the existing product that is to be edited.</p>
     * @see ProductController#setExistingProduct(Product)
     * @param event the user generated event (a button being clicked) that caused this to execute
     * @param passedProduct the product to modify
     * @param fxmlFileName the .fxml file holding the next scene
     * @param windowTitle the new window title to set
     * @throws IOException if .fxml filename cannot be found
     */
    public static void changeScenePassProduct(ActionEvent event,
                                              Product passedProduct,
                                              String fxmlFileName,
                                              String windowTitle) throws IOException {

        FXMLLoader loader = changeScene(event, fxmlFileName, windowTitle);

        ProductController modifyProductController = loader.getController();
        modifyProductController.setExistingProduct(passedProduct);
    }

    /**
     * Displays a confirmation dialog for removing or deleting parts or products.
     * <p>When the user confirms by clicking "OK", the lambda function will execute.
     * If the lambda returns false, this indicates the delete operation was not successful.
     * In this case an error dialog is shown.</p>
     * @param title Dialog box window title
     * @param header Dialog box header text
     * @param content details within confirmation dialog box
     * @param lambda the operation to execute when the user clicks "OK"
     */
    public static void confirmDeletion(String title,
                                       String header,
                                       String content,
                                       BooleanSupplier lambda) {
        boolean success = true;

        Alert confirmRemove = new Alert(Alert.AlertType.CONFIRMATION);
        confirmRemove.setTitle(title);
        confirmRemove.setHeaderText(header);
        confirmRemove.setContentText(content);

        Optional<ButtonType> result = confirmRemove.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK)
            success = lambda.getAsBoolean();

        if (!success) {
            handleInvObjNotFoundException(
                    new InvObjNotFoundException("Unable to remove selected item!"));
        }
    }

    /**
     * Handles input validation for parsing an int from a text field.
     * @param textField the field in question that threw the error
     * @param fieldName the name of the input field
     * @return an int value parsed from input
     * @throws InvalidInputException for controller to handle by halting its operation
     */
    public static int parseIntAndHandleException(TextField textField,
                                                 String fieldName) throws InvalidInputException {
        try {
            return Integer.parseInt(textField.getText().trim());
        }
        catch (NumberFormatException exception) {
            Alert inputError = new Alert(Alert.AlertType.ERROR);
            inputError.setHeaderText("\"" + textField.getText() + "\" is not an Integer!");
            inputError.setContentText("Please enter only numeric (integer) data in the "
                    + "field: \n\"" + fieldName + "\"");
            inputError.showAndWait();

            throw new InvalidInputException("Attempt to parse integer failed", textField, fieldName);
        }
    }

    /**
     * Handles input validation for parsing a double from a text field.
     * @param textField the field in question that threw the error
     * @param fieldName the name of the input field
     * @return a double value parsed from input
     * @throws InvalidInputException for controller to handle by halting its operation
     */
    public static double parseDoubleAndHandleException(TextField textField,
                                                       String fieldName) throws InvalidInputException {
        try {
            return Double.parseDouble(textField.getText().trim());
        }
        catch (NumberFormatException exception) {
            Alert inputError = new Alert(Alert.AlertType.ERROR);
            inputError.setHeaderText("\"" + textField.getText() + "\" is not a Decimal!");
            inputError.setContentText("Please enter only numeric (decimal) data in the "
                    + "field: \n\"" + fieldName + "\"");
            inputError.showAndWait();

            throw new InvalidInputException("Attempt to parse double failed", textField, fieldName);
        }
    }

    /**
     * Gives user an Error dialog box to warn of a serious problem with the Inventory object.
     * This should appear when there is concurrent activity writing to the Inventory.
     * @param exception thrown exception indicating the problem
     */
    public static void handleInvObjNotFoundException(InvObjNotFoundException exception) {
        Alert inventoryError = new Alert(Alert.AlertType.ERROR);
        inventoryError.setHeaderText("Error in Inventory");
        inventoryError.setContentText(exception.getMessage());
        inventoryError.showAndWait();
    }

    /**
     * Pops up dialog box warning user of black input fields.
     * @param exception indicator of a blank field that needs to be inputted
     */
    public static void handleBlankInputException(BlankInputException exception) {
        Alert blankTextWarning = new Alert(Alert.AlertType.WARNING);
        blankTextWarning.setHeaderText(exception.getMessage());
        blankTextWarning.setContentText("Please enter data in each field.");
        blankTextWarning.showAndWait();
    }

    /**
     * Displays dialog box in response to a logical error (input validation).
     * @param content details given to user about the logical error
     * @throws InvalidInputException for controller to handle by halting its operation
     */
    public static void handleLogicalError(String content) throws InvalidInputException {
        Alert deleteError = new Alert(Alert.AlertType.WARNING);
        deleteError.setHeaderText("Input Validation");
        deleteError.setContentText(content);
        deleteError.showAndWait();

        throw new InvalidInputException("Logical error check:\n" + content);
    }

}
