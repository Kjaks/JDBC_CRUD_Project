package com.karolis.jdbc_sql_kj.TableInfo;

/**
 * The Product class represents a product entity in the Celia Shop application.
 * This class stores information about a product, including its ID, name, description, and price.
 * @author Karolis Jakas Stirbyte
 */
public class Product {
    private final String productName,productDescription;
    private final int productID;
    private final double productPVP;

    /**
     * Constructs a new Product object with the specified attributes.
     *
     * @param productID The ID of the product.
     * @param productName The name of the product.
     * @param productDescription The description of the product.
     * @param productPVP The price of the product.
     */
    public Product(int productID, String productName, String productDescription, Double productPVP) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPVP = productPVP;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The name of the product.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Retrieves the description of the product.
     *
     * @return The description of the product.
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Retrieves the ID of the product.
     *
     * @return The ID of the product.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return The price of the product.
     */
    public double getProductPVP() {
        return productPVP;
    }
}

