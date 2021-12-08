package util;

import controller.PartController;
import controller.ProductController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import model.Part;
import model.Product;

import java.io.IOException;
import java.util.InputMismatchException;
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
            Alert deleteError = new Alert(Alert.AlertType.ERROR);
            deleteError.setHeaderText("Error in Inventory!");
            deleteError.setContentText("Unable to remove selected item!");
            deleteError.showAndWait();
        }
    }

    public static int parseIntAndHandleException(TextField field,
                                                 String fieldName) throws InputMismatchException {
        try {
            return Integer.parseInt(field.getText());
        }
        catch (NumberFormatException exception) {
            Alert inputError = new Alert(Alert.AlertType.ERROR);
            inputError.setHeaderText("\"" + field.getText() + "\" is not an Integer!");
            inputError.setContentText("Please enter only numeric (integer) data in the "
                    + "field: \"" + fieldName + "\"");
            inputError.showAndWait();
            throw new InputMismatchException();
        }
    }

    public static double parseDoubleAndHandleException(TextField field,
                                                       String fieldName) throws InputMismatchException {
        try {
            return Double.parseDouble(field.getText());
        }
        catch (NumberFormatException exception) {
            Alert inputError = new Alert(Alert.AlertType.ERROR);
            inputError.setHeaderText("\"" + field.getText() + "\" is not a Decimal!");
            inputError.setContentText("Please enter only numeric (decimal) data in the "
                    + "field: \"" + fieldName + "\"");
            inputError.showAndWait();
            throw new InputMismatchException();
        }
    }
}
