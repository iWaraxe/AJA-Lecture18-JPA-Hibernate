package com.coherentsolutions.advanced.java.section07;

import com.coherentsolutions.advanced.java.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

/**
 * Solves the N+1 problem using JOIN FETCH.
 */
public class Ex03JoinFetchSolution {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Fetch customers with their orders using JOIN FETCH
        List<Customer> customers = em.createQuery(
                        "SELECT c FROM Customer c JOIN FETCH c.orders", Customer.class)
                .getResultList();

        // Now the orders are fetched eagerly
        for (Customer customer : customers) {
            System.out.println("Customer: " + customer.getName());
            customer.getOrders().forEach(order -> System.out.println("Order: " + order.getOrderDate()));
        }

        // Commit transaction
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}