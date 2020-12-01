package com.audally.backend.control;

import com.audally.backend.entity.Lesson;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class LessonRepository implements PanacheRepositoryBase<Lesson,Long> {

}
