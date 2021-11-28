package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * A finished Product with associated parts
 * @author Joseph Curtis
 * @version 2021.11.28
 */

public class Product {
    private final ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        associatedParts = FXCollections.observableArrayList();
//        another way to initialize an observable Array:
//        associatedParts = FXCollections.observableList(new ArrayList<Part>());
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param stock the current inventory stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @param min the minimum stock to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @param max the maximum stock to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the current stock in inventory
     */
    public int getStock() {
        return stock;
    }

    /**
     * @return the minimum stock that must be set
     */
    public int getMin() {
        return min;
    }

    /**
     * @return the maximum stock that can be set
     */
    public int getMax() {
        return max;
    }

    /**
     * @param part the associated part to add
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * @param selectedAssociatedPart part to remove
     * @return true if delete was successful
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * @return the list of parts associated with product
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
