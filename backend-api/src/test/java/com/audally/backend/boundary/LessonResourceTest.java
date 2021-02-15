package com.audally.backend.boundary;

import com.audally.backend.control.CourseRepository;
import com.audally.backend.control.LessonRepository;
import com.audally.backend.entity.Course;
import com.audally.backend.entity.Lesson;
import com.audally.backend.entity.Progress;
import com.audally.backend.entity.User;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class LessonResourceTest {
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
    void whenGetLessonsByCourse_thenLessonsOfCourseShouldBeFound() {
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
        //endregion
        Mockito.when(courseRepository.findById(10L)).thenReturn(course1);
        Mockito.when(lessonRepository.findById(10L)).thenReturn(lesson1);

        given().contentType(ContentType.JSON)
                .pathParam("id","10")
                .when().get("lessons/course/{id}")
                .then().statusCode(200)
                .log().body()
                .body("description",hasItem("First mock lesson description"))
                .body("duration",hasItem("00:14:59"));
    }
    @Test
    void whenGetLessonsByCourse_thenLessonsOfCourseShouldNotBeFound() {
        Mockito.when(courseRepository.findById(10L)).thenReturn(null);
        given().contentType(ContentType.JSON)
                .pathParam("id","10")
                .when().get("lessons/course/{id}")
                .then().statusCode(204);
    }

    @Test
    void whenAddLessonsToCourse_thenLessonsShouldBeAddedToCourse() {
        //region Init
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        Lesson lesson2 = new Lesson();
        lesson2.setId(2L);
        lesson2.setDescription("Second mock lesson description");
        lesson2.setName("Second mock Lesson Name");
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        //endregion
        Mockito.when(courseRepository.findById(10L)).thenReturn(course1);
        Lesson[] postLessonList = new Lesson[]{lesson1,lesson2};

        given().contentType(ContentType.JSON)
                .pathParam("cid","10")
                .when().body(postLessonList).post("lessons/course/{cid}")
                .then().statusCode(200)
                .log().body()
                .body("lessons",hasSize(2));
    }
    @Test
    void whenAddLessonsToCourse_thenLessonsShouldNotBeAddedToCourse() {
        //region Init
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        Lesson lesson2 = new Lesson();
        lesson2.setId(2L);
        lesson2.setDescription("Second mock lesson description");
        lesson2.setName("Second mock Lesson Name");
        Lesson[] postLessonList = new Lesson[]{lesson1,lesson2};
        //endregion
        Mockito.when(courseRepository.findById(10L)).thenReturn(null);

        given().contentType(ContentType.JSON)
                .pathParam("cid","10")
                .when().body(postLessonList).post("lessons/course/{cid}")
                .then().statusCode(204);
    }
    @Test
    void whenRemoveLessonFromCourse_thenLessonOfCourseShouldDelete() {
        //region Init
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        Lesson lesson2 = new Lesson();
        lesson2.setId(11L);
        lesson2.setDescription("Second mock lesson description");
        lesson2.setName("Second mock Lesson Name");
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson1);
        course1.getLessons().add(lesson2);
        //endregion
        Mockito.when(courseRepository.findById(10L)).thenReturn(course1);
        Lesson[] lessons = new Lesson[] {lesson1};
        given().contentType(ContentType.JSON).pathParam("cid","10")
                .when().body(lessons).delete("lessons/course/{cid}")
                .then().statusCode(200)
                .log().body()
                .body("description",hasItem("Second mock lesson description"));
    }
    @Test
    void whenRemoveLessonFromCourse_thenLessonOfCourseNotExist() {
        //region Init
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        Lesson lesson2 = new Lesson();
        lesson2.setId(11L);
        lesson2.setDescription("Second mock lesson description");
        lesson2.setName("Second mock Lesson Name");
        Course course1 = new Course();
        course1.setId(10L);
        course1.setDescription("This is the first mock description");
        course1.setName("First mock course!");
        course1.getLessons().add(lesson2);
        //endregion
        Mockito.when(courseRepository.findById(10L)).thenReturn(course1);
        Lesson[] lessons = new Lesson[] {lesson1};
        given().contentType(ContentType.JSON).pathParam("cid","10")
                .when().body(lessons).delete("lessons/course/{cid}")
                .then().statusCode(204);
    }
    @Test
    void whenRemoveLessonFromCourse_thenLessonOfCourseShouldNotDelete() {
        //region Init
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        //endregion
        Mockito.when(courseRepository.findById(10L)).thenReturn(null);
        Lesson[] lessons = new Lesson[] {lesson1};
        given().contentType(ContentType.JSON).pathParam("cid","10")
                .when().body(lessons).delete("lessons/course/{cid}")
                .then().statusCode(204);
    }
}
