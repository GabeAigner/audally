package com.audally.backend.boundary;

import com.audally.backend.control.CourseRepository;
import com.audally.backend.control.LessonRepository;
import com.audally.backend.entity.Course;
import com.audally.backend.entity.Lesson;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

@QuarkusTest
public class CourseResourceTest {
    @InjectMock
    CourseRepository courseRepository;
    @InjectMock
    LessonRepository lessonRepository;

    @BeforeAll
    public static void setup() {
        //region Setup Classes
        PanacheMock.mock(
                Course.class,
                Lesson.class
        );
    }
    @Test
    void whenGetCourses_thenCoursesShouldBeFound() {
        //region Init
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        Course course2 = new Course();
        course2.setId(11L);
        course2.setDescription("This is the second mock description");
        course2.setName("First mock course!");
        List<Course> courses = new ArrayList<Course>();
        courses.add(course1);
        courses.add(course2);
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.page(Mockito.any())).thenReturn(query);
        Mockito.when(query.list()).thenReturn(courses);
        Mockito.when(courseRepository.findAll())
                .thenReturn(query);
        //endregion

        given().contentType(ContentType.JSON)
                .when().get("courses")
                .then().statusCode(200)
                .log().body()
                .body("description",hasItem("This is the first mock description"))
                .body("description",hasItem("This is the second mock description"));
    }
    @POST
    public Response addCourse(Course course){
        if(courseRepository.findAll().stream().anyMatch(course1 ->
                course1.getName().equals(course.getName()) &&
                        course1.getDescription().equals(course.getDescription()) &&
                        course1.getLessons().equals(course.getLessons()))){
            return Response
                    .status(406,"Course already exists!")
                    .build();
        }
        Course entry = new Course();
        entry.copyProperties(course);
        course.getLessons();
        courseRepository.persist(entry);
        return Response.ok(entry).build();
    }
    @Test
    void whenAddCourse_thenCourseShouldBeCreated() {
        //region Init
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        Course course2 = new Course();
        course2.setId(11L);
        course2.setDescription("This is the second mock description");
        course2.setName("First mock course!");
        List<Course> courses = new ArrayList<Course>();
        courses.add(course2);
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.page(Mockito.any())).thenReturn(query);
        Mockito.when(query.list()).thenReturn(courses);
        Mockito.when(courseRepository.findAll())
                .thenReturn(query);
        //endregion
        given().contentType(ContentType.JSON)
                .when().body(course1).post("courses")
                .then().statusCode(200)
                .log().body()
                .body("description",is(course1.getDescription()));
    }
    @Test
    void whenAddCourse_thenCourseShouldNotBeCreated() {
        //region Init
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        Course course2 = new Course();
        course2.setId(11L);
        course2.setDescription("This is the second mock description");
        course2.setName("First mock course!");
        List<Course> courses = new ArrayList<Course>();
        courses.add(course2);
        courses.add(course1);
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(query.page(Mockito.any())).thenReturn(query);
        Mockito.when(courseRepository.findAll())
                .thenReturn(query);
        Mockito.when(query.list()).thenReturn(courses);
        //endregion
        given().contentType(ContentType.JSON)
                .when().body(course2).post("courses")
                .then().statusCode(406)
                .log().body();
    }
    @Test
    void whenDeleteCourse_thenCourseShouldBeDeleted() {
        //region Init
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        Course course2 = new Course();
        course2.setId(11L);
        course2.setDescription("This is the second mock description");
        course2.setName("First mock course!");
        List<Course> courses = new ArrayList<Course>();
        courses.add(course2);
        courses.add(course1);
        Mockito.when(courseRepository.findById(10L)).thenReturn(course1);
        //endregion
        given().contentType(ContentType.JSON)
                .pathParam("id","10")
                .when().delete("courses/{id}")
                .then().statusCode(200)
                .log().body();
    }
    @Test
    void whenDeleteCourse_thenCourseShouldNotBeDeleted() {
        Mockito.when(courseRepository.findById(12L)).thenReturn(null);
        //endregion
        given().contentType(ContentType.JSON)
                .pathParam("id","12")
                .when().delete("courses/{id}")
                .then().statusCode(204)
                .log().body();
    }
    @Test
    void whenUpdateCourse_thenCourseShouldBeUpdated() {
        //region Init
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        Course course2 = new Course();
        course2.setId(11L);
        course2.setDescription("This is the second mock description");
        course2.setName("Second mock course!");
        List<Course> courses = new ArrayList<Course>();
        courses.add(course2);
        courses.add(course1);
        Mockito.when(courseRepository.findById(10L)).thenReturn(course1);
        //endregion
        given().contentType(ContentType.JSON)
                .pathParam("id","10")
                .when().body(course2).put("courses/{id}")
                .then().statusCode(200)
                .log().body()
                .body("description",is("This is the second mock description"))
                .body("id",is(10));
    }
    @Test
    void whenUpdateCourse_thenCourseShouldNotBeUpdated() {
        Mockito.when(courseRepository.findById(12L)).thenReturn(null);
        //endregion
        given().contentType(ContentType.JSON)
                .pathParam("id","12")
                .when().put("courses/{id}")
                .then().statusCode(204)
                .log().body();
    }
}
