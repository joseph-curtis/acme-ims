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

    // helper function to switch scenes
    public static void changeSceneOnEvent(ActionEvent event,
                                          String fxmlFileName,
                                          String windowTitle) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(GuiUtil.class.getResource(fxmlFileName));
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void changeSceneOnEvent(ActionEvent event,
                                          String fxmlFileName) throws IOException {
        changeSceneOnEvent(event, fxmlFileName, "Acme IMS");
    }
}
