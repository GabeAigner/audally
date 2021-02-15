package com.audally.backend.boundary;

import com.audally.backend.control.CourseRepository;
import com.audally.backend.entity.Course;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/courses")
@Produces(APPLICATION_JSON)
@Transactional
@ApplicationScoped
public class CourseResource {

    @Inject
    CourseRepository courseRepository;

    @GET
    public List<Course> getAll(){
        return doLoader(courseRepository.findAll().list());
    }

    @POST
    public Response addCourse(Course course){
        if(courseRepository.findAll().list().stream().anyMatch(course1 -> course1.equals(course))){
            return Response
                    .status(406,"Course already exists!")
                    .build();
        }
        Course entry = new Course();
        entry.copyProperties(course);
        course.getLessons();
        courseRepository.persist(entry);
        return Response.ok(entry).build();
    }
    @DELETE
    @Path("{id}")
    public Response deleteCourse(@PathParam("id") Long cid){
        if (courseRepository.findById(cid) != null){
            courseRepository.deleteById(cid);
            return Response.ok("Course got deleted").build();
        }
        return Response.noContent().build();
    }
    @PUT
    @Path("{id}")
    public Response updateCourse(@PathParam("id") Long cid,Course course){
        Course change = courseRepository.findById(cid);
        if(change != null){
            change.copyProperties(course);
            change.getLessons();
            courseRepository.persist(change);
            return Response.ok(change).build();
        }
        return Response.noContent().build();
    }
    public List<Course> doLoader(List<Course> courses){
        courses.forEach(course -> course.getLessons());
        return courses;
    }
}
