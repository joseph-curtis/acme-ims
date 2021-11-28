package util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Contains helper functions for changing scenes
 * Use for easier maintenance of code
 * instead of copying the same code over and over
 */
public class GuiUtil {

    // helper function to switch scenes when "New Part/Product" button is clicked
    public static void changeSceneNew(ActionEvent event,
                                      String fxmlFileName,
                                      String windowTitle) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(GuiUtil.class.getResource(fxmlFileName));
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void changeSceneNew(ActionEvent event, String fxmlFileName) throws IOException {
        changeSceneNew(event, fxmlFileName, "Acme IMS");
    }

    // helper function to switch scenes when "New Part/Product" button is clicked
    public static void changeSceneModify(ActionEvent event, FXMLLoader loader, String windowTitle) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.getRoot();
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void changeSceneModify(ActionEvent event, FXMLLoader loader) {
        changeSceneModify(event, loader, "Acme IMS");
    }
}
