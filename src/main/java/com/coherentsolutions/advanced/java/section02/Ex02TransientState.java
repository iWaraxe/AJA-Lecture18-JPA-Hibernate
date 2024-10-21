package com.coherentsolutions.advanced.java.section02;

import com.coherentsolutions.advanced.java.entities.Product;

public class Ex02TransientState {

    public static void main(String[] args) {
        // Creating a new Product instance (Transient state)
        Product product = new Product("Laptop", 1500.00);

        // At this point, the product is in the Transient state
        System.out.println("Product is in Transient state: " + product);
    }
}
