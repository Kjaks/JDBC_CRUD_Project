package com.karolis.jdbc_sql_kj.TableInfo;

public class Buy {
    private final String clientBuy, productBuy, buyDate;
    private final int id;

    public Buy(int id,String clientBuy, String productBuy, String buyDate) {
        this.id = id;
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

    public int getId() {
        return id;
    }
}
