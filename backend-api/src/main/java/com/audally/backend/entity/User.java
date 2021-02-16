package com.audally.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jboss.resteasy.spi.touri.MappedBy;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users",schema = "audally")
public class User implements Serializable {
    @Id
    @SequenceGenerator(
            name = "userSequence",
            sequenceName = "user_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
            ,generator = "userSequence")
    private Long id;
    @NotNull
    @NotEmpty(message = "Not allowed to be empty!")
    private String userName;
    @NotNull
    @Email
    private String email;
    @NotNull
    @NotEmpty
    private String password;
    /*
    @NotNull
    public Date joinDate;
    */
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Subscription> subscriptions = new ArrayList<Subscription>();
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<Course>();
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Progress> progresses = new ArrayList<>();
    @JsonSerialize
    @JsonIgnore
    public List<Course> getCourses() {
        return courses;
    }

    public void copyProperties(User user) {
        setEmail(user.getEmail());
        this.subscriptions = user.getSubscriptions();
        this.courses = user.getCourses();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.progresses = user.getProgresses();
    }
}
