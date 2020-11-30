package com.audally.backend.boundary;

import com.audally.backend.control.CourseRepository;
import com.audally.backend.entity.Course;
import com.audally.backend.entity.User;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/courses")
@Produces(APPLICATION_JSON)
public class CourseResource {

    @Inject
    CourseRepository courseRepository;

    @GET
    public List<Course> getAll(){
        return courseRepository.findAll().list();
    }

    @POST
    @Path("addCourse")
    public Response addCourse(Course course){
        Course entry = new Course();
        entry.properties(course);
        courseRepository.persist(entry);
        return Response.ok(entry).build();
    }
}
