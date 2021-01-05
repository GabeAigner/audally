package com.audally.backend.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.validator.constraints.URL;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
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
    public Long id;
    @NotNull
    public String name;
    @Size(max = 400)
    public String description;
    @URL
    public String audioUrl;
    @JsonbDateFormat("HH:mm:ss")
    public LocalTime duration;
    /*@ManyToOne
    @JsonbTransient
    @JoinColumn(name = "course_id")
    public Course course;
    */
    public void copyProperties(Lesson lesson) {
        this.name = lesson.name;
        this.duration = lesson.duration;
        this.audioUrl = lesson.audioUrl;
        this.description = lesson.description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }
/*
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }*/
}
