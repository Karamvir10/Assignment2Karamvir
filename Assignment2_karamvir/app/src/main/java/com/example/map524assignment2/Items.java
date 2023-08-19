package com.example.map524assignment2;

public class Items {
    String name;
    int quant;
    double price;
    Items(){

        name = "";

        quant = 1;

        price = 0.0;
    }
    Items(String title, int qty, double p){

        name = title;

        quant = qty;

        price = p;
    }
}
