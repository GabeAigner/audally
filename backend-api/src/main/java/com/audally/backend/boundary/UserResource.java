package com.audally.backend.boundary;


import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.annotations.cache.NoCache;
import javax.annotation.security.RolesAllowed;
import com.audally.backend.control.CourseRepository;
import com.audally.backend.control.UserRepository;
import com.audally.backend.entity.Course;
import com.audally.backend.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/users")
@Produces(APPLICATION_JSON)
@Transactional
@ApplicationScoped
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
    @GET
    @Path("/{UserId}")
    public Response getUser(@PathParam("UserId") Long uid){
        return Response.ok(userRepository.findById(uid)).build();
    }
    @GET
    @Path("/{UserId}/courses")
    public Response getCoursesOfUser(@PathParam("UserId") Long uid){
        User user = userRepository.findById(uid);
        if(user == null){
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .header("User was not found!",User.class)
                    .build();
        }
        return Response.ok(user.courses).build();
    }
    @POST
    @Path("{UserId}/courses/{CourseId}")
    public Response addCourseToUser(@PathParam("UserId") Long uid
            ,@PathParam("CourseId") Long cid){
        User user = userRepository.findById(uid);
        Course course = courseRepository.findById(cid);
        if(user == null){
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .header("User was not found!",User.class)
                    .build();
        }
        else if(course == null){
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .header("Course was not found!",Course.class)
                    .build();
        }
        user.addCourses(course);
        userRepository.getEntityManager().merge(user);
        return Response.ok(userRepository.findById(uid)).build();
    }
    @POST
    @Path("addUser")
    public Response addUser(User user){
        User entry = new User();
        if(userRepository.isPersistent(user) == true){
            return Response
                    .status(Response.Status.FOUND)
                    .header("User was found",User.class)
                    .build();
        }
        entry.copyProperties(user);
        userRepository.persist(entry);
        return Response.ok(entry).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id")Long uid){
        if (userRepository.findById(uid) != null){
            userRepository.deleteById(uid);
            return Response.status(Response.Status.GONE)
                    .header("User deleted!",User.class)
                    .build();
        }
        return Response.noContent().build();
    }
    @DELETE
    @Path("removeCourse/{id}-{cid}")
    public Response removeCourse(@PathParam("id")Long uid
            ,@PathParam("cid")Long cid){
        User change = userRepository.findById(uid);
        if(change != null){
            change.courses.remove(cid);
            userRepository.getEntityManager().merge(change);
            return Response.status(Response.Status.GONE)
                    .header("Course removed from User",User.class)
                    .build();
        }
        return Response.noContent().build();
    }
    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id")Long uid,User user){
        User updated = userRepository.findById(uid);
        if (updated != null &&
                userRepository.isPersistent(user) == false){
            updated.copyProperties(user);
            userRepository.getEntityManager().merge(updated);
            return Response.status(Response.Status.FOUND)
                    .header("User updated!",User.class)
                    .build();
        }
        return Response.noContent().build();
    }
}
