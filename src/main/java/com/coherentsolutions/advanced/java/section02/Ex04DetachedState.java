package com.coherentsolutions.advanced.java.section02;

import com.coherentsolutions.advanced.java.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Ex04DetachedState {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Creating and persisting a product
        Product product = new Product("Tablet", 300.00);
        em.persist(product);

        // Committing the transaction (moves product to Persistent state)
        em.getTransaction().commit();

        // Now, detach the product
        em.detach(product);
        product.setName("Tablet");

        // Now the product is in the Detached state
        System.out.println("Product is in Detached state: " + product);

        // Closing the EntityManager
        em.close();
        emf.close();
    }
}
