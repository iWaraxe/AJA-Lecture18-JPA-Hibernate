package com.coherentsolutions.advanced.java.utils;

import com.coherentsolutions.advanced.java.entities.Address;
import com.coherentsolutions.advanced.java.entities.Customer;
import jakarta.persistence.EntityManager;

public class SampleDataInserter {

    public static void insertSampleCustomer(EntityManager em) {
        // Insert a sample customer with ID 1L (if needed)
        Address address = new Address("123 Main St", "Springfield");
        em.persist(address);

        Customer customer = new Customer("John Doe", address);
        em.persist(customer);
    }

    public static void insertSampleCustomers(EntityManager em) {
        Address address1 = new Address("123 Main St", "Springfield");
        em.persist(address1);

        Customer customer1 = new Customer("John Doe", address1);
        Customer customer2 = new Customer("John Smith", address1);
        em.persist(customer1);
        em.persist(customer2);
    }
}
