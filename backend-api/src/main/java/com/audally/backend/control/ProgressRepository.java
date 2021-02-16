package com.audally.backend.control;

import com.audally.backend.entity.Course;
import com.audally.backend.entity.Lesson;
import com.audally.backend.entity.Progress;
import com.audally.backend.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class ProgressRepository implements PanacheRepositoryBase<Progress,Long> {

    public List<Progress> getProgressesOfCourse(Long cid, User user) {
        /*List<Progress> existingList = user.getProgresses()
                .stream().filter(progress -> {
                    AtomicBoolean check = new AtomicBoolean(false);
                    user.getCourses().stream()
                            .filter(course -> course.getId().equals(cid))
                            .map(course -> course.getLessons())
                            .forEach(lessonList -> {
                                Optional<Lesson> l = lessonList.stream()
                                        .filter(lesson -> lesson.getId().equals(progress.getId()))
                                        .findFirst();
                                if (l.isPresent()) {
                                    check.set(true);
                                }
                            });
                    return check.get();
                }).collect(Collectors.toList());*/
        List<Progress> completeList = new ArrayList<>();
        user.getCourses()
                .stream().filter(course -> course.getId().equals(cid))
                .map(course -> course.getLessons())
                .findAny().get()
                .forEach(lesson -> {
                    Optional<Progress> exists = user.getProgresses().stream()
                            .filter(progress -> progress.getLesson().equals(lesson))
                            .findFirst();
                    if (exists.isPresent()) {
                        completeList.add(exists.get());
                    }else{
                        Progress p = new Progress();
                        p.setLesson(lesson);
                        p.setAlreadyListened(false);
                        p.setProgressInSeconds(0);
                        persist(p);
                        completeList.add(p);
                    }
                });
        return completeList;
    }
    public void createProgress(Long lid, Progress progress, User user) {
        user.getCourses().stream()
                .forEach(course -> {
                    Optional<Lesson> l = course.getLessons().stream()
                            .filter(lesson -> lesson.getId().equals(lid))
                            .findFirst();
                    if(l.isPresent()){
                        Progress insertProgress = new Progress();
                        String[] parts = l.get().getDuration().toString().split(":");
                        Duration duration = Duration
                                .ofHours(Long.parseLong(parts[0]))
                                .plusMinutes(Long.parseLong(parts[1]))
                                .plusSeconds(Long.parseLong(parts[2]));
                        if(!progress.isAlreadyListened() &&
                                progress.getProgressInSeconds() > (duration.getSeconds() - 30)){
                            progress.setAlreadyListened(true);
                        }
                        insertProgress.copyProperties(progress);
                        insertProgress.setLesson(l.get());
                        persist(insertProgress);
                        user.getProgresses().add(insertProgress);
                    }
                });
    }
    public void updateProgress(Progress progress, Progress found) {
        Lesson l = found.getLesson();
        String[] parts = l.getDuration().toString().split(":");
        Duration duration = Duration
                .ofHours(Long.parseLong(parts[0]))
                .plusMinutes(Long.parseLong(parts[1]))
                .plusSeconds(Long.parseLong(parts[2]));
        if(!progress.isAlreadyListened()
                && progress.getProgressInSeconds()
                > (duration.getSeconds()-30)) {
            progress.setAlreadyListened(true);
        }
        if(found.isAlreadyListened()){
            found.copyProperties(progress);
            found.setAlreadyListened(true);
        }else found.copyProperties(progress);
        persist(found);
    }
}
