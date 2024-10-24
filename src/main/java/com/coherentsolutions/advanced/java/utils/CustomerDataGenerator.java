package com.coherentsolutions.advanced.java.utils;

import com.github.javafaker.Faker;
import com.coherentsolutions.advanced.java.entities.Address;
import com.coherentsolutions.advanced.java.entities.Customer;
import com.coherentsolutions.advanced.java.entities.Order;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class CustomerDataGenerator {

    private static final Faker faker = new Faker();

    // Method to generate customers with orders
    public static List<Customer> generateCustomersWithOrders(EntityManager em, int customerCount, int ordersPerCustomer) {
        List<Customer> customers = new ArrayList<>();

        // Generate customers with random names, addresses, and orders
        for (int i = 0; i < customerCount; i++) {
            // Generate random address
            Address address = new Address(faker.address().streetAddress(), faker.address().city());

            // Persist address before assigning it to customer
            em.persist(address);

            // Generate random customer
            Customer customer = new Customer(faker.name().fullName(), address);

            // Generate random orders for each customer
            List<Order> orders = new ArrayList<>();
            for (int j = 0; j < ordersPerCustomer; j++) {
                Order order = new Order(
                        faker.commerce().productName(),
                        faker.number().randomDouble(2, 10, 100),  // Random price
                        customer
                );
                order.setOrderDate(faker.date().past(30, java.util.concurrent.TimeUnit.DAYS));  // Random order date
                orders.add(order);
            }
            customer.setOrders(orders);

            customers.add(customer);
        }

        return customers;
    }

    // Method to insert customers with orders into the database
    public static void insertSampleCustomersWithOrders(EntityManager em, int customerCount, int ordersPerCustomer) {
        List<Customer> customers = generateCustomersWithOrders(em, customerCount, ordersPerCustomer);

        // Persist customers and orders
        for (Customer customer : customers) {
            em.persist(customer);  // This will cascade and persist the orders as well
        }

        System.out.println(customerCount + " sample customers with " + ordersPerCustomer + " orders each inserted.");
    }
}
