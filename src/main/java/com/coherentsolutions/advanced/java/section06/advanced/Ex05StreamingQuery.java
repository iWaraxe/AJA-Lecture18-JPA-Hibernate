package com.coherentsolutions.advanced.java.section06.advanced;

import com.coherentsolutions.advanced.java.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.stream.Stream;

/**
 * Demonstrates how to stream large result sets.
 */
public class Ex05StreamingQuery {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Stream customers
        Stream<Customer> customerStream = em.createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultStream();

        // Process each customer in the stream
        customerStream.forEach(customer -> System.out.println("Customer: " + customer.getName()));

        // Commit transaction and close
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
