package com.coherentsolutions.advanced.java.section08;

import com.coherentsolutions.advanced.java.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Ex01FirstLevelCache {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Fetch a Customer entity for the first time
        Customer customer1 = em.find(Customer.class, 1L);
        System.out.println("Customer fetched from database: " + customer1);

        // Fetch the same Customer entity again (from the first-level cache)
        Customer customer2 = em.find(Customer.class, 1L);
        System.out.println("Customer fetched from cache: " + customer2);

        // Commit and close
        em.getTransaction().commit();
        em.close();

        emf.close();
    }
}
