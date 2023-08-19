package com.example.map524assignment2;

import java.util.ArrayList;

public class ItemsManager {
    ArrayList<Items> products = new ArrayList<>(0);

    public void addProducts(Items product) {
        products.add(product);
    }
}
