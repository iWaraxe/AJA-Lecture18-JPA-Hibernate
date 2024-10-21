package com.coherentsolutions.advanced.java.section03;

import com.coherentsolutions.advanced.java.entities.Product;
import com.coherentsolutions.advanced.java.entities.Customer;
import com.coherentsolutions.advanced.java.entities.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Ex01HibernateConfig {

    public static void main(String[] args) {
        // Load EntityManagerFactory from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Persist a product
        Product product = new Product("Laptop", 1200.0);
        em.persist(product);

        // Persist an address for the customer
        Address address = new Address("456 Maple St", "Shelbyville");
        em.persist(address);

        // Persist a customer with the address
        Customer customer = new Customer("Alice Smith", address);
        em.persist(customer);

        // Commit transaction
        em.getTransaction().commit();

        // Closing resources
        em.close();
        emf.close();

        System.out.println("Entities persisted successfully.");
    }
}
