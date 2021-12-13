/**
 * A single class file where the JVM begins program execution.
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
 */
package main;
