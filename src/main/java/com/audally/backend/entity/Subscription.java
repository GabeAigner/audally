package com.audally.backend.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NotNull
@Table(name = "Subscription",schema = "audallydb")
public class Subscription extends PanacheEntity {
    public Date subscriptionDate;
    public Date expiryDate;
    public boolean cancelSubscription = false;

    @ManyToOne
    public SubscriptionType subscriptionType;

    @ManyToOne
    public User user;
}
