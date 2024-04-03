package com.karolis.jdbc_sql_kj.TableInfo;

public class Product {
    private final String productName,productDescription;
    private final int productID;
    private final double productPVP;

    public Product(int productID, String productName, String productDescription, Double productPVP) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPVP = productPVP;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getProductID() {
        return productID;
    }

    public double getProductPVP() {
        return productPVP;
    }
}

