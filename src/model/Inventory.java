package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * the Inventory for the management system
 * this is where all the data for parts & products resides
 * @author Joseph Curtis
 * @version 2021.11.17
 */

public class Inventory {
    private static ObservableList<Part> allParts
            = FXCollections.observableList(new ArrayList<Part>());
    private static ObservableList<Product> allProducts
            = FXCollections.observableList(new ArrayList<Product>());
    private static int partsIdCounter = 0;
    private static int productsIdCounter = 0;

    // Static Initializer to create test data
    static {
        addTestData();
    }

    // Helper function to implement adding test data
    private static void addTestData() {
        InHouse widget = new InHouse(getNewPartId(), "Widget", 7.77, 7, 1, 70, 195);
        InHouse gadget = new InHouse(getNewPartId(), "Gadget", 7.77, 7, 1, 70, 482);
        Outsourced gizmo = new Outsourced(getNewPartId(), "Gizmo", 0.25, 100, 10, 1000, "W.E. Coyote Industries");
        Product macguffin = new Product(getNewProductId(), "MacGuffin", 99.99, 1, 1, 100);
        macguffin.addAssociatedPart(gadget);
        macguffin.addAssociatedPart(gizmo);
        addPart(widget);
        addPart(gadget);
        addPart(gizmo);
        addProduct(macguffin);
    }

    /**
     * @param newPart the part to add to inventory
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * @param newProduct the product to add to inventory
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * @param partId the id of part to lookup
     * @return the part with associated id
     */
    public static Part lookupPart(int partId) {
        //TODO implement method
        return null;
    }

    /**
     * @param productId the id of product to lookup
     * @return the product with associated id
     */
    public static Product lookupProduct(int productId) {
        //TODO implement method
        return null;
    }

    /**
     * @param partName name of part to lookup
     * @return the part with associated name
     */
    public static ObservableList<Part> lookupPart(String partName) {
        //TODO implement method
        return null;
    }

    /**
     * @param productName name of product to lookup
     * @return the product with associated name
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        //TODO implement method
        return null;
    }

    /**
     * @param index
     * @param selectedPart
     */
    public static void updatePart(int index, Part selectedPart) {
        //TODO implement method
    }

    /**
     * @param index
     * @param newProduct
     */
    public static void updateProduct(int index, Product newProduct) {
        //TODO implement method
    }

    /**
     * @param selectedPart part to remove from inventory
     * @return true if delete was successful
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * @param selectedProduct product to remove from inventory
     * @return true if delete was successful
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * @return the list of all parts in inventory
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * @return the list of all products in inventory
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * increases partsIdCounter, ensuring no conflicting parts ID in inventory
     * @return a unique Parts ID
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
