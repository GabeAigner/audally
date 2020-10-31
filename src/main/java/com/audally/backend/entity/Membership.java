package com.audally.backend.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.inject.Default;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Entity
@NotNull
public class Membership extends PanacheEntity {

    public MembershipType membershipType;
    public Date subscriptionDate;
    public Date expiryDate;
    public boolean cancelSubscription = false;
}
