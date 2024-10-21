package com.coherentsolutions.advanced.java.section01;

import com.coherentsolutions.advanced.java.entities.Customer;
import com.coherentsolutions.advanced.java.entities.Product;
import com.coherentsolutions.advanced.java.entities.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * This class demonstrates how to interact with JPA using an EntityManager.
 * We will persist a Product, an Address, and a Customer entity into the database.
 */
public class Main {

    public static void main(String[] args) {
        // Creating an EntityManagerFactory using the "my-persistence-unit" from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Creating and persisting a product
        Product product = new Product("Laptop", 1500.0);
        em.persist(product);

        // Creating and persisting an address
        Address address = new Address("123 Main St", "Springfield");
        em.persist(address);

        // Creating and persisting a customer with the address
        Customer customer = new Customer("John Doe", address);
        em.persist(customer);

        // Commit the transaction
        em.getTransaction().commit();

        // Close the EntityManager
        em.close();
        emf.close();
    }
}
