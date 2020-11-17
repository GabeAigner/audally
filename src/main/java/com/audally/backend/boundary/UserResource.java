package com.audally.backend.boundary;

import com.audally.backend.entity.Course;
import com.audally.backend.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.lang.module.ResolutionException;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/user")
@Produces(APPLICATION_JSON)
public class UserResource {
    @POST
    //          addCourse/1-21
    @Path("/{UserId}/courses/{CourseId}")
    public Response addCourseToUser(@PathParam("UserId") int Uid,@PathParam("CourseId") int Cid){
        User user = User.findById(Uid);
        user.courses.add(Course.findById(Cid));
        user.persist();
        return Response.ok(user.toString()).build();
    }
}
