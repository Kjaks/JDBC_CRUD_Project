package com.karolis.jdbc_sql_kj.TableInfo;

public class Buy {
    private final String clientBuy, productBuy, buyDate;

    public Buy(String clientBuy, String productBuy, String buyDate) {
        this.clientBuy = clientBuy;
        this.productBuy = productBuy;
        this.buyDate = buyDate;
    }

    public String getClientBuy() {
        return clientBuy;
    }

    public String getProductBuy() {
        return productBuy;
    }

    public String getBuyDate() {
        return buyDate;
    }
}
