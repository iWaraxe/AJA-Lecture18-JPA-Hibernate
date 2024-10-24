package com.coherentsolutions.advanced.java.section05.manytomany;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Student entity demonstrates a bidirectional Many-to-Many relationship with Course.
 */
@Entity
@Table(name = "bistudents")
public class BiStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "bistudent_bicourses",
            joinColumns = @JoinColumn(name = "bistudent_id"),
            inverseJoinColumns = @JoinColumn(name = "bicourse_id")
    )
    private List<BiCourse> biCourses = new ArrayList<>();

    public BiStudent() {}

    public BiStudent(String name) {
        this.name = name;
    }

    // Add course
    public void addCourse(BiCourse biCourse) {
        biCourses.add(biCourse);
        biCourse.getStudents().add(this);  // Maintain the bidirectional relationship
    }

    // Remove course
    public void removeCourse(BiCourse biCourse) {
        biCourses.remove(biCourse);
        biCourse.getStudents().remove(this);  // Maintain the bidirectional relationship
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BiCourse> getCourses() {
        return biCourses;
    }

    public void setCourses(List<BiCourse> biCourses) {
        this.biCourses = biCourses;
    }

    @Override
    public String toString() {
        // Avoid printing the full list of courses to prevent recursive calls
        return "BiStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseCount=" + biCourses.size() +  // Print the number of courses instead of the full list
                '}';
    }
}
