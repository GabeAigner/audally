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
import javax.json.bind.annotation.JsonbTransient;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    @JsonbTransient
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
        var user = userRepository.findById(uid);
        doLoader(user);
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
        businessuser.merge("id",user.getId(),(o, o2) -> o = o2);
        businessuser.merge("username",user.getUserName(),(o, o2) -> o = o2);
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
        /*
        businessuser = new JSONObject();
        businessuser.merge("courses",user.getCourses().stream().filter(distinctByKey(course -> course.getName()))
                .collect(Collectors.toList()),(o, o2) -> o = o2);*/
        doLoader(user);
        return Response.ok(user.getCourses()).build();
    }
    @POST
    @Path("{UserId}/courses/{CourseId}")
    public Response addCourseToUser(@PathParam("UserId") Long uid
            ,@PathParam("CourseId") Long cid){
        User user = userRepository.findById(uid);
        Course course = courseRepository.findById(cid);
        course.getLessons();
        if(user.getCourses().contains(course)){
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
        user.getCourses().add(course);
        userRepository.persist(user);
        user.getSubscriptions();
        return Response.ok(user).build();
    }
    @POST
    @Transactional
    @Path("addUser")
    public Response addUser(User user){
        User entry = new User();
        if(userRepository.find("email",user.getEmail()).count() == 1){
            return Response
                    .status(406,"User email already exists!")
                    .build();
        }
        /*List<Course> normalCourses = user.courses.stream()
                .filter(course -> course.id != null)
                .filter(course -> courseRepository.findById(course.id).equals(course))
                .collect(Collectors.toList());
        List<Course> tobeAddedCourses =  user.courses.stream()
                .filter(course -> course.id == null)
                .collect(Collectors.toList());
        user.courses = null;

        normalCourses.stream().distinct().forEach(course -> {
            user.courses.add(course);
        });
        tobeAddedCourses.stream().distinct().forEach(course -> {
            Course newCourse = new Course();
            newCourse.copyProperties(course);
            courseRepository.persist(newCourse);
            user.courses.add(courseRepository.findById(newCourse.id));
        });*/
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
        if(change != null && change.getCourses().contains(courseRepository.findById(cid)) == true){
            change.getCourses().remove(courseRepository.findById(cid));
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
                userRepository.find("email",user.getEmail()).count() == 0){
            updated.copyProperties(user);
            userRepository.getEntityManager().merge(updated);
            return Response
                    .status(202,"User was updated")
                    .build();
        }
        return Response.noContent().build();
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    public void doLoader(User user){
        List<Course> load = user.getCourses();
        load.forEach(course -> course.getLessons());
        user.getSubscriptions();
    }
}
