package com.coherentsolutions.advanced.java.section06;

import com.coherentsolutions.advanced.java.entities.Customer;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

import java.util.List;

/**
 * Demonstrates how to create dynamic queries using Criteria API.
 */
public class Ex03CriteriaAPIQuery {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Create CriteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);

        // Define root (Customer entity)
        Root<Customer> customerRoot = query.from(Customer.class);

        // Create a dynamic condition (e.g., fetch customers where name is 'John Doe')
        Predicate condition = cb.equal(customerRoot.get("name"), "John Doe");

        // Build the query
        query.select(customerRoot).where(condition);

        // Execute the query
        List<Customer> customers = em.createQuery(query).getResultList();

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
