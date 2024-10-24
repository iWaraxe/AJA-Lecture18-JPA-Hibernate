package com.coherentsolutions.advanced.java.section04;

import jakarta.persistence.*;
import com.coherentsolutions.advanced.java.section04.manytomany.Student;
import com.coherentsolutions.advanced.java.section04.manytomany.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates Many-to-Many relationship between Student and Course entities.
 * Shows the effect of CascadeType.ALL and FetchType settings.
 */
public class Ex01ManyToManyDemo {

    public static void main(String[] args) {
        // Create EntityManagerFactory and EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Create courses
        Course course1 = new Course("Mathematics");
        Course course2 = new Course("Physics");
        Course course3 = new Course("Chemistry");

        // Persist courses
        em.persist(course1);
        em.persist(course2);
        em.persist(course3);

        // Create students
        Student student1 = new Student("Alice");
        Student student2 = new Student("Bob");

        // Assign courses to students
        List<Course> coursesForAlice = new ArrayList<>();
        coursesForAlice.add(course1);
        coursesForAlice.add(course2);
        student1.setCourses(coursesForAlice);

        List<Course> coursesForBob = new ArrayList<>();
        coursesForBob.add(course2);
        coursesForBob.add(course3);
        student2.setCourses(coursesForBob);

        // Persist students
        em.persist(student1);
        em.persist(student2);

        // Commit transaction
        em.getTransaction().commit();

        // Close the EntityManager
        em.close();
    }
}
