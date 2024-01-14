package com.apress.spring6recipes.shop;

import java.util.Map;

public class ProductCreator {

    private final Map<String, Product> products;

    public ProductCreator(Map<String, Product> products) {
        this.products = products;
    }

    public Product createProduct(String productId) {
        Product product = products.get(productId);
        if (product != null) {
            return product;
        }

        var msg = "Unknown product " + productId + " ";
        throw new IllegalArgumentException(msg);
    }

//    public static Product createProduct(String productId) {
//        return switch (productId) {
//            case "aaa" -> new Battery("AAA", 2.5, true);
//            case "cdrw" -> new Disc("CD-RW", 1.5, 700);
//            case "dvdrw" -> new Disc("DVD-RW", 3.0, 4700);
//
//            default -> {
//                var msg = "Unknow product " + productId + " ";
//                throw new IllegalArgumentException(msg);
//            }
//        };
//    }
}
