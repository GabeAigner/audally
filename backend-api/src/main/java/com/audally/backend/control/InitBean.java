package com.audally.backend.control;

import com.audally.backend.entity.Course;
import com.audally.backend.entity.Lesson;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalTime;

@ApplicationScoped
public class InitBean {


    @Inject
    CourseRepository courseRepository;

    @Inject
    LessonRepository lessonRepository;

    void onStartup(@Observes StartupEvent event) {
        fillLessons();
        fillCourses();
    }

    @Transactional
    public void fillLessons() {
        Lesson lesson1 = new Lesson();
        lesson1.setDescription("This is a lesson about Hyvebrain's and everything you need to know about it.");
        lesson1.setAudioUrl("https://drive.google.com/file/d/1OHJJ1HYxhTJiJ7iy4OKBNT1tSP43378V/view?usp=sharing");
        lesson1.setName("Hyvebrain - What is it?");
        lesson1.setDuration(LocalTime.of(0,3,11));
        lessonRepository.persist(lesson1);
        Lesson lesson2 = new Lesson();
        lesson2.setDescription("This is a lesson about Audally and everything you need to know about it.");
        lesson2.setAudioUrl("https://drive.google.com/file/d/1hC28UL6IBzNuYbLBSkoGC8JgaPhRwX2V/view?usp=sharing");
        lesson2.setName("Audally - What is it?");
        lesson2.setDuration(LocalTime.of(0,2,8));
        lessonRepository.persist(lesson2);
        Lesson lesson3 = new Lesson();
        lesson3.setDescription("This is a lesson about JungleScout and everything you need to know about it.");
        lesson3.setAudioUrl("https://drive.google.com/file/d/1PkbBfKRql4dSviLOXko0v4i15qmcgK6o/view?usp=sharing");
        lesson3.setName("JungleScout - What is it?");
        lesson3.setDuration(LocalTime.of(0,3,56));
        lessonRepository.persist(lesson3);
    }

    @Transactional
    public void fillCourses() {
        //'A course about business development.', 'How to Startup?','https://images.unsplash.com/photo-1489533119213-66a5cd877091?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80');
        //'A memoir by the creator of NIKE', 'Shoe Dog - Phil Knight','https://images.unsplash.com/photo-1556906781-9a412961c28c?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80');
        //'The course to achieve a healthier and happier life.', 'Start to be happy!','https://images.unsplash.com/photo-1579722820308-d74e571900a9?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80');
        Course course1 = new Course();
        course1.setDescription("Tools you can use for your startup that will help in either the project itself or just to cool down from the stress");
        course1.setName("Good Tools or Apps to use for Startups.");
        course1.setPictureUrl("https://images.unsplash.com/photo-1509381488012-29d23e254085?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
        course1.setLessons(lessonRepository.listAll()); //just for current user action cases.
        courseRepository.persist(course1);
        Course course2 = new Course();
        course2.setDescription("In this Course you will get to learn some quality apps that you can use to make your everyday life easier. Especially during Corona times");
        course2.setName("How to get started with your easier corona filled Daily life.");
        course2.setPictureUrl("https://unsplash.com/photos/kj2C-2w2tGs");
        //course2.setLessons();
        courseRepository.persist(course2);
        Course course3 = new Course();
        course3.setDescription("This is a filler that we can use for testing if need be.");
        course3.setName("Test Number 01 - The Filler Course for everything you need to know.");
        course3.setPictureUrl("https://unsplash.com/photos/IHL-Jbawvvo");
        //course3.setLessons(lessonRepository.listAll());
        courseRepository.persist(course3);
    }

}

