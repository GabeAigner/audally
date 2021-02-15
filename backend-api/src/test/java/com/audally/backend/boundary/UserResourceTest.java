package com.audally.backend.boundary;


import com.audally.backend.control.CourseRepository;
import com.audally.backend.control.LessonRepository;
import com.audally.backend.control.ProgressRepository;
import com.audally.backend.control.UserRepository;
import com.audally.backend.entity.Course;
import com.audally.backend.entity.Lesson;
import com.audally.backend.entity.Progress;
import com.audally.backend.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.time.LocalTime;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

@QuarkusTest
public class UserResourceTest {

    @InjectMock
    UserRepository userRepository;
    @InjectMock
    CourseRepository courseRepository;
    @InjectMock
    LessonRepository lessonRepository;
    @InjectMock
    SecurityIdentity identity;
    @InjectMock
    ProgressRepository progressRepository;

    @BeforeAll
    public static void setup() {
    //region Setup Classes
        PanacheMock.mock(
                Course.class,
                User.class,
                Lesson.class,
                Progress.class
        );
        /*User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);

        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        Course course2 = new Course();
        course2.setId(11L);
        course2.setDescription("This is the second mock description");
        course2.setName("Second mock course!");

        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Lesson lesson2 = new Lesson();
        lesson2.setId(11L);
        lesson2.setDescription("Second mock lesson description");
        lesson2.setName("Second mock Lesson Name");
        lesson2.setDuration(LocalTime.of(0,20,20));
        Lesson lesson3 = new Lesson();
        lesson3.setId(12L);
        lesson3.setDescription("Third mock lesson description");
        lesson3.setName("Third mock Lesson Name");
        lesson3.setDuration(LocalTime.of(0,23,6));
        course1.getLessons().add(lesson1);
        course2.getLessons().add(lesson2);
        course2.getLessons().add(lesson3);

        Progress progress1 = new Progress();
        progress1.setLesson(lesson1);
        progress1.setId(10L);
        progress1.setProgressInSeconds(14*60+30);
        progress1.setAlreadyListened(false);
        Progress progress2 = new Progress();
        progress2.setLesson(lesson2);
        progress2.setId(11L);
        progress2.setProgressInSeconds(19*60+55);
        progress2.setAlreadyListened(true);

        user.getCourses().add(course1);
        user.getCourses().add(course2);
        user.getProgresses().add(progress1);
        user.getProgresses().add(progress2);*/

    //endregion
    }
    @Test
    void whenGetUserById_thenUserShouldBeFound() {
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);

        Mockito.when(userRepository.findById(10L)).thenReturn(user);

