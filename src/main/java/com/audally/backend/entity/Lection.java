package com.audally.backend.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Entity
@Table(name = "Lection",schema = "audallydb")
public class Lection extends PanacheEntity {
    @NotNull
    public String name;
    @Max(400)
    public String description;
    @URL
    public String audioUrl;

    public Time duration;

    @ManyToOne
    public Course course;

}
