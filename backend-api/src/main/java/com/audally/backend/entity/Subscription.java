package com.audally.backend.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
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
    private Date subscriptionDate;
    private Date expiryDate;
    private boolean cancelSubscription = false;
    @ManyToOne
    private SubscriptionType subscriptionType;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private User user;

}
