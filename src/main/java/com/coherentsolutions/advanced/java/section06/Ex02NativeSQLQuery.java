package com.coherentsolutions.advanced.java.section06;

import com.coherentsolutions.advanced.java.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

/**
 * Demonstrates how to use Native SQL to retrieve data.
 */
public class Ex02NativeSQLQuery {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Native SQL Query to fetch all customers
        String nativeSql = "SELECT * FROM customers";
        Query query = em.createNativeQuery(nativeSql, Customer.class);
        List<Customer> customers = query.getResultList();

        // Output result
        for (Customer customer : customers) {
            System.out.println("Customer: " + customer.getName());
        }

        // Commit transaction and close
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
