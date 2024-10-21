package com.coherentsolutions.advanced.java.section02;

import com.coherentsolutions.advanced.java.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Ex03PersistentState {

    public static void main(String[] args) {
        // Creating EntityManager and EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Creating a new Product instance (Transient state)
        Product product = new Product("Smartphone", 800.00);

        // Persist the product to move it to the Persistent state
        em.persist(product);

        // Now the product is in the Persistent state
        System.out.println("Product is in Persistent state: " + product);

        // Commit the transaction (which synchronizes the product with the database)
        em.getTransaction().commit();

        // Close the EntityManager
        em.close();
        emf.close();
    }
}
