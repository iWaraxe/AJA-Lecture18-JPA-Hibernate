package com.coherentsolutions.advanced.java.section07;

import com.coherentsolutions.advanced.java.entities.Customer;
import com.coherentsolutions.advanced.java.entities.Product;
import com.coherentsolutions.advanced.java.utils.CustomerDataGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.stat.Statistics;

import java.util.List;

/**
 * Demonstrates how to use Hibernate statistics to analyze performance.
 */
public class Ex01HibernateStatistics {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Obtain Hibernate statistics
        Statistics stats = emf.unwrap(org.hibernate.SessionFactory.class).getStatistics();
        stats.setStatisticsEnabled(true);

        // Begin transaction
        em.getTransaction().begin();

        // Insert sample data if the tables are empty
        if (em.createQuery("SELECT c FROM Customer c").getResultList().isEmpty()) {
            CustomerDataGenerator.insertSampleCustomersWithOrders(em,100,10);  // Inserts 100 sample customers with addresses
        }

        // Clear first-level cache to force Hibernate to execute queries again
        em.clear();

        // Fetch multiple customers
        List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class)
                .setMaxResults(10)
                .getResultList();
        for (Customer customer : customers) {
            System.out.println("Customer: " + customer.getName());
        }

        // Fetch multiple products (ensure products are inserted in a similar way)
        List<Product> products = em.createQuery("SELECT p FROM Product p", Product.class)
                .setMaxResults(10)
                .getResultList();
        for (Product product : products) {
            System.out.println("Product: " + product.getName());
        }

        // Commit the transaction
        em.getTransaction().commit();

        // Display Hibernate statistics
        System.out.println("Query execution count: " + stats.getQueryExecutionCount());
        System.out.println("Second-level cache hit count: " + stats.getSecondLevelCacheHitCount());

        // Close EntityManager
        em.close();
        emf.close();
    }
}
