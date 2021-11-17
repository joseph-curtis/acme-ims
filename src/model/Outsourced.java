package model;

/**
 * An instantiated class of type Part that is sourced from another company
 * @author Joseph Curtis
 * @version 2021.11.16
 */

public class Outsourced extends Part {
    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * @return the outsourced company's name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the machine ID to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
