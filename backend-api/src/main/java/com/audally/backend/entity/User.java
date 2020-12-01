package com.audally.backend.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.jboss.resteasy.spi.touri.MappedBy;

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
            initialValue = 3,
            allocationSize = 1
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
    @OneToMany(fetch = FetchType.EAGER)
    public List<Course> courses = new ArrayList<Course>();
    public User(){ }

    public void copyProperties(User user) {
        this.email = user.email;
        this.subscriptions = user.subscriptions;
        this.courses = user.courses;
        this.userName = user.userName;
        this.password = user.password;
    }

    public void addCourses(Course courses) {
        this.courses.add(courses);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", subscriptions=" + subscriptions.toArray().toString() +
                ", courses=" + courses.toArray().toString() +
                '}';
    }
}
