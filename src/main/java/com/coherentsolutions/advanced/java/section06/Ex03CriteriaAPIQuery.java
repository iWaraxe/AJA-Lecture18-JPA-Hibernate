package com.coherentsolutions.advanced.java.section06;

import com.coherentsolutions.advanced.java.entities.Address;
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

        // Insert sample customers if the table is empty
        if (em.createQuery("SELECT c FROM Customer c").getResultList().isEmpty()) {
            insertSampleCustomers(em);
        }

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
        System.out.println("Customers:");
        for (Customer customer : customers) {
            System.out.println("Customer: " + customer.getName());
        }

        // Commit transaction and close
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    private static void insertSampleCustomers(EntityManager em) {
        // Create some sample addresses
        Address address1 = new Address("123 Main St", "Springfield");
        Address address2 = new Address("456 Oak Ave", "Shelbyville");
        Address address3 = new Address("789 Pine Rd", "Capital City");

        // Persist addresses
        em.persist(address1);
        em.persist(address2);
        em.persist(address3);

        // Create some sample customers with addresses
        Customer customer1 = new Customer("John Doe", address1);
        Customer customer2 = new Customer("Jane Doe", address2);
        Customer customer3 = new Customer("Alice Smith", address3);

        // Persist customers
        em.persist(customer1);
        em.persist(customer2);
        em.persist(customer3);

        System.out.println("Sample customers with addresses inserted.");
    }
}
