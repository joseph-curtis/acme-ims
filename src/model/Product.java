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

    /**
     * Constructor for creating a product with every field initialized.
     * associatedParts is initialized, but left empty.
     * Use addAssociatedPart method to populate the ObservableList :
     * @see Product#addAssociatedPart(Part)
     * @param id product id
     * @param name name of product
     * @param price price of product
     * @param stock current inventory stock of product
     * @param min minimum inventory stock allowed of product
     * @param max maximum inventory stock allowed of product
     */
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
     * Change the product ID.
     * <p><strong>WARNING:</strong><br>
     * It is <em>HIGHLY</em> recommended to ensure you are using an ID that is <em><u>unique</u></em> to the inventory!</p>
     * @see Inventory#getNewProductId()
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Change the product name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Change the product price.
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Change the amount of stock in inventory.
     * @param stock the current inventory stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Change the min product Inventory can hold.
     * @param min the minimum stock to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Change the max product Inventory can hold.
     * @param max the maximum stock to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Get product ID.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Get product name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get product price.
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get product stock.
     * @return the current stock in inventory
     */
    public int getStock() {
        return stock;
    }

    /**
     * Get the min product Inventory can hold.
     * @return the minimum stock that can be set
     */
    public int getMin() {
        return min;
    }

    /**
     * Get the max product Inventory can hold.
     * @return the maximum stock that can be set
     */
    public int getMax() {
        return max;
    }

    /**
     * Adds a part that is associated with this product.
     * @param part the associated part to add
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Removes the selected associated part from the list.
     * @param selectedAssociatedPart part to remove
     * @return true if delete was successful
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * Gets the list of parts associated with this product.
     * @return associated Parts list
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
