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

package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Part;
import model.Product;

import java.io.IOException;
import java.util.Objects;

/**
 * The main class for the Acme IMS Application.
 * This is where the JVM starts program execution.
 * Contains the Java <code>main</code> method.
 * <br>
 * <h1>JavaDocs folder is located within the root folder, ie: acme-ims\javadocs\</h1>
 * <br>
 * <h1>LOGICAL ERROR</h1>
 * <p>A logical error was found and corrected.
 * Please see: {@link controller.ProductController#setExistingProduct(model.Product)
 *  controller.ProductController.setExistingProduct(Product)} method for details.</p>
 * <br>
 * <h1>FUTURE ENHANCEMENT</h1>
 * <p>The Inventory class should use a database framework for storing Parts and
 * Products data (instead of keeping the data in memory).  This would allow
 * better data recovery in case of failure, and could also enable changing
 * of data concurrently -- ie. two (or more) users could add or modify items
 * at the same time.</p>
 * <p>One possible implementation is including the jTDS open source driver.
 * This implements the Java Database Connectivity (JDBC) Java API. This allows
 * a connection to an external database and communication to it via SQL script
 * commands.</p>
 * <p>The Inventory class would still use static variables to hold all the
 * Parts and Products fetched from the database, but calls to modify any item
 * would update the database, then fetch the modified record to ensure
 * success. This would also mean the Inventory class would not have to keep
 * track of used IDs to ensure unique identifiers. This would rely on the
 * database itself to implement unique identifiers.</p>
 * <br>
 * <p><b>Change <code>{@link model.Inventory#updatePart(int, model.Part)
 * Inventory.updatePart}</code> and
 * <code>{@link model.Inventory#updateProduct(int, model.Product)
 * Inventory.updateProduct}</code> method signatures</b></p>
 * <p>These methods do not need the <code>index</code> to the
 * <code>allParts</code> or <code>allProducts</code> ObservableLists.  By
 * requiring the indexes, we need to handle possible exceptions if the data in
 * Inventory changes from when <code>updatePart</code>/
 * <code>updateProduct</code> is called and the when ObservableLists are
 * actually set! If we change the method signatures to only accept
 * <code>selectedPart</code>/<code>newProduct</code> as the only parameter in
 * each, we ensure accuracy in the case of concurrent operations.</p>
 * <p>Possible solutions using for each loops are written below
 * the method implementation (as commented out code).<br>
 * Please see: {@link model.Inventory#updatePart(int, model.Part)} and
 * {@link model.Inventory#updateProduct(int, model.Product)} source code.</p>
 * @author Joseph Curtis
 * @version 2021.12.09
 */

public class IMS_Application extends Application {
    /**
     * Launches JavaFX GUI.
     * <p>Grabs container hierarchy from .fxml file</p>
     * <p>Uses static method to call <code>load</code> and get the root (parent node).
     * Then creates a new scene and sets the primaryStage to show it.</p>
     * <p>Controllers use methods from the <code>GuiUtil</code> class
     *  for better code encapsulation.</p>
     * @see util.GuiUtil#changeScene(ActionEvent, String, String)
     * @see util.GuiUtil#changeScenePassPart(ActionEvent, Part, String, String)
     * @see util.GuiUtil#changeScenePassProduct(ActionEvent, Product, String, String)
     * @param primaryStage The primary window where the application resides.
     * @throws IOException if the .fxml file cannot be found.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainForm.fxml")));

        primaryStage.setTitle("Acme IMS - Main");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * The Java main method where execution begins.
     * <p>This has only one statement that starts the application.</p>
     * @param args arguments passed from the OS (not used)
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
