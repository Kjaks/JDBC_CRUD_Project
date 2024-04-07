package com.karolis.jdbc_sql_kj.TableInfo;

/**
 * The Buy class represents a purchase entity in the Celia Shop application.
 * This class stores information about a purchase, including its ID, client, product, and purchase date.
 * @author Karolis Jakas Stirbyte
 */
public class Buy {
    private final String clientBuy;
    private final String productBuy;
    private final String buyDate;
    private final int id;

    /**
     * Constructs a new Buy object with the specified attributes.
     *
     * @param id The ID of the purchase.
     * @param clientBuy The client who made the purchase.
     * @param productBuy The product that was purchased.
     * @param buyDate The date of the purchase.
     */
    public Buy(int id, String clientBuy, String productBuy, String buyDate) {
        this.id = id;
        this.clientBuy = clientBuy;
        this.productBuy = productBuy;
        this.buyDate = buyDate;
    }

    /**
     * Retrieves the client who made the purchase.
     *
     * @return The client who made the purchase.
     */
    public String getClientBuy() {
        return clientBuy;
    }

    /**
     * Retrieves the product that was purchased.
     *
     * @return The product that was purchased.
     */
    public String getProductBuy() {
        return productBuy;
    }

    /**
     * Retrieves the date of the purchase.
     *
     * @return The date of the purchase.
     */
    public String getBuyDate() {
        return buyDate;
    }

    /**
     * Retrieves the ID of the purchase.
     *
     * @return The ID of the purchase.
     */
    public int getId() {
        return id;
    }
}
