package com.audally.backend.boundary;

import com.audally.backend.control.ProgressRepository;
import com.audally.backend.entity.Course;
import com.audally.backend.entity.Lesson;
import com.audally.backend.entity.Progress;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.time.LocalTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class ProgressResourceTest {

    @InjectMock
    ProgressRepository progressRepository;

    @BeforeAll
    public static void setup() {
        //region Setup Classes
        PanacheMock.mock(
                Progress.class,
                Lesson.class
        );
    }
    @Test
    void whenUpdateProgress_thenProgressShouldBeUpdated() {
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Progress progress1 = new Progress();
        progress1.setId(10L);
        progress1.setLesson(lesson1);
        progress1.setProgressInSeconds(14*60+30);
        progress1.setAlreadyListened(true);
        Mockito.when(progressRepository.findById(10L)).thenReturn(progress1);
        Progress progress2 = new Progress();
        progress2.setProgressInSeconds(14*60-30);
        progress2.setAlreadyListened(false);
        //endregion
        given().contentType(ContentType.JSON)
                .pathParam("id","10")
                .when().body(progress2).put("progresses/{id}")
                .then().statusCode(200)
                .log().body()
                .body("alreadyListened",is(true));
    }
    @Test
    void whenUpdateProgress_thenProgressShouldUpdateAlreadyListened() {
        Lesson lesson1 = new Lesson();
        lesson1.setId(10L);
        lesson1.setDescription("First mock lesson description");
        lesson1.setName("First mock Lesson Name");
        lesson1.setDuration(LocalTime.of(0,14,59));
        Progress progress1 = new Progress();
        progress1.setId(10L);
        progress1.setLesson(lesson1);
        progress1.setProgressInSeconds(14*60-30);
        progress1.setAlreadyListened(false);
        Mockito.when(progressRepository.findById(10L)).thenReturn(progress1);
        Progress progress2 = new Progress();
        progress2.setProgressInSeconds(14*60+30);
        progress2.setAlreadyListened(false);
        //endregion
        given().contentType(ContentType.JSON)
                .pathParam("id","10")
                .when().body(progress2).put("progresses/{id}")
                .then().statusCode(200)
                .log().body()
                .body("alreadyListened",is(true));
    }
    @Test
    void whenUpdateProgress_thenProgressShouldNotBeUpdated() {
        Mockito.when(progressRepository.findById(12L)).thenReturn(null);
        //endregion
        given().contentType(ContentType.JSON)
                .pathParam("id","12")
                .when().put("progresses/{id}")
                .then().statusCode(204)
                .log().body();
    }
}
