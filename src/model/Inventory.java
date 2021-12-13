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

package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Inventory class for the data layer.
 * <p>Note that there is only one inventory, so all properties and methods are
 * static.  This is where the static data for all parts and products resides
 * (in memory).</p>
 * <p>Pre-populated test data can be added via a Static Initializer calling
 * the {@link #addTestData()} method.</p>
 * @author Joseph Curtis
 * @version 2021.12.08
 */

public class Inventory {
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int partsIdCounter = 0;
    private static int productsIdCounter = 0;

    static {
        addTestData();
    }

    /** Helper function to implement adding test data.
     * <p>Adds data to test while the IMS Application is in production.
     * This relies on a Static Initializer to call this as soon as the
     * Inventory class is initialized.</p>
     */
    protected static void addTestData() {
        InHouse widget = new InHouse(getNewPartId(), "Widget", 7.77, 7, 1, 70, 195);
        InHouse gadget = new InHouse(getNewPartId(), "Gadget", 12.50, 12, 1, 144, 482);
        Outsourced gizmo = new Outsourced(getNewPartId(), "Gizmo", 0.25, 100, 10, 1000, "W.E. Coyote Industries");
        Product macguffin = new Product(getNewProductId(), "MacGuffin", 99.99, 1, 1, 100);
        Product macdonwald = new Product(getNewProductId(), "MacDonwald", 1.49, 2, 1, 100);
        macguffin.addAssociatedPart(gadget);
        macguffin.addAssociatedPart(gizmo);
        addPart(widget);
        addPart(gadget);
        addPart(gizmo);
        addProduct(macguffin);
        addProduct(macdonwald);
    }

    /**
     * Add a new part to allParts
     * @param newPart the part to add to inventory
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Add a new product to allProducts
     * @param newProduct the product to add to inventory
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Search allParts for one with matching id
     * @param partId the id of part to search for
     * @return the part with matching id
     */
    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;    // part not found
    }

    /**
     * Search allProducts for one with matching id
     * @param productId the id of the product to search for
     * @return the product with matching id
     */
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;    // product not found
    }

    /**
     * Get filtered list where name contains search parameter
     * @param partName name of part to search
     * @return an ObservableList of parts filtered to match
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsFoundList = FXCollections.observableArrayList();
//      //            Could also use:     //  FXCollections.observableList(new ArrayList<Part>());

        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                partsFoundList.add(part);
            }
        }
        return partsFoundList;
    }

    /**
     * Get filtered list where name contains search parameter
     * @param productName name of product to search
     * @return an ObservableList of products filtered to match
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productsFoundList = FXCollections.observableArrayList();
//      //                  Could also use:     //  FXCollections.observableList(new ArrayList<Product>());

        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                productsFoundList.add(product);
            }
        }
        return productsFoundList;
    }

    /**
     * Replaces an existing Part with a modified one.
     * <h2>FUTURE ENHANCEMENT</h2>
     * <p>This method does not need the <code>index</code> to the <code>allParts</code>
     * ObservableList.  By requiring it, we need to handle a possible
     * exception if the data in Inventory changes from when <code>updatePart</code> is
     * called and the ObservableList is actually set! If we change the method
     * signature to only accept the <code>selectedPart</code> as a parameter, we ensure
     * accuracy in the case of concurrent operations.</p>
     * <p>A possible solution using a for each loop is written below
     * the method implementation (as commented out code).</p>
     * @param index the index in allParts to target
     * @param selectedPart Part to replace existing object in allParts
     */
    public static void updatePart(int index, Part selectedPart) {
        if (index >= 0)
            allParts.set(index, selectedPart);

        /*  // ForEach method does not need index parameter
        int i = 0;  // index for allParts array list
        int newPartID = selectedPart.getId();

        for (Part part : allParts) {
            if (part.getId() == newPartID) {
                allParts.set(i, selectedPart);
                return;
            }
            i++;    // go to next record
        }
        */
    }

    /**
     * Replaces an existing Product with a modified one.
     * <h2>FUTURE ENHANCEMENT</h2>
     * <p>This method does not need the <code>index</code> to the <code>allProducts</code>
     * ObservableList.  By requiring it, we need to handle a possible
     * exception if the data in Inventory changes from when <code>updateProduct</code> is
     * called and the ObservableList is actually set! If we change the method
     * signature to only accept the <code>newProduct</code> as a parameter, we ensure
     * accuracy in the case of concurrent operations.</p>
     * <p>A possible solution using a for each loop is written below
     * the method implementation (as commented out code).</p>
     * @param index the index in allProducts to target
     * @param newProduct Product to replace existing object in allProducts
     */
    public static void updateProduct(int index, Product newProduct) {
        if (index >= 0)
            allProducts.set(index, newProduct);

        /*  // ForEach method does not need index parameter
        int i = 0;  // index for allProducts array list
        int newProductID = newProduct.getId();

        for (Product product : allProducts) {
            if (product.getId() == newProductID) {
                allProducts.set(i, newProduct);
                return;
            }
            i++;    // go to next record
        }
        */
    }

    /**
     * Remove Part from Inventory
     * @param selectedPart part to remove from inventory
     * @return true if delete was successful
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * Remove Product from Inventory
     * @param selectedProduct product to remove from inventory
     * @return true if delete was successful
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * Gets all products currently saved in Inventory.
     * @return the list of all parts in inventory
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Gets all products currently saved in Inventory.
     * @return the list of all products in inventory
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * increases partsIdCounter, ensuring no conflicting parts ID in inventory
     * @return a unique Part ID
     */
    public static int getNewPartId() {
        return ++partsIdCounter;
    }

    /**
     * increases productIdCounter, ensuring no conflicting product ID in inventory
     * @return a unique Product ID
     */
    public static int getNewProductId() {
        return ++productsIdCounter;
    }
}
