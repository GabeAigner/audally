package com.audally.backend.boundary;

import com.arjuna.ats.arjuna.common.Uid;
import com.audally.backend.control.CourseRepository;
import com.audally.backend.control.UserRepository;
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
    UserRepository userRepository;
    @Inject
    CourseRepository courseRepository;
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
    @Path("addCourse/{UserId}-{CourseId}")
    public Response addCourseToUser(@PathParam("UserId") Long Uid,@PathParam("CourseId") Long Cid){
        User user = userRepository.findById(Uid);
        Course course = courseRepository.findById(Cid);
        user.courses.add(course);
        user.userName = "New John";
        //userRepository.merge(user);
        userRepository.update("update from users_courses set course = ?1",user.courses.listIterator());
        //userRepository.flush();
        return Response.ok(userRepository.findById(Uid)).build();
    }
    @POST
    @Path("addUser")
    public Response addUser(User user){
        User entry = new User();
        entry.properties(user);
        userRepository.persist(entry);
        return Response.ok(entry).build();
    }

    @GET
    @Path("/{UserId}")
    public Response getUser(@PathParam("UserId") Long Uid){
        return Response.ok(userRepository.findById(Uid)).build();
    }
}
