package com.audally.backend.boundary;


import com.audally.backend.control.ProgressRepository;
import com.audally.backend.entity.Lesson;
import com.audally.backend.entity.Progress;
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
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
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
    ProgressRepository progressRepository;
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
        if(user == null){
            return Response
                    .status(204,"User could not be found or doesn't exist!")
                    .build();
        }
        doLoader(user);
        return Response.ok(user).build();
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
                    .status(204,"User does not exist!")
                    .build();
        }
        /*
        businessuser = new JSONObject();
        businessuser.merge("courses",user.getCourses().stream().filter(distinctByKey(course -> course.getName()))
                .collect(Collectors.toList()),(o, o2) -> o = o2);*/
        doLoader(user);
        return Response.ok(user.getCourses()).build();
    }
    @GET
    @Path("/{UserId}/progresses")
    public Response getProgressesOfUser(@PathParam("UserId") Long uid){
        User user = userRepository.findById(uid);
        if(user == null){
            return Response
                    .status(204,"User does not exist")
                    .build();
        }
        doLoader(user);
        return Response.ok(user.getProgresses()).build();
    }
    @GET
    @Path("/{UserId}/courses/{CourseId}/progresses")
    public Response getProgressesOfCourse(@PathParam("UserId") Long uid,
                                          @PathParam("CourseId") Long cid){
        User user = userRepository.findById(uid);
        if(user == null){
            return Response
                    .status(204,"User does not exist")
                    .build();
        }
        doLoader(user);
        return Response.ok(user.getProgresses()
                .stream().filter(progress -> {
                    AtomicBoolean check = new AtomicBoolean(false);
                    user.getCourses().stream()
                            .filter(course -> course.getId().equals(cid))
                            .map(course -> course.getLessons())
                            .forEach(lessonList -> {
                                Optional<Lesson> l = lessonList.stream()
                                    .filter(lesson -> lesson.getId().equals(progress.getId()))
                                    .findFirst();
                                if(l.isPresent()){
                                    check.set(true);
                                }
                            });
                    return check.get();
                }).collect(Collectors.toList()))
        .build();
    }
    @GET
    @Path("/{UserId}/lessons/{LessonId}/progresses")
    public Response getLessonProgressOfUser(@PathParam("UserId") Long uid,@PathParam("LessonId") Long lid){
        User user = userRepository.findById(uid);
        if(user == null){
            return Response
                    .status(204,"User was not found!")
                    .build();
        }
        doLoader(user);
        if(!user.getProgresses().stream()
                .anyMatch(progress -> progress.getLesson().getId().equals(lid))){
            return Response
                    .status(204,"Progress was not found for this lesson!")
                    .build();
        }
        Optional<Progress> p = user.getProgresses().stream()
                .filter(progress -> progress.getLesson().getId().equals(lid))
                .findFirst();
        if(p.isPresent()){
            return Response.ok(p.get()).build();
        }
        return Response.status(204,"Something went wrong with fetching the Progress").build();
    }
    @POST
    @Path("{UserId}/lessons/{LessonId}/progresses")
    public Response addProgressToUser(@PathParam("UserId") Long uid
            ,@PathParam("LessonId") Long lid,Progress progress){
        User user = userRepository.findById(uid);
        if(user == null){
            return Response
                    .status(204,"User was not found!")
                    .build();
        }
        doLoader(user);
        if(user.getProgresses()
        .stream().anyMatch(progress1 -> progress1.getLesson().getId().equals(lid))) {
            return Response
                    .status(202, "Progress already exists! Use update method!")
                    .build();
        }
        user.getCourses().stream()
                .forEach(course -> {
                    Optional<Lesson> l = course.getLessons().stream()
                            .filter(lesson -> lesson.getId().equals(lid))
                            .findFirst();
                    if(l.isPresent()){
                        Progress insertProgress = new Progress();
                        String[] parts = l.get().getDuration().toString().split(":");
                        Duration duration = Duration
                                .ofHours(Long.parseLong(parts[0]))
                                .plusMinutes(Long.parseLong(parts[1]))
                                .plusSeconds(Long.parseLong(parts[2]));
                        if(!progress.isAlreadyListened() &&
                                progress.getProgressInSeconds() > (duration.getSeconds() - 30)){
                            progress.setAlreadyListened(true);
                        }
                        insertProgress.copyProperties(progress);
                        insertProgress.setLesson(l.get());
                        progressRepository.persist(insertProgress);
                        user.getProgresses().add(insertProgress);
                    }
                });

        userRepository.persist(user);
        return Response.ok(user.getProgresses()).build();
    }
    @POST
    @Path("{UserId}/courses/{CourseId}")
    public Response addCourseToUser(@PathParam("UserId") Long uid
            ,@PathParam("CourseId") Long cid){
        User user = userRepository.findById(uid);
        Course course = courseRepository.findById(cid);
        if(course == null){
            return Response
                    .status(204,"Course was not found!")
                    .build();
        }
        if(user == null){
            return Response
                    .status(204,"User was not found!")
                    .build();
        }
        else if(user.getCourses().contains(course)){
            return Response
                    .status(406,"Course already exists in the User!")
                    .build();
        }
        course.getLessons();
        doLoader(user);
        user.getCourses().add(course);
        userRepository.persist(user);
        return Response.ok(user).build();
    }
    @POST
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
            userRepository.persist(change);
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
        if (updated != null){
            updated.copyProperties(user);
            userRepository.persist(updated);
            return Response
                    .status(202,"User was updated")
                    .build();
        }
        return Response.noContent().build();
    }

    /*public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }*/
    public void doLoader(User user){
        List<Course> load = user.getCourses();
        load.forEach(course -> course.getLessons());
        user.getSubscriptions();
        user.getProgresses();
    }
}
