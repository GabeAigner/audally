package com.audally.backend.boundary;


import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.annotations.cache.NoCache;
import javax.annotation.security.RolesAllowed;
import com.audally.backend.control.CourseRepository;
import com.audally.backend.control.UserRepository;
import com.audally.backend.entity.Course;
import com.audally.backend.entity.User;
import org.jose4j.json.internal.json_simple.JSONObject;

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
    private JSONObject businessuser;

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
    @Path("email/{email}")
    public Response getUserByEmail(@PathParam("email") String username){
        User user = userRepository.find("email",username).firstResult();
        if(user == null){
            return Response
                    .status(204,"User could not be found!")
                    .build();
        }
        businessuser = new JSONObject();
        businessuser.merge("id",user.id,(o, o2) -> o = o2);
        businessuser.merge("courses",user.courses,(o, o2) -> o = o2);

        return Response.ok(businessuser).build();
    }
    @GET
    @Path("/{UserId}/courses")
    public Response getCoursesOfUser(@PathParam("UserId") Long uid){
        User user = userRepository.findById(uid);
        if(user == null){
            return Response
                    .status(202,"Course already exists in the User!")
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
        if(user.courses.contains(course)){
            return Response
                    .status(406,"Course already exists in the User!")
                    .build();
        }
        if(user == null){
            return Response
                    .status(204,"User was not found!")
                    .build();
        }
        else if(course == null){
            return Response
                    .status(204,"Course was not found!")
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
        if(userRepository.find("email",user.email).count() == 1){
            return Response
                    .status(406,"User email already exists!")
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
            return Response
                    .status(410,"User was deleted!")
                    .build();
        }
        return Response.noContent().build();
    }
    @DELETE
    @Path("{id}/courses/{cid}")
    public Response removeCourse(@PathParam("id")Long uid
            ,@PathParam("cid")Long cid){
        User change = userRepository.findById(uid);
        if(change != null && change.courses.contains(courseRepository.findById(cid)) == true){
            change.courses.remove(courseRepository.findById(cid));
            userRepository.getEntityManager().merge(change);
            return Response
                    .status(202,"Course was removed from the user!")
                    .build();
        }
        return Response.noContent().build();
    }
    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id")Long uid,User user){
        User updated = userRepository.findById(uid);
        if (updated != null &&
                userRepository.find("email",user.email).count() == 0){
            updated.copyProperties(user);
            userRepository.getEntityManager().merge(updated);
            return Response
                    .status(202,"User was updated")
                    .build();
        }
        return Response.noContent().build();
    }
}
