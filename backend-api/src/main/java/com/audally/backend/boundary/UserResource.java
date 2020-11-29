package com.audally.backend.boundary;

import com.audally.backend.entity.Course;
import com.audally.backend.entity.User;
import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.lang.module.ResolutionException;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/users")
@Produces(APPLICATION_JSON)
public class UserResource {

    @Inject
    SecurityIdentity identity;

    @GET
    @NoCache
    @RolesAllowed("user")
    public SecurityIdentity getUserInfo(){
        return identity;
    }

    @GET
    @NoCache
    @Path("/secret")
    @RolesAllowed("admin")
    public String getAdminInfo(){
        return "This is an Admin.";
    }

    @POST
    @Path("/{UserId}/courses/{CourseId}")
    public Response addCourseToUser(@PathParam("UserId") int Uid,@PathParam("CourseId") int Cid){
        User user = User.findById(Uid);
        user.courses.add(Course.findById(Cid));
        user.persist();
        return Response.ok(user.toString()).build();
    }
}
