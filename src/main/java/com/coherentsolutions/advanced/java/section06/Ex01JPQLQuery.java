package com.coherentsolutions.advanced.java.section06;

import com.coherentsolutions.advanced.java.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

/**
 * Demonstrates how to use JPQL to retrieve data.
 */
public class Ex01JPQLQuery {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // JPQL Query to fetch all customers
        String jpql = "SELECT c FROM Customer c";
        Query query = em.createQuery(jpql);
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
