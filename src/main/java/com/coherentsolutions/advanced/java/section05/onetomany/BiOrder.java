package com.coherentsolutions.advanced.java.section05.onetomany;

import jakarta.persistence.*;

/**
 * The Order entity has a bidirectional Many-to-One relationship with Customer.
 */
@Entity
@Table(name = "biorders")
public class BiOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private double price;

    @ManyToOne
    @JoinColumn(name = "bicustomer_id")
    private BiCustomer bicustomer;

    public BiOrder() {}

    public BiOrder(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BiCustomer getCustomer() {
        return bicustomer;
    }

    public void setCustomer(BiCustomer bicustomer) {
        this.bicustomer = bicustomer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
