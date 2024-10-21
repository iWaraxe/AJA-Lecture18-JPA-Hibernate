package com.coherentsolutions.advanced.java.section02;

import com.coherentsolutions.advanced.java.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Ex05RemovedState {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Fetch an existing product from the database (assume product with id 1 exists)
        Product product = em.find(Product.class, 1L);

        if (product != null) {
            // Removing the product (moves it to Removed state)
            em.remove(product);
            System.out.println("Product is in Removed state and scheduled for deletion: " + product);
        } else {
            System.out.println("Product not found.");
        }

        // Commit the transaction to delete the product from the database
        em.getTransaction().commit();

        // Closing the EntityManager
        em.close();
        emf.close();
    }
}
