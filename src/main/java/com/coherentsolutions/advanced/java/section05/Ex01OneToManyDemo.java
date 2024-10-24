package com.coherentsolutions.advanced.java.section05;

import com.coherentsolutions.advanced.java.section05.onetomany.BiCustomer;
import com.coherentsolutions.advanced.java.section05.onetomany.BiOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Ex01OneToManyDemo {

    public static void main(String[] args) {
        // Create EntityManagerFactory and EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Create new customer
        BiCustomer customer = new BiCustomer("John Doe");

        // Create orders for the customer
        BiOrder order1 = new BiOrder("Laptop", 1200.0);
        BiOrder order2 = new BiOrder("Smartphone", 800.0);

        // Add orders to the customer (maintaining bidirectional relationship)
        customer.addOrder(order1);
        customer.addOrder(order2);

        // Persist the customer (which will cascade and persist the orders due to CascadeType.ALL)
        em.persist(customer);

        // Commit the transaction
        em.getTransaction().commit();

        // Fetch the customer and orders back from the database to verify the relationship
        em.getTransaction().begin();
        BiCustomer fetchedCustomer = em.find(BiCustomer.class, customer.getId());

        System.out.println("Customer details: " + fetchedCustomer);
        for (BiOrder order : fetchedCustomer.getOrders()) {
            System.out.println("Order details: " + order);
        }

        // Demonstrate removing an order and maintaining consistency in the relationship
        System.out.println("\nRemoving an order...");
        fetchedCustomer.removeOrder(order1);
        em.merge(fetchedCustomer);

        // Commit transaction again after removal
        em.getTransaction().commit();

        // Fetch the customer and orders again after removal
        System.out.println("Customer details after removal: " + fetchedCustomer);
        for (BiOrder order : fetchedCustomer.getOrders()) {
            System.out.println("Remaining Order details: " + order);
        }

        // Close EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}
