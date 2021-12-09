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
 * Contains helper functions for changing scenes
 * Use for easier maintenance of code
 * instead of code duplication
 * @author Joseph Curtis
 * @version 2021.12.07
 */

public class GuiUtil {

    // switches scene to create new part/product
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

    // switches scene to Modify Part
    public static void changeScenePassPart(ActionEvent event,
                                           Part passedPart,
                                           String fxmlFileName,
                                           String windowTitle) throws IOException {

        FXMLLoader loader = changeScene(event, fxmlFileName, windowTitle);

        PartController modifyPartController = loader.getController();
        modifyPartController.setExistingPart(passedPart);
    }

    // switches scene to Modify Product
    public static void changeScenePassProduct(ActionEvent event,
                                              Product passedProduct,
                                              String fxmlFileName,
                                              String windowTitle) throws IOException {

        FXMLLoader loader = changeScene(event, fxmlFileName, windowTitle);

        ProductController modifyProductController = loader.getController();
        modifyProductController.setExistingProduct(passedProduct);
    }

    // displays confirmation for removing or deleting parts or products
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

    public static void handleInvObjNotFoundException(InvObjNotFoundException exception) {
        Alert inventoryError = new Alert(Alert.AlertType.ERROR);
        inventoryError.setHeaderText("Error in Inventory");
        inventoryError.setContentText(exception.getMessage());
        inventoryError.showAndWait();
    }

    public static void handleBlankInputException(BlankInputException exception) {
        Alert blankTextWarning = new Alert(Alert.AlertType.WARNING);
        blankTextWarning.setHeaderText(exception.getMessage());
        blankTextWarning.setContentText("Please enter data in each field.");
        blankTextWarning.showAndWait();
    }

    public static void handleLogicalError(String content) throws InvalidInputException {
        Alert deleteError = new Alert(Alert.AlertType.WARNING);
        deleteError.setHeaderText("Input Validation");
        deleteError.setContentText(content);
        deleteError.showAndWait();

        throw new InvalidInputException("Logical error check:\n" + content);
    }

}
