package com.audally.backend.entity;

import jdk.jfr.Percentage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Dictionary;
import java.util.Enumeration;

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
    @JsonbDateFormat("HH:mm:ss")
    private LocalTime duration;
    public void copyProperties(Progress progress) {
        this.lesson = progress.getLesson();
        this.duration = progress.getDuration();
        this.alreadyListened = progress.isAlreadyListened();
    }

}