        given().contentType(ContentType.JSON).pathParam("UserId","10")
                .when().get("/users/{UserId}")
                .then().statusCode(200)
                //.body("size()", is(1))
                .log().body()
                .body("email", is("mock@email.com"))
                .body("userName", is("mock"));
    }
    @Test
    void whenGetUserById_thenUserShouldNotBeFound() {
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);

        Mockito.when(userRepository.findById(10L)).thenReturn(user);

        given().contentType(ContentType.JSON).pathParam("UserId","200")
                .when().get("/users/{UserId}")
                .then().statusCode(204)
                .log().body();
    }
    @Test
    void whenGetUserByEmail_thenUserShouldBeFound() {
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.page(Mockito.any())).thenReturn(query);
        Mockito.when(query.firstResult()).thenReturn(user);
        Mockito.when(userRepository.find("email", "mock@email.com")).thenReturn(query);

        given().contentType(ContentType.JSON).pathParam("email","mock@email.com")
                .when().get("/users/email/{email}")
                .then().statusCode(200)
                .log().body()
                .body("id", is(10))
                .body("username", is("mock"));
    }
    @Test
    void whenGetUserByEmail_thenUserShouldNotBeFound() {

        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.page(Mockito.any())).thenReturn(query);
        Mockito.when(query.firstResult()).thenReturn(null);
        Mockito.when(userRepository.find("email", "notanactualemail@email.covid"))
                .thenReturn(query);

        given().contentType(ContentType.JSON).pathParam("email","notanactualemail@email.covid")
                .when().get("/users/email/{email}")
                .then().statusCode(204)
                .log().body();
    }
    @Test
    void whenGetCoursesByUser_thenCoursesOfUserShouldBeFound() {
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        user.getCourses().add(course1);

        Mockito.when(userRepository.findById(10L)).thenReturn(user);

        given().contentType(ContentType.JSON).pathParam("UserId","10")
                .when().get("/users/{UserId}/courses")
                .then().statusCode(200)
                .log().body()
                .body("name", hasItem("First mock course!"))
                .body("lessons", hasItem(course1.getLessons()));
    }
    @Test
    void whenGetCoursesByUser_thenTwoCoursesOfUserShouldBeFound() {
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        Course course2 = new Course();
        course2.setId(11L);
        course2.setDescription("This is the second mock description");
        course2.setName("Second mock course!");
        user.getCourses().add(course1);
        user.getCourses().add(course2);
        Mockito.when(userRepository.findById(10L)).thenReturn(user);

        given().contentType(ContentType.JSON).pathParam("UserId","10")
                .when().get("/users/{UserId}/courses")
                .then().statusCode(200)
                .log().body()
                .body("name", hasItem("First mock course!"))
                .body("lessons", hasItems(course1.getLessons(),course2.getLessons()));
    }
    @Test
    void whenGetCoursesByUser_thenUserShouldNotBeFound() {
        Mockito.when(userRepository.findById(10L)).thenReturn(null);

        given().contentType(ContentType.JSON).pathParam("UserId","10")
                .when().get("/users/{UserId}/courses")
                .then().statusCode(204);
    }
    @Test
    void whenGetProgressOfUser_thenProgressOfUserShouldBeFound() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Progress progress1 = new Progress();
        progress1.setLesson(lesson1);
        progress1.setId(10L);
        progress1.setProgressInSeconds(14*60+30);
        progress1.setAlreadyListened(false);
        user.getProgresses().add(progress1);
        //endregion
        Mockito.when(userRepository.findById(10L)).thenReturn(user);

        given().contentType(ContentType.JSON).pathParam("UserId","10")
                .when().get("/users/{UserId}/progresses")
                .then().statusCode(200)
                .log().body()
                .body("progressInSeconds",hasItem(14*60+30))
                .body("lesson.name",hasItems("First mock Lesson Name"));
    }
    @Test
    void whenGetProgressOfUser_thenUserShouldNotBeFound() {
        Mockito.when(userRepository.findById(10L)).thenReturn(null);

        given().contentType(ContentType.JSON).pathParam("UserId","10")
                .when().get("/users/{UserId}/progresses")
                .then().statusCode(204);
    }
    @Test
    void whenGetProgressByCourseOfUser_thenProgressByCourseOfUserShouldBeFound() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson1);
        user.getCourses().add(course1);
        Progress progress1 = new Progress();
        progress1.setLesson(lesson1);
        progress1.setId(10L);
        progress1.setProgressInSeconds(14*60+30);
        progress1.setAlreadyListened(false);
        user.getProgresses().add(progress1);
        //endregion
        Mockito.when(userRepository.findById(10L)).thenReturn(user);
        Mockito.when(courseRepository.findById(10L)).thenReturn(course1);
        Mockito.when(progressRepository.getProgressesOfCourse(course1.getId(),user)).thenReturn(user.getProgresses());
        given().contentType(ContentType.JSON).pathParam("UserId","10").pathParam("CourseId","10")
                .when().get("users/{UserId}/courses/{CourseId}/progresses")
                .then().statusCode(200)
                .log().body()
                .body("progressInSeconds",hasItem(14*60+30))
                .body("lesson.name",hasItems("First mock Lesson Name"));
    }
    @Test
    void whenGetProgressByCourseOfUser_thenUserShouldNotBeFound() {
        //region Init
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson1);
        Progress progress1 = new Progress();
        progress1.setLesson(lesson1);
        progress1.setId(10L);
        progress1.setProgressInSeconds(14*60+30);
        progress1.setAlreadyListened(false);
        //endregion
        Mockito.when(userRepository.findById(10L)).thenReturn(null);
        Mockito.when(courseRepository.findById(10L)).thenReturn(course1);
        given().contentType(ContentType.JSON).pathParam("UserId","10")
                .pathParam("CourseId","10")
                .when().get("users/{UserId}/courses/{CourseId}/progresses")
                .then().statusCode(204);
    }
    @Test
    void whenGetProgressByCourseOfUser_thenProgressByCourseOfUserShouldNotBeFound() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson1);
        user.getCourses().add(course1);
        Progress progress1 = new Progress();
        progress1.setLesson(lesson1);
        progress1.setId(10L);
        progress1.setProgressInSeconds(14*60+30);
        progress1.setAlreadyListened(false);
        user.getProgresses().add(progress1);
        //endregion
        Mockito.when(userRepository.findById(10L)).thenReturn(user);
        Mockito.when(courseRepository.findById(10L)).thenReturn(course1);
        given().contentType(ContentType.JSON).pathParam("UserId","10")
                .pathParam("CourseId","11")
                .when().get("users/{UserId}/courses/{CourseId}/progresses")
                .then().statusCode(200);
    }
    @Test
    void whenGetProgressByLessonOfUser_thenProgressByLessonOfUserShouldBeFound() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson1);
        user.getCourses().add(course1);
        Progress progress1 = new Progress();
        progress1.setLesson(lesson1);
        progress1.setId(10L);
        progress1.setProgressInSeconds(14*60+30);
        progress1.setAlreadyListened(false);
        user.getProgresses().add(progress1);
        //endregion
        Mockito.when(userRepository.findById(10L)).thenReturn(user);
        given().contentType(ContentType.JSON).pathParam("UserId","10").pathParam("LessonId","10")
                .when().get("users/{UserId}/lessons/{LessonId}/progresses")
                .then().statusCode(200)
                .log().body()
                .body("progressInSeconds",is(progress1.getProgressInSeconds()))
                .body("lesson.name",is("First mock Lesson Name"));
    }
    @Test
    void whenGetProgressForUser_thenUserShouldNotExist() {
        Mockito.when(userRepository.findById(10L)).thenReturn(null);
        given().contentType(ContentType.JSON)
                .pathParam("UserId","10").pathParam("LessonId","10")
                .when().get("users/{UserId}/lessons/{LessonId}/progresses")
                .then().statusCode(204);
    }
    @Test
    void whenGetProgressOfLessonsForUser_thenProgressOfLessonsUserShouldNotExist() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson1);
        user.getCourses().add(course1);
        Progress progress1 = new Progress();
        progress1.setProgressInSeconds(14*60+30);
        progress1.setAlreadyListened(false);
        progress1.setLesson(lesson1);
        user.getProgresses().add(progress1);
        //endregion
        Mockito.when(userRepository.findById(10L)).thenReturn(user);

        given().contentType(ContentType.JSON)
                .pathParam("UserId","10").pathParam("LessonId","11")
                .when().get("users/{UserId}/lessons/{LessonId}/progresses")
                .then().statusCode(204);
    }
    @Test
    void whenPostProgressForUser_thenProgressOfUserShouldExist() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson1);
        user.getCourses().add(course1);
        Progress progress1 = new Progress();
        progress1.setLesson(lesson1);
        progress1.setId(10L);
        progress1.setProgressInSeconds(14*60+30);
        progress1.setAlreadyListened(false);
        user.getProgresses().add(progress1);
        //endregion
        Mockito.when(userRepository.findById(10L)).thenReturn(user);

        given().contentType(ContentType.JSON).pathParam("UserId","10").pathParam("LessonId","10")
                .when().post("users/{UserId}/lessons/{LessonId}/progresses")
                .then().statusCode(202)
                .log().body();
    }
    @Test
    void whenPostProgressForUser_thenProgressOfUserShouldBePosted() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson1);
        user.getCourses().add(course1);
        Progress progress1 = new Progress();
        progress1.setProgressInSeconds(14*60+30);
        progress1.setAlreadyListened(false);
        //endregion
        Mockito.when(userRepository.findById(10L)).thenReturn(user);
        Mockito.doNothing()
                .when(progressRepository).createProgress(Mockito.any(),Mockito.any(),Mockito.any());
        progressRepository.createProgress(Mockito.any(),Mockito.any(),Mockito.any());
        Mockito.verify(progressRepository,Mockito.times(1))
                .createProgress(Mockito.any(),Mockito.any(),Mockito.any());

        given().contentType(ContentType.JSON)
                .pathParam("UserId","10").pathParam("LessonId","10")
                .when().body(progress1).post("users/{UserId}/lessons/{LessonId}/progresses")
                .then().statusCode(200);
    }

    @Test
    void whenPostCourseForUser_thenCourseOfUserShouldBeFound() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson1);
        //endregion
        Mockito.when(userRepository.findById(10L)).thenReturn(user);
        Mockito.when(courseRepository.findById(10L)).thenReturn(course1);

        given().contentType(ContentType.JSON)
                .pathParam("UserId","10").pathParam("CourseId","10")
                .when().body(course1).post("users/{UserId}/courses/{CourseId}")
                .then().statusCode(200)
                .log().body()
                .body("courses.description",hasItem("This is the first mock description"))
                .body("courses.lessons",hasSize(1));
    }
    @Test
    void whenPostCourseForUser_thenCourseOfUserShouldNotBeFound() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson1);
        //endregion
        Mockito.when(userRepository.findById(10L)).thenReturn(user);
        Mockito.when(courseRepository.findById(10L)).thenReturn(null);

        given().contentType(ContentType.JSON)
                .pathParam("UserId","10").pathParam("CourseId","10")
                .when().body(course1).post("users/{UserId}/courses/{CourseId}")
                .then().statusCode(204);
    }
    @Test
    void whenPostCourseForUser_thenUserShouldNotBeFound() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson1);
        //endregion
        Mockito.when(userRepository.findById(10L)).thenReturn(null);
        Mockito.when(courseRepository.findById(10L)).thenReturn(course1);

        given().contentType(ContentType.JSON)
                .pathParam("UserId","10").pathParam("CourseId","10")
                .when().body(course1).post("users/{UserId}/courses/{CourseId}")
                .then().statusCode(204);
    }
    @Test
    void whenPostCourseForUser_thenCourseOfUserShouldAlreadyExist() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson1);
        user.getCourses().add(course1);
        //endregion
        Mockito.when(userRepository.findById(10L)).thenReturn(user);
        Mockito.when(courseRepository.findById(10L)).thenReturn(course1);

        given().contentType(ContentType.JSON)
                .pathParam("UserId","10").pathParam("CourseId","10")
                .when().body(course1).post("users/{UserId}/courses/{CourseId}")
                .then().statusCode(406);
    }
    @Test
    void whenPostUser_thenUserShouldBeFound() {
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");

        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.page(Mockito.any())).thenReturn(query);
        Mockito.when(query.firstResult()).thenReturn(null);
        Mockito.when(userRepository.find("email", user.getEmail()))
                .thenReturn(query);


        given().contentType(ContentType.JSON)
                .when().body(user).post("users/addUser")
                .then().statusCode(200)
                .log().body()
                .body("email",is("mock@email.com"))
                .body("userName",is("mock"));
    }
    @Test
    void whenPostUser_thenUserShouldAlreadyExist() {
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");

        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.page(Mockito.any())).thenReturn(query);
        Mockito.when(query.count()).thenReturn(1L);
        Mockito.when(userRepository.find("email", user.getEmail()))
                .thenReturn(query);


        given().contentType(ContentType.JSON)
                .when().body(user).post("users/addUser")
                .then().statusCode(406)
                .log().body();
    }
    @Test
    void whenDeleteUser_thenUserShouldBeDeleted() {
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");

        Mockito.when(userRepository.findById(10L)).thenReturn(user);

        given().contentType(ContentType.JSON)
                .pathParam("id","10")
                .when().delete("users/{id}")
                .then().statusCode(410)
                .log().body();
    }
    @Test
    void whenDeleteUser_thenUserShouldNotBeFound() {
       Mockito.when(userRepository.findById(10L)).thenReturn(null);

        given().contentType(ContentType.JSON)
                .pathParam("id","10")
                .when().delete("users/{id}")
                .then().statusCode(204)
                .log().body();
    }
    @Test
    void whenRemoveCourseForUser_thenCourseOfUserShouldBeRemoved() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson1);
        Course course2 = new Course();
        course2.setId(11L);
        course2.setDescription("This is the first mock description");
        course2.setName("First mock course!");
        user.getCourses().add(course1);
        user.getCourses().add(course2);
        Progress progress1 = new Progress();
        progress1.setProgressInSeconds(14*60+30);
        progress1.setAlreadyListened(false);
        //endregion
        Mockito.when(userRepository.findById(10L)).thenReturn(user);
        Mockito.when(courseRepository.findById(10L)).thenReturn(course1);

        given().contentType(ContentType.JSON)
                .pathParam("id","10").pathParam("cid","10")
                .when().body(progress1).delete("users/{id}/courses/{cid}")
                .then().statusCode(202);
    }
    @Test
    void whenRemoveCourseForUser_thenCourseOfUserShouldNotDelete() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson1);
        Course course2 = new Course();
        course2.setId(11L);
        course2.setDescription("This is the first mock description");
        course2.setName("First mock course!");
        user.getCourses().add(course1);
        user.getCourses().add(course2);
        Progress progress1 = new Progress();
        progress1.setProgressInSeconds(14*60+30);
        progress1.setAlreadyListened(false);
        //endregion
        Mockito.when(userRepository.findById(10L)).thenReturn(user);
        Mockito.when(courseRepository.findById(10L)).thenReturn(null);

        given().contentType(ContentType.JSON)
                .pathParam("id","10").pathParam("cid","10")
                .when().body(progress1).delete("users/{id}/courses/{cid}")
                .then().statusCode(204);
    }
    @Test
    void whenUpdateUser_thenUserShouldBeUpdated() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        User user1 = new User();
        user.setEmail("mockUpdate@email.com");
        user.setUserName("mockUpdate");

        Mockito.when(userRepository.findById(10L)).thenReturn(user);

        given().contentType(ContentType.JSON)
                .pathParam("id","10")
                .when().body(user1).put("users/{id}")
                .then().statusCode(202);
    }
    @Test
    void whenUpdateUser_thenUserShouldNotUpdated() {
        //region Init
        User user = new User();
        user.setEmail("mock@email.com");
        user.setUserName("mock");
        user.setId(10L);
        User user1 = new User();
        user.setEmail("mockUpdate@email.com");
        user.setUserName("mockUpdate");

        Mockito.when(userRepository.findById(10L)).thenReturn(null);

        given().contentType(ContentType.JSON)
                .pathParam("id","10")
                .when().body(user1).put("users/{id}")
                .then().statusCode(204);
    }
    @Test
    @TestSecurity(authorizationEnabled = false,user = "testbert",roles = {"admin"})
    void whenGetUserInfo_thenAdminInfoShouldBeReturned() {
        //region Init
        given().when().get("users/secret")
                .then().statusCode(200);
    }
    @Test
    @TestSecurity(user = "testbert")
    void whenGetUserInfo_thenAdminInfoShouldNotBeReturned() {
        //region Init
        given().when().get("users/secret")
                .then().statusCode(403);
    }
    @Test
    @TestSecurity(authorizationEnabled = false,user = "testbert",roles = {"user"})
    void whenGetUserInfo_thenUserInfoShouldBeReturned() {
        //region Init
        given().when().get("users")
                .then().statusCode(200);
    }
    @Test
    @TestSecurity(user = "testbert")
    void whenGetUserInfo_thenUserInfoShouldNotBeReturned() {
        //region Init
        given().when().get("users")
                .then().statusCode(403);
    }
}