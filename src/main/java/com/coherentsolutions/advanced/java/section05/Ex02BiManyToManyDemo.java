package com.coherentsolutions.advanced.java.section05;

import com.coherentsolutions.advanced.java.section05.manytomany.BiCourse;
import com.coherentsolutions.advanced.java.section05.manytomany.BiStudent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Ex02BiManyToManyDemo {

    public static void main(String[] args) {
        // Create EntityManagerFactory and EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Create courses (they should be persisted before assigning to students)
        BiCourse course1 = new BiCourse("Mathematics");
        BiCourse course2 = new BiCourse("Physics");

        // Persist courses first
        em.persist(course1);
        em.persist(course2);

        // Create students
        BiStudent student1 = new BiStudent("Alice");
        BiStudent student2 = new BiStudent("Bob");
        BiStudent student3 = new BiStudent("Charlie");

        // Add students to courses
        student1.addCourse(course1); // Alice takes Mathematics
        student1.addCourse(course2); // Alice takes Physics

        student2.addCourse(course1); // Bob takes Mathematics

        student3.addCourse(course2); // Charlie takes Physics

        // Persist all students (now they can reference the already persisted courses)
        em.persist(student1);
        em.persist(student2);
        em.persist(student3);

        // Commit the transaction
        em.getTransaction().commit();

        // Begin a new transaction to demonstrate modification
        em.getTransaction().begin();

        // Fetch a student to modify their courses
        BiStudent fetchedStudent = em.find(BiStudent.class, student1.getId());
        System.out.println("\nFetched Student (Before modification): " + fetchedStudent);

        // Remove student from a course (e.g., Alice drops Physics)
        fetchedStudent.removeCourse(course2);

        // Merge changes into the persistence context
        em.merge(fetchedStudent);

        // Commit the transaction
        em.getTransaction().commit();

        // Fetch the updated student and print to show the updated courses
        System.out.println("\nFetched Student (After removing Physics): " + fetchedStudent);

        // Close EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}
