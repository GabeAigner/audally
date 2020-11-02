package com.audally.backend.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Course",schema = "audallydb")
public class Course extends PanacheEntity {
    @NotNull
    public String name;
    @Max(400)
    public String description;

    @OneToMany
    public List<Lection> lections;

}
