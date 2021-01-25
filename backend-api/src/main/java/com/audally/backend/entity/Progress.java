package com.audally.backend.entity;

import jdk.jfr.Timespan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Progress implements Serializable {
    @Id
    @SequenceGenerator(
            name = "progressSequence",
            sequenceName = "progress_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
            ,generator = "progressSequence")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Lesson lesson;
    private boolean alreadyListened;
    private int progressInSeconds;

    public void copyProperties(Progress progress) {
        this.progressInSeconds = progress.getProgressInSeconds();
        this.alreadyListened = progress.isAlreadyListened();
    }
}
