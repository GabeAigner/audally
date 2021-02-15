package com.audally.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
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
    private Long id;
    @NotNull
    private String name;
    @Size(max = 400)
    private String description;
    @URL
    private String pictureUrl;
    //    @JsonbTransient - die lessons werden überhaupt nicht angezeigt
    /*@JsonbProperty
    @JsonSerialize*/
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Lesson> lessons = new ArrayList<Lesson>();

    /*@JsonSerialize
    @JsonbProperty*/
    public List<Lesson> getLessons() {
        return lessons;
    }

    public void copyProperties(Course course) {
        this.name = course.getName();
        this.lessons = course.getLessons();
        this.description = course.getDescription();
        this.pictureUrl = course.getPictureUrl();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getName().equals(course.getName()) && Objects.equals(getDescription(),
                course.getDescription()) && Objects.equals(getPictureUrl(),
                course.getPictureUrl());
    }
}
