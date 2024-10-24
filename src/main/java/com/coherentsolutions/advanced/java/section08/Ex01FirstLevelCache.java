package com.coherentsolutions.advanced.java.section08;

import com.coherentsolutions.advanced.java.entities.Address;
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

        // Check if customer with id = 1 exists
        Customer customer = em.find(Customer.class, 1L);
        if (customer == null) {
            // Insert sample customer if not found
            Address address = new Address("123 Main St", "Springfield");
            em.persist(address);  // Save the address first

            Customer newCustomer = new Customer("John Doe", address);
            em.persist(newCustomer);

            em.getTransaction().commit();
            System.out.println("Sample customer inserted with id: " + newCustomer.getId());
        } else {
            em.getTransaction().commit();
        }

        // Fetch the Customer entity for the first time (it will hit the database)
        em.getTransaction().begin();
        Customer customer1 = em.find(Customer.class, 1L);
        System.out.println("Customer fetched from database: " + customer1);

        // Fetch the same Customer entity again (this time it will come from the first-level cache)
        Customer customer2 = em.find(Customer.class, 1L);
        System.out.println("Customer fetched from cache: " + customer2);

        // Commit and close
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
