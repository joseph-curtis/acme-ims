package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * the Inventory for the management system
 * this is where all the data for parts & products resides
 * @author Joseph Curtis
 * @version 2021.11.16
 */

public class Inventory {
    private static ObservableList<Part> allParts
            = FXCollections.observableList(new ArrayList<Part>());
    private static ObservableList<Product> allProducts
            = FXCollections.observableList(new ArrayList<Product>());

    /**
     * @param newPart the part to add to inventory
     */
    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * @param newProduct the product to add to inventory
     */
    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * @param partId the id of part to lookup
     * @return the part with associated id
     */
    public Part lookupPart(int partId) {
        //TODO implement method
        return null;
    }

    /**
     * @param productId the id of product to lookup
     * @return the product with associated id
     */
    public Product lookupProduct(int productId) {
        //TODO implement method
        return null;
    }

    /**
     * @param partName name of part to lookup
     * @return the part with associated name
     */
    public ObservableList<Part> lookupPart(String partName) {
        //TODO implement method
        return null;
    }

    /**
     * @param productName name of product to lookup
     * @return the product with associated name
     */
    public ObservableList<Product> lookupProduct(String productName) {
        //TODO implement method
        return null;
    }

    /**
     * @param index
     * @param selectedPart
     */
    public void updatePart(int index, Part selectedPart) {
        //TODO implement method
    }

    /**
     * @param index
     * @param newProduct
     */
    public void updateProduct(int index, Product newProduct) {
        //TODO implement method
    }

    /**
     * @param selectedPart part to remove from inventory
     * @return true if delete was successful
     */
    public boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * @param selectedProduct product to remove from inventory
     * @return true if delete was successful
     */
    public boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * @return the list of all parts in inventory
     */
    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * @return the list of all products in inventory
     */
    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
