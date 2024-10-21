package com.coherentsolutions.advanced.java.section05.manytomany;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Course entity demonstrates a bidirectional Many-to-Many relationship with Student.
 */
@Entity
@Table(name = "bicourses")
public class BiCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToMany(mappedBy = "biCourses")
    private List<BiStudent> biStudents = new ArrayList<>();

    public BiCourse() {}

    public BiCourse(String title) {
        this.title = title;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BiStudent> getStudents() {
        return biStudents;
    }

    public void setStudents(List<BiStudent> biStudents) {
        this.biStudents = biStudents;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", students=" + biStudents +
                '}';
    }
}
