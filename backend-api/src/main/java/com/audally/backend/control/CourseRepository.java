package com.audally.backend.control;

import com.audally.backend.entity.Course;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CourseRepository implements PanacheRepositoryBase<Course,Long> {

    @PersistenceContext
    EntityManager em;

    public void merge(Course course){
        em.merge(course);
    }
}
