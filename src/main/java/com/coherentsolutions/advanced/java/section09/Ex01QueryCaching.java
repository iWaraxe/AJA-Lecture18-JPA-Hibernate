package com.coherentsolutions.advanced.java.section09;

import com.coherentsolutions.advanced.java.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Ex01QueryCaching {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // First time: Query results are fetched from the database
        List<Customer> customers = em.createQuery("SELECT c FROM Customer c WHERE c.name LIKE 'John%'", Customer.class)
                .setHint("org.hibernate.cacheable", true)
                .getResultList();

        System.out.println("Customers fetched from the database: " + customers.size());

        // Commit transaction
        em.getTransaction().commit();

        // Close EntityManager
        em.close();

        // Start a new session to demonstrate query caching
        em = emf.createEntityManager();
        em.getTransaction().begin();

        // Second time: Query results are fetched from the cache
        List<Customer> cachedCustomers = em.createQuery("SELECT c FROM Customer c WHERE c.name LIKE 'John%'", Customer.class)
                .setHint("org.hibernate.cacheable", true)
                .getResultList();

        System.out.println("Customers fetched from cache: " + cachedCustomers.size());

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
