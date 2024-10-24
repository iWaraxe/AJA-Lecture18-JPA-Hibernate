package com.coherentsolutions.advanced.java.section08;

import com.coherentsolutions.advanced.java.entities.Customer;
import com.coherentsolutions.advanced.java.utils.SampleDataInserter;
import jakarta.persistence.Cache;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Ex03CacheEviction {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        Cache cache = emf.getCache();

        // Insert sample data if necessary
        em.getTransaction().begin();
        SampleDataInserter.insertSampleCustomer(em);
        em.getTransaction().commit();

        // Begin transaction
        em.getTransaction().begin();

        // Fetch a Customer entity
        Customer customer = em.find(Customer.class, 1L);
        System.out.println("Customer fetched: " + customer);

        // Evict Customer from cache
        cache.evict(Customer.class, 1L);
        System.out.println("Customer evicted from cache.");

        // Fetch the Customer again (now from the database since it was evicted)
        Customer refreshedCustomer = em.find(Customer.class, 1L);
        System.out.println("Customer fetched after eviction: " + refreshedCustomer);

        // Commit and close
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
