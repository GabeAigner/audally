package com.audally.backend.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "subscription_types", schema = "audally")
public class SubscriptionType implements Serializable {
    @Id
    @SequenceGenerator(
            name = "subTypeSequence",
            sequenceName = "sub_type_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
            ,generator = "subTypeSequence")
    private Long id;
    private String name;
}
