package com.coherentsolutions.advanced.java.section07;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.stat.Statistics;

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

        // Perform some operations
        em.find(com.coherentsolutions.advanced.java.entities.Customer.class, 1L);
        em.find(com.coherentsolutions.advanced.java.entities.Product.class, 1L);

        // Commit the transaction
        em.getTransaction().commit();

        // Display statistics
        System.out.println("Query execution count: " + stats.getQueryExecutionCount());
        System.out.println("Second-level cache hit count: " + stats.getSecondLevelCacheHitCount());

        // Close EntityManager
        em.close();
        emf.close();
    }
}
