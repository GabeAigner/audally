package com.audally.backend.boundary;

import com.audally.backend.entity.Course;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/courses")
@Produces(APPLICATION_JSON)
public class CourseResource {
    @GET
    public List<Course> getAll(){
        return Course.findAll().list();
    }
}
