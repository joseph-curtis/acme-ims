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
