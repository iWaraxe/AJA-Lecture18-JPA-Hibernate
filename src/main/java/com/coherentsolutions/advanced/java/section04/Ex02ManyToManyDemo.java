package com.coherentsolutions.advanced.java.section04;

import com.coherentsolutions.advanced.java.section04.manytomany.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

/**
 * Demonstrates Many-to-Many relationship between Student and Course entities.
 * Shows the effect of CascadeType.ALL and FetchType settings.
 */
public class Ex02ManyToManyDemo {

    public static void main(String[] args) {
        // Create EntityManagerFactory and EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Re-open EntityManager for deletion demonstration
        em.getTransaction().begin();

        try {
            // Fetch the student to remove by name
            Student studentToRemove = em.createQuery(
                            "SELECT s FROM Student s WHERE s.name = :name", Student.class)
                    .setParameter("name", "Alice")
                    .getSingleResult();

            System.out.println("Deleting student: " + studentToRemove.getName());

            // Remove the student (CascadeType.ALL should remove relationships)
            em.remove(studentToRemove);

            // Commit transaction
            em.getTransaction().commit();

            System.out.println("Deletion complete. Check the database to see the effects of CascadeType.ALL.");

        } catch (NoResultException e) {
            System.out.println("No student found with the name 'Alice'.");
            em.getTransaction().rollback();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            // Close EntityManager and EntityManagerFactory
            em.close();
            emf.close();
        }
    }
}
