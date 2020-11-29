package com.audally.backend.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subscription_types", schema = "audally")
public class SubscriptionType extends PanacheEntity {
    public String name;
}
