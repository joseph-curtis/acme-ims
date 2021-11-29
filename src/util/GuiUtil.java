package util;

import controller.PartController;
import controller.ProductController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import model.Part;
import model.Product;

import java.io.IOException;

/**
 * Contains helper functions for changing scenes
 * Use for easier maintenance of code
 * instead of code duplication
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
        modifyPartController.modifyPartPass(passedPart);
    }

    // switches scene to Modify Product
    public static void changeScenePassProduct(ActionEvent event,
                                              Product passedProduct,
                                              String fxmlFileName,
                                              String windowTitle) throws IOException {

        FXMLLoader loader = changeScene(event, fxmlFileName, windowTitle);

        ProductController modifyProductController = loader.getController();
        modifyProductController.modifyProductPass(passedProduct);
    }
}
