package com.audally.backend.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.validator.constraints.URL;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table(name = "lessons",schema = "audally")
public class Lesson implements Serializable {
    @Id
    @SequenceGenerator(
            name = "lessonSequence",
            sequenceName = "lesson_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
            ,generator = "lessonSequence")
    private Long id;
    @NotNull
    public String name;
    @Size(max = 400)
    public String description;
    @URL
    public String audioUrl;
    @JsonbDateFormat("HH:mm:ss")
    public LocalTime duration;

    @ManyToOne
    @JoinColumn(name = "course_id")
    public Course course;

}
