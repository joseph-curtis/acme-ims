package model;

/**
 * An instantiated class of type Part that is sourced from another company.
 * @author Joseph Curtis
 * @version 2021.11.16
 */

public class Outsourced extends Part {
    private String companyName;

    /**
     * Constructor for outsourced part with every field initialized.
     * @param id part id
     * @param name name of part
     * @param price price/cost of part
     * @param stock current inventory stock of part
     * @param min minimum required inventory stock of part
     * @param max maximum required inventory stock of part
     * @param companyName Outsourced company's name
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Get the outsourced company's name.
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Changes the outsourced company's name.
     * @param companyName the company name to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
