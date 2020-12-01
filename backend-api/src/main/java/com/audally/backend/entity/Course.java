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
            initialValue = 4,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
            ,generator = "courseSequence")
    public Long id;
    @NotNull
    public String name;
    @Size(max = 400)
    public String description;

    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER)
    public List<Lesson> lessons = new ArrayList<Lesson>();

    public Course(){

    }
    public void addLessons(Lesson lesson){
        this.lessons.add(lesson);
    }
    public List<Lesson> getLessons(){
        return this.lessons;
    }
    public void copyProperties(Course course) {
        this.name = course.name;
        this.lessons = course.lessons;
        this.description = course.description;
    }

}
