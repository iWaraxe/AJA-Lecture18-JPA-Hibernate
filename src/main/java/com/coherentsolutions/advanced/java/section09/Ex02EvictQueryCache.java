package com.coherentsolutions.advanced.java.section09;

import jakarta.persistence.Cache;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Ex02EvictQueryCache {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        Cache cache = emf.getCache();

        // Evict the entire query cache
        cache.evictAll();
        System.out.println("Query cache evicted.");

        // You can also evict specific regions if needed
        // cache.evict(Customer.class); // Evicts all cached entities of type Customer

        emf.close();
    }
}
