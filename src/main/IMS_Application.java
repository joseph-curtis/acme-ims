package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IMS_Application extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // grab container hierarchy from .fxml file:
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        primaryStage.setTitle("Acme IMS - Main");
        // create new scene and set primaryStage to show it
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
