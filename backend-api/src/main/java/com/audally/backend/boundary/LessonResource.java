package com.audally.backend.boundary;

import com.audally.backend.control.CourseRepository;
import com.audally.backend.control.LessonRepository;
import com.audally.backend.entity.Course;
import com.audally.backend.entity.Lesson;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/lessons")
@Produces(APPLICATION_JSON)
@Transactional
@ApplicationScoped
public class LessonResource {
    @Inject
    LessonRepository lessonRepository;
    @Inject
    CourseRepository courseRepository;

    @GET
    @Path("getLessonOfCourse/{id}")
    public Response getLessonsOfCourse(@PathParam("id") Long cid){
        Course read = courseRepository.findById(cid);
        if(read == null)return Response.noContent().build();
        return Response.ok(read.getLessons()).build();

    }
    @POST
    @Path("addLessonsToCourse/{cid}")
    public Response addLessonToCourse(@PathParam("cid") Long cid,Lesson[] lessons){
        Course read = courseRepository.findById(cid);
        if(read == null)return Response.noContent().build();
        Arrays.stream(lessons)
                .forEach(l -> {
//                    lessonRepository.persist(l);
                  /*Lesson created = new Lesson();
                    created.copyProperties(l);
                    read.addLessons((lessonRepository.getEntityManager().merge(created)));
                    */
                    Lesson created = new Lesson();
                    created.copyProperties(l);
//                    created.setCourse(read);
                    lessonRepository.persist(created);
                    read.getLessons().add(lessonRepository.findById(created.getId()));
                });
        courseRepository.persist(read);
        return Response.ok(courseRepository.findById(cid)).build();
    }
    @DELETE
    @Path("rmvLessonsFromCourse/{cid}")
    public Response removeLessonsFromCourse
            (@PathParam("cid") Long cid,Lesson[] lessons){
        Course change = courseRepository.findById(cid);
        if(change == null)return Response.noContent().build();
        /*Arrays.stream(lessons)
                .forEach(l -> {
                    if (change.lessons.stream()
                            .anyMatch(lesson -> lesson.equals(l))){
                    }
                });*/
        AtomicBoolean toDelete = new AtomicBoolean(false);
        List<Lesson> ToDeleteLessons = new ArrayList<>();
        change.getLessons().stream()
                .forEach(l -> {
                    if(Arrays.stream(lessons).anyMatch(nl -> nl.getId().equals(l.getId())) == true){
                        toDelete.set(true);
                        ToDeleteLessons.add(l);
                        lessonRepository.delete(l);
                    }
                });
        if(toDelete.get()) ToDeleteLessons.forEach(lesson -> change.getLessons().remove(lesson));
        else return Response
                .status(204,"Lessons were not found!")
                .build();
        courseRepository.persist(change);
        return Response.ok(change.getLessons()).build();
    }
}
