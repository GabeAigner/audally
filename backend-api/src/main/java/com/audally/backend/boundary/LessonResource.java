package com.audally.backend.boundary;

import com.audally.backend.control.CourseRepository;
import com.audally.backend.control.LessonRepository;
import com.audally.backend.entity.Course;
import com.audally.backend.entity.Lesson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/lessons")
@Produces(APPLICATION_JSON)
@Transactional
public class LessonResource {
    @Inject
    LessonRepository lessonRepository;
    @Inject
    CourseRepository courseRepository;

    @GET
    @Path("/{id}")
    public Response getLessonsOfCourse(@PathParam("id") Long cid){
        Course read = courseRepository.findById(cid);
        if(read == null)return Response.noContent().build();
        return Response.ok(read.lessons).build();
    }
    @POST
    @Path("addLessonsToCourse/{cid}")
    public Response addLessonToCourse(@PathParam("cid") Long cid,Lesson[] lessons){
        Course read = courseRepository.findById(cid);
        if(read == null)return Response.noContent().build();
        Arrays.stream(lessons)
                .forEach(l -> {
//                    lessonRepository.persist(l);
                    read.lessons.add(lessonRepository.getEntityManager().merge(l));
                });
        courseRepository.getEntityManager().merge(read);
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

        change.lessons.stream()
                .forEach(l -> {
                    if(Arrays.stream(lessons).anyMatch(nl -> nl.equals(l)) == true){
                        lessonRepository.delete(l);
                        change.lessons.remove(l);
                    }
                });
        courseRepository.getEntityManager().merge(change);
        return Response.ok(change.lessons).build();
    }
}
