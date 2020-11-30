package com.audally.backend.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses",schema = "audally")
public class Course implements Serializable {
    @Id
    @SequenceGenerator(
            name = "courseSequence",
            sequenceName = "course_id_seq",
            initialValue = 4
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
            ,generator = "courseSequence")
    public Long id;
    @NotNull
    public String name;
    @Size(max = 400)
    public String description;

    @OneToMany(mappedBy = "course")
    public List<Lesson> lessons = new ArrayList<Lesson>();

    public Course(){

    }
    public void properties(Course course) {
        this.name = course.name;
        this.description = course.description;
        this.lessons = course.lessons;
    }
}
