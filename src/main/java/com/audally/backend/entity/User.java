package com.audally.backend.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users",schema = "audally")
public class User extends PanacheEntity {
    @NotNull
    @NotEmpty(message = "Not allowed to be empty!")
    public String userName;
    @NotNull
    @Email
    public String email;
    @NotNull
    @NotEmpty
    public String password;
    @NotNull
    public Date joinDate;
    @OneToMany(mappedBy = "user")
    public List<Subscription> subscriptions;
    @OneToMany
    public List<Course> courses;
}
