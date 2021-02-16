package com.audally.backend.control;

import com.audally.backend.entity.Course;
import com.audally.backend.entity.Lesson;
import com.audally.backend.entity.Progress;
import com.audally.backend.entity.User;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

@ApplicationScoped
@Transactional
public class InitBean {

    @Inject
    UserRepository userRepository;
    @Inject
    CourseRepository courseRepository;
    ProgressRepository progressRepository;
    @Inject
    LessonRepository lessonRepository;

    void onStartup(@Observes StartupEvent event) {
        //fillLessons(); // Comment it out if you are doing tests.
        //fillCourses(); // Comment it out if you are doing tests.
        //fillUsers(); // Comment it out if you are doing tests.
    }

    /*@Transactional
    private void fillUsers() {
        User john = userRepository.findById(1L);
        User jane = userRepository.findById(2L);
        doLoader(john);
        doLoader(jane);
        john.getCourses().add(courseRepository.findById(4L));
        john.getCourses().forEach(course -> course.getLessons()
                .forEach(lesson -> progressRepository
                        .createProgress(lesson.getId(),new Progress(),john)));
        jane.getCourses().add(courseRepository.findById(4L));
        jane.getCourses().forEach(course -> course.getLessons()
                .forEach(lesson -> progressRepository
                        .createProgress(lesson.getId(),new Progress(),jane)));
    }*/

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
        Lesson lesson4 = new Lesson();
        lesson4.setDescription("A second kind of Lesson, the 4th one of its kind.");
        lesson4.setAudioUrl("https://drive.google.com/file/d/1OHJJ1HYxhTJiJ7iy4OKBNT1tSP43378V/view?usp=sharing");
        lesson4.setName("Hyvebrain - Version 4.0");
        lesson4.setDuration(LocalTime.of(0,3,11));
        lessonRepository.persist(lesson4);
        Lesson lesson5 = new Lesson();
        lesson5.setDescription("This is a generic description for our lessons, hoping that we can have a lot of data.");
        lesson5.setAudioUrl("https://drive.google.com/file/d/1hC28UL6IBzNuYbLBSkoGC8JgaPhRwX2V/view?usp=sharing");
        lesson5.setName("Audally - Is it any good?");
        lesson5.setDuration(LocalTime.of(0,2,8));
        lessonRepository.persist(lesson5);
        Lesson lesson6 = new Lesson();
        lesson6.setDescription("This is a Podcast aimed at the new uprising star of the Jungle protection apps.");
        lesson6.setAudioUrl("https://drive.google.com/file/d/1PkbBfKRql4dSviLOXko0v4i15qmcgK6o/view?usp=sharing");
        lesson6.setName("JungleScout - Is this the new future for Jungle Walking?");
        lesson6.setDuration(LocalTime.of(0,3,56));
        lessonRepository.persist(lesson6);
        Lesson lesson7 = new Lesson();
        lesson7.setDescription("Just a random description that is set for the 7th lesson");
        lesson7.setAudioUrl("https://drive.google.com/file/d/1OHJJ1HYxhTJiJ7iy4OKBNT1tSP43378V/view?usp=sharing");
        lesson7.setName("Hyvebrain - Whats new to the news?");
        lesson7.setDuration(LocalTime.of(0,3,11));
        lessonRepository.persist(lesson7);
        Lesson lesson8 = new Lesson();
        lesson8.setDescription("This is a generic description for our lessons, hoping that we can have a lot of data.");
        lesson8.setAudioUrl("https://drive.google.com/file/d/1hC28UL6IBzNuYbLBSkoGC8JgaPhRwX2V/view?usp=sharing");
        lesson8.setName("Audally - Is there anything new to this business?");
        lesson8.setDuration(LocalTime.of(0,2,8));
        lessonRepository.persist(lesson8);
        Lesson lesson9 = new Lesson();
        lesson9.setDescription("This is a Podcast aimed at the new uprising star of the Jungle protection apps.");
        lesson9.setAudioUrl("https://drive.google.com/file/d/1PkbBfKRql4dSviLOXko0v4i15qmcgK6o/view?usp=sharing");
        lesson9.setName("JungleScout - Let's talk about it?");
        lesson9.setDuration(LocalTime.of(0,3,56));
        lessonRepository.persist(lesson9);
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
        course1.getLessons().add(lessonRepository.findById(1L)); //just for current user action cases.
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
        Course course4 = new Course();
        course4.setDescription("The course that will let you experience everything you need to know about cooking.");
        course4.setName("Cooking Classes 101, how to do it right.");
        course4.setPictureUrl("https://unsplash.com/photos/IHL-Jbawvvo");
        //course4.setLessons(lessonRepository.listAll());
        courseRepository.persist(course4);
        Course course5 = new Course();
        course5.setDescription("This podcast will have a weekly increase in viewer ship just because of it's description.");
        course5.setName("How vulnerable is Audally really?");
        course5.setPictureUrl("https://unsplash.com/photos/IHL-Jbawvvo");
        //course5.setLessons(lessonRepository.listAll());
        courseRepository.persist(course5);
        Course course6 = new Course();
        course6.setDescription("This podcast can compete with the best ones out there!");
        course6.setName("Braintastic - Is it facing clash back with new Contenders?");
        course6.setPictureUrl("https://unsplash.com/photos/IHL-Jbawvvo");
        //course6.setLessons(lessonRepository.listAll());
        courseRepository.persist(course6);
        Course course7 = new Course();
        course7.setDescription("This podcast will give you an insight about Elon Musk and how he operates things.");
        course7.setName("Everything related to Elon Musk!");
        course7.setPictureUrl("https://unsplash.com/photos/IHL-Jbawvvo");
        //course7.setLessons(lessonRepository.listAll());
        courseRepository.persist(course7);
        Course course8 = new Course();
        course8.setDescription("This podcast aims to tell people how to correctly use Fitavari.");
        course8.setName("Fitavari - How to use it correctly!");
        course8.setPictureUrl("https://unsplash.com/photos/IHL-Jbawvvo");
        //course8.setLessons(lessonRepository.listAll());
        courseRepository.persist(course8);
        Course course9 = new Course();
        course9.setDescription("This podcast will be the best one out there!");
        course9.setName("Some Course that is related to Audally.");
        course9.setPictureUrl("https://unsplash.com/photos/IHL-Jbawvvo");
        //course6.setLessons(lessonRepository.listAll());
        courseRepository.persist(course9);
        Course course10 = new Course();
        course10.setDescription("10th course Anniversary. A one in a life time opportunity");
        course10.setName("The 10th Course to get you started with Audally.");
        course10.setPictureUrl("https://unsplash.com/photos/IHL-Jbawvvo");
        //course10.setLessons(lessonRepository.listAll());
        courseRepository.persist(course10);
    }
    public void doLoader(User user){
        List<Course> load = user.getCourses();
        load.forEach(course -> course.getLessons());
        user.getSubscriptions();
        user.getProgresses();
    }
}

