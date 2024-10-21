package com.coherentsolutions.advanced.java.section05.onetomany;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Customer entity has a bidirectional One-to-Many relationship with Orders.
 */
@Entity
@Table(name = "bicustomers")
public class BiCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "bicustomer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BiOrder> biorders = new ArrayList<>();

    public BiCustomer() {}

    public BiCustomer(String name) {
        this.name = name;
    }

    // Add convenience method for bidirectional relationship
    public void addOrder(BiOrder biorder) {
        biorders.add(biorder);
        biorder.setCustomer(this);  // Ensure bidirectional relationship is maintained
    }

    // Remove convenience method for bidirectional relationship
    public void removeOrder(BiOrder biorder) {
        biorders.remove(biorder);
        biorder.setCustomer(null);
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BiOrder> getOrders() {
        return biorders;
    }

    public void setOrders(List<BiOrder> biorders) {
        this.biorders = biorders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orders=" + biorders +
                '}';
    }
}
