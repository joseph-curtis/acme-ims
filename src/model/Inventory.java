package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * The Inventory class for the data layer. Note that there is only one
 * inventory, so all properties and methods are static.
 * This is where static data stored for parts & products resides
 * @author Joseph Curtis
 * @version 2021.11.22
 */

public class Inventory {
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int partsIdCounter = 0;
    private static int productsIdCounter = 0;

    /**
     * Static Initializer to create test data.
     */
    static {
        addTestData();
    }

    // Helper function to implement adding test data
    private static void addTestData() {
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

//        Outsourced edited = new Outsourced(widget.getId(), "EDITED", 9000, 9000, 9000, 9000, "THIS WAS CHANGED");
//        updatePart(edited.getId(), edited);
//
//        deletePart(widget);
//
//        ObservableList<Product> copiedList = lookupProduct("mAc");
//        for (Product product : copiedList) {
//            addProduct(product);
//        }
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
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;    // part not found
    }

    /**
     * @param productId the id of product to lookup
     * @return the product with associated id
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
     * @param partName name of part to lookup
     * @return a list of parts whose Name contains partName parameter
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
     * @param productName name of product to lookup
     * @return a list of products whose Name contains productsName parameter
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
     * @param index the ID of the Part to edit
     * @param selectedPart Part object to replace existing Part with same ID
     */
    public static void updatePart(int index, Part selectedPart) {
        int i = 0;  // index for allParts array list

        for (Part part : allParts) {
            if (part.getId() == index) {
                allParts.set(i, selectedPart);
                return;
            }
            i++;    // go to next record
        }
        //TODO update all associated parts as well?
    }

    /**
     * @param index the observableArrayList index in allProducts to target
     * @param newProduct Product to replace existing object in allProducts
     */
    public static void updateProduct(int index, Product newProduct) {
        if (index >= 0)
            allProducts.set(index, newProduct);

        /*  // ForEach method does not need index parameter
        int i = 0;  // index for allProducts array list
        int partID = newProduct.getId();

        for (Product product : allProducts) {
            if (product.getId() == partID) {
                allProducts.set(i, newProduct);
                return;
            }
            i++;    // go to next record
        }
         */
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
