package com.coherentsolutions.advanced.java.section08;

import com.coherentsolutions.advanced.java.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Ex02SecondLevelCache {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Fetch a Customer entity (First time: from database)
        Customer customer = em.find(Customer.class, 1L);
        System.out.println("Customer fetched from database: " + customer);

        // Commit and close the first session
        em.getTransaction().commit();
        em.close();

        // Open a new session
        em = emf.createEntityManager();
        em.getTransaction().begin();

        // Fetch the Customer again (from second-level cache)
        Customer cachedCustomer = em.find(Customer.class, 1L);
        System.out.println("Customer fetched from second-level cache: " + cachedCustomer);

        // Commit and close
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
