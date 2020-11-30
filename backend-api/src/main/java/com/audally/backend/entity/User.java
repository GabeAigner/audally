package com.audally.backend.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users",schema = "audally")
public class User implements Serializable {
    @Id
    @SequenceGenerator(
            name = "userSequence",
            sequenceName = "user_id_seq",
            initialValue = 3
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
            ,generator = "userSequence")
    public Long id;
    @NotNull
    @NotEmpty(message = "Not allowed to be empty!")
    public String userName;
    @NotNull
    @Email
    public String email;
    @NotNull
    @NotEmpty
    public String password;
    /*
    @NotNull
    public Date joinDate;
    */
    @OneToMany(mappedBy = "user")
    public List<Subscription> subscriptions = new ArrayList<Subscription>();
    @OneToMany
    public List<Course> courses = new ArrayList<Course>();
    public User(){ }

    public void properties(User user) {
        this.email = user.email;
        this.subscriptions = user.subscriptions;
        this.courses = user.courses;
        this.userName = user.userName;
        this.password = user.password;
    }
    public void setCourses(List<Course> course){
        this.courses = course;
    }
}
