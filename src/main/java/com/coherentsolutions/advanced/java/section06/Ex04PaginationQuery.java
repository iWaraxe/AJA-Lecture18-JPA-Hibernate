package com.coherentsolutions.advanced.java.section06;

import com.coherentsolutions.advanced.java.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

/**
 * Demonstrates how to handle large result sets using pagination.
 */
public class Ex04PaginationQuery {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // JPQL Query to fetch all customers
        String jpql = "SELECT c FROM Customer c";
        Query query = em.createQuery(jpql);

        // Set pagination (start at first result, fetch 10 at a time)
        int pageSize = 10;
        query.setFirstResult(0);  // Starting index
        query.setMaxResults(pageSize);  // Number of results to fetch

        // Fetch and print paginated results
        List<Customer> customers = query.getResultList();
        while (!customers.isEmpty()) {
            for (Customer customer : customers) {
                System.out.println("Customer: " + customer.getName());
            }

            // Move to next page
            query.setFirstResult(query.getFirstResult() + pageSize);
            customers = query.getResultList();
        }

        // Commit transaction and close
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
