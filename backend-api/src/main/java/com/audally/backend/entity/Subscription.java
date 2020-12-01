package com.audally.backend.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NotNull
@Table(name = "subscriptions",schema = "audally")
public class Subscription{
    @Id
    @SequenceGenerator(
            name = "subscriptionSequence",
            sequenceName = "sub_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
            ,generator = "subscriptionSequence")
    private Long id;
    public Date subscriptionDate;
    public Date expiryDate;
    public boolean cancelSubscription = false;
    @ManyToOne
    public SubscriptionType subscriptionType;
    @ManyToOne
    public User user;

}
