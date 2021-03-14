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
    @Inject
    LessonRepository lessonRepository;

    void onStartup(@Observes StartupEvent event) {
        fillLessons(); // Comment it out if you are doing tests.
        fillCourses(); // Comment it out if you are doing tests.
        fillUsers(); // Comment it out if you are doing tests.
    }

    @Transactional
    private void fillUsers() {
        User john = new User();
        john.setEmail("john@doe.co");
        john.setUserName("john");
        userRepository.persist(john);
        User jane = new User();
        jane.setEmail("jane@doe.co");
        jane.setUserName("jane");
        userRepository.persist(jane);
        User admin = new User();
        admin.setEmail("admin@admin.co");
        admin.setUserName("admin");
        userRepository.persist(admin);
        User creator = new User();
        creator.setEmail("creator@creator.co");
        creator.setUserName("creator");
        userRepository.persist(creator);
    }

    @Transactional
    public void fillLessons() {
        Lesson lesson1 = new Lesson();
        lesson1.setDescription("This is a lesson about Hyvebrain's and everything you need to know about it.");
        lesson1.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/One+Piece+Original+SoundTrack+-+Stealty+Night+Shadow.mp3");
        lesson1.setName("Hyvebrain - What is it?");
        lesson1.setDuration(LocalTime.of(0,3,27));
        lessonRepository.persist(lesson1);
        Lesson lesson2 = new Lesson();
        lesson2.setDescription("This is a lesson about Audally and everything you need to know about it.");
        lesson2.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/AudallyTadeotPresentation5BHIF.mp3");
        lesson2.setName("Audally - What is it?");
        lesson2.setDuration(LocalTime.of(0,2,8));
        lessonRepository.persist(lesson2);
        Lesson lesson3 = new Lesson();
        lesson3.setDescription("This is a lesson about JungleScout and everything you need to know about it.");
        lesson3.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/One+Piece+Original+SoundTrack+-+Stealty+Night+Shadow.mp3");
        lesson3.setName("JungleScout - What is it?");
        lesson3.setDuration(LocalTime.of(0,3,27));
        lessonRepository.persist(lesson3);
        Lesson lesson4 = new Lesson();
        lesson4.setDescription("A second kind of Lesson, the 4th one of its kind.");
        lesson4.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/One+Piece+Original+SoundTrack+-+Stealty+Night+Shadow.mp3");
        lesson4.setName("Hyvebrain - Version 4.0");
        lesson4.setDuration(LocalTime.of(0,3,27));
        lessonRepository.persist(lesson4);
        Lesson lesson5 = new Lesson();
        lesson5.setDescription("This is a generic description for our lessons, hoping that we can have a lot of data.");
        lesson5.setName("This is a generic Name for our lessons");
        lesson5.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/AudallyTadeotPresentation5BHIF.mp3");
        lesson5.setDuration(LocalTime.of(0,2,8));
        lessonRepository.persist(lesson5);
        Lesson lesson6 = new Lesson();
        lesson6.setDescription("This is a Podcast aimed at the new uprising star of the Jungle protection apps.");
        lesson6.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/AudallyTadeotPresentation5BHIF.mp3");
        lesson6.setName("JungleScout - Is this the new future for Jungle Walking?");
        lesson6.setDuration(LocalTime.of(0,2,8));
        lessonRepository.persist(lesson6);
        Lesson lesson7 = new Lesson();
        lesson7.setDescription("Just a random description that is set for the 7th lesson");
        lesson7.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/One+Piece+Original+SoundTrack+-+Stealty+Night+Shadow.mp3");
        lesson7.setName("Hyvebrain - Whats new to the news?");
        lesson7.setDuration(LocalTime.of(0,3,27));
        lessonRepository.persist(lesson7);
        Lesson lesson8 = new Lesson();
        lesson8.setDescription("This is a generic description for our lessons, hoping that we can have a lot of data.");
        lesson8.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/AudallyTadeotPresentation5BHIF.mp3");
        lesson8.setName("Audally - Is there anything new to this business?");
        lesson8.setDuration(LocalTime.of(0,2,8));
        lessonRepository.persist(lesson8);
        Lesson lesson9 = new Lesson();
        lesson9.setDescription("This is a Podcast aimed at the new uprising star of the Jungle protection apps.");
        lesson9.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/One+Piece+Original+SoundTrack+-+Stealty+Night+Shadow.mp3");
        lesson9.setName("JungleScout - Let's talk about it?");
        lesson9.setDuration(LocalTime.of(0,3,27));
        lessonRepository.persist(lesson9);
        Lesson lesson10 = new Lesson();
        lesson10.setDescription("This is a Podcast aimed at the new uprising star");
        lesson10.setAudioUrl("https://drive.google.com/file/d/1PkbBfKRql4dSviLOXko0v4i15qmcgK6o/view?usp=sharing");
        lesson10.setName("Is there any meaning to mathematics?");
        lesson10.setDuration(LocalTime.of(0,3,56));
        lessonRepository.persist(lesson10);
        Lesson lesson11 = new Lesson();
        lesson11.setDescription("This is a Podcast aimed at the new uprising star");
        lesson11.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/AudallyTadeotPresentation5BHIF.mp3");
        lesson11.setName("What do animals do when they are bored?");
        lesson11.setDuration(LocalTime.of(0,2,8));
        lessonRepository.persist(lesson11);
        Lesson lesson12 = new Lesson();
        lesson12.setDescription("This is a Podcast aimed at the new uprising star");
        lesson12.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/One+Piece+Original+SoundTrack+-+Stealty+Night+Shadow.mp3");
        lesson12.setName("The first ever submerged podcast session.");
        lesson12.setDuration(LocalTime.of(0,3,27));
        lessonRepository.persist(lesson12);
        Lesson lesson13 = new Lesson();
        lesson13.setDescription("This is a Podcast aimed at the new uprising star");
        lesson13.setAudioUrl("https://drive.google.com/file/d/1PkbBfKRql4dSviLOXko0v4i15qmcgK6o/view?usp=sharing");
        lesson13.setName("How to make fine cupcakes");
        lesson13.setDuration(LocalTime.of(0,3,56));
        lessonRepository.persist(lesson13);
        Lesson lesson14 = new Lesson();
        lesson14.setDescription("This is a Podcast aimed at the new uprising star");
        lesson14.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/AudallyTadeotPresentation5BHIF.mp3");
        lesson14.setName("This podcast session surrounds the myths of Bigfoot.");
        lesson14.setDuration(LocalTime.of(0,2,8));
        lessonRepository.persist(lesson14);
        Lesson lesson15 = new Lesson();
        lesson15.setDescription("This is a Podcast aimed at the new uprising star.");
        lesson15.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/One+Piece+Original+SoundTrack+-+Stealty+Night+Shadow.mp3");
        lesson15.setName("This course is really nice! (ASMR)");
        lesson15.setDuration(LocalTime.of(0,3,27));
        lessonRepository.persist(lesson15);
        Lesson lesson16 = new Lesson();
        lesson16.setDescription("This is a Podcast aimed at the new uprising star");
        lesson16.setAudioUrl("https://drive.google.com/file/d/1PkbBfKRql4dSviLOXko0v4i15qmcgK6o/view?usp=sharing");
        lesson16.setName("A new Beginning for podcasts");
        lesson16.setDuration(LocalTime.of(0,3,56));
        lessonRepository.persist(lesson16);
        Lesson lesson17 = new Lesson();
        lesson17.setDescription("This is a Podcast aimed at the new uprising star");
        lesson17.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/AudallyTadeotPresentation5BHIF.mp3");
        lesson17.setName("How to make a great presentation");
        lesson17.setDuration(LocalTime.of(0,2,8));
        lessonRepository.persist(lesson17);
        Lesson lesson18 = new Lesson();
        lesson18.setDescription("This is a Podcast aimed at the new uprising star.");
        lesson18.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/One+Piece+Original+SoundTrack+-+Stealty+Night+Shadow.mp3");
        lesson18.setName("Hello.. this is me you are looking for?");
        lesson18.setDuration(LocalTime.of(0,3,27));
        lessonRepository.persist(lesson18);
        Lesson lesson19 = new Lesson();
        lesson19.setDescription("This is a Podcast aimed at the new uprising star");
        lesson19.setAudioUrl("https://drive.google.com/file/d/1PkbBfKRql4dSviLOXko0v4i15qmcgK6o/view?usp=sharing");
        lesson19.setName("A new Hope for podcasts");
        lesson19.setDuration(LocalTime.of(0,3,56));
        lessonRepository.persist(lesson19);
        Lesson lesson20 = new Lesson();
        lesson20.setDescription("This is a Podcast aimed at the new uprising star");
        lesson20.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/AudallyTadeotPresentation5BHIF.mp3");
        lesson20.setName("How to make a great Project");
        lesson20.setDuration(LocalTime.of(0,2,8));
        lessonRepository.persist(lesson20);
        Lesson lesson21 = new Lesson();
        lesson21.setDescription("This is a Podcast aimed at the new uprising star.");
        lesson21.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/One+Piece+Original+SoundTrack+-+Stealty+Night+Shadow.mp3");
        lesson21.setName("Hello.. this is maybe phone you are looking for?");
        lesson21.setDuration(LocalTime.of(0,3,27));
        lessonRepository.persist(lesson21);
        Lesson lesson22 = new Lesson();
        lesson22.setDescription("This is a Podcast aimed at the new uprising star");
        lesson22.setAudioUrl("https://drive.google.com/file/d/1PkbBfKRql4dSviLOXko0v4i15qmcgK6o/view?usp=sharing");
        lesson22.setName("A old Beginning for podcasts");
        lesson22.setDuration(LocalTime.of(0,3,56));
        lessonRepository.persist(lesson22);
        Lesson lesson23 = new Lesson();
        lesson23.setDescription("This is a Podcast aimed at the new uprising star");
        lesson23.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/AudallyTadeotPresentation5BHIF.mp3");
        lesson23.setName("How to fail a great presentation");
        lesson23.setDuration(LocalTime.of(0,2,8));
        lessonRepository.persist(lesson23);
        Lesson lesson24 = new Lesson();
        lesson24.setDescription("This is a Podcast aimed at the new uprising star.");
        lesson24.setAudioUrl("https://audally-audio-files.s3.eu-central-1.amazonaws.com/One+Piece+Original+SoundTrack+-+Stealty+Night+Shadow.mp3");
        lesson24.setName("Hello.. this is not me you are looking for?");
        lesson24.setDuration(LocalTime.of(0,3,27));
        lessonRepository.persist(lesson24);
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
        course1.getLessons().add(lessonRepository.findById(2L));
        course1.getLessons().add(lessonRepository.findById(3L));
        course1.getLessons().add(lessonRepository.findById(4L));
        course1.getLessons().add(lessonRepository.findById(5L));
        course1.getLessons().add(lessonRepository.findById(6L));
        course1.getLessons().add(lessonRepository.findById(7L));
        course1.getLessons().add(lessonRepository.findById(8L));
        courseRepository.persist(course1);
        Course course2 = new Course();
        course2.setDescription("In this Course you will get to learn some quality apps that you can use to make your everyday life easier. Especially during Corona times");
        course2.setName("How to get started with your easier corona filled Daily life.");
        course2.setPictureUrl("https://images.unsplash.com/photo-1603367436420-05df3d9f12ef?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=634&q=80");
        course2.getLessons().add(lessonRepository.findById(9L));
        course2.getLessons().add(lessonRepository.findById(10L));
        course2.getLessons().add(lessonRepository.findById(11L));
        courseRepository.persist(course2);
        Course course3 = new Course();
        course3.setDescription("This is a filler that we can use for testing if need be.");
        course3.setName("Test Number 01 - The Filler Course for everything you need to know.");
        course3.setPictureUrl("https://images.unsplash.com/photo-1514064019862-23e2a332a6a6?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=916&q=80");
        course3.getLessons().add(lessonRepository.findById(12L));
        course3.getLessons().add(lessonRepository.findById(13L));
        courseRepository.persist(course3);
        Course course4 = new Course();
        course4.setDescription("The course that will let you experience everything you need to know about cooking.");
        course4.setName("Cooking Classes 101, how to do it right.");
        course4.setPictureUrl("https://images.unsplash.com/photo-1528712306091-ed0763094c98?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=680&q=80");
        course4.getLessons().add(lessonRepository.findById(14L));
        courseRepository.persist(course4);
        Course course5 = new Course();
        course5.setDescription("This podcast will have a weekly increase in viewer ship just because of it's description.");
        course5.setName("How vulnerable is Audally really?");
        course5.setPictureUrl("https://images.unsplash.com/photo-1563206767-5b18f218e8de?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1349&q=80");
        course5.getLessons().add(lessonRepository.findById(15L));
        course5.getLessons().add(lessonRepository.findById(16L));
        courseRepository.persist(course5);
        Course course6 = new Course();
        course6.setDescription("This podcast can compete with the best ones out there!");
        course6.setName("Braintastic - Is it facing clash back with new Contenders?");
        course6.setPictureUrl("https://images.unsplash.com/photo-1557804506-669a67965ba0?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1267&q=80");
        course6.getLessons().add(lessonRepository.findById(17L));
        courseRepository.persist(course6);
        Course course7 = new Course();
        course7.setDescription("This podcast will give you an insight about Elon Musk and how he operates things.");
        course7.setName("Everything related to Elon Musk!");
        course7.setPictureUrl("https://images.unsplash.com/photo-1612810806563-4cb8265db55f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=564&q=80");
        course7.getLessons().add(lessonRepository.findById(18L));
        course7.getLessons().add(lessonRepository.findById(19L));
        course7.getLessons().add(lessonRepository.findById(20L));
        courseRepository.persist(course7);
        Course course8 = new Course();
        course8.setDescription("A memoir by the creator of NIKE");
        course8.setName("Shoe Dog - Phil Knight");
        course8.setPictureUrl("https://images.unsplash.com/photo-1556906781-9a412961c28c?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80");
        course8.getLessons().add(lessonRepository.findById(21L));
        courseRepository.persist(course8);
        Course course9 = new Course();
        course9.setDescription("The course to achieve a healthier and happier life.");
        course9.setName("Start to be happy!");
        course9.setPictureUrl("https://images.unsplash.com/photo-1579722820308-d74e571900a9?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80");
        course9.getLessons().add(lessonRepository.findById(22L));
        courseRepository.persist(course9);
        Course course10 = new Course();
        course10.setDescription("A course about business development.");
        course10.setName("How to Startup?");
        course10.setPictureUrl("https://images.unsplash.com/photo-1489533119213-66a5cd877091?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80");
        course10.getLessons().add(lessonRepository.findById(23L));
        course10.getLessons().add(lessonRepository.findById(24L));
        courseRepository.persist(course10);
    }
    public void doLoader(User user){
        List<Course> load = user.getCourses();
        load.forEach(course -> course.getLessons());
        user.getSubscriptions();
        user.getProgresses();
    }
}

