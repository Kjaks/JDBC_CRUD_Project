package com.karolis.jdbc_sql_kj.Controller;

import Models.productModel;

public class productController {
    productModel productModel = new productModel();
    public String getProductsInfo(){
        return productModel.getProducts();
    }
}
