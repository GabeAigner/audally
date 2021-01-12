package com.audally.backend.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lessons",schema = "audally")
//@JsonSerialize
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
    private String name;
    @Size(max = 400)
    private String description;
    @URL
    private String audioUrl;
    @JsonbDateFormat("HH:mm:ss")
    private LocalTime duration;
    /*@ManyToOne
    @JsonbTransient
    @JoinColumn(name = "course_id")
    public Course course;
    */
    public void copyProperties(Lesson lesson) {
        this.name = lesson.getName();
        this.duration = lesson.getDuration();
        this.audioUrl = lesson.getAudioUrl();
        this.description = lesson.getDescription();
    }
}
